name := "tlfun"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.1",
  "org.scalacheck" %% "scalacheck" % "1.13.1" % "test"
)

initialCommands += """
  import tlfun._
"""

reformatOnCompileSettings
