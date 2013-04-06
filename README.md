AMQP Lift Module
==================

This module provides integration with the [Advanced Message Queuing Protocol (AMQP)](http://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol).

---

Quick start for users
=====================

Add the dependency to your `built.sbt`. For example, for Lift 2.5:

    "net.liftmodules" %% "amqp_2.5" % "1.2"


Useful links
------------

**Note:** The module package changed from `net.liftweb.amqp` to `net.liftmodules.amqp` in May 2012.  Please consider this when referencing documentation written before that date.

* [Lift AMQP with RabbitMQ and Scala – Tutorial and Screencast](http://timperrett.com/2009/05/22/lift-amqp-with-rabbitmq-and-scala-tutorial-and-screencast/) from Tim Perrett.

* _Exploring Lift_, chapter 13.2 on [AMQP](http://exploring.liftweb.net/master/index-13.html).

* [Distributing Comet Across Multiple Servers](http://cookbook.liftweb.net/#DistributedComet) from _The Lift Cookbook_ uses this module.

---

Notes for module developers
===========================

* The [Jenkins build](https://liftmodules.ci.cloudbees.com/job/amqp/) is triggered on a push to master.

Learn more about modules on the [Lift Wiki page for modules](https://www.assembla.com/spaces/liftweb/wiki/Modules).
