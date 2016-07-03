package tlfun.syntax

import tlfun.{Materialize, Tree}

trait Lam[T]

object Lam {
  implicit def materializeLam[T](
      implicit m: Materialize[T]): Materialize.Aux[Lam[T], m.R] =
    Materialize.instance(Tree.Lambda(m.toTree))
}
