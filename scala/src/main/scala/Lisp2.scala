class Lisp2 {

  type Expr = Any


  /*
   * Input: (first (list 1 (+ 2 3) 9))
   * Tokens: ["(", "first", "(", "list", "1", "(", "+", 2, 3, ")", 9, ")", ")"]
   * Output: ["first", ["list", 1, ["+", 2, 3], 9]].
   *
   * Input: (begin (define r 10) (* pi (* r r)))
   * Tokens: ['(', 'begin', '(', 'define', 'r', '10', ')', '(', '*', 'pi', '(', '*', 'r', 'r', ')', ')', ')']
   * Output: ['begin', ['define', 'r', 10], ['*', 'pi', ['*', 'r', 'r']]]
   */
  def parse(tokens: List[String]): Any = {

    val res = List.empty

    def helper(tokens: List[String]) = tokens match {
      case x :: xs if x == "(" =>
        List(parse(xs))
      case x :: xs if x == ")" =>
        //      val expr
        Unit
      case x :: xs =>
        x
    }

    helper(tokens)
  }

}
