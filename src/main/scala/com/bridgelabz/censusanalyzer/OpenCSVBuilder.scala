package com.bridgelabz.censusanalyzer
import java.io.Reader
import java.util
import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}
import java.io.Reader
import java.util
import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}

class OpenCSVBuilder[A] extends CSVBuilderTrait {
  /**
   *
   * @param reader : to read file passed
   * @param csvClass : India Census or India State code file
   * @tparam A : Generics
   * @throws ; Csv Builder exception
   * @return : Csv file iterator
   */
  @throws[CSVBuilderException]
  def getCSVFileIterator[A](reader: Reader, csvClass: Class[A]): util.Iterator[A] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.iterator()
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }

  /**
   *
   * @param reader : to read file passed
   * @param csvClass : India Census or India State code file
   * @tparam A : Generics
   * @return : Csv file list
   */
  def getCSVFileList[A](reader: Reader, csvClass: Class[A]): util.List[A] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.parse()
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }

  /**
   *
   * @param reader : to read file passed
   * @param csvClass : India Census or India State code file
   * @tparam A : Generics
   * @return : Csv to bean
   */
  def getCSVBean[A](reader: Reader, csvClass: Class[A]): CsvToBean[A] = {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[A](reader)
      csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }
}