import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test1.WeightedDirectedGraph;

import static org.junit.jupiter.api.Assertions.*;
class lab1Test1 {

    @Test
    @DisplayName("randomWalk_start不存在")
    void randomWalk() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1.createGraph(graph, "I am a cat,I am an apple.");
        assertEquals(null, lab1.randomWalk(graph,"you"));
      }

    @Test
    @DisplayName("randomWalk_全部")
    void testRandomWalk() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1.createGraph(graph, "I am a cat,I ");
        //lab1.showDirectedGraph(graph);
        assertEquals("i am a cat i am ", lab1.randomWalk(graph,"I"));
      }

    @Test
    @DisplayName("randomWalk_邻居为空")
    void testRandomWalk1() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1.createGraph(graph, "I am a cat,I am an apple.");
        assertEquals("an apple ", lab1.randomWalk(graph,"an"));
      }
    @Test
    @DisplayName("randomWalk_有q")
    void testRandomWalk2() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1.createGraph(graph, "I am a cat,I am an apple.");
        assertEquals("i ", lab1.randomWalk(graph,"I"));
    }
    @Test
    @DisplayName("randomWalk_810")
    void testRandomWalk3() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1.createGraph(graph, "I am I ");
        assertEquals("i am i am ", lab1.randomWalk(graph,"I"));
    }
}