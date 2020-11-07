package com.bridgelabz.censusanalyzer
import java.io.FileNotFoundException

import scala.io.Source
class CensusAnalyzer {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFile)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
        }

        if (countRow == 0){
          if(cols(0) != "State" || cols(1) != "Population" || cols(2) != "AreaInSqKm" || cols(3) != "DensityPerSqKm"){
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectPath)
    }
  }

  def loadIndiaStateCode(filePath:String):Int={
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFile)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
        }

        if (countRow == 0){
          if(cols(1) != "State Name" || cols(2) != "TIN" || cols(3) != "StateCode"){
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.UnableToParse)
    }
  }
}