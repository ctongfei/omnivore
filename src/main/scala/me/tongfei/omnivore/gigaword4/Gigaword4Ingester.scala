package me.tongfei.omnivore.gigaword4

import me.tongfei.omnivore.util._
import edu.jhu.hlt.granite._
import java.nio.file._

import edu.jhu.hlt.concrete.uuid._
import edu.jhu.hlt.granite.io._

import scala.collection.JavaConversions._
import scala.xml._

/**
 * Ingests files whose name follows the pattern "LTW_ENG_20070401.0153.LDC2009T13.xml".
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

    val sd = new SpanDetector(xml)

    new Communication {
      id = docid
      uuid = uf.next()
      typ = "newswire"
      text = xml
      metadata = new AnnotationMetadata {
        kBest = 1
        tool = "Omnivore"
        timestamp = 0
      }
      sectionList = {
        val headlineSpan = sd.spanOf(headline)
        val headlineSection = new Section {
          uuid = uf.next()
          kind = "headline"
          textSpan = new TextSpan(headlineSpan._1, headlineSpan._2)
        }
        val paragraphSections = paragraphs map { p =>
          val paragraphSpan = sd.spanOf(p)
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
    ConcreteIO.save(comm)(args(1))
  }

}

