package me.tongfei.omnivore.mst

import edu.jhu.hlt.concrete.uuid._
import edu.jhu.hlt.granite._

/**
 * Ingests MST Parser format sentences.
 * @author Tongfei Chen
 * @since 0.4.0
 */
object MstParserFormatIngester {


  def ingest(s: String) = {

    val uf = new AnalyticUUIDGeneratorFactory().create()

    val Array(tokens, pos, depRel, depGovOneIndexed, nerWithBITag) = s.split("\n") map { _.split("\t") }

    val ner = nerWithBITag map {
      case "-" => "O"
      case x if x endsWith "-B" => x.substring(0, x.length - 2)
      case x if x endsWith "-I" => x.substring(0, x.length - 2)
      case x => x
    }

    val depGov = depGovOneIndexed map { _.toInt - 1 }

    new Sentence {
      uuid = uf.next()
      tokenization = new Tokenization {
        tokenList = tokens.toSeq.zipWithIndex.map { case (t, i) =>
          new Token {
            text = t
            tokenIndex = i
          }
        }
        tokenTaggingList = Seq(
          new TokenTagging {
            taggingType = "NER"
            taggedTokenList = ner.toSeq.zipWithIndex.map { case (t, i) =>
              new TaggedToken {
                tag = t
                tokenIndex = i
              }
            }
          }
        )
        dependencyParseList = Seq(
          new DependencyParse {
            dependencyList = (depRel zip depGov).toSeq.zipWithIndex.map { case ((r, g), i) =>
              new Dependency {
                gov = g
                dep = i
                edgeType = r
              }
            }
          }
        )
      }
    }

  }

}
