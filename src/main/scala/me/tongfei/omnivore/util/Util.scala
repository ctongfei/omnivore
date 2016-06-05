package me.tongfei.omnivore.util

/**
 * @author Tongfei Chen
 */
object Util {

  def spanOf(pattern: String, src: String) = {

    val i = src.indexOf(pattern)
    (i, i + pattern.length)

  }

}
