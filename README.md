AMQP Lift Module
==================

[![Build Status](https://travis-ci.org/liftmodules/amqp.svg?branch=master)](https://travis-ci.org/liftmodules/amqp)

This module provides integration with the [Advanced Message Queuing Protocol (AMQP)](http://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol).

Quick start for users
=====================

To include this module in your Lift project change `build.sbt` to include:

    libraryDependencies += "net.liftmodules" %% "amqp_3.0" % "1.4.0"

Previous releases
-----------------

| Lift Version | Scala Version | Module Version |
|--------------|---------------|----------------|
| 3.1          |  2.12, 2.11   | 1.5.0          |
| 3.0          |  2.12, 2.11   | 1.4.0          |
| 2.6          |  2.10, 2.9    | 1.3            |
| 2.5          |  2.10, 2.9    | 1.3            |

Historic snapshot releases
--------------------------

| Lift Version | Scala Version | Module Version |
|--------------|---------------|----------------|
| 2.6          |   2.11        | 1.4-SNAPSHOT   |
| 3.0          |   2.10        | 1.2-SNAPSHOT   |


For snapshots you'll also need:

    resolvers += Resolver.sonatypeRepo("snapshots")


Documentation
=============

**Note:** The module package changed from `net.liftweb.amqp` to `net.liftmodules.amqp` in May 2012.  Please consider this when referencing documentation written before that date.

* [Lift AMQP with RabbitMQ and Scala – Tutorial and Screencast](http://timperrett.com/2009/05/22/lift-amqp-with-rabbitmq-and-scala-tutorial-and-screencast/) from Tim Perrett.

* _Exploring Lift_, chapter 13.2 on [AMQP](http://exploring.liftweb.net/master/index-13.html).

* [Distributing Comet Across Multiple Servers](http://cookbook.liftweb.net/#DistributedComet) from _The Lift Cookbook_ uses this module.


Notes for module developers
===========================

Learn more about modules on the [Lift Wiki page for modules](https://www.assembla.com/spaces/liftweb/wiki/Modules).

Travis hosts the [project build](https://travis-ci.org/liftmodules/amqp/).
SNAPSHOT versions are automatically published to Sonatype when merged to master.

This project compiles for Lift 3 by default.
To build for other versions of Lift, change the value of `liftVersion` in SBT.
For example:

```
> set LiftModule.liftVersion := "2.6.3"
> ++ 2.11.8
> package
```

