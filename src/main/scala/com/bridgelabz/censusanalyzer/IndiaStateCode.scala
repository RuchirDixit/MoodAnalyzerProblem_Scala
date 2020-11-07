package com.bridgelabz.censusanalyzer
import com.opencsv.bean.CsvBindByName

case class StateCode() {
  @CsvBindByName(column = "SrNo", required = true) var SrNo: String = _
  @CsvBindByName(column = "State Name", required = true) var state: String = _
  @CsvBindByName(column = "TIN", required = true) var TIN: Int = 0
  @CsvBindByName(column = "StateCode", required = true) var stateCode: String = _

  override def toString: String = "StateCode{" + "SrNo='" + SrNo + '\''+ ", state='" + state + '\'' + ", TIN='" + TIN + '\'' + ", stateCode='" + stateCode + '\'' + '}'
}