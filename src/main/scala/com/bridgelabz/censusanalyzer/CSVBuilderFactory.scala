package com.bridgelabz.censusanalyzer

object CSVBuilderFactory {
  def createCSVBuilder(): CSVBuilderTrait = {
    new OpenCSVBuilder()
  }
}
