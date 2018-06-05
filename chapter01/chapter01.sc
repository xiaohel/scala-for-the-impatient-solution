/**
 * The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero.
 * Write a function that computes this value.
 */
def signum(x: Double): Int = {
  if (x > 0) 1
  else if (x < 0) -1
  else 0
}

signum(100)
signum(-100)
signum(0)

/**
 * What is the value of an empty block expression {}? What is its type?
 */
println({})
()
Its type Unit

/**
 * Come up with one situation where the assignment x = y = 1 is valid in Scala. (Hint: Pick a suitable type for x.)
 */
var x: Unit
var y: Int
x = y = 10

/**
  * Write a Scala equivalent for the Java loop
  * for (int i = 10; i >= 0; i--)
  *   System.out.println(i);
  */
for (i <- 10 to 0 by -1)
  println(i)

/**
  * Write a procedure countdown(n: Int) that prints the numbers from n to 0.
  */
def countdown(n: Int): Unit = {
  for (i <- n to 0 by -1)
    println(i)
}

countdown(100)

/**
  * Write a for loop for computing the product of the Unicode codes of all letters in a string.
  * For example, the product of the characters in "Hello" is 9415087488L.
  */
def productLoop(s: String): Long = {
  var res = 1L;
  for (c <- s) {
    res = res * c
  }
  res
}

productLoop("Hello")

/**
  * Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)
  */
def productNoLoop(s: String): Long = {
  s.map(_.toLong).product
}

productNoLoop("Hello")

/**
  * Write a function product(s : String) that computes
  * the product, as described in the preceding exercises.
  */
def product(s: String): Long = {
  s.map(_.toLong).product
}

product("Hello")

/**
  * Make the function of the preceding exercise a recursive function.
  */
def recursiveProduct(s: String): Long = {
  if (s.length == 0)
    1L
  else 
    s.head * recursiveProduct(s.tail)
}

recursiveProduct("Hello")
  
/**
  * Write a function that computes x^n, where n is an integer. Use the following recursive definition:
  * • x^n = y · y if n is even and positive, where y = x^(n/2).
  * • x^n = x · x^(n-1) if n is odd and positive.
  * • x^0 = 1.
  * • xn = 1 / x^(–n) if n is negative.
  * Don’t use a return statement.
  */
def pow(x: Double, n: Int): Double = {
  if (n == 0) {
    1
  } else if (n % 2 == 0) {
    pow(x, n / 2) * pow(x, n / 2)
  } else {
    x * pow(x, n - 1)
  }
}

pow(-2, 2)

/**
  * Define a string interpolator date so that you can define a java.time.LocalDate as date"$year-$month-
  * $day". You need to define an “implicit” class with a date method, like this:"
  * "implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
  *   def date(args: Any*): LocalDate = . . .
  * }
  * args(i) is the value of the ith expression. Convert each to a string and then to an integer, and pass them to the LocalDate.of method.
  * If you already know some Scala, add error handling. Throw an exception if there aren't three arguments, or if they aren't integers, or
  * if they aren't separated by dashes. (You get the strings in between the expressions as sc.parts.)
  */

import java.time.LocalDate

implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
  def date(args: Any*): LocalDate = {
    if (args.size != 3) {
      throw new IllegalArgumentException("number of arguments should be 3")
    }

    for (arg <- args) {
      if (!arg.isInstanceOf[Int]) {
        throw new IllegalArgumentException("arguments should be integers")
      }
    }

    if (sc.parts.size != 4) throw new IllegalArgumentException("illegal format")
    sc.parts.foreach(println)
    if (!sc.parts(1).equals("-") || !sc.parts(2).equals("-")) {
      throw new IllegalArgumentException("parts should be delimited by '-'")
    }

    val year = args(0).toString.toInt
    val month = args(1).toString.toInt
    val day = args(2).toString.toInt
    LocalDate.of(year, month, day)
  }
}

val year=2012
val month=11
val day=5

date"$year-$month-$day"
