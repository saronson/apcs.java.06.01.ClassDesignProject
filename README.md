# Class Design Project

1. Pick some topic that holds interest for you.
2. Add a file for that class (for example, if I am designing a Dog class, I would add a file named Dog.java).
3. In this file, design and write the Java class that represents this topic. The class should have:

- instance variables (at least two)
- constants (at least one)
- constructors (at least two, one of which is a default constructor)
- accessor and mutator methods (gets and sets for each instance variable)
- ```public String toString()``` method
- ```public boolean equals(Object obj)``` method 
- at least one method that uses the instance variables to do something interesting

4. Add code to the ```main``` in the Test class to make some objects from your class and call each of your methods to prove that they work

To get input from a user in your test program,  here is code to do that:

  ```Scanner scan = new Scanner(System.in);```
  
  ```scan.next()``` will have user enter a String
  
  ```scan.nextInt()``` will have user enter an integer
  
  ```scan.nextDouble()``` will have user enter a double
