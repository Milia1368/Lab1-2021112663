package test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//import test1.WeightedDirectedGraph;
public class Main {
    public static void main(String[] args) {
        File inputFile = new File("D:/VScode/java实验/test1/input.txt");

        try {
            ParserWord parser = new ParserWord();
            Scanner scanner = new Scanner(inputFile);
            WeightedDirectedGraph graph = new WeightedDirectedGraph();
            //单词提取
            String line = scanner.nextLine();
            ArrayList<String> wordslist = new ArrayList<>();
            wordslist.addAll(parser.parseWords(line));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                wordslist.addAll(parser.parseWords(line));
            }
            scanner.close();
            //构图
            while (wordslist.size() >= 2) {
                graph.addEdge(wordslist.get(0), wordslist.get(1), 1);
                wordslist.remove(0);
            }
            //给定句子
            String linetest="seek to explore new and exciting synergies";
            wordslist = new ArrayList<>();
            wordslist.addAll(parser.parseWords(linetest));
            //功能测试
            //展示
             // graph.displayGraph();
            //桥接词
            QuerryBrige.brige(graph, "seek", "to");
            QuerryBrige.brige(graph, "to", "explore");
            QuerryBrige.brige(graph, "explore", "new");
            QuerryBrige.brige(graph, "new", "and");
            QuerryBrige.brige(graph, "exciting", "synergies");
            //生成新文本
            // QuerryBrige.generateText(graph, "seek", "to", 10);
            //插入文本测试
            QuerryBrige.generateText(graph, wordslist.toArray(new String[0])); 
        //小零件测试
            // graph.getNode("new");
           
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}

class ParserWord {
    public List<String> parseWords(String input) {
        // 空格、制表符、换行符、回车符、换页符、以及标点符号作为分隔符
        String[] words = input.toLowerCase().split("[ \\t\\n\\x0B\\f\\r\\p{P}]+");
        return Arrays.asList(words);
    }
}

class QuerryBrige {

    public static void brige(WeightedDirectedGraph graph, String start, String end) {
        int flag = 0;
        String[] brigeStrings = new String[100];
        int brigeNum = 0;
        if (graph.containsNode(start)) {
            flag = 1;
        }
        if (graph.containsNode(end)) {
            flag = flag + 2;
        }
        if (flag == 3) {
            for (String i : graph.getNode(start)) {
                if (graph.getEdgeWeight(i, end) != -1) {
                    brigeStrings[brigeNum++] = i;
                }
            }
            if (brigeNum !=0) {
                System.out.printf("The bridge words from \"%s\" to \"%s\" is: ", start, end);
                if(brigeNum == 1) {
                    System.out.printf("%s\n", brigeStrings[0]);
                    return;
                }
                for (int i = 0; i < brigeNum - 1; i++) {
                    System.out.printf("%s、", brigeStrings[i]);
                }
                System.out.printf("and %s\n", brigeStrings[brigeNum - 1]);
            } else {
                System.out.println("No bridge words from \"" + start + "\" to \"" + end + "\" !");
            }
        } else {
            if (flag == 1) {
                System.out.println("No \"" + start + "\" in the graph!");
            } else if (flag == 2) {
                System.out.println("No \"" + end + "\" in the graph!");
            } else {
                System.out.println("No \"" + start + "\" and \"" + end + "\" in the graph!");
            }
        }
    }
    public static String getbrige(WeightedDirectedGraph graph, String start, String end) {
        int flag = 0;        
        if (graph.containsNode(start)) {
            flag = 1;
        }
        if (graph.containsNode(end)) {
            flag = flag + 2;
        }
        if (flag != 3) {return null;}
        if (flag == 3) {
            for (String i : graph.getNode(start)) {
                if (graph.getEdgeWeight(i, end) != -1) {
                    return i;
                }
            }  
        } 
        return null;
    }


    public static void generateText(WeightedDirectedGraph graph, String[] text) {
        int size=text.length;
        int i=0;
        int j=0;
        String[] newText=new String[2*size];
        if (size<2) return;
        while(i<size-1){
            newText[j++]=text[i];
            String brige=getbrige(graph,text[i],text[i+1]);
            if(brige!=null){
                newText[j++]=brige;
                }
                i++;}
            newText[j++]=text[i];
            //打印旧文本
            for (String string : text) {                
                System.out.print(string+" ");
            }
            System.out.println();
            //打印新文本
            for (String string : newText) {
                System.out.print(string+" ");
                j--;
                if(j==0) break;
            }
            System.out.println();
}
}
class ShortestPath {
    public static void shortestPath(WeightedDirectedGraph graph, String start, String end) {
        int flag = 0;
        if (graph.containsNode(start)) {
            flag = 1;            
        }
        if (graph.containsNode(end)) {
            flag = flag + 2;
        }
        if (flag == 3) {
            int[] dist = new int[graph.nodeNum()];
            boolean[] visited = new boolean[graph.nodeNum()];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visited, false);
            dist[graph.getNodeIndex(start)] = 0;
            for (int i = 0; i < graph.nodeNum(); i++) {
                int minDist = Integer.MAX_VALUE;
                int minIndex = -1;
                for (int j = 0; j < graph.nodeNum(); j++) {
                    if (!visited[j] && dist[j] < minDist) {
                        minDist = dist[j];
                        minIndex = j;
                    }
                }
                if (minIndex == -1) {
                    break;
                }
                visited[minIndex] = true;
                for (int j = 0; j < graph.nodeNum(); j++) {
                    if (!visited[j] && graph.getEdgeWeight(minIndex, j) != -1) {
                        int newDist = dist[minIndex] + graph.getEdgeWeight(minIndex, j);
                        if (newDist < dist[j]) {
                            dist[j] = newDist;
                        }
                    }
                }
            }
            if (dist[graph.getNodeIndex(end)] == Integer.MAX_VALUE) {
                System.out.println("No path from \"" + start + "\" to \"" + end + "\" !");
            } else {
                System.out.printf("The shortest path from \"%s\" to \"%s\" is: %d\n", start, end, dist[graph.getNodeIndex(end)]);
            }
        } else {
            if (flag == 1) {
                System.out.println("No \"" + start + "\" in the graph!");
            } else if (flag == 2) {
                System.out.println("No \"" + end + "\" in the graph!");
            } else {
                System.out.println("No \"" + start + "\" and \"" + end + "\" in the graph!");
            }
        }
    }
}