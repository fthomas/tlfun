package tlfun

trait App[S <: Lam[_], T]

object App {
  implicit def materializeApp[S <: Lam[_], T](
      implicit ms: Materialize[S],
      mt: Materialize[T]): Materialize.Aux[App[S, T], ms.R] =
    Materialize.instance(AST.Application(ms.toAST, mt.toAST))
}
