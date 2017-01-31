package me.tongfei.omnivore.treceval

import poly.io.Local._

/**
 * Represents an item in a `trec_eval` result file.
 * @author Tongfei Chen
 * @since 0.9.0
 * @note `iter` and `rank` are ignored, yet required, by `trec_eval`.
 */
case class TrecEvalResultFileItem(queryId: String, iter: String, docId: String, rank: Int, sim: Double, runId: String) {
  override def toString = s"$queryId\t$iter\t$docId\t$rank\t$sim\t$runId"
}

object TrecEvalResultFileItem {
  def parse(s: String): TrecEvalResultFileItem = {
    val Array(queryId, iter, docId, rank, sim, runId) = s split "\\s+"
    TrecEvalResultFileItem(queryId, iter, docId, rank.toInt, sim.toDouble, runId)
  }
}

object TrecEvalResultFileReader {
  def read(fn: String) = File(fn).lines.map(TrecEvalResultFileItem.parse).toIndexedSeq
}

object TrecEvalResultFileWriter {
  def write(fn: String)(xs: Traversable[TrecEvalResultFileItem]) = {
    val pw = new java.io.PrintWriter(fn)
    for (x <- xs) pw.println(x.toString())
    pw.close()
  }
}
