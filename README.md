# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Refleksi 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Clean code principles that I have implemented are: Meaningful Names, One Function One Task, DRY (Don't Repeat Yourself), Consistent Code and Error Handling while security practices that I have implemented are: I/O Validation and Dependency Management. Currently there is little to be improved in my code in terms format, though the logic of the code can be improved.

### Refleksi 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

There should be one unit test covering a specific area in the code. 100% code coverage doesn't guarantee your code is 100% bug free.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

one test should only have one functionality in one area of the code, but creating a new class means repeating the same boilerplate that is in other test classes, this violates DRY. we can also use inheritance to reduce redundancy.
