package com.bridgelabz.censusanalyzer

class CensusAnalyzer {
  def loadCSVInfo(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFile)
      }
      val FileReader = io.Source.fromFile(filePath)
      var rowsCounted = 0
      for(line <- FileReader.getLines()) {
        val cols = line.split(",").map(_.trim)
        if(cols.length != 4) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectDelimiter)
        }
        if(rowsCounted == 0){
          if(cols(0).toLowerCase != "state" || cols(1).toLowerCase != "population" ||
            cols(2).toLowerCase != "areainsqkm" || cols(3).toLowerCase != "densitypersqkm" ) {
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectFields)
          }
        }
        rowsCounted += 1
      }
      FileReader.close()
      rowsCounted - 1
    }
    catch {
      case _:java.io.FileNotFoundException =>
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.InCorrectPath)
    }
  }
}