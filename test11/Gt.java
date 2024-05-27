package test1;

import java.io.File;
import java.io.IOException;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.file.FileSinkImages;
import java.util.Map;
import java.util.HashMap;

public class WeightedDirectedGraph {
    private Map<String, Map<String, Integer>> graph;
    private Graph visualGraph;

    public WeightedDirectedGraph() {
        graph = new HashMap<>();
        visualGraph = new SingleGraph("WeightedDirectedGraph");
    }

    public void addNode(String node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new HashMap<>());
            visualGraph.addNode(node);
        }
    }

    public void addEdge(String source, String destination, int weight) {
        int w = getEdgeWeight(source, destination);
        if (w != -1)
            visualGraph.addEdge(source + "-" + destination, source, destination, true).setAttribute("weight", w + weight);
        else {
            if (!graph.containsKey(source)) {
                addNode(source);
            }
            if (!graph.containsKey(destination)) {
                addNode(destination);
            }
            visualGraph.addEdge(source + "-" + destination, source, destination, true).setAttribute("weight", weight);
        }
        graph.get(source).put(destination, weight);
    }

    public int getEdgeWeight(String source, String destination) {
        if (graph.containsKey(source) && graph.get(source).containsKey(destination)) {
            return graph.get(source).get(destination);
        } else {
            return -1;
        }
    }

    public void generateGraphImage() {
        visualGraph.display();
        FileSinkImages picOut = new FileSinkImages(FileSinkImages.OutputType.PNG, FileSinkImages.Resolutions.VGA);
        File output = new File("weighted_graph.png");
        try {
            picOut.writeAll(visualGraph, output.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}