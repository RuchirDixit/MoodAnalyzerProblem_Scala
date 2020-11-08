package com.bridgelabz.censusanalyzer


class CensusAnalyzerException(message:CensusAnalyzerExceptionEnums.Value) extends Exception(message.toString) {}
object CensusAnalyzerExceptionEnums extends Enumeration {
  type CensusAnalyzerException = Value
  val InCorrectPath: CensusAnalyzerExceptionEnums.Value = Value("Incorrect Path Specified")
  val InCorrectType: CensusAnalyzerExceptionEnums.Value = Value("Incorrect File Specified")
  val UnableToParse: CensusAnalyzerExceptionEnums.Value = Value("Not able to Parse Invalid Delimiter or Fields")
  val NoCensusData: CensusAnalyzerExceptionEnums.Value = Value("Not Data available")
}
