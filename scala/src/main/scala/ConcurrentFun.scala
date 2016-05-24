import scala.io.Source
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.concurrent.Await
import scala.concurrent.duration._


object ConcurrentFun {

  implicit val ec = scala.concurrent.ExecutionContext.Implicits.global

  def readFileConcurrent(fn: String)(implicit ec: ExecutionContext): Future[String] = {
    val fc = Source.fromFile(fn).getLines.mkString
    Future(fc)
  }

  def doFactorialCalc(num: Int)(implicit ec: ExecutionContext): Future[BigInt] = {
    val fac = (BigInt(1) to BigInt(num)).product
    Future(fac)
  }

  def readFileAndDoFac(fn: String, num: Int)(implicit ec: ExecutionContext): (String, BigInt) = {
    val agg = for {
      f1 <- readFileConcurrent(fn)
      f2 <- doFactorialCalc(num)
    } yield (f1 , f2)

    Await.result(agg, 4 seconds)
  }

}


