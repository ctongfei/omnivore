package me.tongfei.omnivore.wikiqa

import poly.io.Local._
import scala.collection.immutable._

/**
 * @author Tongfei Chen
 * @since 0.5.0
 */
case class WikiQAItem(
  questionId: String,
  question: String,
  documentId: String,
  documentTitle: String,
  sentenceId: String,
  sentence: String,
  label: Boolean
)

object WikiQAIngester {

  // WikiQA string for document titles are not properly encoded
  def decipherUTF8(s: String) = new String(s.toCharArray.map(_.toByte), "UTF-8")

  def ingest(fn: String) = {
    val items = File(fn).lines.tail.map { l =>
      val Array(questionId, question, documentId, documentTitle, sentenceId, sentence, label) = l.split("\t")
      WikiQAItem(questionId, question, documentId, decipherUTF8(documentTitle), sentenceId, sentence, label == "1")
    }.groupBy(_.questionId)
    SortedMap(items.toSeq: _*)
  }

}

