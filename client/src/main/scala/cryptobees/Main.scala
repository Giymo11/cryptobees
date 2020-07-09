package cryptobees

import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.{console, document, window}

import scala.scalajs.js

object Main {
  def main(args: Array[String]): Unit = {
    val lib = new CryptobeeLib
    println(lib.sq(2))
    println(s"Using Scala.js version ${System.getProperty("java.vm.version")}")
    D3.select("body").append("h1").text("Hello World")
  }

  @JSExportTopLevel("sayHello")
  def sayHello(): Unit = {
    D3.select("body").append("h1").text("Say World")
  }
}

