package com.bridgelabz
import org.scalatest.FunSuite
import com.bridgelabz.MoodAnalyzer
/**
 * author - Ruchir
 * date - 04-11-2020
 * time - 23:37
 * package - com.bridgelabz
 * title - Test class for moodanalyzer
 */


class MoodAnalyzerTest extends FunSuite {

  test("givenMessageWhenSadShouldReturnSad") {
    val moodAnalyzer = new MoodAnalyzer("I am in Sad Mood")
    assert(moodAnalyzer.MoodAnalyzer.analyzeMood() === "SAD")
  }
  test("givenMessageWhenHappyShouldReturnHappy") {
    val moodAnalyzer = new MoodAnalyzer("I am in Happy Mood")
    assert(moodAnalyzer.MoodAnalyzer.analyzeMood() === "HAPPY")
  }
  test("givenAnyMessageShouldReturnHappy") {
    val moodAnalyzer = new MoodAnalyzer("I am in Any Mood")
    assert(moodAnalyzer.MoodAnalyzer.analyzeMood() == "HAPPY")
  }
}
