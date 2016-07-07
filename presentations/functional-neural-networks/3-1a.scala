/**
 * Version 3:
 * MapAccum in practice
 */

// as: the iterable we'd like to traverse
// c: base value
// f: next, acc -> last, acc
// returns: (output acc, transformed iterable)
final def mapAccumLeft[A, B, C](as: Seq[A])(c: C, f: (C, A) => (C, B)): (C, Seq[B])

// Seq(2,4,6) -> Seq((1,2),(2,4),(3,6))
final def zipWithIndex[A](as: Seq[A]): Seq[(Int, A)] =
  mapAccumLeft(as)(0, (i: Int, a: A) => (i + 1, (i: Int, a: A)))._2

