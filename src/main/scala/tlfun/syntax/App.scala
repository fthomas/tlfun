package tlfun.syntax

import tlfun.{Materialize, Tree}

trait App[A <: Lam[_], B]

object App {
  implicit def materializeApp[A <: Lam[_], B](
      implicit ma: Materialize[A],
      mb: Materialize[B]): Materialize.Aux[App[A, B], ma.R] =
    Materialize.instance(Tree.Application(ma.toTree, mb.toTree))
}
