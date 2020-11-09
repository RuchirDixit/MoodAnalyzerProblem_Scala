package com.bridgelabz.censusanalyzer
import java.io.Reader
import java.util

@throws[CSVBuilderException]
trait CSVBuilderTrait {
  def getCSVFileIterator[A] (reader:Reader,csvClass:Class[A]): util.Iterator[A]
  def getCSVFileList[A] (reader:Reader,csvClass:Class[A]): util.List[A]
}