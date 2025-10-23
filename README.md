# Assignment 3: Optimization of City Transportation Network
## Minimum Spanning Tree Algorithms Implementation

### Project Overview
This project implements Prim's and Kruskal's algorithms to solve the Minimum Spanning Tree (MST) problem for optimizing city transportation networks. The system determines the most cost-effective set of roads that connect all city districts while minimizing total construction costs.

### Objective
- Model city districts as graph vertices and potential roads as weighted edges
- Find the minimum set of roads that connects all districts with lowest cost
- Compare performance and efficiency of Prim's vs Kruskal's algorithms
- Analyze algorithmic behavior across different graph sizes and densities

### Project Structure
```
assignment_3/
│
├── MSTAlgorithms.java # Main algorithm implementations
├── Graph.java # Graph data structure
├── Edge.java # Edge representation
├── UnionFind.java # Union-Find for Kruskal's
├── GraphGenerator.java # Test data generation
├── MSTTest.java # JUnit test suite
├── Main.java # Main execution class
├── README.md # Project documentation
├── pom.xml # Maven configuration
│
├── input/ # Input datasets
│ ├── small_graphs.json
│ ├── medium_graphs.json
│ └── large_graphs.json
│
└── output/ # Analysis results
├── results.json
├── comparison.csv
└── performance_report.txt

```

### Quick Start

#### Compilation

# Compile all Java files
javac *.java

# Or use Maven
mvn compile
Execution
# Run complete analysis
java Main

# Run specific graph size only
java Main --size small

# Run performance tests only
java -cp .:junit.jar org.junit.runner.JUnitCore MSTTest
Test Datasets
The system generates 4 categories of graphs:

Category	Graphs	Vertices	Edges	Purpose
Small	5	4-6	5-10	Correctness verification
Medium	10	10-15	15-30	Moderate performance testing
Large	10	20-35	35-100	Scalability analysis
Extra Large	3	1000-2000	1500-2500	Efficiency testing
Output Metrics
For each algorithm and graph, the system records:

Correctness Metrics
MST edges list with weights

Total construction cost

Edge count verification (V-1 edges)

Connectivity validation

Performance Metrics
Execution time (milliseconds)

Operation counts:

Prim: comparisons, heap operations

Kruskal: comparisons, union/find operations

Memory usage patterns

Algorithm efficiency ratios

Comparison Metrics
Cost equality between algorithms

Time performance differences

Operation efficiency analysis

Scalability across graph sizes

Algorithm Implementation Details
Prim's Algorithm
Approach: Greedy algorithm building MST from starting vertex

Data Structure: Priority queue (min-heap)

Time Complexity: O(E log V)

Best For: Dense graphs, adjacency list representation

Kruskal's Algorithm
Approach: Sort all edges and add smallest that don't form cycles

Data Structure: Union-Find (Disjoint Set Union)

Time Complexity: O(E log E)

Best For: Sparse graphs, edge list representation

Automated Testing
The test suite verifies:

Correctness Tests
testMSTCostEquality: Prim and Kruskal produce same cost

testMSTEdgeCount: MST has exactly V-1 edges

testMSTConnectivity: All vertices connected

testMSTAcyclic: No cycles in MST

testEmptyGraphHandling: Edge case handling

Performance Tests
testExecutionTimeMeasurement: Time measurement accuracy

testOperationCounts: Operation counting validity

testConsistency: Reproducible results


Comparison Table (CSV format)
| Category | GraphID | Vertices | Edges | Prim_Cost | Kruskal_Cost | Prim_Time_ms | Kruskal_Time_ms | Prim_Operations | Kruskal_Operations |

Performance Analysis
The system provides comprehensive analysis:

Theoretical Comparison
Aspect	Prim's Algorithm	Kruskal's Algorithm
Time Complexity	O(E log V)	O(E log E)
Space Complexity	O(V + E)	O(E)
Best Case	Dense graphs	Sparse graphs
Data Structure	Priority Queue	Union-Find + Sorting
Practical Insights
Small Graphs: Both algorithms perform similarly

Medium Graphs: Prim's often faster due to better cache performance

Large Graphs: Kruskal's may excel with sparse graphs

Extra Large: Implementation optimizations become critical


# Generate and analyze all test cases
java Main
Custom Analysis
```
// Generate specific graph
Graph graph = GraphGenerator.generateConnectedGraph(10, 15);

// Compare algorithms
MSTResult prim = MSTAlgorithms.primsAlgorithm(graph);
MSTResult kruskal = MSTAlgorithms.kruskalsAlgorithm(graph);

System.out.printf("Prim: %d in %.3fms%n", prim.getTotalCost(), prim.getExecutionTime());
System.out.printf("Kruskal: %d in %.3fms%n", kruskal.getTotalCost(), kruskal.getExecutionTime());
Customization
Modifying Graph Sizes
Edit the createComprehensiveTestDatasets() method in GraphGenerator class:


// Custom graph configurations
Map<String, List<Graph>> customConfigs = new HashMap<>();
customConfigs.put("tiny_graphs", Arrays.asList(
    generateConnectedGraph(3, 3),
    generateConnectedGraph(4, 4)
));
Adding New Metrics
Extend the algorithm classes to track additional metrics:

java
public class EnhancedMSTAlgorithms {
    // Add memory usage tracking
    // Add cache performance metrics
    // Add detailed step-by-step analysis
} 
```
Expected Results
Correctness Verification
MST costs identical for both algorithms

All MSTs have V-1 edges

All MSTs are connected and acyclic

Consistent results across multiple runs

Performance Patterns
Prim's excels with dense graphs

Kruskal's excels with sparse graphs

Linear scaling with graph size

Predictable operation counts

Troubleshooting
Common Issues
Memory errors with large graphs

Solution: Use sparse data structures

Increase JVM heap size: java -Xmx2g Main

Performance slower than expected

Check: Graph connectivity verification

Optimize: Priority queue operations

Test failures

Verify: Graph generation logic

Check: Union-Find implementation

Debug Mode
java 
// Enable detailed logging
System.setProperty("java.util.logging.config.file", "logging.properties");
Theoretical Background
Minimum Spanning Tree
Connects all vertices with minimum total edge weight

Contains no cycles

Applications: Network design, clustering, approximation algorithms

Algorithm Selection Guide
Use Prim's when: Graph is dense, using adjacency matrix

Use Kruskal's when: Graph is sparse, edges are pre-sorted

Considerations: Memory constraints, implementation complexity

Team & Contribution
This implementation follows academic best practices:

Clean, documented code

Comprehensive testing

Performance optimization

Academic integrity compliance

License
Academic Use - Please adhere to your institution's honor code and citation requirements.

References
Cormen, T. H., et al. "Introduction to Algorithms"

Sedgewick, R., & Wayne, K. "Algorithms"

Java Documentation: PriorityQueue, Collections, Arrays
