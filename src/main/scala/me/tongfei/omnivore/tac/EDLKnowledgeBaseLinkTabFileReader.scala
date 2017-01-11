package me.tongfei.omnivore.tac

import poly.io.Local._
import scala.collection._

/**
 * Reads something like "tac_2014_kbp_english_EDL_training_KB_links.tab" in TAC KBP.
 * @author Tongfei Chen
 * @since 0.8.0
 */
object EDLKnowledgeBaseLinkTabFileReader {

  private def yn(x: String) = x match {
    case "Y" => true
    case "N" => false
  }

  def read(fn: String) = {
    val b = SortedMap.newBuilder[String, EDLKnowledgeBaseLink]
    val pairs = File(fn).lines.tail.map { l =>
      val Array(queryId, entityId, entityType, genre, isWebSearch, isWikiText, isUnknown) = l split "\t"
      queryId -> EDLKnowledgeBaseLink(queryId, entityId, entityType, genre, yn(isWebSearch), yn(isWikiText), yn(isUnknown))
    } foreach b.+=
    b.result()
  }

}
