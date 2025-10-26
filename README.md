# Assignment 3: Optimization of a City Transportation Network (MST)

**Student:** Tastemir Akerke  
**Group:** SE-2401

---

## 🏙 Introduction

The goal of this project was to **optimise a city’s transportation network** using two classical Minimum Spanning Tree (MST) algorithms: **Prim’s** and **Kruskal’s**.  
The city is represented as a **weighted undirected graph**, where:
- **Vertices** represent city districts,
- **Edges** represent potential roads,
- **Edge weights** represent construction costs.

The objective is to find the **minimum set of roads** that connects all districts at the **lowest possible total cost**, ensuring that every district remains reachable.

---

## ⚙️ Methodology

### Kruskal’s Algorithm
1. Sort all edges in non-decreasing order of weight.
2. Select the smallest edge that connects two **different components** (using DSU / Union-Find).
3. If it does not create a cycle, include it in the MST.
4. Repeat until the MST contains **V − 1** edges.

### Prim’s Algorithm
1. Start from any vertex.
2. Add the smallest edge that connects the current MST to a new vertex.
3. Repeat until all vertices are connected.

### Metrics Recorded
For both algorithms, the following were measured:
- List of MST edges
- Total cost of MST
- Number of vertices and edges in the original graph
- Number of operations (comparisons, unions, extractions, etc.)
- Execution time in milliseconds

---

## 📊 Results

The algorithms were executed on **two test graphs**, taken from `ass_3_input.json`.  
The results were automatically written to `ass_3_output.json`.

| Graph ID | Algorithm | Vertices (V) | Edges (E) | MST Total Cost | Operations Count | Execution Time (ms) |
|-----------|------------|--------------|------------|----------------|------------------|---------------------|
| 1 | Prim’s | 5 | 7 | 16 | 10 | 1.36 |
| 1 | Kruskal’s | 5 | 7 | 16 | 14 | 3.65 |
| 2 | Prim’s | 4 | 5 | 6 | 6 | 0.03 |
| 2 | Kruskal’s | 4 | 5 | 6 | 9 | 0.03 |

✅ Both algorithms produced **identical total MST costs**, confirming the correctness of the implementations.

---

## 🔬 Analysis and Comparison

### Theoretical Time Complexities
| Algorithm | Complexity | Notes |
|------------|-------------|-------|
| **Prim’s** | `O(E log V)` (with PriorityQueue) | Efficient for **dense graphs** |
| **Kruskal’s** | `O(E log E)` (due to sorting) | Efficient for **sparse graphs** |

### Empirical Observations
- **Graph 1 (V=5, E=7):**  
  Prim’s was faster (1.36 ms vs 3.65 ms) and required fewer operations, because Kruskal spent more time sorting and performing DSU operations.
- **Graph 2 (V=4, E=5):**  
  Both performed almost identically; the difference is negligible due to the small input size.
- In all cases, the **total cost was the same**, proving algorithmic correctness.

### Implementation Complexity
- **Prim’s Algorithm:**
  - Requires adjacency list and priority queue.
  - Intuitive incremental growth of MST.
  - Slightly more code due to heap management.
- **Kruskal’s Algorithm:**
  - Conceptually simpler: edge sorting + union checks.
  - Depends on correct implementation of DSU (path compression & union by rank).

---

## 🧠 Conclusions

| Graph Type | Best Algorithm | Reason |
|-------------|----------------|--------|
| **Sparse Graphs** (E ≈ V) | ✅ Kruskal’s | Sorting few edges is cheap, DSU is efficient |
| **Dense Graphs** (E ≈ V²) | ✅ Prim’s | Priority queue avoids sorting entire edge list |

**Key Takeaways:**
- For small or dense graphs → **Prim’s** algorithm is slightly faster.
- For large, sparse networks → **Kruskal’s** scales better and performs fewer operations.
- Both are correct and reliable for MST construction in real-world transportation systems.

> **Practical Insight:**  
> In most city networks (which are typically sparse), Kruskal’s algorithm is more efficient overall, while Prim’s algorithm offers speed advantages for small or dense cases.

---

## ⚒️ Build and Run Instructions

This project uses **Apache Maven** for dependency management and can be run directly from IntelliJ IDEA.

### ▶️ Run via IntelliJ IDEA
1. Open the project in IntelliJ.
2. Right-click on `Main.java`.
3. Select **Run 'Main.main()'**.
4. The program reads from `data/ass_3_input.json` and writes results to `data/ass_3_output.json`.

