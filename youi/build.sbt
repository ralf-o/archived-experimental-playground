enablePlugins(ScalaJSPlugin)

name := "todomvc"
scalaJSStage in Global := FullOptStage

scalaVersion := "2.11.6"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
