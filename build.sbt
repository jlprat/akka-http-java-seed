name := "akka-http-java-seed"
organization := "io.github.jlprat"
version := "1.0.0"
scalaVersion := "2.11.8"

lazy val akkaVersion = "2.4.11"

libraryDependencies ++= Seq(

  // akka http
  "com.typesafe.akka" %% "akka-http-experimental"             % akkaVersion,

  // testing
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % "test",
  "org.scalatest"     %% "scalatest"         % "3.0.0"     % "test",
  "junit"              % "junit"             % "4.12"      % "test",
  "com.novocode"       % "junit-interface"   % "0.11"      % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


fork in run := true