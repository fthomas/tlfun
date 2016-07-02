package tlfun

trait Lam[T]

object Lam {
  implicit def materializeLam[T](
      implicit m: Materialize[T]): Materialize.Aux[Lam[T], m.R] =
    Materialize.instance(AST.Lambda(m.toAST))
}
