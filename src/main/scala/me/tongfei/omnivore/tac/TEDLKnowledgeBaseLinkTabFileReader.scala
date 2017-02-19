package me.tongfei.omnivore.tac

import poly.io.Local._
import scala.collection._

/**
 * Reads something like "tac_kbp_2015_tedl_training_gold_standard_entity_mentions.tab".
 * @since 0.11.0
 * @author Tongfei Chen
 */
object TEDLKnowledgeBaseLinkTabFileReader {

  private def yn(x: String) = x match {
    case "Y" => true
    case "N" => false
  }

  def read(fn: String) = {
    val b = SortedMap.newBuilder[String, TEDLKnowledgeBaseLink]
    val pairs = File(fn).lines.map { l =>
      val Array(ldc, queryId, name, ref, entityId, entityType, mentionType, score, isWebSearch, isWikiText, isUnknown) = l split "\t"
      val Array(docId, beginEnd) = ref split ":"
      val Array(begin, end) = beginEnd split "-"
      queryId -> TEDLKnowledgeBaseLink(queryId, name, docId, begin.toInt, end.toInt, entityId, entityType, mentionType, score.toDouble, yn(isWebSearch), yn(isWikiText), yn(isUnknown))
    } foreach b.+=
    b.result()
  }

}
