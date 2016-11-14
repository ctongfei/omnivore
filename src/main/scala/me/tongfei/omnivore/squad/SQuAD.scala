package me.tongfei.omnivore.squad

/**
 * Contains definitions of the SQuAD (Stanford Question/Answers Dataset):
 * [[https://rajpurkar.github.io/SQuAD-explorer/]]
 * @author Tongfei Chen
 */

case class SQuAD(data: Seq[SQuAD.Article], version: String)

object SQuAD {

  case class Mention(answer_start: Int, text: String)

  case class QuestionAnswerSet(answers: Seq[Mention], id: String, question: String)

  case class Paragraph(context: String, qas: Seq[QuestionAnswerSet])

  case class Article(paragraphs: Seq[Paragraph], title: String)

}
