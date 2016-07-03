name := "tlfun"

scalaVersion := "2.11.8"
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard"
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.1",
  "org.scalacheck" %% "scalacheck" % "1.13.1" % "test"
)

initialCommands += """
  import tlfun._
"""

reformatOnCompileSettings
