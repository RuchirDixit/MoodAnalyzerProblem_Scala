package com.bridgelabz.censusanalyzer
import java.util
import java.util.Comparator

import com.bridgelabz.censusanalyzer.Country.Country
import com.google.gson.Gson

class CensusAnalyzer {
  var censusMap: Map[String, CensusDAO] = Map()

  /**
   *
   * @param country : India or US
   * @param filepath : path of file passes as paramter
   * @return : size of census map
   */
  def loadCensusData(country: Country,filepath:String*): Int = {
    censusMap = new CensusLoader().loadData(country,filepath)
    censusMap.size
  }

  def sort(choice: Int): String = {
    if (censusMap == null || censusMap.isEmpty) {
      throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.NoCensusData)
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