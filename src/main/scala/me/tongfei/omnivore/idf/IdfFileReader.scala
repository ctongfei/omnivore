package me.tongfei.omnivore.idf

import poly.io.Local._

/**
 * @author Tongfei Chen
 */
object IdfFileReader {

  def read(fn: String) = File(fn).lines.map { l =>
    val Array(w, x) = l split "\t"
    w -> x.toDouble
  }.toMap

}
