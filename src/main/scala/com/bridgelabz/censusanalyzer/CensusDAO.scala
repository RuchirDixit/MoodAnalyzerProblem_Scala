package com.bridgelabz.censusanalyzer
class CensusDAO {
  var state:String = _
  var population:Double = 0
  var totalArea :Double= 0
  var populationDensity :Double= 0
  var stateCode:String = _
  var housingUnits:Double = 0
  var waterArea:Double = 0
  var landArea:Double = 0
  var housingDensity:Double = 0

  /**
   *
   * @param indiaCensus : Constructor for India State Census
   */
  def this(indiaCensus: IndiaStateCensus){
    this()
    state = indiaCensus.state
    totalArea = indiaCensus.areaInSqKm
    populationDensity = indiaCensus.densityPerSqKm
    population = indiaCensus.population
  }

  /**
   *
   * @param stateCensusCode : Constructor for India State Census
   */
  def this(stateCensusCode: StateCode){
    this()
    state = stateCensusCode.stateName
    stateCode = stateCensusCode.stateCode
  }

  /**
   *
   * @param usCensusCSV : Constructor for US State Census
   */
  def this(usCensusCSV:USCensusDTO){
    this()
    stateCode = usCensusCSV.stateId
    state = usCensusCSV.state
    totalArea = usCensusCSV.totalArea
    populationDensity = usCensusCSV.populationDensity
    population = usCensusCSV.population
    housingDensity = usCensusCSV.housingDensity
    waterArea = usCensusCSV.waterArea
    landArea = usCensusCSV.landArea
    housingUnits=usCensusCSV.housingUnits
  }
}
