package com.bridgelabz.censusanalyzer
class IndiaStateCensusDAO {
  var state:String = _
  var population = 0
  var areaInSqKm = 0
  var densityPerSqKm = 0
  var stateCode:String = _

  def this(indiaCensusCSV: IndiaStateCensus){
    this()
    state=indiaCensusCSV.state
    areaInSqKm=indiaCensusCSV.areaInSqKm
    densityPerSqKm=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }

  def this(indiaStateCodeCSV: StateCode){
    this()
    state=indiaStateCodeCSV.state
    stateCode= indiaStateCodeCSV.stateCode
  }
}
