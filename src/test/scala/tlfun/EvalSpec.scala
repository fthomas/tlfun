package tlfun

import org.scalacheck.Prop._
import org.scalacheck.Properties
import tlfun.Tree._

object EvalSpec extends Properties("Tree.eval") {

  property("Literal") = secure {
    eval(Literal(42)) ?= 42
  }
}
