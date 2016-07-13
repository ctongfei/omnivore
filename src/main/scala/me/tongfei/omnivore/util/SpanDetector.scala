package me.tongfei.omnivore.util

/**
 * @author Tongfei Chen
 */
class SpanDetector(source: String) {

  private[this] var index = 0

  def spanOf(pattern: String) = {
    val i = source.substring(index).indexOf(pattern)
    val res = (index + i, index + i + pattern.length)
    if (i != -1) // the pattern is actually found
      index += i + pattern.length
    res
  }

}
