package com.bridgelabz.censusanalyzer
import com.opencsv.bean.CsvBindByName

class USCensusDTO {
  @CsvBindByName(required = true,column = "State Id")
  var stateId:String = _

  @CsvBindByName(required = true,column = "State")
  var state:String = _

  @CsvBindByName(required = true,column = "Population")
  var population:Double = 0

  @CsvBindByName(required = true,column = "Housing units")
  var housingUnits:Double = 0

  @CsvBindByName(required = true,column = "Total area")
  var totalArea:Double = 0

  @CsvBindByName(required = true,column = "Water area")
  var waterArea:Double = 0

  @CsvBindByName(required = true,column = "Land area")
  var landArea:Double = 0

  @CsvBindByName(required = true,column = "Population Density")
  var populationDensity:Double = 0

  @CsvBindByName(required = true,column = "Housing Density")
  var housingDensity:Double = 0

  override def toString: String = "USCensusCSV{" +
    "StateID= " + stateId + "\n" +
    "State= " + state + "\n" +
    ", Population='" + population + "\n" +
    ", Housing units='" + housingUnits + "\n" +
    ", Water area='" + waterArea + "\n" +
    ", Land area='" + landArea + "\n" +
    ", Population Density='" + populationDensity + "\n" +
    ", Housing Density='" + housingDensity + "\n"
  + '}';
}
