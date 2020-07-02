package cryptobees

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom
import org.scalajs.dom.{document, window}

object Main {
  def main(args: Array[String]): Unit = {
    val lib = new CryptobeeLib
    println(lib.sq(2))

    println(s"Using Scala.js version ${System.getProperty("java.vm.version")}")
  }

  @JSExportTopLevel("sayHello")
  def sayHello(): Unit = {
    window.alert("Hello from Sjs")
  }
}
