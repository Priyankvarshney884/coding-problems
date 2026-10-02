# Data Structures

Back to the [module map](../../../README.md) · Full [syllabus](../../../src/syllabus.md).

## Checklist

- [ ] Arrays
- [ ] Strings
- [ ] Linked Lists
- [ ] Stack
- [ ] Queue
- [ ] Hashing
- [ ] Trees
- [ ] BST
- [ ] Heap
- [ ] Trie
- [ ] Graphs
- [ ] Union Find

## Notes and examples

Add short explanations, Java gotchas, and small examples here. Prefer links to runnable solutions in `src/`.

### Stack (LIFO)

A stack is **last in, first out**: the most recently added item is the first one removed. Think of a stack of plates. In Java, prefer `Deque` with `ArrayDeque` for stack behavior. `java.util.Stack` is a legacy class; its methods are synchronized and it extends `Vector`, exposing list operations that are usually not wanted for a stack.

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<String> stack = new ArrayDeque<>();
stack.push("A"); // add at the top
stack.push("B");
String top = stack.peek(); // B, leave it on the stack
String removed = stack.pop(); // B, remove the top
boolean empty = stack.isEmpty();
int size = stack.size();
```

#### Working diagram

Top is shown at the left. `push` adds at the top; `pop` removes from the top; `peek` only reads it.

```text
Start       push(A)       push(B)       peek()         pop()
  []          [A]          [B, A]         [B, A]         [A]
                             ^ top          ^ top         ^ top
                                           returns B     returns B
```

#### Java stack operations

| Operation | `Deque` method | Effect | Empty-stack behavior | Typical cost |
|---|---|---|---|---|
| Push | `push(value)` | Adds at the top | N/A | O(1) amortized |
| Pop | `pop()` | Removes and returns the top | Throws `NoSuchElementException` | O(1) |
| Peek | `peek()` | Returns top without removing | Returns `null` | O(1) |
| Check top (exception form) | `element()` | Returns top without removing | Throws `NoSuchElementException` | O(1) |
| Add at top (exception form) | `addFirst(value)` | Same end as `push` | N/A | O(1) amortized |
| Remove at top (exception form) | `removeFirst()` | Same end as `pop` | Throws `NoSuchElementException` | O(1) |
| Read top | `getFirst()` | Same end as `peek` | Throws `NoSuchElementException` | O(1) |
| Empty check | `isEmpty()` | Tests whether there are elements | `true` when empty | O(1) |
| Count | `size()` | Returns number of elements | `0` when empty | O(1) |
| Clear | `clear()` | Removes every element | Remains empty | O(n) |

`Deque` also has `offerFirst`, `pollFirst`, and `peekFirst`: these are the non-throwing forms of add, remove, and inspect at the front. For a stack, use one end consistently. A deque can also act as a queue, but mixing both ends without a clear reason makes code harder to read.

`ArrayDeque` rejects `null`; use a separate sentinel or `Optional` where absence needs representation. Its push/pop/peek operations are O(1) amortized; resizing can make an individual push O(n). It is not thread-safe. For concurrent access, select a concurrent collection based on the coordination needs instead of sharing an `ArrayDeque` across threads.

#### Legacy `Stack<E>` API

If reading older code, `java.util.Stack<E>` provides `push(E)`, `pop()`, `peek()`, `empty()`, and `search(Object)`. `search` returns a one-based distance from the top, or `-1` if absent. Since `Stack` also inherits `Vector` methods such as `get`, `set`, `remove`, and `add`, those are available but usually violate the intended LIFO interface. Use `Deque` for new code.

#### Common stack patterns

**Balanced delimiters:** push opening symbols; for each closing symbol, verify and pop the matching opener. At the end, the stack must be empty. A mismatch or a closing symbol when empty means invalid input. The [parentheses solution](../../../src/Stack/ValidParanthesis.java) is the exercise file for this pattern.

**Monotonic stack:** keep indices or values in sorted order; pop while the new item makes the top obsolete. This is useful for next-greater-element, histogram, and temperature problems. Each item is pushed and popped at most once, so the scan is O(n).

**Undo / history:** push prior state or actions, then pop to reverse the latest action. Store enough information to restore state safely.

#### Compact Java syntax used with stacks

These are common Java idioms, not special stack operators:

```java
Deque<Character> chars = new ArrayDeque<>(); // diamond <> infers Character
chars.push(c);                                // no need to write chars.push<Character>(c)

if (!chars.isEmpty() && chars.peek() == expected) {
    chars.pop();
}

// Pattern matching (Java 16+): type check and cast in one expression
if (value instanceof String text) {
    chars.push(text.charAt(0));
}

// Ternary expression for a short value choice
String result = chars.isEmpty() ? "empty" : String.valueOf(chars.peek());
```

Use `var` only when the initializer makes the type obvious (`var stack = new ArrayDeque<Integer>();`). Prefer an explicit `Deque<Integer>` declaration when the abstraction matters. Avoid clever one-liners for pop/peek logic: an explicit empty check makes underflow behavior clear. `peek() == expected` is fine for primitive wrappers that Java unboxes (such as `Character`); use `.equals` for general object values and be mindful of `null`.

## Practice log

| Date | Problem / exercise | Pattern | Result / follow-up |
|---|---|---|---|
| | | | |

## Revision prompts

- What are the key operations and their complexity?
- Which edge cases are easy to miss?
- Can I explain and implement this from memory?
