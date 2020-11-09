package com.bridgelabz.censusanalyzer
import java.util
import java.util.Comparator
import com.google.gson.Gson

class CensusAnalyzer {
  var censusMap: Map[String, IndiaStateCensusDAO] = Map()
  var censusStateMap: Map[String, IndiaStateCensusDAO] = Map()

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
   * @param censusComparator : To compare Map
   * @return : sorted values based on attribute passed
   */
  def sort(censusComparator: Comparator[IndiaStateCensusDAO]): String = {
    if (censusMap == null || censusMap.isEmpty) {
      throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.NoCensusData)
    }
    val size = censusMap.size
    val censusCSVList = censusMap.values.toArray
    for (count <- 0 until size - 1) {
      for (secondCount <- 0 until size - count - 1) {
        val census1 = censusCSVList(secondCount)
        val census2 = censusCSVList(secondCount + 1)
        if (censusComparator.compare(census1, census2) > 0) {
          censusCSVList(secondCount) = census2
          censusCSVList(secondCount + 1) = census1
        }
      }
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  // method to return state code wise sorted data
  def getStateCodeWiseSortedCensusData():String={
    for (stateNameCensus <- censusMap.keys;stateName <- censusStateMap.keys;if (stateName.equals(stateNameCensus))){
      val censusData = censusMap(stateNameCensus)
      censusData.stateCode = censusStateMap(stateName).stateCode
    }
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(censusData1: IndiaStateCensusDAO, censusData2: IndiaStateCensusDAO): Int = {
        censusData1.stateCode.compareTo(censusData2.stateCode)
      }
    }
    sort(censusComparator)
  }

  // method to return state name wise sorted data
  def getStateWiseSortedCensusData(): String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(census1: IndiaStateCensusDAO, census2: IndiaStateCensusDAO): Int = {
        census1.state.compareTo(census2.state)
      }
    }
    sort(censusComparator)
  }

  // method to return state population density wise sorted data
  def getPopulationDensityWiseSortedCensusData():String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(obj1: IndiaStateCensusDAO, obj2: IndiaStateCensusDAO): Int = {
        obj1.densityPerSqKm.compareTo(obj2.densityPerSqKm)
      }
    }
    sort(censusComparator.reversed())
  }

  // method to return state population wise sorted data
  def getPopulationWiseSortedCensusData(): String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(obj1: IndiaStateCensusDAO, obj2: IndiaStateCensusDAO): Int = {
        obj1.population.compareTo(obj2.population)
      }
    }
    sort(censusComparator.reversed())
  }

  // method to return state area wise sorted data
  def getAreaWiseSortedCensusData(): String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(obj1: IndiaStateCensusDAO, obj2: IndiaStateCensusDAO): Int = {
        obj1.areaInSqKm.compareTo(obj2.areaInSqKm)
      }
    }
    sort(censusComparator.reversed())
  }

  /**
   *
   * @param fileIterator : to iterate over file to find count
   * @tparam T : Generics
   * @return : count of rows
   */
  def getCountRows[T](fileIterator: util.Iterator[T]):Int = {
    var rowsCounted = 0
    while(fileIterator.hasNext) {
      rowsCounted += 1
      fileIterator.next()
    }
    rowsCounted
  }
}