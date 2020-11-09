package com.bridgelabz.censusanalyzer
class CensusAnalyzerException(message: CensusAnalyzerExceptionEnums.Value) extends Exception(message.toString) {}

object CensusAnalyzerExceptionEnums extends Enumeration {
  type CensusAnalyserException = Value

  val InCorrectFilePath: CensusAnalyzerExceptionEnums.Value = Value("Incorrect File Path provided")
  val InCorrectFileType: CensusAnalyzerExceptionEnums.Value = Value("Incorrect File Type provided")
  val UnableToParse: CensusAnalyzerExceptionEnums.Value = Value("Not able to Parse Invalid Delimiter or Fields")
  val NoCensusData: CensusAnalyzerExceptionEnums.Value = Value("Not Data available")
}