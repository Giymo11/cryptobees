// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x

import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "cryptobees"

version := "0.1"

scalaVersion := "2.13.2"

ThisBuild / scalaVersion := "2.13.2"

val http4sVersion = "0.21.5"

val endpoints4sVersion = "1.0.0"
val endpoints4sVersionJs = endpoints4sVersion + "+sjs1"


lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
    scalaVersion := "2.13.1",
    organization := "Wasabi Science",
    name := "cryptobees",

    publish := {},
    publishLocal := {},

    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % "0.9.1",
      "com.lihaoyi" %%% "upickle" % "1.1.0",
    )
  )
  .jsConfigure(_.enablePlugins(ScalaJSBundlerPlugin))
lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js



lazy val server = project
  .dependsOn(sharedJvm)
  .enablePlugins(WebScalaJSBundlerPlugin)
  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    libraryDependencies ++= Seq(
      "com.vmunier" %% "scalajs-scripts" % "1.1.4",

      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "org.http4s" %% "http4s-scalatags" % http4sVersion,

      "org.endpoints4s" %% "algebra" % endpoints4sVersion,
      "org.endpoints4s" %% "json-schema-generic" % endpoints4sVersion,

      "org.endpoints4s" %% "http4s-server" % endpoints4sVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    ),
    WebKeys.packagePrefix in Assets := "public/",
    managedClasspath in Runtime += (packageBin in Assets).value
  )




lazy val client = project
  .dependsOn(sharedJs)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    webpackBundlingMode := BundlingMode.LibraryAndApplication(),
    scalaJSUseMainModuleInitializer := true,

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",

      "org.endpoints4s" %%% "algebra" % endpoints4sVersionJs,
      "org.endpoints4s" %%% "json-schema-generic" % endpoints4sVersionJs,

      "org.endpoints4s" %%% "xhr-client" % endpoints4sVersionJs,
    ),
    npmDependencies in Compile ++= Seq(
      "snabbdom" -> "0.5.3",
      "d3" -> "5.16.0"
    )
  )

