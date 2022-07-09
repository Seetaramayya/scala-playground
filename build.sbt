name := "scala-playground"

version := "1.0"

scalaVersion := "2.13.8"

val rabbitMqClient = "5.14.2"
val catsCore = "2.7.0"
// https://github.com/aws/aws-sdk-java
val awsSdkVersion = "1.12.239"
val logbackVersion = "1.2.11"
val commonsIo = "2.6"

libraryDependencies ++= Seq(
  "org.bouncycastle" % "bcprov-jdk15on" % "1.70",
  "com.nimbusds" % "nimbus-jose-jwt" % "9.23",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "org.typelevel" %% "cats-core" % catsCore,
  "com.rabbitmq" % "amqp-client" % rabbitMqClient,
  "com.amazonaws" % "aws-java-sdk-core" % awsSdkVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % awsSdkVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "commons-io" % "commons-io" % commonsIo,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "junit" % "junit" % "4.13.2" % Test,
  "org.scalatest" %% "scalatest" % "3.2.12" % Test,
  "org.scalatestplus" %% "scalacheck-1-16" % "3.2.12.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.16.0"
)
