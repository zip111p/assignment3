# Assignment 3: Optimization of City Transportation Network
## Minimum Spanning Tree Algorithms Implementation

### Project Overview
This project implements **Prim's** and **Kruskal's** algorithms to solve the Minimum Spanning Tree (MST) problem for optimizing city transportation networks. The system determines the most cost-effective set of roads that connect all city districts while minimizing total construction costs.

### Objective
- Model city districts as graph vertices and potential roads as weighted edges
- Find the minimum set of roads that connects all districts with lowest cost
- Compare performance and efficiency of Prim's vs Kruskal's algorithms
- Analyze algorithmic behavior across different graph sizes and densities

### Project Structure
assignment_3/
│
├──  mst_assignment.py # Main Python implementation
├──  requirements.txt # Project dependencies
├──  assign_3_input.json # Generated test datasets (auto)
├──  assign_3_output.json # Algorithm results (auto)
├──  comparison_table.csv # Performance comparison (auto)
├──  README.md # Project documentation
├──  sample_input_structure.json # Input format example
│
├──  java/ # Bonus Java implementation
│ ├── Graph.java
│ ├── Edge.java
│ └── MSTMain.java
│
└──  report/ # Analysis report
├── performance_analysis.pdf
└── algorithm_comparison.docx

text

###  Installation & Setup

#### Python Environment
```bash
# Clone or download the project files
# Navigate to project directory
cd assignment_3

# Verify Python version (3.7+ required)
python --version

# No external dependencies needed - uses only standard library
Java Bonus (Optional)
bash
cd java
javac Graph.java Edge.java MSTMain.java
java MSTMain
 How to Run
Complete Analysis
bash
python mst_assignment.py
This will:

 Generate test datasets of various sizes

 Run both MST algorithms on all graphs

 Measure performance metrics and operation counts

 Generate output files and comparison tables

 Run automated tests

 Display comprehensive summary

Run Tests Only
bash
python -m unittest mst_assignment.MSTTest -v
Specific Graph Testing
python
# Custom testing example
from mst_assignment import Graph, MSTAlgorithms

# Create custom graph
edges = [(0, 1, 4), (0, 2, 1), (1, 2, 2), (1, 3, 5)]
graph = Graph(4, edges)

# Run algorithms
prim_result = MSTAlgorithms.prims_algorithm(graph)
kruskal_result = MSTAlgorithms.kruskals_algorithm(graph)
 Test Datasets
The system automatically generates 4 categories of graphs:

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

 Automated Testing
The test suite verifies:

Correctness Tests
python
def test_mst_cost_equality()          # Prim and Kruskal produce same cost
def test_mst_edge_count()             # MST has exactly V-1 edges
def test_mst_connectivity()           # All vertices connected
def test_mst_acyclic()                # No cycles in MST
def test_empty_graph_handling()       # Edge case handling
Performance Tests
python
def test_execution_time_measurement() # Time measurement accuracy
def test_operation_counts()           # Operation counting validity
def test_consistency()               # Reproducible results
 Generated Files
Input Data (assign_3_input.json)
json
{
  "small_graphs": [
    {
      "id": "small_1",
      "vertices": 5,
      "edges": 7,
      "graph": [[0,1,4], [0,2,1], [1,2,2], ...]
    }
  ],
  "medium_graphs": [...],
  "large_graphs": [...],
  "extra_large_graphs": [...]
}
Output Results (assign_3_output.json)
json
{
  "small_graphs": [
    {
      "graph_id": "small_1",
      "vertices": 5,
      "edges": 7,
      "prim_algorithm": {
        "mst_edges": [[0,2,1], [2,1,2], ...],
        "total_cost": 12,
        "execution_time": 0.045,
        "operation_count": 56
      },
      "kruskal_algorithm": {...}
    }
  ]
}
Comparison Table (comparison_table.csv)
Category	GraphID	Vertices	Edges	Prim_Cost	Kruskal_Cost	Prim_Time_ms	Kruskal_Time_ms	...
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

 Key Features
Comprehensive Implementation
 Complete Prim's algorithm with priority queue

 Complete Kruskal's algorithm with Union-Find

 Operation counting for detailed analysis

 Memory-efficient data structures

Robust Testing
 20+ automated test cases

 Edge case handling (empty graphs, single vertex)

 Performance validation

 Correctness verification

Analysis Tools
 Multiple graph generation strategies

 Performance measurement framework

 Comparative analysis output

 Scalability testing

 Usage Examples
Basic Usage
python
# Generate and analyze all test cases
python mst_assignment.py
Custom Analysis
python
from mst_assignment import GraphGenerator, MSTAlgorithms

# Generate specific graph
graph = GraphGenerator.generate_connected_graph(10, 15)

# Compare algorithms
prim = MSTAlgorithms.prims_algorithm(graph)
kruskal = MSTAlgorithms.kruskals_algorithm(graph)

print(f"Prim: {prim['total_cost']} in {prim['execution_time']}ms")
print(f"Kruskal: {kruskal['total_cost']} in {kruskal['execution_time']}ms")
 Customization
Modifying Graph Sizes
Edit the create_comprehensive_test_datasets() method in GraphGenerator class:

python
# Custom graph configurations
custom_configs = {
    "tiny_graphs": [(3, 3), (4, 4), (5, 6)],
    "huge_graphs": [(5000, 8000), (10000, 15000)]
}
Adding New Metrics
Extend the algorithm classes to track additional metrics:

python
def prims_algorithm_enhanced(graph):
    # Add memory usage tracking
    # Add cache performance metrics
    # Add detailed step-by-step analysis
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

Increase Python heap size if needed

Performance slower than expected

Check: Graph connectivity verification

Optimize: Priority queue operations

Test failures

Verify: Graph generation logic

Check: Union-Find implementation

Debug Mode
python
# Enable detailed logging
import logging
logging.basicConfig(level=logging.DEBUG)
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

Python Documentation: heapq, time, unittest modules

 Academic Integrity Notice: This implementation is provided for educational purposes. Students should understand the algorithms and be able to explain the implementation details during defense sessions.

text

This comprehensive README provides:
- ✅ Complete setup and execution instructions
- ✅ Detailed explanation of all features
- ✅ Usage examples and customization guide
- ✅ Theoretical background and performance analysis
- ✅ Troubleshooting and academic guidelines

The documentation is professional, thorough, and suitable for academic submission.
