name := "tlfun"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.0",
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test"
)

initialCommands += """
  import tlfun._
"""
