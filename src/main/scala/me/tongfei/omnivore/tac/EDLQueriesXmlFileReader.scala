package me.tongfei.omnivore.tac

import scala.collection._
import scala.xml._

/**
 * Reads something like "tac_2014_kbp_english_EDL_training_queries.xml".
 * @author Tongfei Chen
 * @since 0.8.0
 */
object EDLQueriesXmlFileReader {

  def read(fn: String) = {
    val xml = XML.loadFile(fn)
    val queries = (xml \ "query") map { n =>
      val id = n \@ "id"
      val name = (n \ "name").head.text.trim
      val docId = (n \ "docid").head.text.trim
      val begin = (n \ "beg").head.text.trim.toInt
      val end = (n \ "end").head.text.trim.toInt
      id -> EDLTrainingQuery(id, name, docId, begin, end)
    }
    val b = SortedMap.newBuilder[String, EDLTrainingQuery]
    queries foreach b.+=
    b.result()

  }

}
