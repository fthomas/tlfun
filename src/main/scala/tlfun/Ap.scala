package tlfun

trait Ap1[T1]

trait Ap2[T1, T2]

trait Ap3[T1, T2, T3]

object Ap1 {
  /*
    implicit def ap1Materialize[T1](ast1: AST[T1]): Materialize.Aux[] =
      Materialize.instance(AST.Apply1(ast1))
*/
}

object Ap2 {

}
