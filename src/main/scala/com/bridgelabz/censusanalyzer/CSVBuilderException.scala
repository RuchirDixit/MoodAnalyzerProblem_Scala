package com.bridgelabz.censusanalyzer

class CSVBuilderException(message:CSVBuilderExceptionEnum.Value) extends Exception(message.toString) {}


object CSVBuilderExceptionEnum extends Enumeration {
  type CSVBuilderException = Value
  val InCorrectPath: CSVBuilderExceptionEnum.Value = Value("Incorrect Path Specified")
  val InCorrectFile: CSVBuilderExceptionEnum.Value = Value("Incorrect File Specified")
  val UnableToParse: CSVBuilderExceptionEnum.Value = Value("Not able to Parse Invalid Delimiter or Fields")
}
