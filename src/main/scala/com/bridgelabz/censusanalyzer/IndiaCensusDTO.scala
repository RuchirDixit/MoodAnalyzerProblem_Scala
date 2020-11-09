package com.bridgelabz.censusanalyzer
import com.opencsv.bean.CsvBindByName
/***
 * class Binds Data to form Json format
 * Uses OpenCSV dependency which is added to build.sbt
 */
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
