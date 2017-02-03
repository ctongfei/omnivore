package me.tongfei.omnivore.basic

import poly.io.Local._
import scala.collection._

/**
 * @author Tongfei Chen
 */
object WordSetReader {

  def read(fn: String) = File(fn).lines.toSet

}

object WordSetWriter {
  def write(fn: String)(s: Set[String]) = {
    val pw = new java.io.PrintWriter(fn)
    s foreach pw.println
    pw.close()
  }
}
