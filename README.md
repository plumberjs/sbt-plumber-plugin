sbt-plumber-plugin
==================

An [sbt](http://www.scala-sbt.org/) plugin to wrap [Plumber](https://github.com/theefer/plumber) pipelines and expose them as sbt tasks.

This plugin provides a `plumberPipeline` function will return an sbt task wrapping the Plumber pipeline with the given name. You can use it to add steps to your compile or test setup in your `build.sbt` file, e.g.:

     (compile in Compile) <<= (compile in Compile) dependsOn (plumberPipeline("my-build"))


This plugin also exposes a `plumber` command which can be called from the sbt console:

     > plumber jshint  # run the "jshint" plumber pipeline
     
     Run pipeline: jshint
     ...
