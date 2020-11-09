package com.bridgelabz.censusanalyzer
import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

class CensusLoader {
  /**
   *
   * @param csvClass : Class which is passed by Census Analyzer
   * @param filePaths : all files paths passed
   * @tparam A : Generic
   * @return : Map with census data
   */
  def loadData[A](csvClass: Class[A], filePaths: String*): Map[String, CensusDAO] = {
    try {
      var censusMap: Map[String, CensusDAO] = Map()
      for (filePath <- filePaths) {
        if (!filePath.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFileType)
        }
        val reader = Files.newBufferedReader(Paths.get(filePath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if(csvClass.getName == "com.bridgelabz.censusanalyzer.IndiaStateCensus"){
          val censusCSVIterator: util.Iterator[IndiaStateCensus] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaStateCensus])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.state -> new CensusDAO(objDAO))
          }
        }
        else if(csvClass.getName == "com.bridgelabz.censusanalyzer.StateCode"){
          val censusCSVIterator: util.Iterator[StateCode] = csvBuilder.getCSVFileIterator(reader, classOf[StateCode])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.stateName -> new CensusDAO(objDAO))
          }
        }
        else if (csvClass.getName == "com.bridgelabz.censusanalyzer.USCensusDTO"){
          val censusCSVIterator: util.Iterator[USCensusDTO] = csvBuilder.getCSVFileIterator(reader,classOf[USCensusDTO])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO))}
        }
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }
}