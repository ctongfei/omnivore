package me.tongfei.omnivore.ldc2015e75newswire

import me.tongfei.omnivore.util._
import me.tongfei.granite._
import me.tongfei.granite.io._
import java.nio.file._

import edu.jhu.hlt.concrete.uuid._

import scala.collection.JavaConversions._
import scala.xml._

/**
 * Ingests files whose name follows the pattern "ENG_NW_001001_20150404_F0000000.xml".
 * @author Tongfei Chen
 */
object LDC2015E75NewswireIngester {

  def ingest(xml: String): Communication = {

    val doc = XML.loadString(xml)
    val docid = doc.attributes.get("id").head.text.trim
    val dateTime = (doc \ "DATE_TIME").head.text.trim
    val headline = (doc \ "HEADLINE").head.text.trim
    val paragraphs = (doc \ "TEXT" \ "P") map { _.text.trim }

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
    ConcreteIO.save(args(1))(comm)
  }

}

