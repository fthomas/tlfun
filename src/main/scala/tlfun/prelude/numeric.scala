package tlfun.prelude

import tlfun.{ AST, Ap1, Ap2, Materialize }

object numeric {
  trait Abs[X] extends Ap1[X]

  object Abs {
    implicit def absMaterialize[T1, R](implicit m: Materialize.Aux[T1, R], n: Numeric[R]): Materialize.Aux[Abs[T1], R] =
      Materialize.instance(AST.Apply1(m.toAST, n.abs))
  }
}