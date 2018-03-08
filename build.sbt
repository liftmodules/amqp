import LiftModule.{liftVersion, liftEdition}

name := "amqp"

organization := "net.liftmodules"

version := "1.6.0-SNAPSHOT"

liftVersion := "3.2.0"

liftEdition := liftVersion.value.substring(0,3)

moduleName := name.value + "_" + liftEdition.value

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.12.4", "2.11.12")

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++=
  "net.liftweb" %% "lift-webkit" % liftVersion.value % "provided" ::
  "net.liftweb" %% "lift-actor"  % liftVersion.value % "provided" ::
  Nil

libraryDependencies += "com.rabbitmq" % "amqp-client" % "4.2.0"

publishTo := (version.value.endsWith("SNAPSHOT") match {
  case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
)

credentials ++= (for {
  username <- Option(System.getenv().get("SONATYPE_USERNAME"))
  password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
} yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/liftmodules/amqp</url>
  <licenses>
    <license>
      <name>Apache 2.0 License</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:liftmodules/amqp.git</url>
    <connection>scm:git:git@github.com:liftmodules/amqp.git</connection>
  </scm>
  <developers>
    <developer>
      <id>liftmodules</id>
      <name>Lift Team</name>
      <url>http://www.liftmodules.net</url>
  </developer>
  </developers>
)
