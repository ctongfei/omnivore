package me.tongfei.omnivore.treceval

import poly.io.Local._

/**
 * Represents an item in a `trec_eval` reference file.
 * @author Tongfei Chen
 * @since 0.9.0
 * @note `iter` can be set to anything. This field is ignored (yet required) by `trec_eval`.
 */
case class TrecEvalReferenceFileItem(queryId: String, iter: String, docId: String, relevance: Double) {
  override def toString() = s"$queryId\t$iter\t$docId\t$relevance"
}

object TrecEvalReferenceFileItem {
  def parse(s: String): TrecEvalReferenceFileItem = {
    val Array(queryId, iter, docId, relevance) = s split "\\s+"
    TrecEvalReferenceFileItem(queryId, iter, docId, relevance.toDouble)
  }
}

object TrecEvalReferenceFileReader {
  def read(fn: String) = File(fn).lines.map(TrecEvalReferenceFileItem.parse).toIndexedSeq
}

object TrecEvalReferenceFileWriter {
  def write(fn: String)(xs: Traversable[TrecEvalReferenceFileItem]) = {
    val pw = new java.io.PrintWriter(fn)
    for (x <- xs) pw.println(x.toString())
    pw.close()
  }
}
