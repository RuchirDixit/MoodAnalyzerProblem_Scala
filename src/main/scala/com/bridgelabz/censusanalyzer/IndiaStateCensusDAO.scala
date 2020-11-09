package com.bridgelabz.censusanalyzer
class IndiaStateCensusDAO {
  var state:String = _
  var population = 0
  var areaInSqKm = 0
  var densityPerSqKm = 0
  var stateCode:String = _

  /**
   *
   * @param indiaCensusCSV : Constructor for India State Census
   */
  def this(indiaCensusCSV: IndiaStateCensus){
    this()
    state=indiaCensusCSV.state
    areaInSqKm=indiaCensusCSV.areaInSqKm
    densityPerSqKm=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }

  /**
   *
   * @param indiaStateCodeCSV : Constructor for India State Code
   */
  def this(indiaStateCodeCSV: StateCode){
    this()
    state=indiaStateCodeCSV.stateName
    stateCode= indiaStateCodeCSV.stateCode
  }
}
