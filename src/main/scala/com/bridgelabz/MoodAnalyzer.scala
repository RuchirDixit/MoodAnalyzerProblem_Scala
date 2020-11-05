package com.bridgelabz

/**
 * author - Ruchir
 * date - 04-11-2020
 * time - 23:34
 * package - com.bridgelabz
 * title - Mood analyzer class to check mood
 */
class MoodAnalyzerException(exceptionMsg:TypeException.Value ) extends Exception(exceptionMsg.toString){}
class MoodAnalyzer(var message: String) {
  def analyzeMood(): String = {
    try {
      if (message.length==0){
        throw new MoodAnalyzerException(TypeException.EmptyType)
      }
      if (message.contains("Sad"))
        "SAD"
      else
        "HAPPY"
    }
    catch {
      case nullPointerException: NullPointerException=>
        throw new MoodAnalyzerException(TypeException.NullType)
    }
  }
}