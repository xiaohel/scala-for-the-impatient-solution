/**
  * Set up a map of prices for a number of gizmos that you covet. Then produce a
  * second map with the same keys and the prices at a 10 percent discount.
  */
val gizmos = Map[String, Double]("gizmos1" -> 100, "gizmos2" -> 200, "gizmos3" -> 300)
val secondMap = for ((k, v) <- gizmos) yield (k, v * 0.9)

/**
  * Write a program that reads words from a file. Use a mutable map to count how
  * often each word appears. To read the words, simply use a java.util.Scanner:
  *   val in = new java.util.Scanner(new java.io.File("myfile.txt"))
  *   while (in.hasNext()) process in.next()
  *
  * Or look at Chapter 9 for a Scalaesque way.
  * At the end, print out all words and their counts.
  */
import java.io.File
import java.util.Scanner

val in = new Scanner(new File("/Users/xilan/raptor"))
val wordCount = scala.collection.mutable.Map[String, Int]()
while (in.hasNext()) {
  val word = in.next()
  val count = wordCount.getOrElse(word, 0)
  wordCount(word) = wordCount.getOrElse(word, 0) + 1
}
wordCount.foreach(println)

/**
  * Repeat the preceding exercise with an immutable map.
  */

import java.io.File
import java.util.Scanner

val in = new Scanner(new File("/Users/xilan/raptor"))
var wordCount = new scala.collection.immutable.HashMap[String, Int]()
while (in.hasNext()) {
  val word = in.next()
  wordCount = wordCount + (word -> (wordCount.getOrElse(word, 0) + 1))
}

wordCount.foreach(println)

/**
  * Repeat the preceding exercise with a sorted map, so that the words are prin-
  * ted in sorted order.
  */

import java.io.File
import java.util.Scanner

val in = new Scanner(new File("/Users/xilan/raptor"))
var wordCount = new scala.collection.immutable.TreeMap[String, Int]()
while (in.hasNext()) {
  val word = in.next()
  wordCount = wordCount + (word -> (wordCount.getOrElse(word, 0) + 1))
}

wordCount.foreach(println)

/**
  * Repeat the preceding exercise with a java.util.TreeMap that you adapt to th-
  * e Scala API.
  */

import java.io.File
import java.util
import java.util.Scanner

import scala.collection.JavaConverters._

val in = new Scanner(new File("/Users/xilan/raptor"))
val javaMap = new util.TreeMap[String, Int]()
val wordCount: scala.collection.mutable.Map[String, Int] = javaMap.asScala
while (in.hasNext()) {
  val word = in.next()
  wordCount(word) = wordCount.getOrElse(word, 0) + 1
}

wordCount.foreach(println)

/**
  * Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, a-
  * nd similarly for the other weekdays. Demonstrate that the elements are visi-
  * ted in insertion order.
  */
val weekdaysMap = scala.collection.mutable.LinkedHashMap(
  "Monday" -> java.util.Calendar.MONDAY,
  "Tuesday" -> java.util.Calendar.TUESDAY,
  "Wednesday" -> java.util.Calendar.WEDNESDAY,
  "Thursday" -> java.util.Calendar.THURSDAY,
  "Friday" -> java.util.Calendar.FRIDAY,
  "Saturday" -> java.util.Calendar.SATURDAY,
  "Sunday" -> java.util.Calendar.SUNDAY)
weekdaysMap.foreach(println)

/**
  * Print a table of all Java properties reported by the getProperties method o-
  * f the java.lang.System class, like this:
  * java.runtime.name             | Java(TM) SE Runtime Environment
  * sun.boot.library.path         | /home/apps/jdk1.6.0_21/jre/lib/i386
  * java.vm.version               | 17.0-b16
  * java.vm.vendor                | Sun Microsystems Inc.
  * java.vendor.url               | http://java.sun.com/
  * path.separator                | :
  * java.vm.name                  | Java HotSpot(TM) Server VM
  *
  * You need to find the length of the longest key before you can print the table.
  */

import scala.collection.JavaConverters._

val props: scala.collection.Map[String, String] =
  System.getProperties.asScala
val maxLength = props.keySet.map(_.length).max
for ((k, v) <- props) {
  val length = k.length
  println(k + (" "  * (maxLength - length)) + "| " + v)
}

/**
  * Write a function minmax(values: Array[Int]) that returns a pair containing
  * the smallest and the largest values in the array.
  */
def minmax(values: Array[Int]): (Int, Int) = (values.min, values.max)

minmax(Array(2, 3, 4, 100, -200))

/**
  * Write a function lteqgt(values: Array[Int], v: Int) that returns a triple c-
  * ontaining the counts of values less than v, equal to v, and greater than v.
  */
def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = (
  values.count(_ < v), values.count(_ == v), values.count(_ > v)
)

lteqgt(Array(1, 1, 1, 9, 20, 8, 20, 1, 7), 5)

/**
  * What happens when you zip together two strings, such as "Hello".zip("World"-
  * )? Come up with a plausible use case.
  */
val caseConverter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  .zip("abcdefghijklmnopqrstuvwxyz")
    .toMap
caseConverter('D')
