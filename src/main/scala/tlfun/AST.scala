package tlfun

sealed trait AST[T]

object AST {
  case class Literal[T](t: T) extends AST[T]
  case class DeBruijnIndex[T](i: Int) extends AST[T]
  case class Lambda[T](ast: AST[T]) extends AST[T]
  case class Apply1[T1, R](t1: AST[T1], f: T1 => R) extends AST[R]
  case class Apply2[T1, T2, R](t1: AST[T1], t2: AST[T2], f: (T1, T2) => R) extends AST[R]
}
