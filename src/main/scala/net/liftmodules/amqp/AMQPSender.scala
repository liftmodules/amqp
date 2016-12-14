/*
 * Copyright 2007-2016 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.liftmodules
package amqp

import net.liftweb.actor._
import com.rabbitmq.client._
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

/**
 * An actor with a long-lived connection to an AMQP exchange/queue.
 *
 * @see ExampleStringAMQPSender for an example use.
 * @author Steve Jenson (stevej@pobox.com)
 */
abstract class AMQPSender[T](cf: ConnectionFactory, exchange: String, routingKey: String) extends LiftActor {
  val conn = cf.newConnection()
  val channel = conn.createChannel()

  /**
   * Override this to use your own AMQP queue/exchange with the given channel.
   */
  def configure(channel: Channel): AnyRef

  def sendMsg(msg: T) {
    // Now write an object to a byte array and shove it across the wire.
    val bytes = new ByteArrayOutputStream
    val store = new ObjectOutputStream(bytes)
    store.writeObject(msg)
    store.close()
    channel.basicPublish(exchange, routingKey, null, bytes.toByteArray)
  }

  protected def messageHandler = {
    case AMQPMessage(msg: T) => sendMsg(msg)
  }
}

/**
 * An example subclass of AMQPSender[T]
 *
 * An example of how to send messages to an AMQP queue/exchange. Notice that this
 * is setup with the same params as StringAQMPExample. After making a new instance of
 * StringAMQPExample, just send ExampleAMQPSender ! "hi" to see the message "hi"
 * appear in the output log. Fun and Easy!
 *
 * If you are planning to send lots of messages to lots of different exchange/queues,
 * consider creating Actor-based Senders, that will help your application to scale.
 */
class StringAMQPSender(cf: ConnectionFactory, exchange: String, routingKey: String)
  extends AMQPSender[String](cf, exchange, routingKey) {
  override def configure(channel: Channel) = {
    val conn = cf.newConnection()
    val channel = conn.createChannel()
    channel
  }
}

/**
 * An Example of how to use the Example subclass of AMQPSender[T]. Still following?
 */
class ExampleStringAMQPSender {
  val factory = new ConnectionFactory {
    import ConnectionFactory._

    setHost(DEFAULT_HOST)
    setPort(DEFAULT_AMQP_PORT)
    setUsername(DEFAULT_USER)
    setPassword(DEFAULT_PASS)
    setVirtualHost(DEFAULT_VHOST)
  }

  val amqp = new StringAMQPSender(factory, "mult", "routeroute")
  amqp ! AMQPMessage("hi")
}

/**
 * An example of using AMQP in a short-lived manner, setting up and tearing down
 * the connection whenever you need it. The long-lived example above is more
 * efficient with resources.
 */
object ExampleDirectAMQPSender {
  lazy val factory = new ConnectionFactory {
    import ConnectionFactory._

    setHost(DEFAULT_HOST)
    setPort(DEFAULT_AMQP_PORT)
    setUsername(DEFAULT_USER)
    setPassword(DEFAULT_PASS)
    setVirtualHost(DEFAULT_VHOST)
  }

  def sendMsg[T](msg: T) {
    sendMsg(msg, factory)
  }

  def sendMsg[T](msg: T, factory: ConnectionFactory, host: String, port: Int) {
    val conn = factory.newConnection()
    val channel = conn.createChannel()
    // Now write an object to a byte array and shove it across the wire.
    val bytes = new ByteArrayOutputStream
    val store = new ObjectOutputStream(bytes)
    store.writeObject(msg)
    store.close()
    channel.basicPublish("mult", "routeroute", null, bytes.toByteArray)
  }
}

/**
 * Just a few examples of how you might test this. Mostly useful snippets
 * for me to run from script/console to see the bits flying.
 */
object AMQPExampleFunPack {
  def directExample: Unit = {
    val recv = new ExampleStringAMQPListener()
    // You should see the message 'hi'
    val sender = new ExampleStringAMQPSender()
  }
  def actorExample: Unit = {
    val recv = new ExampleStringAMQPListener()
    // You probably know what message you are going to see. 'hello!'
    val sender = ExampleDirectAMQPSender.sendMsg("hello!")
  }
}

