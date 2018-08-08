/**
  * Improve the Counter class in Section 5.1, "Simple Classes and Parameterless
  * Methods," on page 55 so that it doesn't turn negative at Int.MaxValue.”
  */
class Counter {
  private var value = 0

  def increment() {
    if (value < Int.MaxValue)
      value += 1
  }

  def current: Int = {
    value
  }
}

/**
  * Write a class BankAccount with methods deposit and withdraw, and a read-only
  * property balance.
  */
class BankAccount {
  private var _balance: Double = 0

  def deposit(amount: Double): Unit = {
    if (amount >= 0)
      _balance += amount
  }
  def withdraw(amount: Double): Unit = {
    if (_balance >= amount && amount >= 0)
      _balance -= amount
  }

  def balance : Double = _balance
}

val b = new BankAccount
b.balance
b.deposit(100)
b.balance
b.withdraw(9.2)
b.balance

/**
  * Write a class Time with read-only properties hours and minutes and a method
  * before(other: Time): Boolean that checks whether this time comes before the
  * other. A Time object should be constructed as new Time(hrs, min), where hrs
  * is in military time format (between 0 and 23).
  */
class Time(val hrs: Int, val min: Int) {
  if (hrs < 0 || hrs > 23)
    throw new IllegalArgumentException("hrs must be between 0 and 23")
  if (min < 0 || min > 59)
    throw new IllegalArgumentException("min must be between 0 and 59")
  def before(other: Time): Boolean = {
    (hrs < other.hrs) || ((hrs == other.hrs) && (min < other.min)) 
  }
}

val t1 = new Time(11, 45)
val t2 = new Time(23, 59)

println(t1.before(t2))

/**
  * Reimplement the Time class from the preceding exercise so that the internal
  * representation is the number of minutes since midnight (between 0 and 24 ×
  * 60 – 1). Do not change the public interface. That is, client code should be
  * unaffected by your change.
  */
class Time(val hrs: Int, val min: Int) {
  if (hrs < 0 || hrs > 23)
    throw new IllegalArgumentException("hrs must be between 0 and 23")
  if (min < 0 || min > 59)
    throw new IllegalArgumentException("min must be between 0 and 59")

  private val minutes: Int = hrs * 24 + min

  def before(other: Time): Boolean = {
    minutes < other.minutes
  }
}

val t1 = new Time(11, 45)
val t2 = new Time(23, 59)

println(t1.before(t2))


/**
  * Make a class Student with read-write JavaBeans properties name (of type Str-
  * ing) and id (of type Long). What methods are generated? (Use javap to check.
  * ) Can you call the JavaBeans getters and setters in Scala? Should you?
  */
import scala.beans.BeanProperty

class Student(@BeanProperty var name: String, @BeanProperty var id: Long)

val s = new Student("Gee", 1233456)
println(s.getName)
println(s.getId)
s.setName("Suee")
s.setId(1900882)
println(s.getName)
println(s.getId)

scala> class Student(@BeanProperty var name: String, @BeanProperty var id: Long)
defined class Student

scala> :javap -p Student
Compiled from "<console>"
public class $line19.$read$$iw$$iw$$iw$Student {
  private java.lang.String name;
  private long id;
  public java.lang.String name();
  public void name_$eq(java.lang.String);
  public long id();
  public void id_$eq(long);
  public long getId();
  public java.lang.String getName();
  public void setId(long);
  public void setName(java.lang.String);
  public $line19.$read$$iw$$iw$$iw$Student(java.lang.String, long);
}


/**
  * In the Person class of Section 5.1, “Simple Classes and Parameterless Metho-
  * ds,” on page 55, provide a primary constructor that turns negative ages to 0.
  */
class Person(var age: Int) {
  if (age < 0) {
    age = 0
  }
}

/**
  * Write a class Person with a primary constructor that accepts a string conta-
  * ining a first name, a space, and a last name, such as new Person("Fred Smith
  * "). Supply read-only properties firstName and lastName. Should the primary c
  * onstructor parameter be a var, a val, or a plain parameter? Why?
  *
  */
class Person(val name: String) {
  val firstName: String = name.split(" ")(0)
  val lastName: String = name.split(" ")(1)
}

val p = new Person("Fred Smith")
println(p.firstName)
println(p.lastName)

/**
  * Make a class Car with read-only properties for manufacturer, model name, an-
  * d model year, and a read-write property for the license plate. Supply four
  * constructors. All require the manufacturer and model name. Optionally, mode-
  * l year and license plate can also be specified in the constructor. If not,
  * the model year is set to -1 and the license plate to the empty string. Which
  * constructor are you choosing as the primary constructor? Why?
  */
class Car(val manufacturer: String, val modelName: String, val modelYear: Int = -1, licensePlate: String = "") {
  def this(manufacturer: String, modelName: String, licensePlate: String) {
    this(manufacturer, modelName, -1, licensePlate)
  }
}
new Car("A", "B", 100, "C")
new Car("A", "B", 101)
new Car("A", "B", "C")
new Car("A", "B", 1000)
new Car("A", "B")
new Car("A", "B", licensePlate = "C", modelYear = 100)

/**
 * Reimplement the class of the preceding exercise in Java, C#, or C++ (your ch-
 * oice). How much shorter is the Scala class?
 *
 * Reimplemented in Java, it is much longer.
 */
public class Car {
  private String manufacturer;
  private String modelName;
  private int modelYear;
  private String licensePlate;
  
  public Car(String manufacturer, String modelName, int modelYear, String licensePlate) {
    this.manufacturer = manufacturer;
    this.modelName = modelName;
    this.modelYear = modelYear;
    this.licensePlate = licensePlate;
  }
  
  public Car(String manufacturer, String modelName, int modelYear) {
    this(manufacturer, modelName, modelYear, "");
  }
  
  public Car(String manufacturer, String modelName, String licensePlate) {
    this(manufacturer, modelName, -1, licensePlate);
  }
  
  public Car(String manufacturer, String modelName) {
    this(manufacturer, modelName, -1, "");
  }
  
  public String getManufacturer() {
    return manufacturer;
  }
  
  public String getModelName() {
    return modelName;
  }
  
  public int getModelYear() {
    return modelYear;
  }
  
  public String getLicensePlate() {
    return licensePlate;
  }
  
  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }
}

/**
  * Consider the class
  * class Employee(val name: String, var salary: Double) {
  *   def this() { this("John Q. Public", 0.0) }
  * }
  *
  * Rewrite it to use explicit fields and a default primary constructor. Which
  * form do you prefer? Why?”
  */

class Employee(val name: String = "John Q. Public", var salary: Double = 0.0)

