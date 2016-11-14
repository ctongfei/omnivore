package me.tongfei.omnivore.squad

import io.circe.generic.auto._
import io.circe.parser._
import poly.io.Local._

/**
 * @author Tongfei Chen
 */
object SQuADIngester {

  def ingest(fn: String) = {
    val json = decode[SQuAD](File(fn).slurp)
    json.right.get
  }

}
