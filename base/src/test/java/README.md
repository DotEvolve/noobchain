# Test Structure for dot-base

This directory contains the test structure for the dot-base project. The test structure mirrors the main codebase structure to make it easy to find and write tests for specific components.

## Directory Structure

The test directory structure follows the same package structure as the main codebase:

```
base/src/test/java/
├── net/
│   └── dotevolve/
│       └── base/
│           ├── application/
│           ├── constants/
│           ├── data/
│           │   └── commons/
│           │       ├── entity/
│           │       └── object/
│           ├── database/
│           ├── service/
│           ├── structure/
│           ├── thirdParty/
│           ├── updater/
│           └── utils/
└── META-INF/
```

## Writing Tests

### Naming Conventions

- Test classes should be named after the class they are testing, with "Test" appended to the end.
  - Example: `CodeHelp.java` → `CodeHelpTest.java`
- Test methods should be named to clearly describe what they are testing.
  - Example: `testSplitCamelCase()`, `testIsEmpty()`

### Test Structure

Each test method should follow the Arrange-Act-Assert pattern:

1. **Arrange**: Set up the test data and preconditions
2. **Act**: Call the method being tested
3. **Assert**: Verify the expected outcome

Example:
```java
@Test
public void testIsEmpty() {
    // Arrange
    String emptyString = "";
    
    // Act
    boolean result = CodeHelp.isEmpty(emptyString);
    
    // Assert
    assertTrue(result);
}
```

### Test Coverage

Aim to test:
- Normal cases (expected inputs)
- Edge cases (empty strings, null values, etc.)
- Error cases (invalid inputs that should be handled)

## Running Tests

Tests can be run using Maven:

```
mvn test
```

To run a specific test class:

```
mvn test -Dtest=ClassName
```

## Best Practices

1. **Keep tests independent**: Each test should be able to run independently of others.
2. **Keep tests simple**: Each test should test one specific behavior.
3. **Use descriptive names**: Test method names should describe what is being tested.
4. **Use assertions effectively**: Choose the appropriate assertion method for the test.
5. **Test both positive and negative cases**: Ensure the code handles both valid and invalid inputs correctly.
6. **Mock external dependencies**: Use mocking frameworks (like Mockito) to isolate the code being tested.
7. **Keep tests fast**: Tests should run quickly to encourage frequent testing.