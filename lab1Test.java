import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test1.WeightedDirectedGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class lab1Test {

    @Test
    @DisplayName("queryBridgeWords_正常")
    void queryBridgeWords_1() throws IOException {
        String fileName = ".\\input1.txt";
        lab1 lab1tm = new lab1();
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        // 将字节数组转换为字符串
        String text = new String(bytes);
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1tm.createGraph(graph, text);
        assertEquals("worlds、civilizations",lab1.queryBridgeWords(graph,"new", "to"));//正常
      }
      @Test
      @DisplayName("queryBridgeWords_start找不到")
    void queryBridgeWords_2() throws IOException {
        String fileName = ".\\input1.txt";
        lab1 lab1tm = new lab1();
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        // 将字节数组转换为字符串
        String text = new String(bytes);
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1tm.createGraph(graph, text);
        assertEquals(null,lab1.queryBridgeWords(graph,"lovw", "new"));//start找不到
    }
    @Test
    @DisplayName("queryBridgeWords_相反")
    void queryBridgeWords_3() throws IOException {
        String fileName = ".\\input1.txt";
        lab1 lab1tm = new lab1();
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        // 将字节数组转换为字符串
        String text = new String(bytes);
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1tm.createGraph(graph, text);
        assertEquals(null,lab1.queryBridgeWords(graph,"new", "life"));//相反
    }
    @Test
    @DisplayName("queryBridgeWords_非字符")
    void queryBridgeWords_4() throws IOException {
        String fileName = ".\\input1.txt";
        lab1 lab1tm = new lab1();
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        // 将字节数组转换为字符串
        String text = new String(bytes);
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        lab1tm.createGraph(graph, text);
        assertEquals(null,lab1.queryBridgeWords(graph,"11", "22"));//非字符
}
}