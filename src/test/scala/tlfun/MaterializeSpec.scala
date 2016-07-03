package tlfun

import org.scalacheck.Prop._
import org.scalacheck.Properties
import tlfun.Tree._
import tlfun.prelude.numeric.{Abs, Plus}
import tlfun.syntax.DBI.{X1, X2}
import tlfun.syntax.{App, Lam}

object MaterializeSpec extends Properties("Materialize") {

  property("Literal") = secure {
    Materialize[W.`"abc"`.T].toTree ?= Literal("abc")
  }

  property("DeBruijnIndex") = secure {
    Materialize[X1[String]].toTree ?= DeBruijnIndex[String](1)
  }

  property("x => 0") = secure {
    Materialize[Lam[W.`0`.T]].toTree ?= Lambda(Literal(0))
  }

  property("(x: Int) => x") = secure {
    Materialize[Lam[X1[Int]]].toTree ?= Lambda(DeBruijnIndex[Int](1))
  }

  property("(x: Int) => y => x") = secure {
    Materialize[Lam[Lam[X2[Int]]]].toTree ?=
    Lambda(Lambda(DeBruijnIndex[Int](2)))
  }

  property("((x: Int) => x)(1)") = secure {
    Materialize[App[Lam[X1[Int]], W.`1`.T]].toTree ?=
    Application(Lambda(DeBruijnIndex[Int](1)), Literal(1))
  }

  property("((x: Int) => abs(x))(-1)") = secure {
    Materialize[App[Lam[Abs[X1[Int]]], W.`-1`.T]].toTree match {
      case Application(Lambda(Apply1(DeBruijnIndex(1), f)), Literal(-1)) =>
        true
      case _ => false
    }
  }

  property("abs(-1)") = secure {
    Materialize[Abs[W.`-1`.T]].toTree match {
      case Apply1(Literal(x), f) => f(x) == 1
      case _ => false
    }
  }

  property("(x: Int) => (y: Int) => x + y") = secure {
    Materialize[Lam[Lam[Plus[X2[Int], X1[Int]]]]].toTree match {
      case Lambda(Lambda(Apply2(DeBruijnIndex(2), DeBruijnIndex(1), f))) =>
        true
      case _ => false
    }
  }
}
