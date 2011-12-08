package com.github.kxbmap.scalaadvent2011.day8

import scala.tools.nsc.io.Path

object Main extends App with sclz.ConfigReader {
  val path = Path("config")

  val config =
    read[Path => GameConfig](path / "config.scala" toFile)(path)

  val localized =
    read[(GameConfig, Path) => GameConfig](path / "localize.scala" toFile)(config, path / "localize" / "en")

  println(config.weapons)
  println(localized.weapons)
}
