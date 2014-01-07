import sbt._
import Keys._

object SbtPlumberPlugin extends Plugin {

  private def runPlumber(pipeline: String) = {
    val cmd = "plumber " + pipeline
    cmd !
  }

  // Plumber command

  def plumberCommand = {
    Command.single("plumber") { (state: State, pipeline: String) =>
      runPlumber(pipeline)

      state
    }
  }

  // Plumber pipeline

  def plumberPipeline(pipelineName: String) = streams map { (s: TaskStreams) =>
      val retval = runPlumber(pipelineName)
      if (retval != 0) {
        throw new Exception("Plumber pipeline %s failed".format(pipelineName))
      }
  }


  // Expose plugin

  override lazy val settings = Seq(commands += plumberCommand)

}
