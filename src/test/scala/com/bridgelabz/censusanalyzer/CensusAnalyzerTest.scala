package com.bridgelabz.censusanalyzer

import org.scalatest.FunSuite
import com.bridgelabz.censusanalyzer.CensusAnalyzer
import com.bridgelabz.censusanalyzer.CensusAnalyzerExceptionEnum

class CensusAnalyzerTest extends FunSuite {
  val IndiaCensusCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.csv"
  val WrongCSVFilePath = "C:\\Users\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.csv"
  val WrongCSVFileTypePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.pdf"
  val InvalidDelimiterFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataDelimiter.csv"
  val InvalidHeaderFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataInvalidField.csv"
  val CensusAnalyzerObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectRecords"){
    assert(CensusAnalyzerObj.loadCSVInfo(IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVInfo(WrongCSVFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectPath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVInfo(WrongCSVFileTypePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectFile.toString)
  }

  test("IfFileDelimiterWrong_ReturnsIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVInfo(InvalidDelimiterFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectDelimiter.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVInfo(InvalidHeaderFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectFields.toString)
  }

}