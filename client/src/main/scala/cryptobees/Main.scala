package cryptobees

import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport._

object Main {
  def main(args: Array[String]): Unit = {
    val lib = new CryptobeeLib
    println(lib.sq(2))

    println(s"Using Scala.js version ${System.getProperty("java.vm.version")}")

    D3.select("body").append("h1").text("yo")
  }

  @JSExportTopLevel("sayHello")
  def sayHello(): Unit = {
    window.alert("Hello from Sjs")
  }
}

@JSImport("d3", Namespace)
@js.native
object D3 extends js.Object {
  def select(selector: String): js.Dynamic = js.native
}

// 'private' not to pollute the Scala API with this object
private object Reexports {
  @JSExportTopLevel("d3") // or another name
  val d3 = D3
}