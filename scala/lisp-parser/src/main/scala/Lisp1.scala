import scala.util.parsing.combinator.JavaTokenParsers

object Lisp1 extends JavaTokenParsers {

  type Expr = Any

  def parse(line: String): List[Expr] = parseAll(program, line) match {
    case Success(r, _) => r
  }

  // grammar
  def program: Parser[List[Any]] = rep(exp)
  def list: Parser[List[Any]] = "("~>rep(exp)<~")"
  def exp: Parser[Any] = (
      integer
      | list
      | token
    )
  def integer: Parser[Long] = wholeNumber ^^ (n => n.toLong)
  def token: Parser[String] = """[^() ]+""".r ^^ (n => n.toString)

  def intepret(input: List[Expr]): Any = ???
}
