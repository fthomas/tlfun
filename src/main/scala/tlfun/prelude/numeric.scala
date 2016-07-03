package tlfun.prelude

import tlfun.{Materialize, Tree}

object numeric {
  trait Abs[T1]

  object Abs {
    implicit def absMaterialize[T1, R](
        implicit m: Materialize.Aux[T1, R],
        n: Numeric[R]): Materialize.Aux[Abs[T1], R] =
      Materialize.instance(Tree.Apply1(m.toTree, n.abs))
  }

  trait Plus[T1, T2]

  object Plus {
    implicit def plusMaterialize[T1, T2, R](
        implicit m1: Materialize.Aux[T1, R],
        m2: Materialize.Aux[T2, R],
        n: Numeric[R]): Materialize.Aux[Plus[T1, T2], R] =
      Materialize.instance(Tree.Apply2(m1.toTree, m2.toTree, n.plus))
  }
}
