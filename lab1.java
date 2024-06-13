import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import test1.WeightedDirectedGraph;

public class lab1 {
  //static WeightedDirectedGraph graph;

  static void createGraph(WeightedDirectedGraph graph, String text) { // 构图
    ParserWord parser = new ParserWord();
    ArrayList<String> wordslist = new ArrayList<>(parser.parseWords(text));
    // 图的构建
    //graph = new WeightedDirectedGraph();
    while (wordslist.size() >= 2) {
      graph.addEdge(wordslist.get(0), wordslist.get(1), 1);
      wordslist.remove(0);
    }
  }

  static void showDirectedGraph(WeightedDirectedGraph graph) { // 展示有向图
    graph.displayGraphPicture(null);
  }

  static String queryBridgeWords(WeightedDirectedGraph graph, String word1, String word2) { // 查询桥接词
     String x = graph.findBridgeWords(word1, word2);
    // System.out.println(x);
     return x;
  }

  static String generateNewText(WeightedDirectedGraph graph, String inputText) { // 生成新文本
    ParserWord parser = new ParserWord();
    ArrayList<String> wordslist = new ArrayList<>();
    wordslist.addAll(parser.parseWords(inputText));
    return graph.generateText(wordslist.toArray(new String[wordslist.size()]));
  }

  static String calcShortestPath(WeightedDirectedGraph graph, String word1, String word2) { // 计算两个词的最短路径
    return graph.shortestPath(word1, word2);
  }

  static String randomWalk(WeightedDirectedGraph graph) { // 随机游走
    int randomIndex = new Random().nextInt(graph.getSize());
    String x = graph.walkBian(graph.getNodeName(randomIndex));
    String path = "./randomwalk.txt"; // 指定文件路径

    try {
      // 将字符串写入文件，StandardCharsets.UTF_8指定编码
      Files.writeString(Paths.get(path), x);
      System.out.println("随机游走路径成功写入文件！");
    } catch (IOException e) {
      System.err.println("写入文件时发生错误: " + e.getMessage());
    }
    return x;
  }

  // 主函数
  public static void main(String[] args) {
    System.out.println("Hello, world!");
    String fileName;

    if (args.length == 0) {
      fileName = ".\\input1.txt";
    } else {
      fileName = args[0];
    }

    try {
      // 一次性将整个文件读入字节数组
      byte[] bytes = Files.readAllBytes(Paths.get(fileName));
      // 将字节数组转换为字符串
      String text = new String(bytes);
      WeightedDirectedGraph graph = new WeightedDirectedGraph();
      WeightedDirectedGraph graph1 = new WeightedDirectedGraph();
      createGraph(graph, text);

      // 创建图完毕
      // 提示选择相应功能
      while (true) {
        System.out.println("请输入相应功能：");
        System.out.println("1. 展示有向图");
        System.out.println("2. 查询桥接词");
        System.out.println("3. 生成新文本");
        System.out.println("4. 计算两个词的最短路径");
        System.out.println("5. 随机游走");
        System.out.println("0. 退出");
        //int choice = Integer.parseInt(System.console().readLine());
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        try {
          choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
          System.out.println("输入无效！请确保您输入的是一个整数。");
        }
        switch (choice) {
          case 0:
            // 退出程序
            System.exit(0);
            break;
          case 1:
            showDirectedGraph(graph);
            break;
          case 2:
            System.out.println("请输入起始词：");
            String start = scanner.nextLine();
            System.out.println("请输入终止词：");
            String end = scanner.nextLine();
            queryBridgeWords(graph, start.toLowerCase(), end.toLowerCase());
            break;
          case 3:
            System.out.println("请输入文本：");
            String inputText = scanner.nextLine();
            generateNewText(graph, inputText);
            break;
          case 4:
            System.out.println("请输入起始词：");
            String start3 = scanner.nextLine();
            System.out.println("请输入终止词：");
            String end3 = scanner.nextLine();
            calcShortestPath(graph, start3.toLowerCase(), end3.toLowerCase());
            break;
          case 5:
            randomWalk(graph);
            break;
          default:
            System.out.println("输入错误，请重新输入！");
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class ParserWord { // 完成文本划分为单词
  public List<String> parseWords(String input) {
    // 空格、制表符、换行符、回车符、换页符、以及标点符号作为分隔符
    String[] words = input.toLowerCase().split("[ \\t\\n\\x0B\\f\\r\\p{P}]+");
    return Arrays.asList(words);
  }
}
