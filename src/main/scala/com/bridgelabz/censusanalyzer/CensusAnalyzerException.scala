package com.bridgelabz.censusanalyzer

class CensusAnalyzerException(message:CensusAnalyzerExceptionEnum.Value) extends Exception(message.toString) {}
object CensusAnalyzerExceptionEnum extends Enumeration {
  type CensusAnalyzerExceptionEnum = Value
  val InCorrectFile: CensusAnalyzerExceptionEnum.Value = Value("Incorrect File")
  val InCorrectPath: CensusAnalyzerExceptionEnum.Value = Value("Incorrect path")
  val InCorrectDelimiter:CensusAnalyzerExceptionEnum.Value = Value("Incorrect Delimiter mentioned")
  val InCorrectFields: CensusAnalyzerExceptionEnum.Value = Value("Incorrect Fields mentioned")
}
