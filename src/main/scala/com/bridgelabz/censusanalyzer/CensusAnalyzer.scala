package com.bridgelabz.censusanalyzer
import java.util
import java.util.Comparator
import com.google.gson.Gson

class CensusAnalyzer {
  var censusMap: Map[String, CensusDAO] = Map()
  var censusStateMap: Map[String, CensusDAO] = Map()

  /**
   *
   * @param filePath : path of csv file
   * @return : get India State census file and load it from Census loader class
   */
  def loadIndiaCensusData(filePath: String): Int = {
    censusMap = new CensusLoader().loadData(classOf[IndiaStateCensus], filePath)
    censusMap.size
  }

  /**
   *
   * @param filePath : path of csv file
   * @return : get India State code file and load it from Census loader class
   */
  def loadIndiaStateCode(filePath: String): Int = {
    censusStateMap = new CensusLoader().loadData(classOf[StateCode], filePath)
    censusStateMap.size
  }

  /**
   *
   * @param filePath : path of csv file
   * @return : get US census file and load it from Census loader class
   */
  def loadCensusUSData(filePath: String):Int={
    censusMap = new CensusLoader().loadData(classOf[USCensusDTO],filePath)
    censusMap.size
  }

  def sort(choice: Int): String = {
    if (censusMap == null || censusMap.isEmpty) {
      throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.NoCensusData)
    }
    var censusCSVList = censusMap.values.toArray
    censusCSVList = choice match {
      case 1 => censusCSVList.sortBy(_.state)
      case 2 => censusCSVList.sortBy(_.stateCode)
      case 3 => censusCSVList.sortBy(_.population).reverse
      case 4 => censusCSVList.sortBy(_.populationDensity).reverse
      case 5 => censusCSVList.sortBy(_.totalArea).reverse
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateWiseSortedCensusData: String = {
    sort(1)
  }

  def getStateCodeWiseSortedCensusData: String = {
    for (stateNameCensus <- censusMap.keys; stateName <- censusStateMap.keys; if (stateName.equals(stateNameCensus))) {
      val censusData = censusMap(stateNameCensus)
      censusData.stateCode = censusStateMap(stateName).stateCode
    }
    sort(2)
  }

  def getPopulationWiseSortedCensusData: String = {
    sort(3)
  }

  def getPopulationDensityWiseSortedCensusData: String = {
    sort(4)
  }

  def getAreaWiseSortedCensusData: String = {
    sort(5)
  }
}