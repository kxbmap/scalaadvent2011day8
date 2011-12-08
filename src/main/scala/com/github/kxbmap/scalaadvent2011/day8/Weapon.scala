package com.github.kxbmap.scalaadvent2011.day8

import scalaz.Semigroup
import scala.tools.nsc.io.Path._

case class Weapon(name: String, attack: Int)

object Weapon extends sclz.ConfigReader {
  implicit lazy val WeaponSemigroup: Semigroup[Weapon] =
    read("typeclasses" / "WeaponSemigroup.scala" toFile)
}
