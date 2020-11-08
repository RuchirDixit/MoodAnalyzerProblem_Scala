package com.bridgelabz.censusanalyzer

class CSVBuilderException(message:CSVBuilderExceptionEnums.Value) extends Exception(message.toString) {}


object CSVBuilderExceptionEnums extends Enumeration {
  type CSVBuilderException = Value
  val InCorrectPath: CSVBuilderExceptionEnums.Value = Value("Incorrect Path Specified")
  val InCorrectFile: CSVBuilderExceptionEnums.Value = Value("Incorrect File Specified")
  val UnableToParse: CSVBuilderExceptionEnums.Value = Value("Not able to Parse Invalid Delimiter or Fields")
}
