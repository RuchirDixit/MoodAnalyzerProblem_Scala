import com.bridgelabz.censusanalyzer.{CensusAnalyzer, CensusAnalyzerExceptionEnums, IndiaStateCensus}
import com.google.gson.Gson
import org.scalatest.FunSuite

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
  val CensusObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectRecords") {

    assert(CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
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
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
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

  test("givenIndianCensusDataAndStateDateWhenSortedByPopulationDensityShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationDensityWiseSortedCensusData()
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Bihar")
    assert(censusCSV.last.state === "Arunachal Pradesh")
  }
}