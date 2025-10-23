# City Transportation Network Optimization

## Project Overview
This project implements Prim's and Kruskal's algorithms to solve the Minimum Spanning Tree (MST) problem for optimizing city transportation networks. The system determines the most cost-effective set of roads that connect all city districts while minimizing total construction costs.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Algorithms](#algorithms)
- [Input/Output Format](#inputoutput-format)
- [Testing](#testing)
- [Performance Analysis](#performance-analysis)
- [Results](#results)
- [Requirements Fulfilled](#requirements-fulfilled)

## Features
- Prim's Algorithm with priority queue optimization
- Kruskal's Algorithm with Union-Find and path compression
- Automatic dataset generation (small, medium, large graphs)
- Comprehensive testing with JUnit
- Performance analysis with CSV reports
- JSON input/output processing
- Professional code structure and documentation

## Project Structure
mst-transportation/
├── src/
│ └── main/
│ └── java/
│ └── com/
│ └── transportation/
│ ├── Main.java # Application entry point
│ ├── model/ # Data transfer objects
│ │ ├── Edge.java
│ │ ├── Graph.java
│ │ ├── InputData.java
│ │ ├── MSTResult.java
│ │ ├── GraphResult.java
│ │ ├── OutputData.java
│ │ └── InputStats.java
│ ├── algorithm/ # MST implementations
│ │ ├── PrimAlgorithm.java
│ │ ├── KruskalAlgorithm.java
│ │ └── UnionFind.java
│ └── util/ # Utilities
│ ├── JsonFileProcessor.java
│ ├── DataGenerator.java
│ └── PerformanceAnalyzer.java
├── src/test/java/com/transportation/ # Unit tests
│ └── MSTAlgorithmTest.java
├── data/ # Input/Output datasets
│ ├── input/
│ │ ├── assign_3_input.json
│ │ ├── small_graphs.json
│ │ ├── medium_graphs.json
│ │ └── large_graphs.json
│ └── output/
│ ├── assign_3_output.json
│ └── performance_analysis.csv
├── README.md
└── pom.xml

## Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Git

### Setup
```bash
# Clone the repository
git clone <your-repository-url>
cd mst-transportation

# Compile the project
mvn clean compile
Usage
Running the Application
# Generate datasets and run analysis
mvn exec:java -Dexec.mainClass="com.transportation.Main"

# Run tests
mvn test

# View results
ls -la data/output/
cat data/output/assign_3_output.json
Example Output
json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 5,
        "edges": 7
      },
      "prim": {
        "mst_edges": [
          {"from": "B", "to": "C", "weight": 2},
          {"from": "A", "to": "C", "weight": 3},
          {"from": "B", "to": "D", "weight": 5},
          {"from": "D", "to": "E", "weight": 6}
        ],
        "total_cost": 16,
        "operations_count": 42,
        "execution_time_ms": 1.52
      },
      "kruskal": {
        "mst_edges": [
          {"from": "B", "to": "C", "weight": 2},
          {"from": "A", "to": "C", "weight": 3},
          {"from": "B", "to": "D", "weight": 5},
          {"from": "D", "to": "E", "weight": 6}
        ],
        "total_cost": 16,
        "operations_count": 37,
        "execution_time_ms": 1.28
      }
    }
  ]
}
Algorithms
Prim's Algorithm
Approach: Greedy algorithm that grows MST from an arbitrary starting node

Data Structure: Priority queue (min-heap)

Time Complexity: O(E + V log V)

Best For: Dense graphs

Kruskal's Algorithm
Approach: Sort all edges and add them to MST if they don't form cycles

Data Structure: Union-Find with path compression

Time Complexity: O(E log E)

Best For: Sparse graphs

Input/Output Format
Input JSON Structure

{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2}
      ]
    }
  ]
}
Output JSON Structure
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 5,
        "edges": 7
      },
      "prim": {
        "mst_edges": [...],
        "total_cost": 16,
        "operations_count": 42,
        "execution_time_ms": 1.52
      },
      "kruskal": {
        "mst_edges": [...],
        "total_cost": 16,
        "operations_count": 37,
        "execution_time_ms": 1.28
      }
    }
  ]
}
Testing
Test Categories
Correctness Tests: MST properties, cycle detection, connectivity

Performance Tests: Execution time, operation counts, scalability

Edge Cases: Single vertex, empty graphs, disconnected graphs

Test Datasets
Category	Vertices	Graphs	Purpose
Small	4-6	10	Correctness verification
Medium	10-15	10	Moderate performance
Large	20-30	15	Scalability analysis

Running Tests
mvn test
Performance Analysis
Metrics Collected
Total MST Cost: Construction cost (identical for both algorithms)

Execution Time: Real-time performance in milliseconds

Operation Count: Algorithmic steps and comparisons

Expected Performance
Graph Size	Prim Time	Kruskal Time	Prim Ops	Kruskal Ops
Small	~0.5-1.0ms	~0.3-0.8ms	~40-60	~30-50
Medium	~1.0-2.0ms	~0.7-1.5ms	~150-300	~100-250
Large	~2.0-5.0ms	~1.5-3.0ms	~500-1000	~400-800

Results
Algorithm Comparison
Aspect	Prim's Algorithm	Kruskal's Algorithm
Approach	Vertex-based	Edge-based
Best Case	Dense graphs	Sparse graphs
Time Complexity	O(E + V log V)	O(E log E)
Implementation	More complex	Simpler

Key Findings
Cost Consistency: Both algorithms produce identical MST costs

Performance: Kruskal generally faster for sparse graphs

Operations: Prim has more comparisons, Kruskal has more union operations

Course: Design and Analytic of Methods