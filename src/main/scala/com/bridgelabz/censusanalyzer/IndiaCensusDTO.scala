package com.bridgelabz.censusanalyzer
import com.opencsv.bean.CsvBindByName

class IndiaCensusDTO() {

  @CsvBindByName(column = "State", required = true)
  var state: String = _
  @CsvBindByName(column = "Population", required = true)
  var population: Int = 0
  @CsvBindByName(column = "AreaInSqKm", required = true)
  var areaInSqKm: Int = 0
  @CsvBindByName(column = "DensityPerSqKm", required = true)
  var densityPerSqKm: Int = 0

  override def toString: String = "IndiaCensusCSV{" +
    "State='" + state + '\'' +
    ", Population='" + population + '\'' +
    ", AreaInSqKm='" + areaInSqKm + '\'' +
    ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}'
}
