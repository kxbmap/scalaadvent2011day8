package com.github.kxbmap.scalaadvent2011.day8

import com.twitter.util.Eval
import scala.tools.nsc.io.{File, Path}
import java.io.InputStream

trait ConfigReader {
  private val eval = new Eval()

  def read[A](opener: => InputStream): A = {
    val in = opener
    try eval[A](in)
    finally in.close()
  }

  def read[A](file: File): A = read(file.inputStream())

  def readMap[A, B](path: Path): Map[A, B] =
    if (path.isFile)
      read[Map[A, B]](path.toFile)
    else
      readDirectoryMap(path)

  private def readDirectoryMap[A, B](dir: Path): Map[A, B] =
    dir.walkFilter(_.isFile)
      .map(_.toFile)
      .map(read[(A, B)])
      .foldLeft(Map.empty[A, B])(_ + _)


  def localize[A, B](base: Map[A, B], path: Path): Map[A, B] = {
    val loc = readMap[A, B => B](path)
    for ((id, item) <- base)
    yield id -> (loc.get(id).map(_(item)) getOrElse item)
  }
}