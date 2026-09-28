# 01. Java Fundamentals

Back to the [module map](../../../README.md) · Full [syllabus](../../../src/syllabus.md).

This module rebuilds the Java skills you need to read, write, debug, and explain small programs. Because you have backend experience, use it as an active-recall checkpoint: move quickly through ideas you can already demonstrate, and spend practice time on anything rusty.

## Learning outcomes

By the end, you should be able to:

- Read and write small Java programs without relying on copied syntax.
- Choose primitive types appropriately and recognize integer overflow and narrowing conversions.
- Use expressions, branches, and loops to express logic clearly.
- Decompose a task into methods with clear inputs, outputs, and side effects.
- Traverse and update arrays safely, including boundary cases.
- Work with `String` and `StringBuilder` correctly.
- Read simple input and print predictable output.
- Trace code by hand, explain its behavior, and diagnose common errors.

## Study order

Read the [Fundamentals guide](GUIDE.md) in order. It covers each syllabus item with short examples. Then complete the practice section below without looking up a finished solution first.

1. Syntax, variables, and types
2. Operators and expressions
3. Conditions and loops
4. Methods and parameter passing
5. Arrays
6. Strings
7. Input and output
8. Mixed practice and review

## Practice set

Implement each as a small method in a clearly named class under `src/Arrays/`, `src/Numbers/`, or a new topic folder as appropriate. Keep one public class per file. For each solution, note the approach, time/space complexity where relevant, and edge cases. Use the [problem template](../../templates/PROBLEM.md) for problems that take more than a few minutes.

1. Given an integer, report whether it is positive, negative, or zero.
2. Given `n`, compute the sum from 1 through `n`; define what should happen for `n <= 0`.
3. Given an integer, count its digits and handle zero and negative input.
4. Given an integer array, return its minimum and maximum. Decide how an empty array should be handled.
5. Return a new array containing the input array in reverse order.
6. Given an array, return the sum of its even values.
7. Given a string, count vowels without treating uppercase and lowercase differently.
8. Return whether a string is a palindrome, ignoring letter case.
9. Given an array and a target, return the target's first index, or `-1` if absent.
10. Read a count followed by that many integers, then print their sum and average.

For every task, first write a few examples and boundary cases. Then implement, manually trace one example, and explain why the loop bounds are correct.

## Mini-project: command-line number analyzer

Build a small program that reads a count and that many integers, then prints:

- the minimum and maximum;
- the sum and average;
- how many values are even and how many are odd;
- whether the sequence is non-decreasing.

Use separate methods for input-independent calculations. Decide how to handle a count of zero and document that decision. Avoid global mutable state.

## Self-check

Close the guide and answer these from memory:

- When can `int` overflow, and how would you choose a wider type?
- What is the difference between `==` and `.equals()` for strings?
- Why is `StringBuilder` useful in a loop that builds a string?
- What are the valid indexes of an array of length `n`?
- Does Java pass method arguments by value? What does that mean for an array argument?
- How do you prevent a loop from reading one position past the end?
- What is the difference between integer division and floating-point division?
- When is `Scanner` convenient, and when might buffered input be preferable?

## Completion criteria

Mark this module complete in the [syllabus](../../../src/syllabus.md) when you can:

- Explain each self-check answer without notes.
- Complete at least 8 of the 10 exercises independently, including arrays and strings.
- Finish the mini-project and test empty, one-element, negative, and ordinary inputs.
- Read your solutions aloud and explain the loop bounds and method contracts.
- Re-solve one missed exercise the next day without looking at your earlier code.

## Notes and practice log

Use this page to record what you learned and what you need to revisit. Keep small code examples in `src/` so they can be compiled and run; keep explanations and reflection here.

| Date | Topic / exercise | What I recalled | What I need to revisit | Revisit date |
|---|---|---|---|---|
| | | | | |
