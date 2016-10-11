name := "omnivore"
organization := "me.tongfei"
version := "0.5.0"

isSnapshot := true

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "me.tongfei" %% "poly-io" % "0.2.1"
libraryDependencies += "me.tongfei" % "progressbar" % "0.4.1"
libraryDependencies += "me.tongfei" %% "granite" % "4.10.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.3.7"

assemblyMergeStrategy in assembly := {
  case "log4j2.json" => MergeStrategy.last
  case "english.sutime.txt" => MergeStrategy.last
  case x => MergeStrategy.defaultMergeStrategy(x)
}
