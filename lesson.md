# Lesson: Introduction to Object-Oriented Programming (OOP)

# Lesson Overview

This lesson introduces the core principles of Object-Oriented Programming (OOP) in Java. You will move from loose variables and methods to designing reusable classes with attributes, constructors, and encapsulation. The lesson also covers `static` members, overriding `toString()`, and data modeling using POJOs and records through hands-on examples like `Customer`, `Product`, and `ShoppingCart`.

The `Customer` class you build in this lesson is the same entity you will carry forward into the Spring Boot section of this module, where it becomes a REST resource and later a database entity.

# Lesson Objectives

- Understand the purpose of classes and objects in organizing code.
- Create and use constructors, including overloaded and copy types.
- Apply encapsulation with private fields, getters, and setters.
- Differentiate instance and static members and their usage.
- Override `toString()` for better object representation.
- Model data using POJOs and records in practical exercises.

## Part 1: Class Attributes and Methods

Create an `App.java` and code along.

We had been defining variables and methods previously in our code.

Let's define some variables to represent a customer in a system, plus `static` methods to display a profile and place an order.

```java
public class App {
  public static void main(String[] args) {

    String name1 = "Tony Stark";
    int customerId1 = 1001;
    String email1 = "tony@starkindustries.com";
    boolean isActive1 = true;

    String name2 = "Bruce Banner";
    int customerId2 = 1002;
    String email2 = "bruce@gammalabs.com";
    boolean isActive2 = false;

    displayProfile(name1, customerId1, email1, isActive1);
    placeOrder(name1, "Laptop");

    displayProfile(name2, customerId2, email2, isActive2);
    placeOrder(name2, "Monitor");
  }

  public static void displayProfile(String name, int customerId, String email, boolean isActive) {
    System.out.println("Customer #" + customerId + ": " + name + " (" + email + ")");
  }

  public static void placeOrder(String name, String item) {
    System.out.println(name + " placed an order for: " + item);
  }
}
```

If you observe the above code, these variables are related to each other. They are all attributes of a customer.

We can group them together in a class so that we can create multiple objects of the same type. This is easier than having to define variables separately for each customer. More importantly, this will make our code more organized and easier to maintain.

Notice also that nothing in this code actually links `name1`, `customerId1`, `email1` and `isActive1` together. They are related only because we named them with matching numbers. The compiler has no idea they belong to the same customer.

### Class

A class is a blueprint for creating objects. It defines the attributes and methods that an object will have.

There can only be one public class in a file. The name of the file should match the name of the public class.

Create a `Customer.java` file and define a `Customer` class.

```java
public class Customer {

}
```

#### Access Modifiers for Classes

Classes also have access modifiers. The access modifier controls the visibility of the class.

| Access Keyword | Description                        |
| -------------- | ---------------------------------- |
| `public`       | accessible from anywhere           |
| blank          | accessible from within the package |

### Methods and Attributes

When we talk about objects in the real world e.g. a car, a dog, a house, etc. we can describe it in terms of its **state** and its **behaviour**.

For example, a dog has state like its color, weight, breed, etc. It also has behaviours like bark, eat, run and play.

In Java, the state of an object is represented by its **attributes** and its behaviour is represented by its **methods**.

Let's add some attributes and methods to our `Customer` class.

```java
public class Customer {

  String name;
  int customerId;
  String email;
  boolean isActive;

  public void displayProfile() {
    System.out.println("Customer #" + customerId + ": " + name + " (" + email + ")");
  }

  public void placeOrder(String item) {
    System.out.println(name + " placed an order for: " + item);
  }
}
```

Notice that `displayProfile()` and `placeOrder()` no longer need `name`, `customerId` or `email` passed in as parameters. The object already holds this data as its own attributes, so the methods can use them directly.

We can instantiate the `Customer` class in our `main` method and invoke the instance methods.

```java
public class App {
  public static void main(String[] args) {
    Customer customerA = new Customer();

    customerA.displayProfile();
    customerA.placeOrder("Laptop");
  }
}
```

Observe the output. Is the result expected?

---

## Part 2: Constructors

A **constructor** is a special method that is called when an object is instantiated i.e. when the `new` keyword is used. The purpose of a constructor is to initialize values for the newly created object.

A constructor:

- is defined using the same syntax as a method
- does not have a return type.
- name must be the same as the class name.

> **Note:** If you write `public void Customer()` by mistake, that is no longer a constructor. It becomes an ordinary method that happens to share the class name, and it will never run when you call `new`. This compiles without error, so watch for it.

Currently, we do not have a constructor defined in our `Customer` class. But Java provides a default constructor that does not take in any parameters. This is called the **no-argument constructor**. Since there are no arguments, this constructor will initialize the attributes to their default values.

This is why we could instantiate the `Customer` object without any errors. However, the attributes are not initialized to the values that we want.

We could also explicitly declare the no-argument constructor in our class.

```java
public class Customer {

  // Explicitly declaring the no-argument constructor
  public Customer() {

  }

}
```

Let's add a **parameterized constructor** to our `Customer` class.

This can be done by right-clicking in VSCode, "Source Action", "Generate Constructors".

```java
public Customer(String name, int customerId, String email, boolean isActive) {
  this.name = name;
  this.customerId = customerId;
  this.email = email;
  this.isActive = isActive;
}
```

With the constructor defined, we can now instantiate the `Customer` object with the values that we want.

```java
public class App {
  public static void main(String[] args) {
    Customer tony = new Customer("Tony Stark", 1001, "tony@starkindustries.com", true);

    tony.displayProfile();
    tony.placeOrder("Laptop");
  }
}
```

Note that if an explicit constructor is defined, there will no longer be a default constructor implicitly defined.

Remove the no-argument constructor and try to instantiate the `Customer` object again without any arguments. What happens?

> **Note:** This rule matters later in the module. Frameworks such as Hibernate and Jackson need a no-argument constructor to create your objects before populating them. If you write a class with only an all-argument constructor, you will hit a runtime error when those frameworks try to instantiate it.

### What is `this`?

The `this` keyword is used to refer to the current instance in a method or constructor.

In the `Customer` class, we now have a constructor that takes in 4 parameters.

```java
public Customer(String name, int customerId, String email, boolean isActive)
```

However, the parameters have the same name as the attributes. How does Java know e.g., whether the `name` variable refers to the argument or the attribute?

This is where the `this` keyword comes in. The `this` keyword refers to the current instance of the object. It can be used to refer to the attributes and methods of the object.

Using a constructor with arguments, we can initialize the attributes of the object with the values passed in.

> **Note:** If you write `name = name;` without `this.`, the code still compiles and runs, but it assigns the parameter to itself and the attribute stays `null`. Always use `this.` inside constructors.

### Constructor Overloading

We can define multiple constructors for a class. This is called **constructor overloading**.

In our previous lesson, we learned method overloading. The constructor is also a method, so we can apply the same concept here.

For example, we can define a constructor to take in two parameters and set default values for the other attributes.

```java
public Customer(String name, int customerId) {
  this.name = name;
  this.customerId = customerId;
  this.email = "not-provided";
  this.isActive = true;
}
```

We could improve this further by calling the all-argument constructor from the two-argument constructor. Remember that our constructor is a method, so we can call it from another method.

```java
public Customer(String name, int customerId) {
  this(name, customerId, "not-provided", true);
}
```

This is called **constructor chaining**. All your initialization logic lives in one place, the all-argument constructor, and every other constructor funnels into it. If you add validation later, you only add it once.

> **Note:** `this.name` with a dot accesses an attribute. `this(...)` with parentheses calls another constructor. The `this(...)` call must be the first statement in the constructor.

Test it out by instantiating the `Customer` object with two arguments.

```java
Customer bruce = new Customer("Bruce Banner", 1002);

bruce.displayProfile();
bruce.placeOrder("Monitor");
```

### Copy Constructor

A **copy constructor** is a constructor that takes in an object of the same class and copies its values to the new object. This is actually just another overloaded constructor.

```java
public Customer(Customer customer) {
  this.name = customer.name;
  this.customerId = customer.customerId;
  this.email = customer.email;
  this.isActive = customer.isActive;
}
```

Or we can call the all-argument constructor.

```java
public Customer(Customer customer) {
  this(customer.name, customer.customerId, customer.email, customer.isActive);
}
```

Then to invoke the copy constructor, we pass in an object of the same class.

```java
Customer bruceClone = new Customer(bruce);
```

> **Note:** This is a **shallow copy**. Our attributes are `String`, `int` and `boolean`, so this is safe. But if the class held a `List` or a `Map`, the copy would share the same underlying collection with the original, and changing one would change the other. That is the difference between a shallow copy and a deep copy.

---

## 👨‍💻 Activity

Create a `Product` class with the following attributes:

- `String productId`
- `String name`
- `double price`
- `int stockQuantity`

Create the following constructors:

- no-argument constructor
- constructor that takes in `productId`, `name`, `price` and `stockQuantity`
- Add validation to all args constructor that `price` and `stockQuantity` cannot be negative.
- constructor that takes in `productId`, `name` and `price`, and sets `stockQuantity` to 0
- copy constructor

Test all four constructors from your `App` class.

---

## Part 3: Encapsulation, Accessor and Mutator Methods

In the previous lesson, we learned how to use access modifiers to control access to methods. We can also use access modifiers to control access to attributes. We refer to class attributes and methods as **class members**.

| Access Keyword | Description                                     |
| -------------- | ----------------------------------------------- |
| `public`       | accessible from anywhere                        |
| `private`      | accessible only from within the class           |
| `protected`    | accessible from within the class and subclasses |
| blank          | accessible from within the class and package    |

Currently, we can access attributes directly from the `App` class.

```java
System.out.println(tony.name);
System.out.println(tony.customerId);
System.out.println(tony.email);
System.out.println(tony.isActive);
```

Generally, we should keep all fields private because we do not want other classes to be able to access and modify the values directly.

```java
private String name;
private int customerId;
private String email;
private boolean isActive;
```

The moment we do this though, we will get an error in the `App` class because we can no longer access the attributes directly. This is because the attributes are now set to `private`.

How do we access them then? This is where **accessor** and **mutator** methods come in. These are more commonly known as **getters** and **setters** respectively. These methods are used to access and modify the values of the attributes.

A getter method is used to retrieve the value of an attribute. It is a public method that returns the value of the attribute.

```java
public String getName() {
  return name;
}
```

A setter method is used to change the value of an attribute. It is a public method that takes in a parameter and sets the value of the attribute.

```java
public void setName(String name) {
  this.name = name;
}
```

The naming convention for getters and setters is to prefix the attribute name with `get` and `set` respectively, except for boolean attributes. For boolean attributes, the prefix should be `is` instead of `get` e.g. `isActive`.

In VSCode, we do not have to type these out but can instead generate them by right-clicking, "Source Action", "Generate Getters and Setters".

```java
public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public int getCustomerId() {
  return customerId;
}

public void setCustomerId(int customerId) {
  this.customerId = customerId;
}

public String getEmail() {
  return email;
}

public void setEmail(String email) {
  this.email = email;
}

public boolean isActive() {
  return isActive;
}

public void setActive(boolean isActive) {
  this.isActive = isActive;
}
```

Now, we can access the attributes using these methods.

```java
System.out.println(tony.getName());
System.out.println(tony.getCustomerId());
System.out.println(tony.getEmail());
System.out.println(tony.isActive());
```

We could also set the values using the setter methods.

```java
tony.setName("Tony Stark Jr");
tony.setEmail("tony.jr@starkindustries.com");
```

It is good practice to keep fields private and provide getters and setters to access and modify the values because it allows us to control how the values are accessed and modified.

A setter is not just an assignment. It is a place where you can add rules. For example, we can reject an email that does not contain an `@` symbol.

```java
public void setEmail(String email) {
  if (email == null || !email.contains("@")) {
    System.out.println("Invalid email. Value not changed.");
    return;
  }
  this.email = email;
}
```

If the field were public, there would be no place to put this rule. Anyone could assign anything at any time.

But there is a catch. A setter only protects the object if every path goes through it. Our constructor currently assigns the field directly:

```java
public Customer(String name, int customerId, String email, boolean isActive) {
  this.email = email; // bypasses the setter, no validation runs
  // ...
}
```

So `new Customer("Tony Stark", 1001, null, true)` would sail straight past the check. Fix it by having the constructor call the setter:

```java
public Customer(String name, int customerId, String email, boolean isActive) {
  this.name = name;
  this.customerId = customerId;
  setEmail(email); // validation now runs
  this.isActive = isActive;
}
```

> **Note:** Validation only protects you if every way in uses it. A class usually has two ways to set a value, the constructor and the setter, so both must apply the same rule. Either the constructor calls the setter, or both call a shared private helper method.

> **Note:** Rejecting the value does not stop the object from being created. The customer is still built, just without an email. In production you would normally throw an exception instead so an invalid object never exists at all. We cover exceptions in a later lesson.

This is one of the principles of Object Oriented Programming (OOP), **Encapsulation**.

<img src="https://files.prepinsta.com/2023/05/Encapsulation.webp" width=500>

Source: https://prepinsta.com/java/encapsulation/

In OOP, encapsulation means two things:

1. The bundling of methods and attributes on a single object
2. The hiding of the internal representation, or state, of an object from the outside

The methods are usually public because we want to give users a way to interact with the object. The attributes are usually private because we do not want users to be able to access and modify the values directly.

This encapsulates our class which allows us to provide a public interface that never changes while the implementation details can change at any time without affecting the users.

For example, we might change the implementation of the `displayProfile` method to include the account status.

```java
public void displayProfile() {
  System.out.println("Customer #" + getCustomerId() + ": " + getName() + " (" + getEmail() + ")");
  System.out.println(isActive() ? "Status: Active" : "Status: Inactive");
}
```

To the users, the interface remains the same regardless of the changes. They can still call the `displayProfile` method and get the same result.

---

## Part 4: `static` Keyword

In the previous lesson we used the `static` keyword on methods. We can also use it on attributes.

Just like methods, `static` attributes are accessed using the class name while instance attributes are accessed using the object name.

A `static` member belongs to the class, not to any individual object. Every instance shares the same single copy.

Let's define a `static` attribute.

```java
public static final String CUSTOMER_TYPE = "RETAIL";
```

The `final` keyword is used to restrict modification of the variable.

> **Note:** `static` and `final` are two separate keywords doing two different jobs. `static` means shared across all instances. `final` means the value cannot be reassigned. They are often used together for constants, but a `static` field can change, as we will see next.

And access them in our `main` method.

```java
// Printing out a static variable
System.out.println(Customer.CUSTOMER_TYPE);
```

Let's add another static variable to keep track of the number of Customer objects instantiated.

```java
private static int customerCount = 0;
```

And a corresponding static method to get the value.

```java
public static int getCustomerCount() {
  return customerCount;
}
```

Now, we will increment it every time a new Customer is instantiated.

```java
public Customer(String name, int customerId, String email, boolean isActive) {
  this.name = name;
  this.customerId = customerId;
  this.email = email;
  this.isActive = isActive;
  customerCount++;
}
```

We can now instantiate a few more customers and use the static method to display the count.

```java
Customer peter = new Customer("Peter Parker", 1003, "peter@dailybugle.com", true);
Customer wanda = new Customer("Wanda Maximoff", 1004, "wanda@westview.com", false);

System.out.println(Customer.getCustomerCount());

// Should not use instance to call a static method
System.out.println(peter.getCustomerCount());
```

It is usually not recommended to call a static member with an instance variable instead of the class name. This is because it can be confusing and make the code less readable.

Static members are associated with the class, not with any particular instance of the class. Therefore, it is more consistent to access them using the class name. This also makes it clear that the member is static, which can be important for understanding the code.

> **Note:** A static method cannot access instance attributes directly. It runs without any particular object in hand, so it has no `this` to resolve `name` or `email` against. Static methods can only use static fields, or data passed in as parameters.

---

## Part 5: `toString` and `@Override`

By default, if we try to print out an object, we will get the class name and the hashcode.

```java
System.out.println(tony);
```

We can override the `toString` method to return a string representation of the object. With VSCode, we can generate the `toString` method by right-clicking, "Source Action", "Generate toString()".

```java
@Override
public String toString() {
  return "Customer [name=" + name + ", customerId=" + customerId + ", email=" + email + ", isActive=" + isActive + "]";
}
```

The `@Override` annotation is used to indicate that the method is overriding a method from the superclass. This is optional but it is good practice to include it. We will learn more about annotations in later lessons.

> **Note:** `@Override` asks the compiler to verify that you really are overriding a parent method. Without it, a small typo such as `ToString` would silently create a brand new method that never gets called, and your object would still print the hashcode.

> **Note:** In real systems, `toString()` is what shows up in log files and stack traces. A readable `toString()` saves a lot of time when debugging. For the same reason, never include sensitive values such as passwords or tokens in it.

---

## 👨‍💻 Activity

Continue with the `Product` class from the previous activity.

1. Encapsulate the attributes and provide accessor and mutator methods.
1. Add a `static` attribute to keep track of the number of products instantiated and a corresponding static method to get the value.
1. Override the `toString` method to return a string representation of the object.
1. Add a rule inside `setPrice` so that a negative price is rejected and the existing value is left unchanged.
1. Add a rule inside `setStockQuantity` so that a negative quantity is rejected.
1. Change the all-argument constructor so it calls `setPrice()` and `setStockQuantity()` instead of assigning those two fields directly, so that both ways in are validated.

---

## Part 6: Intro to the POJO

A Plain Old Java Object (POJO) is a simple object used to represent data in a Java program.

We define it using the `class` keyword with a set of attributes and corresponding getters and setters.

Take for example, an order item object.

```java
public class OrderItem {
  private String productName;
  private double amount;

  public OrderItem() {

  }

  public OrderItem(String productName, double amount) {
    this.productName = productName;
    this.amount = amount;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
```

We could then create an `ArrayList` of order items.

```java
ArrayList<OrderItem> items = new ArrayList<>();

items.add(new OrderItem("Laptop", 1200.00));
items.add(new OrderItem("Monitor", 350.00));
System.out.println(items);
```

But we will not be able to see the data. This is because the `toString` method is not overridden in the `OrderItem` class. So we can either override the `toString` method or loop through the `ArrayList` and print out the values.

By using a POJO, we can easily create objects to represent our data instead of using arrays or ArrayLists of strings or numbers. We could also use it to pass data between components or store data in a database.

> **Note:** POJOs are the backbone of Spring Boot applications. When a REST API receives JSON, it is converted into a POJO. When it sends a response, that response is built from a POJO. Database rows are mapped to POJOs as well. You will hear the term **DTO** (Data Transfer Object) used for a POJO whose job is to carry data between layers of an application.

---

## Part 7: Intro to the Record

The Record is a new feature introduced in Java 14. It is a new type of class that is designed to be a simple and concise way to create classes whose main purpose is to hold data.

Creating a record is similar to creating a POJO. We use the `record` keyword instead of the `class` keyword.

```java
public record OrderLine(String productName, double amount) {}
```

> **Note:** We name the record `OrderLine` rather than `OrderItem` because the POJO from Part 6 already uses that name, and two types in the same folder cannot share a name. Keeping both lets you compare them side by side.

This `Record` has two attributes, `productName` and `amount`. These attributes are `private` and `final` by default. This means that they cannot be modified once the object is created.

It also has a constructor that takes in two parameters and sets the values of the attributes.

To access the fields, we can use the getter methods, which are automatically generated.

```java
OrderLine line = new OrderLine("Laptop", 1200.00);

System.out.println(line.productName());
System.out.println(line.amount());
```

> **Note:** Record accessors have no `get` prefix, and there is no public field to reach with dot notation. Writing `line.amount` will not compile. Use `line.amount()` instead.

The `toString` method is also automatically generated. Try putting a few `OrderLine` objects in an `ArrayList` and printing it.

```java
ArrayList<OrderLine> lines = new ArrayList<>();
lines.add(new OrderLine("Laptop", 1200.00));
lines.add(new OrderLine("Monitor", 350.00));
System.out.println(lines);
```

Compare this with the `OrderItem` POJO from Part 6. Same data, but `OrderItem` took around 30 lines and still printed hashcodes until we wrote a `toString`. `OrderLine` is one line and prints properly straight away.

> **Note:** Use a record when the object is a fixed bundle of data that never changes after creation, such as a DTO or an API response. Use a regular class when the object needs changeable state, which is why database entities are usually still written as classes.

---

## 👨‍💻 Activity **(15 minutes)**

Let's bring the whole lesson together by building a `ShoppingCart` class that holds `OrderItem` objects.

Create a `ShoppingCart` class with the following:

1. Private fields: `String cartId`, `String customerName`, and an `ArrayList<OrderItem> items`.
1. A constructor that takes `cartId` and `customerName`, and initialises the list.
1. An `addItem(OrderItem item)` method. Do not write a setter for the list.
1. A `getTotal()` method that loops through the items and returns the sum of their amounts.
1. A `static` counter for the number of carts created, plus a static method to read it.
1. An overridden `toString()` showing the cart ID, the customer name, the number of items, and the total.

Test it in `App` by creating two carts, adding a few items to each, printing both carts, and printing the total number of carts created.

> **Note:** The list must be created in the constructor with `new ArrayList<>()`. If you only declare the field, it stays `null` and the first `addItem()` call throws a NullPointerException. Object fields default to `null`, not to an empty collection.

### Optional challenge

Add a `getItems()` method that returns the list.

First write it the obvious way:

```java
public ArrayList<OrderItem> getItems() {
  return items;
}
```

Then test it like this:

```java
ArrayList<OrderItem> copy = cart1.getItems();
copy.clear();
System.out.println(cart1);
```

The cart is now empty, even though the field is private and there is no setter. This is because the getter handed out a reference to the actual list, not a copy of it.

Now fix it by returning a copy instead:

```java
public ArrayList<OrderItem> getItems() {
  return new ArrayList<>(items);
}
```

Run the same test again. The cart keeps its items.

> **Note:** This is the most common encapsulation bug in real code. The moment a field holds a reference to something changeable, making it private is not enough. You also have to control who gets hold of the reference, both when values come in and when they go out.

---

END