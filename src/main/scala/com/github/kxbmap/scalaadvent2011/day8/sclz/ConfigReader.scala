package com.github.kxbmap.scalaadvent2011.day8.sclz

import scalaz._, Scalaz._
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

  def readMap[A, B: Semigroup](path: Path): Map[A, B] =
    if (path.isFile)
      read[Map[A, B]](path.toFile)
    else
      readDirectoryMap(path)

  private def readDirectoryMap[A, B: Semigroup](dir: Path): Map[A, B] = {
    implicit val r = Reducer[(A, B), Map[A, B]](Map(_))
    dir.walkFilter(_.isFile).toStream
      .map(_.toFile)
      .map(read[(A, B)])
      .foldReduce
  }


  def localize[A, B: Semigroup](base: Map[A, B], path: Path): Map[A, B] = {
    val loc = readMap[A, B => B](path)
    for ((id, item) <- base)
    yield id -> (loc.get(id).map(_(item)) getOrElse item)
  }

}
