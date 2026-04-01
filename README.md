# closest-pair-of-points-recursive
# Closest Pair of Points (Divide and Conquer)

## 1. Requirement

Given a set of **N** points in a 2D plane $P_1, P_2, \dots, P_n$, the objective is to find the minimum Euclidean distance between any two distinct points in the set.

While a brute-force approach takes $O(n^2)$, this implementation uses a **Divide and Conquer** strategy to achieve a much more efficient time complexity of **$O(n \log n)$**.



---

## 2. Algorithm Description

The algorithm recursively splits the search space and optimizes the "merge" step by only checking a small subset of point pairs near the dividing line.

### Phase I: Pre-processing
* The points are sorted by their **X-coordinates** (and by **Y-coordinates** as a tie-breaker).
* This allows for an efficient split into two halves using the median abscissa $m$.

### Phase II: Divide and Conquer
1.  **Split:** The set $S$ is divided into $S_1$ (left) and $S_2$ (right) by the vertical line $x = m$.
2.  **Recursion:** The minimum distances $\delta_1$ (in $S_1$) and $\delta_2$ (in $S_2$) are calculated recursively.
3.  **Local Minimum:** We define the current minimum distance as:
    $$\delta^* = \min(\delta_1, \delta_2)$$

### Phase III: The Strip (Merge) Optimization
A pair of points $(A, B)$ could potentially be closer than $\delta^*$ if $A \in S_1$ and $B \in S_2$. However, such points must lie within a vertical "strip" of width $2\delta^*$ centered at $m$.

1.  **Filter:** Only points $H$ with an X-coordinate in the range $(m - \delta^*, m + \delta^*)$ are considered.
2.  **Y-Sorting:** The points in the strip are sorted by their Y-coordinates.
3.  **Constrained Comparison:** For each point $A \in S_1$ in the strip, we only compare it with points $B \in S_2$ if:
    $$|y_A - y_B| < \delta^*$$
    
*Mathematical proof shows that for any point in the strip, we only need to check a constant number of neighboring points (at most 7) to guarantee finding the minimum distance.*



---

## 3. Complexity
* **Time Complexity:** **$O(n \log n)$**. The sorting takes $O(n \log n)$, and the recurrence is $T(n) = 2T(n/2) + O(n)$.
* **Space Complexity:** **$O(n)$** for storing the points and the recursion stack.

## 4. Features
* **Euclidean Distance:** Uses the standard formula $d = \sqrt{(x_2-x_1)^2 + (y_2-y_1)^2}$.
* **Optimized Merge:** Significantly faster than $O(n^2)$ for large datasets.
