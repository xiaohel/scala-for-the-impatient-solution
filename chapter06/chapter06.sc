/**
  * Write an object Conversions with methods inchesToCentimeters, gallonsToLite-
  * rs, and milesToKilometers.
  */
object Conversions {
  def inchesToCentimeters(inches: Double): Double = {
    2.54 * inches
  }

  def gallonsToLiters(gallons: Double): Double = {
    3.78541 * gallons
  }

  def milesToKilometers(miles: Double): Double = {
    1.60934 * miles
  }
}

/**
  * The preceding problem wasn’t very object-oriented. Provide a general superc-
  * lass UnitConversion and define objects InchesToCentimeters, GallonsToLiters,
  * and MilesToKilometers that extend it.
  */
class UnitConversion

object InchesToCentimeters extends UnitConversion {
  def convert(inches: Double) = 2.54 * inches
}

object GallonsToLiters extends UnitConversion {
  def convert(gallons: Double) = 3.78541 * gallons
}

object MilesToKilometers extends UnitConversion {
  def convert(miles: Double) = 1.60934 * miles
}

/**
  * Define an Origin object that extends java.awt.Point. Why is this not actual-
  * ly a good idea? (Have a close look at the methods of the Point class.)
  */
object Origin extends java.awt.Point

val o = Origin

println(o)

/**
  * Define a Point class with a companion object so that you can construct Poin-
  * t instances as Point(3, 4), without using new.
  */
class Point(x: Double, y: Double) {
  override def toString: String = "(x = " + x + ", y = " + y + ")"
}

object Point {
  def apply(x: Double, y: Double): Point = new Point(x, y)
}

val p = Point(2, 3)
println(p)

/**
  * Write a Scala application, using the App trait, that prints the command-line
  * arguments in reverse order, separated by spaces. For example, scala Reverse
  * Hello World should print World Hello.
  */
object Reverse extends App {
  for(s <- args.reverse) print(s + " ")
  println()
}

/**
  * Write an enumeration describing the four playing card suits so that the toS-
  * tring method returns ♣, ♦, ♥, or ♠.
  */
object CardSuite extends Enumeration {
  val Club = Value("♣")
  val Diamond = Value("♦")
  val Heart = Value("♥")
  val Spade = Value("♠")
}

import CardSuite._
println(Club)
println(Diamond)
println(Heart)
println(Spade)

/**
  * Implement a function that checks whether a card suit value from the preceding
  * exercise is red.
  */
object CardSuite extends Enumeration {
  type CardSuite = Value
  val Club = Value("♣")
  val Diamond = Value("♦")
  val Heart = Value("♥")
  val Spade = Value("♠")

  def isRed(cardSuite: CardSuite): Boolean = {
    cardSuite == Diamond || cardSuite == Heart
  }
}

for (value <- CardSuite.values) println(CardSuite.isRed(value))

/**
  * Write an enumeration describing the eight corners of the RGB color cube. As
  * IDs, use the color values (for example, 0xff0000 for Red).
  */
object RGBCube extends Enumeration {
  val black = Value(0x000000, "Black")
  val red = Value(0xff0000, "Red")
  val green = Value(0x00ff00, "Green")
  val blue = Value(0x0000ff, "Blue")
  val yellow = Value(0xffff00, "Yellow")
  val magenta = Value(0xff00ff, "Magenta")
  val cyan = Value(0x00ffff, "Cyan")
  val white = Value(0xffffff, "White")
}

for( c <- RGBCube.values ) println("0x%06x: %s".format(c.id, c))
