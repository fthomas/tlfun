package tlfun

sealed trait Tree[+T] extends Product with Serializable

object Tree {
  case class Literal[R](t: R) extends Tree[R]
  case class DeBruijnIndex[R](i: Int) extends Tree[R] {
    type Out = R
  }
  case class Lambda[T](tree: Tree[T]) extends Tree[T]
  case class Application[A, B](lam: Tree[A], arg: Tree[B]) extends Tree[A]
  case class Apply1[T1, R](t1: Tree[T1], f: T1 => R) extends Tree[R]
  case class Apply2[T1, T2, R](t1: Tree[T1], t2: Tree[T2], f: (T1, T2) => R)
      extends Tree[R]

  def replaceDeBruijnIndex[T, V](tree: Tree[T], value: Any): Tree[Any] = {
    def go(tree: Tree[Any], depth: Int): Tree[Any] =
      tree match {
        case DeBruijnIndex(i) if i == depth => Literal(value)
        case Lambda(subtree) => go(subtree, depth + 1)
        case Application(s1, s2) => Application(go(s1, depth), go(s2, depth))
        case Apply1(t1, f) => Apply1(go(t1, depth), f)
        case Apply2(t1, t2, f) => Apply2(go(t1, depth), go(t2, depth), f)
        case x => x
      }
    go(tree, 0)
  }

  def eval[T](tree: Tree[T]): Any =
    tree match {
      case Literal(t) => t
      case Lambda(t) =>
        val x = findIndex(tree).get
        (r: x.Out) =>
          eval(replaceDeBruijnIndex(tree, r))
        case Apply1(t1, f) => f(eval(t1).asInstanceOf[T])
      case _ => ???
    }

  def findIndex[A, B](lambda: Tree[A]): Option[DeBruijnIndex[Any]] = {
    def go(tree: Tree[Any], depth: Int): Option[DeBruijnIndex[Any]] =
      tree match {
        case index @ DeBruijnIndex(i) if i == depth => Some(index)
        case Lambda(t) => go(t, depth + 1)
        case Application(t1, t2) => go(t1, depth).orElse(go(t2, depth))
        case Apply1(t1, _) => go(t1, depth)
        case _ => ???
      }
    go(lambda, 0)
  }

}
