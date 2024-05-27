package test1;

public class test {
    public static void main(String[] args) {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        
        // Adding nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");

        // Adding edges
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 3);
        graph.addEdge("A", "C", 10);

        // Testing getEdgeWeight
        System.out.println("Weight from A to B: " + graph.getEdgeWeight("A", "B")); // Expected: 5
        System.out.println("Weight from B to C: " + graph.getEdgeWeight("B", "C")); // Expected: 3
        System.out.println("Weight from A to C: " + graph.getEdgeWeight("A", "C")); // Expected: 10

        // Adding additional weight to an existing edge
        graph.addEdge("A", "B", 2);
        System.out.println("Updated weight from A to B: " + graph.getEdgeWeight("A", "B")); // Expected: 7

        // Testing edge that does not exist
        System.out.println("Weight from C to A: " + graph.getEdgeWeight("C", "A")); // Expected: -1 (or throws an exception if uncommented)
    }
}