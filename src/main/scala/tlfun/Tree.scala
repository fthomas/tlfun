package tlfun

sealed trait Tree[T] extends Product with Serializable

object Tree {
  case class Literal[T](t: T) extends Tree[T]
  case class DeBruijnIndex[T](i: Int) extends Tree[T]
  case class Lambda[T](tree: Tree[T]) extends Tree[T]
  case class Application[S, T](lam: Tree[S], arg: Tree[T]) extends Tree[S]
  case class Apply1[T1, R](t1: Tree[T1], f: T1 => R) extends Tree[R]
  case class Apply2[T1, T2, R](t1: Tree[T1], t2: Tree[T2], f: (T1, T2) => R)
      extends Tree[R]
}
