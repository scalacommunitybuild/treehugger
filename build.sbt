import ls.Plugin.LsKeys._

organization := "com.eed3si9n"

name := "treehugger"

version := "0.1.0"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.9.1", "2.8.1")

homepage := Some(url("http://eed3si9n.com/treehugger"))

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

description := "a library to code Scala programmatically."

initialCommands in console := """import treehugger.forest._
                                |import definitions._
                                |import treehuggerDSL._""".stripMargin

libraryDependencies <++= (scalaVersion) { (sv) => sv match {
  case "2.8.1"   => Seq("org.specs2" %% "specs2" % "1.5" % "test",
                        "org.specs2" %% "specs2-scalaz-core" % "5.1-SNAPSHOT" % "test")
  case "2.9.0-1" => Seq("org.specs2" %% "specs2" % "1.6.1" % "test",
                        "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2" % "test")
  case _ =>         Seq("org.specs2" %% "specs2" % "1.7.1" % "test")
}}

parallelExecution in Test := false

resolvers ++= Seq("scala-tools snapshots" at "http://scala-tools.org/repo-snapshots",
                  "scala-tools releases"  at "http://scala-tools.org/repo-releases")

seq(lsSettings :_*)

pomExtra <<= (homepage) { (h) =>
  (<url>{h.get.toString}</url>
  <scm>
    <url>git@github.com:eed3si9n/treehugger.git</url>
    <connection>scm:git:git@github.com:eed3si9n/treehugger.git</connection>
  </scm>
  <developers>
    <developer>
      <id>eed3si9n</id>
      <name>Eugene Yokota</name>
      <url>http://eed3si9n.com</url>
    </developer>
  </developers>) }

// --- Sonatype settings ---

publishMavenStyle := true

publishArtifact in (Compile, packageBin) := true

publishArtifact in Test := false

publishArtifact in (Test, packageBin) := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) 
    Some("snapshots" at nexus + "content/repositories/snapshots") 
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { x => false }
