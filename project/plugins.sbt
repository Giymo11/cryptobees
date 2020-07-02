val scalajsVersion = "0.6.33"
val isV1 = scalajsVersion.startsWith("1.")

val `sbt-web-scalajs_Version` = if (isV1) "1.1.0" else "1.1.0-0.6"
val `sbt-scalajs-bundler_Artifact` = if(isV1) "sbt-scalajs-bundler" else "sbt-scalajs-bundler-sjs06"
val `sbt-web-scalajs-bundler_Artifact` = if (isV1) "sbt-web-scalajs-bundler" else "sbt-web-scalajs-bundler-sjs06"



addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalajsVersion)


addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.1")
// addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.18.0")
// addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % `sbt-web-scalajs_Version`)
addSbtPlugin("ch.epfl.scala" % `sbt-web-scalajs-bundler_Artifact` % "0.18.0")


addSbtPlugin("io.spray"                  % "sbt-revolver"              % "0.9.1")
addSbtPlugin("com.eed3si9n"              % "sbt-assembly"              % "0.14.10")
addSbtPlugin("com.typesafe.sbt"          % "sbt-native-packager"       % "1.7.3")