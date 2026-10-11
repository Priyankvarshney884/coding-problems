# Testing

Back to the [module map](../../../README.md) · Full [syllabus](../../../src/syllabus.md).

## Checklist

- [ ] Unit testing
- [ ] JUnit
- [ ] Assertions
- [ ] Mocking
- [ ] Test design
- [ ] Edge cases
- [ ] Integration testing

## Notes and examples

Add short explanations, Java gotchas, and small examples here. Prefer links to runnable solutions in `src/`.

### JUnit 5: testing `PrimeNumberService`

The example in [`PrimeNumberServiceTest.java`](../../../src/TestScenerios/PrimeNumberServiceTest.java)
tests the real [`PrimeNumberService`](../../../src/TestScenerios/PrimeNumberService.java) object. Each test
calls `isPrime` with representative inputs and checks the returned boolean:

- Prime numbers such as `2` and `7` should return `true`.
- Composite numbers such as `9` and `100` should return `false`.
- Values below `2` (`-1`, `0`, and `1`) should return `false`.

`@Test` tells JUnit to run a method. `assertTrue` and `assertFalse` compare the actual result with the expected
behavior. `assertAll` runs all of its assertions and reports every failure in the group. These are unit tests:
they check this class's logic without a database, network, or mock dependency.

#### Run the test in IntelliJ IDEA

1. Open `PrimeNumberServiceTest.java`.
2. Confirm JUnit 5 is configured for the project. The import `org.junit.jupiter.api.Test` must resolve; if it
   does not, add JUnit Jupiter to the project/module dependencies or configure the project with Maven or Gradle.
3. Click the green run icon next to `PrimeNumberServiceTest` or a `@Test` method, then choose **Run**.
4. In the Run window, green means all tests passed. Red shows the failed test and assertion so you can compare
   the expected and actual behavior.

To see the test detect a bug, temporarily change the service so a known input returns the wrong result, rerun
the test, and observe the failure. Restore the implementation afterwards. The test does not call `main`; JUnit
discovers the `@Test` methods and invokes them.

#### A face-to-face explanation

> “I test the prime service with JUnit 5. Each `@Test` method exercises a behavior and asserts its expected
> result. I cover prime values, composite values, and values below 2. If an assertion fails, JUnit reports the
> test and expected versus actual result, which makes a regression visible.”

Likely follow-ups: why is `1` not prime; what would you test for `2`; how would you verify an exception with
`assertThrows`; and how would testing change if the service depended on a database or another service?

## Practice log

| Date | Problem / exercise | Pattern | Result / follow-up |
|---|---|---|---|
| | | | |

## Revision prompts

- What are the key operations and their complexity?
- Which edge cases are easy to miss?
- Can I explain and implement this from memory?
