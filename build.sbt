name := "scala-playground"

version := "1.0"

scalaVersion := "2.12.2"


val rabbitMqClient = "5.4.1"
val catsCore = "1.0.0"
// https://github.com/aws/aws-sdk-java
val awsSdkVersion = "1.11.483"

libraryDependencies ++= Seq (
  "org.bouncycastle"       %  "bcprov-jdk15on"            % "1.54",
  "com.nimbusds"           %  "nimbus-jose-jwt"           % "5.1",
  "org.scala-lang.modules" %% "scala-parser-combinators"  % "1.0.6",
  "org.typelevel"          %% "cats-core"                 % catsCore,
  "com.rabbitmq"           %  "amqp-client"               % rabbitMqClient,
  "com.amazonaws"          %  "aws-java-sdk-core"         % awsSdkVersion,
  "com.amazonaws"          %  "aws-java-sdk-s3"           % awsSdkVersion,
  "com.novocode"           %  "junit-interface"           % "0.11"          % Test,
  "junit"                  %  "junit"                     % "4.12"          % Test,
  "org.scalatest"          %% "scalatest"                 % "3.0.1"         % Test
)
