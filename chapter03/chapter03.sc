/**
  * Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
  */
def getArray(n: Int): Array[Int] = {
  val a = new Array[Int](n);
  val r = scala.util.Random
  for (i <- 0 until n) {
    a(i) = r.nextInt(n)
  }
  a
}

getArray(100)

/**
  * Write a loop that swaps adjacent elements of an array of integers. For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
  */
def swapArrayElements[T](a: Array[T]): Array[T] = {
  for (i <- 1 until a.length by 2) {
    val e = a(i)
    a(i) = a(i - 1)
    a(i - 1) = e
  }
  a
}

swapArrayElements(Array[Int]())
swapArrayElements(Array(1, 2, 3))
swapArrayElements(Array("a", "bc", "def"))

/**
  * Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield.
  */
def swapArrayElements[T](a: Array[T]): Seq[T] = {
  val res = for (i <- 0 until a.length)
    yield if (i % 2 == 1) a(i - 1) else if (i == a.length -1) a(i) else a(i + 1)
  res
}

swapArrayElements(Array[Int]())
swapArrayElements(Array(1, 2, 3, 4, 5))
swapArrayElements(Array("a", "bc", "def", "ghij"))

/**
  * Given an array of integers, produce a new array that contains all positive
  * values of the original array, in their original order, followed by all va-
  * lues that are zero or negative, in their original order.
  */
import scala.collection.mutable.ArrayBuffer

def posZeroNeg(a: Array[Int]): Array[Int] = {
  val b: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  b ++= (for (i <- a if i > 0) yield i)
  b ++= (for (i <- a if i == 0) yield i)
  b ++= (for (i <- a if i < 0) yield i)
  b.toArray
}

posZeroNeg(Array(1, -2, 0, 1, 0, -9, 10, 11))

/**
  * How do you compute the average of an Array[Double]?
  */
def average(a: Array[Double]): Double = {
  if (a.length > 0) a.sum / a.length else 0
}

average(Array())
average(Array(1, 2))
average(Array(10000000))

/**
  * How do you rearrange the elements of an Array[Int] so that they appear in
  * reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
  */
import scala.collection.mutable.ArrayBuffer

def rearrangeArray(a: Array[Int]): Array[Int] = {
  a.sortWith(_ > _)
}

rearrangeArray(Array(3, 4, 9, 1))

def rearrangeArrayBuffer(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
  a.sortWith(_ > _)
}

rearrangeArrayBuffer(ArrayBuffer(3, 4, 9, 1))

/**
  * Write a code snippet that produces all values from an array with duplicates
  * removed. (Hint: Look at Scaladoc.)
  */
def arrayDistinct[T](a: Array[T]): Array[T] = {
  a.distinct
}

arrayDistinct(Array(1, 1, 2, 1, 2, 4, 3, 3, 5, 3))

/**
  * Suppose you are given an array buffer of integers and want to remove all b-
  * ut the first negative number. Here is a sequential solution that sets a fl-
  * ag when the first negative number is called, then removes all elements bey-
  * ond.
  * var first = true
  * var n = a.length
  * var i = 0
  * while (i < n) {
  *   if (a(i) >= 0) i += 1
  *   else {
  *     if (first) { first = false; i += 1 }
  *     else { a.remove(i); n -= 1 }
  *   }
  * }
  *
  * This is a complex and inefficient solution. Rewrite it in Scala by collect-
  * ing positions of the negative elements, dropping the first element, revers-
  * ing the sequence, and calling a.remove(i) for each index.
  */
import scala.collection.mutable.ArrayBuffer

def removeNegativesButFirst(b: ArrayBuffer[Int]): ArrayBuffer[Int] = {
  var negPositions = for (i <- b.indices if b(i) < 0) yield i
  negPositions = negPositions.drop(1)
  for (i <- negPositions.reverse) b.remove(i)
  b
}

removeNegativesButFirst(ArrayBuffer(1, 2, 3, -1, -2, -3, -1))

import scala.collection.mutable.ArrayBuffer

/**
  * Improve the solution of the preceding exercise by collecting the positions
  * that should be moved and their target positions. Make those moves and trun-
  * cate the buffer. Don’t copy any elements before the first unwanted element.
  */

  def removeNegativesButFirst2(b: ArrayBuffer[Int]): Array[Int] = {
    val res = for (i <- b.indices if (i <= b.indexWhere(_ < 0) || b(i) >= 0)) yield b(i)
    res.toArray
  }

removeNegativesButFirst2(ArrayBuffer(1, 2, 3, -1, -2, -3, -1))

/**
  * Make a collection of all time zones returned by java.util.TimeZone.getAvail-
  * ableIDs that are in America. Strip off the "America/" prefix and sort the r-
  * esult.
  */
val ids = java.util.TimeZone
  .getAvailableIDs
  .filter(_.startsWith("America"))
  .map(_.drop(8))
  .sorted
ids.foreach(println)

/**
  * Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
  * val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  * Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get
  * the return value as a Scala buffer. (Why this obscure class? It’s hard to find uses of
  * java.util.List in the standard Java library.)
  *
  */
import java.awt.datatransfer._

import scala.collection.JavaConverters

val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
val buf = JavaConverters.asScalaBuffer(
  flavors.getNativesForFlavor(DataFlavor.imageFlavor))
