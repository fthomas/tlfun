package tlfun

import shapeless.Witness

trait DBI[I, T]

object DBI {
  type X1[T] = DBI[W.`1`.T, T]
  type X2[T] = DBI[W.`2`.T, T]
  type X3[T] = DBI[W.`3`.T, T]

  implicit def dbiMaterialize[I <: Int, T](implicit w: Witness.Aux[I]): Materialize.Aux[DBI[I, T], T] =
    Materialize.instance(AST.DeBruijnIndex[T](w.value))
}
