# Java Stream API Practice Problems

These problems are intended for Java 8 or later. Try each prompt before reading its solution. The solutions favor a clear, single-pass stream where that is efficient; streams do not make an algorithm faster by themselves, so the complexity still depends on the operations used.

## Easy — Filter, transform, and collect

### Problem

Given a list of integers, return the squares of the even numbers in their original order. Do not modify the input list.

```text
Input:  [1, 2, 3, 4, 5, 6]
Output: [4, 16, 36]
```

Return an empty list when the input is empty. Assume values are small enough that squaring them does not overflow an `int`.

### Solution

```java
import java.util.List;
import java.util.stream.Collectors;

static List<Integer> evenSquares(List<Integer> numbers) {
    return numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .collect(Collectors.toList());
}
```

`filter` removes odd values, `map` squares each remaining value, and `collect` materializes the result. The pipeline processes each input element once: **O(n)** time and **O(k)** output space, where `k` is the number of even values. It preserves encounter order for a list.

## Medium — Find the second-highest distinct value

### Problem

Given a list of integers, return the second-highest *distinct* value. If fewer than two distinct values exist, return `Optional.empty()`.

```text
Input:  [10, 40, 20, 40, 30]
Output: Optional[30]

Input:  [7, 7]
Output: Optional.empty
```

### Solution

```java
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

static Optional<Integer> secondHighest(List<Integer> numbers) {
    return numbers.stream()
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst();
}
```

`distinct` ensures repeated maximum values do not count as the second value. Sorting descending puts the answer in the second position; `Optional` naturally represents missing results, including an empty input. This direct solution takes **O(n log n)** time and **O(n)** additional space in the general case.

If the input is large and only the second value is needed, sorting all values is unnecessary. A one-pass reduction can track the two largest distinct values in **O(n)** time and **O(1)** extra state:

```java
import java.util.List;
import java.util.Optional;

static Optional<Integer> secondHighestLinear(List<Integer> numbers) {
    int[] top = {0, 0};
    boolean[] present = {false, false};

    numbers.forEach(n -> {
        if (present[0] && n == top[0] || present[1] && n == top[1]) {
            return; // Ignore duplicate values.
        }
        if (!present[0] || n > top[0]) {
            top[1] = top[0];
            present[1] = present[0];
            top[0] = n;
            present[0] = true;
        } else if (!present[1] || n > top[1]) {
            top[1] = n;
            present[1] = true;
        }
    });

    return present[1] ? Optional.of(top[1]) : Optional.empty();
}
```

This uses `forEach` with mutable state, so it is deliberately sequential and should not be changed to `parallelStream()`. The sorted stream version is simpler and often preferable unless profiling or input size justifies the linear scan.

## Hard — Highest-paid employee in each department

### Problem

Given employees with a name, department, and salary, return one highest-paid employee per department. If salaries tie within a department, choose the employee whose name comes first alphabetically. The returned map should be keyed by department.

```text
Input:
  Ana, Engineering, 120000
  Bo,  Engineering, 120000
  Cy,  Sales,        90000

Output:
  Engineering -> Ana
  Sales       -> Cy
```

### Solution

```java
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

static final class Employee {
    final String name;
    final String department;
    final int salary;

    Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", " + salary + ")";
    }
}

static Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
    Comparator<Employee> ranking = Comparator
            .comparingInt((Employee e) -> e.salary)
            .thenComparing(e -> e.name, Comparator.reverseOrder());

    return employees.stream().collect(Collectors.groupingBy(
            e -> e.department,
            Collectors.collectingAndThen(
                    Collectors.maxBy(ranking),
                    optional -> optional.orElseThrow(IllegalStateException::new))));
}
```

The comparator ranks salary first. For equal salaries it reverses the name comparison, so `maxBy` selects the alphabetically earliest name. `groupingBy` builds a group per department, and the downstream `maxBy` computes each winner during collection instead of sorting every department separately. The work is **O(n)** for `n` employees, plus map storage of **O(d)** for `d` departments; the collector retains one current best employee per department rather than sorted copies of each group.

For an empty input, the result is an empty map. Departments with employees always have a winner, so the downstream optional is present. If employee data can contain null departments or names, validate or normalize them before collecting because the comparator and default `groupingBy` do not accept null keys/elements in all such cases.

## Choosing the right stream operation

| Need | Useful operation | Watch out for |
|---|---|---|
| Keep matching items | `filter` | Predicate cost is paid per item |
| Convert items | `map` | Mapping should avoid unexpected side effects |
| Remove repeated values | `distinct` | Needs state and generally uses extra memory |
| Order items | `sorted` | Usually costs **O(n log n)** and buffers values |
| Aggregate by key | `groupingBy` | Memory grows with the number of groups |
| Get an optional result | `findFirst`, `max`, `min` | Account for empty input |

Use streams to express transformations and aggregation clearly. Prefer a linear aggregation over sorting when only a small summary is required, and avoid mutable side effects in stream pipelines when a collector or reduction can express the result.
