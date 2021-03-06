package me.tongfei.omnivore.tac

/**
 * @author Tongfei Chen
 * @since 0.8.0
 */
case class EDLKnowledgeBaseLink(
  queryId: String,
  entityId: String,
  entityType: String,
  genre: String,
  isWebSearch: Boolean,
  isWikiText: Boolean,
  isUnknown: Boolean
)

/**
 * @author Tongfei Chen
 * @since 0.8.0
 */
case class EDLTrainingQuery(
  queryId: String,
  name: String,
  docId: String,
  begin: Int,
  end: Int
)

case class TEDLKnowledgeBaseLink(
  queryId: String,
  name: String,
  docId: String,
  begin: Int,
  end: Int,
  entityId: String,
  entityType: String,
  mentionType: String,
  score: Double,
  isWebSearch: Boolean,
  isWikiText: Boolean,
  isUnknown: Boolean
)
