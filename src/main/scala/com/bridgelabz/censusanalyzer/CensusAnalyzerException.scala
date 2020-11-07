package com.bridgelabz.censusanalyzer


class CensusAnalyzerException(message:CensusAnalyzerExceptionEnum.Value) extends Exception(message.toString) {}
object CensusAnalyzerExceptionEnum extends Enumeration {
  type CensusAnalyzerExceptionEnum = Value
  val InCorrectPath: CensusAnalyzerExceptionEnum.Value = Value("Incorrect Path Specified")
  val InCorrectFile: CensusAnalyzerExceptionEnum.Value = Value("Incorrect File Specified")
  val UnableToParse: CensusAnalyzerExceptionEnum.Value = Value("Not able to Parse Invalid Delimiter or Fields")

}
