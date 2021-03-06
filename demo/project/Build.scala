import com.oysterbooks.scavro.plugin.AvroCodegenPlugin
import sbt._
import sbt.Keys._
import AvroCodegenPlugin.autoImport._


object DemoBuild extends Build {
  lazy val demoSettings = Defaults.defaultSettings ++ baseAvroCodegenSettings ++ Seq(
    // General settings
    organization := "oyster",
    scalaVersion := "2.10.4",
    version := "0.0.2",
    libraryDependencies ++= Seq(
      "com.oysterbooks" % "scavro_2.10_0.13" % "0.9.0" from
        "https://oss.sonatype.org/content/repositories/releases/com/oysterbooks/scavro_2.10_0.13/0.9.0/scavro-0.9.0.jar",
      "org.apache.avro" % "avro" % "1.7.7",
      "org.apache.avro" % "avro-tools" % "1.7.7",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test"
    ),
    resolvers ++= Seq(
      // "Local Maven" at Path.userHome.asFile.toURI.toURL + ".ivy2/local",
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),

    // scavro plugin settings
    avroSchemaFiles := Seq(
      (resourceDirectory in Compile).value / "item.avsc"
    ),

    mainClass in (Compile, run) := Some("com.oysterbooks.scavrodemo.ReadWriteDemo")
  )

  lazy val root = Project(id = "demo", base = file("."))
    .settings(demoSettings: _*)
    .settings(excludeFilter in unmanagedResources := "*.avsc")
}
