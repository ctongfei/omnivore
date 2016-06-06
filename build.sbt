name := "omnivore"
organization := "me.tongfei"
version := "0.1.0"

isSnapshot := true

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "me.tongfei" %% "poly-io" % "0.1.1-SNAPSHOT"
libraryDependencies += "me.tongfei" % "progressbar" % "0.4.0"
libraryDependencies += "me.tongfei" %% "granite" % "4.8.1-SNAPSHOT"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"

assemblyMergeStrategy in assembly := {
  case "log4j2.json" => MergeStrategy.last
  case "english.sutime.txt" => MergeStrategy.last
  case x => MergeStrategy.defaultMergeStrategy(x)
}
