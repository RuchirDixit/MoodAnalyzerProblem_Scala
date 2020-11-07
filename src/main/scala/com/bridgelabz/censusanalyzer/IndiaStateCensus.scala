package com.bridgelabz.censusanalyzer
import com.opencsv.bean.CsvBindByName

case class IndiaStateCensus() {
  @CsvBindByName(column = "State", required = true) var state: String = _
  @CsvBindByName(column = "Population", required = true) var population = 0
  @CsvBindByName(column = "AreaInSqKm", required = true) var areaInSqKm = 0
  @CsvBindByName(column = "DensityPerSqKm", required = true) var densityPerSqKm = 0

  override def toString: String = "IndiaStateCensus" + "state='" + state + '\'' + ", population=" + population + ", areaInSqKm=" + areaInSqKm + ", densityPerSqKm=" + densityPerSqKm + '}'
}