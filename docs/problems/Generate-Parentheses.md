# Problem: Generate Parentheses

- **Source:** LeetCode 22 (problem statement is also summarized in the source file)
- **Topic / pattern:** Backtracking, recursion, strings
- **Difficulty:** Medium
- **Status:** Solved

## Prompt

Given `n` pairs of parentheses, return every distinct string that uses all `n` pairs and is well formed. A string is well formed when every opening parenthesis is eventually closed and no prefix has more closing parentheses than opening parentheses.

See the Java implementation in [GenerateParanthesis.java](../../src/Strings/GenerateParanthesis.java).

## Examples and constraints

```text
n = 3 -> ["((()))", "(()())", "(())()", "()(())", "()()()"]
n = 1 -> ["()"]
n = 0 -> [""]
```

Assume `n >= 0`. For `n < 0`, the implementation throws `IllegalArgumentException`. With zero pairs, the one valid arrangement is the empty string.

## First idea

Generate every string of length `2n` using `(` or `)`, then check whether each string is balanced. There are `2^(2n)` candidates, and most are invalid, so this does a lot of wasted work.

## Improved approach

Build each answer one character at a time and backtrack when a choice is finished. Track:

- `openCount`: how many `(` have been used.
- `closeCount`: how many `)` have been used.
- `current`: the partial string being built.

There are only two legal choices at each step:

1. Add `(` if `openCount < n`.
2. Add `)` if `closeCount < openCount` (there is an unmatched opening parenthesis to close).

When `current` has length `2n`, it is complete and can be added to the result. After each recursive call, remove the character just appended; this restores `current` for the next branch. This restore step is **backtracking**.

### Walkthrough for `n = 2`

```text
                         ""
                         |
                        "("          (can open)
                       /   \
                   "(("   "()"        (close allowed only after an open)
                    |       |
                  "(()"   "()("
                    |       |
                 "(())"  "()()"       (complete: 2 opens + 2 closes)
```

The branch `")"` is never created: at the start `closeCount == openCount`, so adding a close would make the prefix invalid. This pruning is what avoids generating invalid combinations.

For `n = 3`, the same choices produce the five strings shown in the examples.

## Correctness / invariant

At every recursive call:

1. `current` contains exactly `openCount` opening and `closeCount` closing parentheses.
2. `0 <= closeCount <= openCount <= n`, so no prefix has more closes than opens.
3. Every choice still permitted can lead to a valid completion: an opening is added only until `n` are used, and a closing is added only to match an existing opening.

Therefore every completed string has exactly `n` pairs and is well formed. Conversely, every well-formed answer must make one of the permitted choices at each position, so the recursion visits every answer. Each path determines one string, so no answer is duplicated.

## Complexity

- **Time:** O(Cₙ × n), where `Cₙ = (1 / (n + 1)) × binomial(2n, n)` is the number of valid strings. Each output string has length `2n` and must be copied into the result.
- **Space:** O(n) recursion and working string, excluding the returned output. The output itself occupies O(Cₙ × n).

## Edge cases

- [x] Empty or minimum input: `n = 0` returns a list containing `""`; `n = 1` returns `["()"]`.
- [ ] One element: not applicable; input is a count of pairs.
- [ ] Duplicates: not applicable; each output is generated once.
- [x] Negative / boundary values: negative `n` is rejected; zero is supported.

## Reflection

- **What was difficult?** Remembering the close rule: close only when `closeCount < openCount`.
- **What clue points to this pattern?** The prompt asks for *all combinations*, and each character choice can be made only when it preserves a prefix rule. That calls for backtracking with pruning.
- **Revisit on:** 1 day / 1 week / 1 month
