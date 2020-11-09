import com.bridgelabz.censusanalyzer.{CensusAnalyzer, CensusAnalyzerExceptionEnums, Country, IndiaCensusDTO}
import com.google.gson.Gson
import org.scalatest.FunSuite
/***
 * Test Use case Class which extends FunSuite scala test class
 * Added ScalaTest dependency in Build.sbt
 * Uses Gson and CSV dependencies
 */
class CensusAnalyzerTest extends FunSuite {

  val IndiaCensusCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFilePath = "resources\\IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFileTypePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusData.txt"
  val IndiaCensusInvalidDelimiterFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataDelimiter.csv"
  val IndiaCensusInvalidHeaderFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCensusDataInvalidField.csv"
  val IndiaStateCodeCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFilePath = "resources\\IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFileTypePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCode.txt"
  val IndiaStateCodeInvalidCSVDelimiterFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeDelimiter.csv"
  val IndiaStateCodeInvalidCSVHeaderFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\IndiaStateCodeInvalidHeader.csv"
  val USCensusCSVFilePath = "C:\\Users\\Admin\\IdeaProjects\\HelloScala\\src\\resources\\USCensusData.csv"
  val CensusObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileWhenCorrectShouldReturnCorrectRecords") {
    assert(CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(Country.India,IndiaCensusWrongCSVFilePath,IndiaCensusWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(Country.India,IndiaCensusWrongCSVFileTypePath,IndiaCensusWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(Country.India,IndiaCensusInvalidDelimiterFilePath,IndiaCensusInvalidDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(Country.India,IndiaCensusInvalidHeaderFilePath,IndiaCensusInvalidHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusData_whenSortedByState_shouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("givenIndianCensusDataWhenEmptyDataShouldReturnException") {
    val CensusObj1 = new CensusAnalyzer()
    val throws = intercept[Exception] {
      CensusObj1.getStateCodeWiseSortedCensusData
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusDataAndStateDataWhenSortedByStateShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath)
    val sortedCensusData = CensusObj.getStateCodeWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Andhra Pradesh")
    assert(censusCSV.last.state === "West Bengal")
  }

  test("givenIndianStateDataWhenEmptyDataShouldReturnException") {
    val CensusObj1 = new CensusAnalyzer()
    val throws = intercept[Exception] {
      CensusObj1.getStateCodeWiseSortedCensusData
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusDataAndStateDataWhenSortedByPopulationDensityShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationDensityWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Bihar")
    assert(censusCSV.last.state === "Arunachal Pradesh")
  }

  test("givenIndianCensusDataAndStateDataWhenSortedByPopulationShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Uttar Pradesh")
    assert(censusCSV.last.state === "Sikkim")
  }

  test("givenIndianStateDataWhenEmptyDataPopulationShouldReturnException") {
    val CensusObj1 = new CensusAnalyzer()
    val throws = intercept[Exception] {
      CensusObj1.getPopulationWiseSortedCensusData
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusDataAndStateDataWhenSortedByAreaShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.India,IndiaCensusCSVFilePath,IndiaStateCodeCSVFilePath)
    val sortedCensusData = CensusObj.getAreaWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Rajasthan")
    assert(censusCSV.last.state === "Goa")
  }

  test("givenIndianStateDataWhenEmptyDataAreaShouldReturnException") {
    val CensusObj1 = new CensusAnalyzer()
    val throws = intercept[Exception] {
      CensusObj1.getAreaWiseSortedCensusData
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenUSCensusCSVFileShouldReturnCorrectRecords"){
    assert(CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath) == 51)
  }

  test("givenUSCensusDataWhenSortedByPopulationShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "California")
    assert(censusCSV.last.state === "Wyoming")
  }
  test("givenUSCensusDataWhenSortedByAreaShouldReturnSortedResult"){
    CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getAreaWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alaska")
    assert(censusCSV.last.state === "District of Columbia")
  }

  test("givenUSCensusDataWhenSortedByPopulationDensityShouldReturnSortedResult"){
    CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationDensityWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "District of Columbia")
    assert(censusCSV.last.state === "Alaska")
  }

  test("givenUSCensusDataWhenSortedByStateWiseSortedShouldReturnSortedResult"){
    CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alabama")
    assert(censusCSV.last.state === "Wyoming")
  }

  test("givenUSCensusDataWhenSortedByStateCodeWiseSortedShouldReturnSortedResult"){
    CensusObj.loadCensusData(Country.USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateCodeWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alaska")
    assert(censusCSV.last.state === "Wyoming")
  }
  test("givenIndiaCensus&USCensusDataWhenSortedByPopulationDensityShouldReturnSortedResult") {
    CensusObj.loadCensusData(Country.USA, USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationDensityWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "District of Columbia")

    CensusObj.loadCensusData(Country.India, IndiaCensusCSVFilePath, IndiaStateCodeCSVFilePath)
    val sortedCensusDataIndia = CensusObj.getPopulationDensityWiseSortedCensusData
    val censusCSVIndia = new Gson().fromJson(sortedCensusDataIndia, classOf[Array[IndiaCensusDTO]])
    assert(censusCSVIndia(0).state === "Bihar")
  }
}