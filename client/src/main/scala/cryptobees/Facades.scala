package cryptobees

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSImport("d3", JSImport.Namespace)
@js.native
object D3 extends js.Object {
  def select(selector: String): js.Dynamic = js.native
}

// 'private' not to pollute the Scala API with this object
private object Reexports {
  @JSExportTopLevel("d3") // or another name
  val d3 = D3
}

