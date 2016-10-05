name := "akka-http-example"
organization := "io.github.jlprat"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val akkaVersion = "2.4.11"

libraryDependencies ++= Seq(
  // akka core
  "com.typesafe.akka" %% "akka-actor"        % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"      % akkaVersion,

  // akka http
  "com.typesafe.akka" %% "akka-http-experimental"             % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental"  % akkaVersion,
  "com.typesafe.akka" %% "akka-http-jackson-experimental"     % akkaVersion,

  //Jackson
  "com.fasterxml.jackson.core"     % "jackson-annotations"   % "2.7.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.7.1",

  // testing
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % "test",
  "org.scalatest"     %% "scalatest"         % "3.0.0"     % "test",
  "junit"              % "junit"             % "4.12"      % "test",
  "com.novocode"       % "junit-interface"   % "0.11"      % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")