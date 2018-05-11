name := "ztest"

version := "0.1"

scalaVersion := "2.12.4"

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.36"
)
