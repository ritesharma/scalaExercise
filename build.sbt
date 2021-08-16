name := "Exercise2"

version := "0.1"
val akkaVersion = "2.6.5"
val akkaHttpVersion = "10.2.0"
scalaVersion := "2.13.4"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.10",
  "com.typesafe.akka" %% "akka-http" % "10.2.2",
 // "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "org.json4s" %% "json4s-jackson" % "3.7.0-M7",
  "org.json4s" %% "json4s-native" % "3.7.0-M7",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.1"
)