# Java Fundamentals Guide

Return to the [module checklist and practice plan](README.md).

These are quick reference notes, not a replacement for writing code. For each section, read it once, close the page, and explain the idea in your own words. Then use a small example to check your explanation.

## 1. Program shape and syntax

A Java application is made of classes. Execution starts at `main`:

```java
public class Example {
    public static void main(String[] args) {
        int answer = add(2, 3);
        System.out.println(answer);
    }

    static int add(int left, int right) {
        return left + right;
    }
}
```

Java is statically typed: each variable has a declared type, and the compiler checks many errors before the program runs. Statements generally end with `;`; braces define blocks and scope. Names are case-sensitive. Use `UpperCamelCase` for classes and `lowerCamelCase` for methods and variables. A public top-level class belongs in a file with the same name.

When reading a compiler error, start at the first error. Later errors may be consequences of that first one.

## 2. Variables and types

Primitive types hold values directly: `boolean`, `char`, and numeric types (`byte`, `short`, `int`, `long`, `float`, `double`). For everyday integer calculations, `int` is common; use `long` when the valid range or intermediate products may exceed `int`. `double` is the usual floating-point type. Reference types include arrays, `String`, and objects; a reference variable can also be `null`.

```java
int count = 4;
long product = 3_000_000_000L;
double average = 7.5;
char initial = 'J';
boolean ready = true;
String name = "Java";
```

An integer operation can overflow even if its result is assigned to a `long`: the operation's operands determine its arithmetic type. Widen before multiplying when needed, for example `(long) a * b`. A cast such as `(int) someDouble` narrows and discards the fractional part; it is not rounding. Use clear types and check constraints instead of casting blindly.

Local variables must be assigned before use. Fields get default values, but relying on that distinction in algorithm code can make initialization bugs harder to see.

## 3. Operators and expressions

Arithmetic: `+ - * / %`. With integer operands, `/` performs integer division (`7 / 2 == 3`). If a fractional result is intended, make one operand floating point (`7.0 / 2 == 3.5`). `%` gives the remainder and is useful for divisibility and parity.

Comparisons produce booleans: `== != < <= > >=`. Boolean operators are `&&`, `||`, and `!`. `&&` and `||` short-circuit, so the right side is evaluated only when needed:

```java
if (index >= 0 && index < values.length) {
    System.out.println(values[index]);
}
```

This makes the array access safe because the bounds check runs first. Use parentheses when they make precedence clearer. Avoid clever expressions that are harder to read than a few explicit statements.

For strings, `==` compares whether two references point to the same object; `.equals()` compares text:

```java
if (input.equals("quit")) {
    // compare string contents
}
```

Call `.equals()` on a known non-null value, or use `"quit".equals(input)` if `input` may be null.

## 4. Conditions and loops

Use `if / else if / else` for decisions and `switch` when selecting among discrete cases. Make branches cover the cases you intend, including boundaries.

Use a `for` loop when the number of iterations or index progression is clear; use `while` when repetition depends on a condition. A `do-while` executes its body at least once. `break` exits a loop; `continue` skips to the next iteration.

```java
for (int i = 0; i < values.length; i++) {
    System.out.println(values[i]);
}

for (int value : values) {
    System.out.println(value);
}
```

The index loop is appropriate when you need positions or want to update array elements. The enhanced `for` loop is concise for reading every element. For an array of length `n`, valid indexes are `0` through `n - 1`; `i < n` is the usual traversal condition. Before writing a loop, state what is true before each iteration and how the loop makes progress.

## 5. Methods and parameter passing

A method gives a task a name and defines its contract: inputs (parameters), output (return value), and any side effects. Prefer methods that do one understandable job.

```java
static int sum(int[] values) {
    int total = 0;
    for (int value : values) {
        total += value;
    }
    return total;
}
```

A `void` method returns no value. A method can return early when a result is already known. Method variables have local scope; use parameters instead of depending on unrelated mutable state.

Java always passes arguments by value. For a primitive, the method receives a copy of the value. For an array or object, the copied value is a copy of the reference: the method can mutate the referenced object, but reassigning its parameter does not reassign the caller's variable.

```java
static void changeFirst(int[] numbers) {
    numbers[0] = 99;          // caller sees this element change
    numbers = new int[3];     // caller's variable still refers to its original array
}
```

Document assumptions such as whether null or empty input is allowed. Throw a clear exception for invalid input when that is the chosen contract; do not silently return a misleading answer.

## 6. Arrays

An array has a fixed length and stores elements of one type. Its length is available as `values.length` (no parentheses). Indexing outside `0 .. length - 1` throws `ArrayIndexOutOfBoundsException`.

```java
int[] scores = {8, 5, 10};
int[] empty = new int[0];

for (int i = 0; i < scores.length; i++) {
    scores[i] += 1;
}
```

Arrays are reference types. Assigning one array variable to another copies the reference, not the elements. If you need an independent copy, use `Arrays.copyOf` or copy elements explicitly. Be deliberate about whether a method mutates its input or returns a new array.

For each array algorithm, check at least: empty array, one element, repeated values, negative values, and first/last position. Avoid assuming a non-empty array unless the problem guarantees it.

## 7. Strings

`String` is immutable: operations that appear to change a string produce another string. This is convenient and safe, but repeatedly concatenating in a large loop can do unnecessary work.

```java
String word = "java";
char first = word.charAt(0);
String upper = word.toUpperCase();

StringBuilder result = new StringBuilder();
for (char ch : word.toCharArray()) {
    result.append(ch);
}
String copy = result.toString();
```

A string's valid character indexes range from `0` to `length() - 1`; the method is `length()`, unlike an array's `length` field. Useful operations include `charAt`, `substring`, `equals`, `toCharArray`, and `StringBuilder.append`.

For beginner problems restricted to English letters, a `char` loop may be enough. General Unicode text can use multi-unit characters, so Java `char` is not always one complete human-perceived character. Follow the problem's stated character constraints.

## 8. Basic input and output

`System.out.print` prints without a newline; `println` adds one. `printf` supports formatting:

```java
System.out.printf("Average: %.2f%n", average);
```

`Scanner` is easy to read and is fine for small exercises:

```java
Scanner scanner = new Scanner(System.in);
int n = scanner.nextInt();
```

For larger input, `BufferedReader` with `StringTokenizer` is often faster, though it requires more parsing code. Choose based on input size and environment. Do not mix token-based reads and line-based reads without accounting for the newline left after `nextInt()`.

When a problem provides a method signature (as many coding platforms do), implement that method rather than adding console input. Separate algorithm logic from input/output so it is easier to reason about and reuse.

## 9. Debugging and code quality habits

- Reproduce the issue with the smallest input that shows it.
- Trace variable values on paper or with a debugger.
- Check loop start, stop condition, and update together.
- Check array/string bounds before indexing.
- Distinguish `=` (assignment) from `==` (comparison).
- Compile after small changes and read the first useful error carefully.
- Use names that describe meaning (`total`, `left`, `target`) rather than type (`x1`, `temp2`).
- Keep formatting consistent; indentation should reveal the block structure.
