package tlfun

import shapeless.Witness

trait Materialize[T] {
  type R
  def toTree: Tree[R]
}

object Materialize {
  type Aux[T, R0] = Materialize[T] { type R = R0 }

  def apply[T](implicit m: Materialize[T]): Materialize.Aux[T, m.R] = m

  def instance[T, R0](ast: Tree[R0]): Materialize.Aux[T, R0] =
    new Materialize[T] {
      override type R = R0
      override def toTree: Tree[R] = ast
    }

  implicit def witnessMaterialize[T, S <: T](
      implicit w: Witness.Aux[S]): Materialize.Aux[S, T] =
    instance(Tree.Literal(w.value))
}
