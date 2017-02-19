name := "omnivore"
organization := "me.tongfei"
version := "0.11.0"

isSnapshot := true
scalaVersion := "2.11.8"

val artifactory = "http://sparsity.ad.hltcoe.jhu.edu:8081/artifactory"

resolvers += "Artifactory Realm" at "http://sparsity.ad.hltcoe.jhu.edu:8081/artifactory/maven-repo"
resolvers += "Artifactory Realm" at "http://localhost:7727/artifactory/maven-repo"
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "edu.jhu.hlt" %% "granite" % "4.12.3"
libraryDependencies += "me.tongfei" %% "poly-io" % "0.3.2"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
libraryDependencies += "io.circe" %% "circe-core" % "0.6.0"
libraryDependencies += "io.circe" %% "circe-generic" % "0.6.0"
libraryDependencies += "io.circe" %% "circe-parser" % "0.6.0"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.3.7"

assemblyMergeStrategy in assembly := {
  case "log4j2.json" => MergeStrategy.last
  case "english.sutime.txt" => MergeStrategy.last
  case x => MergeStrategy.defaultMergeStrategy(x)
}


publishTo := {
  if (isSnapshot.value)
    Some("snapshots" at artifactory + "/libs-snapshot-local")
  else
    Some("releases"  at artifactory + "/libs-release-local")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
