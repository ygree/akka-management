import sbt._
import Keys._

object Dependencies {

  val AkkaVersion = "2.5.7"
  val AkkaHttpVersion = "10.0.10"

  val JUnitVersion = "4.12"
  val SprayJsonVersion = "1.3.3"

  // val akkaDns = "2.4.2" // external lib

  val Common = Seq(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % Test // ApacheV2
    )
  )

  private object DependencyGroups {
    val AkkaActor = Seq(
      "com.typesafe.akka" %% "akka-actor"                         % AkkaVersion
    )

    val AkkaHttpCore = Seq(
      "com.typesafe.akka" %% "akka-http-core"                     % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"               % AkkaHttpVersion,
      "io.spray"          %% "spray-json"                         % SprayJsonVersion                  // ApacheV2
    )
    val AkkaHttp = Seq(
      "com.typesafe.akka" %% "akka-http"                          % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"               % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"                        % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor"                         % AkkaVersion,
      "io.spray"          %% "spray-json"                         % SprayJsonVersion                  // ApacheV2
    )

    val AkkaCluster = Seq(
      "com.typesafe.akka" %% "akka-cluster"                       % AkkaVersion
    )

    val AkkaSharding = Seq(
      "com.typesafe.akka" %% "akka-cluster"                       % AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-sharding"              % AkkaVersion
    )

    val AkkaTesting = Seq(
      "com.typesafe.akka" %% "akka-testkit"                       % AkkaVersion     % "test",
      "com.typesafe.akka" %% "akka-http-testkit"                  % AkkaHttpVersion % "test",
      "junit"             % "junit"                               % JUnitVersion    % "test",
      "org.mockito"       % "mockito-all"                         % "1.10.19"       % "test"  // Common Public License 1.0
    )
  }

  val Discovery = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaActor ++
      DependencyGroups.AkkaTesting
  )

  val DiscoveryDns = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaActor ++
      DependencyGroups.AkkaTesting ++ Seq(
        // FIXME this would be needed to use the SRV records, but more likely we want to implement it ourselves
        // "ru.smslv.akka"     %% "akka-dns"                           % akkaDns, // ApacheV2 (we do need it)
      )
  )

  val DiscoveryKubernetesApi = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaActor ++
      DependencyGroups.AkkaHttp
  )

  val DiscoveryAwsApi = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaActor ++ Seq("com.amazonaws" % "aws-java-sdk-ec2" % "1.11.257")
  )

  val ManagementHttp = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaHttp ++
      DependencyGroups.AkkaTesting
  )
  val ClusterHttp = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaSharding ++
      DependencyGroups.AkkaHttpCore ++
      DependencyGroups.AkkaTesting ++ Seq(
      "com.typesafe.akka" %% "akka-distributed-data"              % AkkaVersion     % "test"
    )
  )

  val ClusterBootstrap = Seq(
    libraryDependencies ++=
      DependencyGroups.AkkaCluster ++
      DependencyGroups.AkkaHttpCore ++
      DependencyGroups.AkkaTesting ++ Seq(
      "com.typesafe.akka" %% "akka-distributed-data"              % AkkaVersion     % "test"
    )
  )

}
