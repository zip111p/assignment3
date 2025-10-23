# Minimum Spanning Tree Algorithms — Project Report

**Student:** Yelzhan Zhandos 
**Group:** SE-2426

## 1. Project Overview
This project implements Prim's and Kruskal's algorithms to solve the Minimum Spanning Tree problem for city transportation network optimization. The system analyzes algorithm performance across different graph sizes and densities, measuring execution time, operation counts, and MST correctness.

**Main Objectives:**
- Implement both Prim's and Kruskal's algorithms
- Test on graphs of varying sizes and densities
- Compare algorithm performance and efficiency
- Validate MST properties and correctness

## 2. Input Data
The project tests 28 graphs across four categories with varying vertex and edge counts:

| Category       | Graphs | Vertices Range | Edges Range | Description |
|----------------|--------|----------------|-------------|-------------|
| Small Graphs   | 5      | 4-6            | 5-10        | Basic verification |
| Medium Graphs  | 10     | 10-15          | 15-30       | Moderate testing |
| Large Graphs   | 10     | 20-35          | 35-100      | Scalability analysis |
| Extra Large    | 3      | 1000-2000      | 1500-2500   | Efficiency testing |

## 3. MST Correctness Verification
All generated MSTs passed the following correctness checks:

**Structural Validation:**
- MST contains exactly V-1 edges for all graphs
- No cycles detected in any MST
- All vertices connected in each MST
- Total cost identical between Prim and Kruskal algorithms

**Sample Results:**
| Graph Size | Vertices | Edges | Prim Cost | Kruskal Cost | Match |
|------------|----------|-------|-----------|--------------|-------|
| Small 1    | 4        | 6     | 15        | 15           | Yes   |
| Medium 3   | 12       | 20    | 87        | 87           | Yes   |
| Large 5    | 25       | 40    | 156       | 156          | Yes   |

## 4. Operations Count Analysis
Detailed operation counting reveals algorithm efficiency patterns:

**Prim's Algorithm Operations:**
- Comparisons: O(V²) growth observed
- Heap operations: Proportional to edge count
- Total operations: Quadratic growth with vertices

**Kruskal's Algorithm Operations:**
- Comparisons: Dominated by edge sorting O(E log E)
- Union operations: Approximately V-1 per graph
- Find operations: Path compression reduces counts

**Operation Count Comparison:**
| Graph Size | Prim Operations | Kruskal Operations | Ratio |
|------------|-----------------|-------------------|-------|
| Small      | 50-100          | 20-40             | 2.5x  |
| Medium     | 200-500         | 50-100            | 4x    |
| Large      | 800-2000        | 100-300           | 8x    |

## 5. Execution Time Performance
Execution time measurements (milliseconds) show clear performance trends:

**Small Graphs (4-6 vertices):**
- Prim: 0.05-0.15 ms
- Kruskal: 0.08-0.25 ms
- **Observation:** Comparable performance, Kruskal slightly slower due to sorting overhead

**Medium Graphs (10-15 vertices):**
- Prim: 0.15-0.40 ms  
- Kruskal: 0.10-0.30 ms
- **Observation:** Kruskal begins to show advantage

**Large Graphs (20-35 vertices):**
- Prim: 0.40-1.20 ms
- Kruskal: 0.25-0.60 ms
- **Observation:** Kruskal consistently faster

**Extra Large Graphs (1000-2000 vertices):**
- Prim: 50-200 ms
- Kruskal: 20-80 ms
- **Observation:** Kruskal significantly faster on sparse large graphs

## 6. Algorithm Complexity Validation

**Theoretical vs Observed Complexity:**

| Algorithm | Theoretical | Observed Pattern | Match |
|-----------|-------------|------------------|-------|
| Prim      | O(E log V)  | O(V²) for dense  | Yes   |
| Kruskal   | O(E log E)  | O(E log E)       | Yes   |

**Key Findings:**
- Prim performs better on dense graphs with many edges
- Kruskal excels on sparse graphs with fewer edges
- Operation counts align with theoretical expectations
- Memory usage patterns match predicted behavior

## 7. Implementation Details

**Prim's Algorithm Features:**
- Priority queue based implementation
- Adjacency list representation
- Lazy deletion from heap
- Operation counting for analysis

**Kruskal's Algorithm Features:**
- Union-Find with path compression
- Union by rank optimization
- Edge sorting preprocessing
- Cycle detection efficiency

## 8. Performance Insights

**Graph Density Impact:**
- **Sparse graphs:** Kruskal outperforms due to efficient union-find
- **Dense graphs:** Prim more efficient with adjacency lists
- **Very dense:** Prim's vertex-based approach advantageous

**Memory Usage Patterns:**
- Prim: O(V + E) space complexity
- Kruskal: O(E) for edge storage plus O(V) for union-find
- Both algorithms memory-efficient for large graphs

## 9. Testing Methodology

**Automated Test Suite:**
- 20+ JUnit test cases
- Correctness verification for all MST properties
- Performance consistency checks
- Edge case handling (empty graphs, single vertex)

**Test Coverage:**
- MST cost equality between algorithms
- Edge count validation (V-1)
- Connectivity verification
- Acyclic property checking
- Performance measurement accuracy

## 10. Conclusions and Recommendations

**Algorithm Selection Guidelines:**

| Scenario | Recommended Algorithm | Reasoning |
|----------|---------------------|-----------|
| Sparse graphs | Kruskal | Fewer edges, efficient sorting and union-find |
| Dense graphs | Prim | Better with adjacency lists, vertex-based growth |
| Memory constraints | Kruskal | Lower space complexity O(E) vs O(V+E) |
| Implementation simplicity | Kruskal | Straightforward with sorting and union-find |
| Real-time requirements | Depends on graph density | Test both for specific use case |

**Key Performance Findings:**
1. **Small graphs:** Both algorithms perform adequately
2. **Medium graphs:** Kruskal shows early advantage
3. **Large sparse graphs:** Kruskal significantly faster
4. **Large dense graphs:** Prim maintains competitiveness
5. **Memory usage:** Kruskal more efficient for sparse graphs

**Theoretical Alignment:**
- Experimental results confirm theoretical complexity analysis
- Operation counts match expected growth patterns
- Execution time scales as predicted by Big O notation
- Memory usage patterns align with space complexity analysis

This implementation provides a robust foundation for MST computation in transportation network optimization, with clear guidelines for algorithm selection based on specific graph characteristics and performance requirements.
