package tlfun

import org.scalacheck.Prop._
import org.scalacheck.Properties
import tlfun.AST._
import tlfun.DBI.{X1, X2}
import tlfun.prelude.numeric.Abs

object MaterializeSpec extends Properties("Materialize") {

  property("Literal") = secure {
    Materialize[W.`"abc"`.T].toAST ?= Literal("abc")
  }

  property("DeBruijnIndex") = secure {
    Materialize[X1[String]].toAST ?= DeBruijnIndex[String](1)
  }

  property("Lambda x => 0") = secure {
    Materialize[Lam[W.`0`.T]].toAST ?= Lambda(Literal(0))
  }

  property("Lambda (x: Int) => x") = secure {
    Materialize[Lam[X1[Int]]].toAST ?= Lambda(DeBruijnIndex[Int](1))
  }

  property("Lambda (x: Int) => (y: Int) => x") = secure {
    Materialize[Lam[Lam[X2[Int]]]].toAST ?= Lambda(
        Lambda(DeBruijnIndex[Int](2)))
  }

  property("Application ((x: Int) => x)(1)") = secure {
    Materialize[App[Lam[X1[Int]], W.`1`.T]].toAST ?= Application(
        Lambda(DeBruijnIndex[Int](1)),
        Literal(1))
  }

  property("Apply1 abs(-1)") = secure {
    Materialize[Abs[W.`-1`.T]].toAST match {
      case Apply1(Literal(x), f) => f(x) == 1
      case _ => false
    }
  }
}
