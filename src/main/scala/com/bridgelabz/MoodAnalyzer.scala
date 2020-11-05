package com.bridgelabz

/**
 * author - Ruchir
 * date - 04-11-2020
 * time - 23:34
 * package - com.bridgelabz
 * title - Mood analyzer class to check mood
 */
class MoodAnalyzer (message: String) {
  object MoodAnalyzer {
    // method which checks message and prints "Happy" or "Sad"
    def analyzeMood(): String = {
      try {
        if (message == null) {
          return "HAPPY"
        }
        if (message.contains("SAD"))
          "SAD"
        else
          "HAPPY"
      }
      catch {
        case nullPointerException: NullPointerException =>
          throw nullPointerException
      }
    }
  }
}
