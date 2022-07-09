package com.seeta.codility.company1

/** Find max number of words in a sentence.
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object Task1 {
  def solution(s: String): Int = {
    val sentences        = s.split("\\.|\\?|\\!")
    val wordsInSentences = sentences.map(sentence => sentence.split("\\s+").count(_.length > 0))
    if (wordsInSentences.isEmpty) 0 else wordsInSentences.max
  }
}
