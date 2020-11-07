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

  val IndiaStateCodeCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.csv"
  val WrongCSVFilePathStateCode = "C:\\Users\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.csv"
  val WrongStateCSVFileType = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.pdf"
  val InvalidStateDelimiter = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeDelimiter.csv"
  val InvalidHeaderFilePathState = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeInvalidHeader.csv"
  val CensusAnalyzerObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectRecords"){
    assert(CensusAnalyzerObj.loadIndiaCensusData(IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndiaStateCensusInputFilePathIsWrongShouldReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaCensusData(WrongCSVFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectPath.toString)
  }

  test("givenIndiaStateCensusInputFileTypeWhenWrongShouldReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaCensusData(WrongCSVFileTypePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectFile.toString)
  }

  test("givenIndiaStateCensusInputFileDelimiterWhenWrongShouldReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaCensusData(InvalidDelimiterFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.UnableToParse.toString)
  }

  test("givenIndiaStateCensusInputFileFieldsWhenWrongShouldReturnIncorrectFieldsException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaCensusData(InvalidHeaderFilePath)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.UnableToParse.toString)
  }

  test("givenIndiaStateCodeMatchingNumberOfRowsInputCSVFileWithRightPathShouldReturnNumberOfRows"){
    assert(CensusAnalyzerObj.loadIndiaStateCode(IndiaStateCodeCSVFilePath) === 37)
  }

  test("givenInputFilePathIsWhenWrongShouldReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaStateCode(WrongCSVFilePathStateCode)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.UnableToParse.toString)
  }

  test("givenInputFileTypeWhenWrongShouldReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaStateCode(WrongStateCSVFileType)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.InCorrectFile.toString)
  }

  test("givenInputFileDelimiterWhenWrongShouldReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaStateCode(InvalidStateDelimiter)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.UnableToParse.toString)
  }

  test("givenInputFileFieldsWhenWrongShouldReturnIncorrectFieldsException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadIndiaStateCode(InvalidHeaderFilePathState)
    }
    assert(thrown.getMessage === CensusAnalyzerExceptionEnum.UnableToParse.toString)
  }



}