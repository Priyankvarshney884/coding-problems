# Longest Valid Parentheses — Two Passes

Given a string containing only `(` and `)`, return the length of its longest
contiguous well-formed substring. The Java implementation is in
[`LongestValidParentheses.java`](LongestValidParentheses.java).

## Approach

Count opening and closing parentheses while scanning in both directions.

### Left to right

- When the counts are equal, the current segment is balanced. Its length is
  twice the closing count; use it to update the best answer.
- When `close > open`, this segment cannot start a valid substring: no later
  character can match the extra close. Reset both counts and start after it.

This scan handles extra closing parentheses, but it can miss a valid substring
before extra opening parentheses. For example, scanning `(()` left to right
never reaches equal counts.

### Right to left

Scan in reverse with the same counting idea. When `open > close`, reset the
counts. This catches valid substrings before unmatched opening parentheses.

## Dry run: `s = "(()"`

**Left to right** (counts shown as `(open, close)`):

| Character | Counts | Action |
|---|---:|---|
| `(` | `(1, 0)` | Keep scanning |
| `(` | `(2, 0)` | Keep scanning |
| `)` | `(2, 1)` | Keep scanning; no balanced segment yet |

This pass finds length `0`.

**Right to left:**

| Character | Counts | Action |
|---|---:|---|
| `)` | `(0, 1)` | Keep scanning |
| `(` | `(1, 1)` | Balanced; length is `2` |
| `(` | `(2, 1)` | Keep scanning |

Return `2`.

## Dry run: `s = ")()())"`

In the left-to-right pass, the first `)` makes `close > open`, so reset. The
next four characters `()()` reach equal counts at the fourth character:
`open = 2`, `close = 2`, giving length `2 × 2 = 4`. The maximum remains `4`.

## Dry run: `s = ""`

Both scans have no characters to process. `longest` remains `0`, so return `0`.

## Why both directions are needed

The left-to-right scan resets when it sees too many closes, but unmatched opens
at the end can prevent a valid earlier substring from being counted. The
right-to-left scan resets on too many opens and finds those substrings. Together
the scans account for both kinds of imbalance.

## Complexity

- **Time:** `O(n)`; each character is visited once in each direction.
- **Extra space:** `O(1)`.
