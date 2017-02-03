package me.tongfei.omnivore.embedding

import poly.io.Local._

/**
 * @author Tongfei Chen
 */
object WordEmbeddingsReader {

  def read(fn: String) = File(fn).lines.map { l =>
    val tokens = l split "\\s+"
    tokens.head -> tokens.tail.map(_.toDouble)
  }.toMap

}
