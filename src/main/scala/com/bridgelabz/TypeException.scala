package com.bridgelabz

/**
 * author - Ruchir
 * date - 04-11-2020
 * time - 23:50
 * package - com.bridgelabz
 * title - Custom exception to handle Null and Empty type
 */
object TypeException extends Enumeration {
  type TypeException = Value

  val NullType: TypeException.Value =Value("it cant be null, Please enter proper message ")
  val EmptyType: TypeException.Value =Value("it cant be empty, Please enter proper message")
}
