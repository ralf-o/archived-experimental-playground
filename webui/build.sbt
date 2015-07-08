name := "webui"

version := "0.1" 

scalaVersion := "2.11.6"

val vaadinVersion = "7.5.0"

libraryDependencies ++= Seq(
   "com.vaadin" % "vaadin-server" % vaadinVersion,
   "com.vaadin" % "vaadin-client-compiled" % vaadinVersion,
   "com.vaadin" % "vaadin-themes" % vaadinVersion,
   "io.reactivex" %% "rxscala" % "0.24.1"
)

jetty(port = 8081)
//tomcat(port = 8081)
