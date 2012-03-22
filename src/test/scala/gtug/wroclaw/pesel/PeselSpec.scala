package com.github.przemek_pokrywka.pesel

import org.specs2.mutable._

class PeselSpec extends Specification {

  "The PESEL helper" should {
    
    "recognize valid PESEL" in {
      Pesel.valid("78040105890") must beTrue
    }
    
    "treat too long PESELs as invalid" in {
      Pesel.valid("0000000000000000") must beFalse
    }
    
    "treat PESELs with non-existent dates as invalid" in {
      Pesel.valid("01047805890") must beFalse
    }
  }
}
