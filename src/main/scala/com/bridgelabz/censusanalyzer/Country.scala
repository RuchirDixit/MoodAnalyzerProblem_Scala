package com.bridgelabz.censusanalyzer
/***
 * Country class Which provides Enum type for CensusAnalyzer
 */
object Country extends Enumeration {
  type Country = Value
  val India: Country.Value = Value("India")
  val USA: Country.Value = Value("USA")
}
