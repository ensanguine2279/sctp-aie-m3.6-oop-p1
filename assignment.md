# Assignment (Optional)

## Brief

Create a program called OOPAssignment.java and solve the following problems using classes, constructors, encapsulation, and OOP principles.

1. **Book Class with Encapsulation**
   - Create a `Book` class with the following private attributes:
     - `String title`
     - `String author`
     - `double price`
     - `int stock`
   - Create the following constructors:
     - A parameterized constructor that takes in all four attributes
     - A constructor that takes in `title` and `author`, and sets price to 0.0 and stock to 0
     - A copy constructor
   - Implement proper getter and setter methods for all attributes
   - Add a static variable `totalBooks` to count the number of Book objects created
   - Add a static method `getTotalBooks()` to return the count
   - Override the `toString()` method to display book information
   - Create at least 3 Book objects in your main method and test all the methods

2. **Student Grade Manager (POJO/Record)**
   - Create a `Student` class (or Record) with the following attributes:
     - `String name`
     - `int studentId`
     - `double grade`
   - Create an `ArrayList` to store at least 5 Student objects
   - Add methods or functionality to:
     - Add students to the list
     - Display all students
     - Find and display the student with the highest grade
     - Calculate and display the average grade of all students
   - Test your implementation by creating students, adding them to the list, and displaying the results

## Submission (Optional)

- Submit the URL of the GitHub Repository that contains your work to NTU black board.
- Should you reference the work of your classmate(s) or online resources, give them credit by adding either the name of your classmate or URL.

## References
- Java: https://docs.oracle.com/javase/
- Spring Boot: https://docs.spring.io/spring-boot/docs/current/reference/html/
- PostgreSQL: https://www.postgresql.org/docs/
- OWASP: https://cheatsheetseries.owasp.org/