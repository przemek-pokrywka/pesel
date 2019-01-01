package com.github.przemek_pokrywka.pesel

import java.time.LocalDate

class Pesel private (pesel: String) {

  lazy val peselDigits = pesel.toList.map(_.asDigit)
  lazy val gender = if (peselDigits(9) % 2 == 0) "F" else "M"

}

object Pesel {
  
  def hasValidCheckSum(pesel: String): Boolean = {
    val factors = List(1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
    val peselDigits = pesel.map(_.asDigit)
    val pairs = factors.zip(peselDigits)
    
    val products = pairs map { case (factor, digit) => factor * digit }
    
    (products.sum + peselDigits.last) % 10 == 0
  }
  
  private def hasValidDate(pesel: String) = {
    val encodedDate = pesel.take(6)
    val dateParts = yyMmDd(encodedDate).map(_.toInt)
    val year :: mm :: day :: Nil = dateParts
    val (century, month) = centuryAndMonth(mm)
    dateExists(century + year, month, day)
  }

  private def centuryAndMonth(mm: Int): (Int, Int) = {
    val century = mm / 20 match {
      case 0 => 1900
      case 1 => 2000
      case 2 => 2100
      case 3 => 2200
      case 4 => 1800
    }
    val month = mm % 20
    (century, month)
  }
  
  private def dateExists(year: Int, month: Int, day: Int) =
    try {
      date(year, month, day)
      true
    } catch {
      case _: Throwable => false
    }

  private def date(year: Int, month: Int, day: Int): LocalDate = LocalDate.of(year, month, day)

  private def yyMmDd(encodedDate: String) = encodedDate.sliding(2, 2).toList

  def valid(pesel: String): Boolean = {
    pesel.length == 11 &&
    pesel.forall(_.isDigit) &&
    hasValidCheckSum(pesel) &&
    hasValidDate(pesel)
  }

  def apply(pesel: String): Pesel = {
    require(valid(pesel))
    new Pesel(pesel)
  }

  def apply(pesel: Long): Pesel = {
    require(valid(pesel.toString))
    new Pesel(pesel.toString)
  }

  def unapply(pesel: String): Option[Pesel] = if (valid(pesel)) Some(Pesel(pesel)) else None

}
