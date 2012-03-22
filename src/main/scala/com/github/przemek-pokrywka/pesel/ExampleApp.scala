package com.github.przemek_pokrywka.pesel

object ExampleApp extends App {

  val data =
    """	Przemek,Pokrywka,78040105890
	  	Antoni,Kowalski,80010124215
	  	Jerzy,Nowak,81100890418			"""
  
  val trimmedLines = data.lines.map(_.trim)

  // printing valid PESELs
  trimmedLines.map(_.split(",")).foreach {
    
    case Array(firstName, lastName, pesel) => 
      println("%s %s %s - valid? %s".format(
        firstName, lastName, pesel, Pesel.valid(pesel)))

    case x => println(x)
  }

}
