package tlfun.syntax

import shapeless.Witness
import tlfun.{Materialize, Tree, _}

trait DBI[I <: Int, T]

object DBI {
  type X1[T] = DBI[W.`1`.T, T]
  type X2[T] = DBI[W.`2`.T, T]
  type X3[T] = DBI[W.`3`.T, T]

  implicit def dbiMaterialize[I <: Int, T](
      implicit w: Witness.Aux[I]): Materialize.Aux[DBI[I, T], T] =
    Materialize.instance(Tree.DeBruijnIndex[T](w.value))
}
