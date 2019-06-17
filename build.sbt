name := "scala-akka-api-test"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.8"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.23"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23"

enablePlugins(JavaAppPackaging)

val stage = taskKey[Unit]("Stage task")

val Stage = config("stage")

stage := {
  //(herokuAppName in Compile).value
  (update in Stage).value.allFiles.foreach { f =>
    if (f.getName.matches("webapp-runner-[0-9\\.]+.jar")) {
      println("copying " + f.getName)
      IO.copyFile(f, baseDirectory.value / "target" / "webapp-runner.jar")
    }
  }
}
