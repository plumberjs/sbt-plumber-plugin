package cc.inso

import sbt._
import Keys._

object SbtPlumberPlugin extends Plugin {

  private def runPlumber(executable: String, pipeline: String) = {
    val cmd = s"$executable $pipeline"
    cmd !
  }

  // REPL command

  def plumberCommand = {
    Command.single("plumber") { (state: State, pipeline: String) =>
      // FIXME: how do you access the plumberExecutable in here?
      runPlumber("plumber", pipeline)

      state
    }
  }

  // sbt task

  def plumberPipeline(pipelineName: String) =
    (plumberExecutable, streams) map { (exec, s: TaskStreams) =>
      val retval = runPlumber(exec, pipelineName)
      if (retval != 0) {
        throw new Exception("Plumber pipeline %s failed".format(pipelineName))
      }
    }


  // Expose plugin

  val plumberExecutable = SettingKey[String]("plumber-executable", "Path to the plumber executable. Use plumber on $PATH by default")

  lazy val plumberSettings: Seq[Project.Setting[_]] = Seq(
    plumberExecutable := "plumber",
    // FIXME: is it even registered?
    commands += plumberCommand
  )

}
