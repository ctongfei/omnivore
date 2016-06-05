package me.tongfei.omnivore.gigaword4

import me.tongfei.omnivore.util._
import java.nio.file._
import edu.jhu.hlt.concrete.uuid._
import me.tongfei.granite._
import me.tongfei.granite.io._
import scala.collection.JavaConversions._
import scala.xml._

/**
 * @author Tongfei Chen
 */
object Gigaword4Ingester {

  def ingest(xml: String): Communication = {

    val doc = XML.loadString(xml)

    val docid = (doc \ "DOCID").head.text.trim

    val dateTime = (doc \ "DATETIME").head.text.trim

    val headline = (doc \ "BODY" \ "HEADLINE").head.text.trim

    val paragraphs = (doc \ "BODY" \ "TEXT" \ "P") map { _.text.trim }

    val uf = new AnalyticUUIDGeneratorFactory().create()

    new Communication {
      id = docid
      uuid = uf.next()
      typ = "newswire"
      text = xml
      sectionList = {
        val headlineSpan = Util.spanOf(headline, xml)
        val headlineSection = new Section {
          uuid = uf.next()
          kind = "headline"
          textSpan = new TextSpan(headlineSpan._1, headlineSpan._2)
        }
        val paragraphSections = paragraphs map { p =>
          val paragraphSpan = Util.spanOf(p, xml)
          new Section {
            uuid = uf.next()
            kind = "passage"
            textSpan = new TextSpan(paragraphSpan._1, paragraphSpan._2)
          }
        }
        headlineSection +: paragraphSections
      }

    }
  }

  def main(args: Array[String]): Unit = {
    val input = Files.readAllLines(Paths.get(args(0))).mkString("\n")
    val comm = ingest(input)
    ConcreteIO.save(args(1))(comm)
  }

}

