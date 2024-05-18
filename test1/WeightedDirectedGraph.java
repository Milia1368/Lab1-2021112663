package test1;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class WeightedDirectedGraph {
    private Map<String, Map<String, Integer>> graph;
    private Map<String, Integer> graphnode;
    public WeightedDirectedGraph() {
        graph = new HashMap<>();
    }

    public boolean containsNode(String node) {
        return graph.containsKey(node);
    }
    public void addNode(String node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new HashMap<>());
        }
    }
 public String[] getNode(String node) {
    if (graph.containsKey(node)) {
        Map<String, Integer> edges = graph.get(node);
        String[] keys = edges.keySet().toArray(new String[0]);
        System.out.println(keys);
        return keys;
    } else {
        System.out.println("Node not found");
    return null;} 

 }  
    public void addEdge(String source, String destination, int weight) {
        int w=getEdgeWeight(source, destination);
        if(w!=-1)
            graph.get(source).put(destination, w+weight);
            else{
        if (!graph.containsKey(source)) {
            addNode(source);
        }
        if (!graph.containsKey(destination)) {
            addNode(destination);
        }
        graph.get(source).put(destination, weight);
            }}

    public int getEdgeWeight(String source, String destination) {
        if (graph.containsKey(source) && graph.get(source).containsKey(destination)) {
            return graph.get(source).get(destination);
        } else {
            //hrow new IllegalArgumentException("Edge not found"); // 根据需求抛出异常
            return -1;
        }
    }
    public String getNode(String source, String destination) {
        if ( graph.get(source).containsKey(destination)) {
            return source + "->" + destination + " weight: " + graph.get(source).get(destination);
        } else {
            //hrow new IllegalArgumentException("Edge not found"); // 根据需求抛出异常
            return "Edge not found";
        }
    }

    public void displayGraph() {
        for (String source : graph.keySet()) {
            System.out.println( source + " --:");
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                System.out.println("  -> " + entry.getKey() + " weight: " + entry.getValue());
            }
        }
    }

    public void adjTable() {
        int INF = Integer.MAX_VALUE;
        int size=graph.size();
        int[][] table=new int[size][size];
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                table[i][j]=INF;    }
            }

        for (String source : graph.keySet()) {
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                int weight=entry.getValue();
                int dest=graphnode.get(entry.getKey());
                table[graphnode.get(source)][dest]=weight;
            }   
        }
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }



}
