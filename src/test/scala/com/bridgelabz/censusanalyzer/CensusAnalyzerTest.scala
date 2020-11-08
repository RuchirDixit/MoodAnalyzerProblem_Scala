package com.bridgelabz.censusanalyzer

import org.scalatest.FunSuite
import com.bridgelabz.censusanalyzer.CensusAnalyzer
import com.bridgelabz.censusanalyzer.CensusAnalyzerExceptionEnums
import com.google.gson.Gson

class CensusAnalyzerTest extends FunSuite {
  val IndiaCensusCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFilePath = "C:\\Users\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFileTypePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.pdf"
  val IndiaCensusInvalidDelimiterFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataDelimiter.csv"
  val IndiaCensusInvalidHeaderFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataInvalidField.csv"

  val IndiaStateCodeCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFilePath = "C:\\Users\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFileTypePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.pdf"
  val IndiaStateCodeInvalidCSVDelimiterFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeDelimiter.csv"
  val IndiaStateCodeInvalidCSVHeaderFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeInvalidHeader.csv"
  val CensusObj = new CensusAnalyzer()
  test("givenIndianCensusCSVFileShouldReturnCorrectRecords") {

    assert(CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectPath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectType.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusInvalidDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusInvalidHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndiaStateCodeCSVFileShouldReturnCorrectRecords") {
    assert(CensusObj.loadIndiaStateCode(IndiaStateCodeCSVFilePath) == 37)
  }
  test("givenIndianStateCodeCSVFileWhenWithWrongPathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectPath.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectType.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeInvalidCSVDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }
  test("givenIndianStateCodeCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeInvalidCSVHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }
  test("givenIndianCensusDataWhenSortedByStateShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Andhra Pradesh")
    assert(censusCSV.last.state === "West Bengal")
  }

  test("givenIndianCensusDataWhenEmptyDataShouldReturnException"){
    val objCensus = new CensusAnalyzer()
    val throws = intercept[Exception]{
      objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusDataAndStateDateWhenSortedByStateShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    CensusObj.loadIndiaStateCode(IndiaStateCodeCSVFilePath)

    val sortedCensusData = CensusObj.getStateCodeWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Andhra Pradesh")
    assert(censusCSV.last.state === "West Bengal")
  }
  test("givenIndianStateDataWhenEmptyDataShouldReturnException"){
    val objCensus = new CensusAnalyzer()
    val throws = intercept[Exception]{
      objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }
}