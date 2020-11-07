package com.bridgelabz.censusanalyzer
import java.nio.file.{Files, Paths}
import java.util

class CensusAnalyzer {

  def loadCSVDataIndiaStateCensus(filePath:String): Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFile)
      }
      listRowReturns(filePath,classOf[IndiaStateCensus])
    }
    catch {
      case _:java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
    }
  }

  def loadCSVDataIndiaStateCode(filePath:String):Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFile)
      }
      listRowReturns(filePath,classOf[StateCode])
    }
    catch {
      case _: java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
    }
  }
  def listRowReturns[T](filePath: String,c :Class[T]):Int = {
    val reader = Files.newBufferedReader(Paths.get(filePath))
    val csvBuilder = CSVBuilderFactory.createCSVBuilder()
    val censusCSVList = csvBuilder.getList(reader,c)
    censusCSVList.size()
  }


  def getCountRows[T](fileIterator: util.Iterator[T]):Int = {
    var countRows = 0
    while(fileIterator.hasNext) {
      countRows += 1
      fileIterator.next()
    }
    countRows
  }
}