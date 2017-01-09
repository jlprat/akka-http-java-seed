name := "akka-http-java-seed"
organization := "io.github.jlprat"
version := "1.0.1"
scalaVersion := "2.12.0"

lazy val akkaHttpVersion = "10.0.1"

libraryDependencies ++= Seq(

  // akka http
  "com.typesafe.akka" %% "akka-http"             % akkaHttpVersion,

  // testing
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
  "org.scalatest"     %% "scalatest"         % "3.0.1"     % "test",
  "junit"              % "junit"             % "4.12"      % "test",
  "com.novocode"       % "junit-interface"   % "0.11"      % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


fork in run := true
