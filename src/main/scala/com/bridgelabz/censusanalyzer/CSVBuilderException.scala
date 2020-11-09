package com.bridgelabz.censusanalyzer

class CSVBuilderException(message: CSVBuilderExceptionEnums.Value) extends Exception(message.toString) {}

object CSVBuilderExceptionEnums extends Enumeration {
  type CSVBuilderException = Value
  val InCorrectFilePath: CSVBuilderExceptionEnums.Value = Value("Incorrect File Path provided")
  val InCorrectFileType: CSVBuilderExceptionEnums.Value = Value("Incorrect File Type provided")
  val UnableToParse: CSVBuilderExceptionEnums.Value = Value("Not able to Parse Invalid Delimiter or Fields")
}