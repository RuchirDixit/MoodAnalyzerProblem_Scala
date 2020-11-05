package com.bridgelabz
import org.scalatest.FunSuite
import com.bridgelabz.{MoodAnalyzerException,MoodAnalyzer,TypeException}
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
    assert(moodAnalyzer.analyzeMood() === "SAD")
  }
  test("givenMessageWhenHappyShouldReturnHappy") {
    val moodAnalyzer = new MoodAnalyzer("I am in Happy Mood")
    assert(moodAnalyzer.analyzeMood() === "HAPPY")
  }
  test("givenAnyMessageShouldReturnHappy") {
    val moodAnalyzer = new MoodAnalyzer("I am in Any Mood")
    assert(moodAnalyzer.analyzeMood() === "HAPPY")
  }
  test("givenNullMessageShouldReturnException"){
    val moodAnalyzer = new MoodAnalyzer(null)
    val exception = intercept[MoodAnalyzerException]{
      moodAnalyzer.analyzeMood()
    }
    assert( exception.getMessage === TypeException.NullType.toString)
  }
  test("givenEmptyMessageShouldReturnException"){
    val moodAnalyzer = new MoodAnalyzer("")
    val exception = intercept[MoodAnalyzerException]{
      moodAnalyzer.analyzeMood()
    }
    assert(exception.getMessage === TypeException.EmptyType.toString)
  }
}