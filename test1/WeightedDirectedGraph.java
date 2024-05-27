package test1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.zip.Inflater;
import java.util.Random;

import org.w3c.dom.Node;

public class WeightedDirectedGraph {
    private  Map<String, Map<String, Integer>> graph;//图是通过list实现的，每个节点用一个map表示，key是邻接节点，value是权重
    private  Map<String, Integer> graphnode;//用于建立节点名称和邻接矩阵编号对应关系
    public int[][] adjTable;
    public int[][] tempadjTable;
    int INF = Integer.MAX_VALUE;
    public WeightedDirectedGraph() {
        graph = new HashMap<>();
        graphnode = new HashMap<>();
    }
    //简单判断操作
    public int getSize() {   //返回图的节点数
        return graph.size();
    }
    public int getNodeIndex(String node) {   //返回节点在邻接矩阵中的索引
        if (graphnode.containsKey(node)) {
            return graphnode.get(node);
        } else {
            return -1;
        }
    }
    public String getNodeName(int index) {   //返回节点名称
        // System.out.println(graphnode.size());
        // System.out.println(graphnode.entrySet());
        // System.out.println(graphnode.keySet());
        for (Map.Entry<String, Integer> entry : graphnode.entrySet()) {
            // System.out.println(entry);
            // System.out.println(entry.getKey() + " " + entry.getValue());
            if (entry.getValue() == index) {
                return entry.getKey();
            }
    }
    return null;
}
    //节点度是否大于0
    public boolean isConnected(String node) {
        if (graph.containsKey(node)) {
            return graph.get(node).size() > 0;
        } else {
            return false;
        }
    }
     
    public boolean containsNode(String node) {
        return graph.containsKey(node);
    }

    public boolean hasPath(String source, String destination) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            return false;
        }
        Map<String, Integer> edges = graph.get(source);
        if (edges.containsKey(destination)) {
            return true;
        }
        for (String node : edges.keySet()) {
            if (hasPath(node, destination)) {
                return true;
            }
    }
    return false;
}
    //基本插入删除节点操作

    public void addNode(String node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new HashMap<>());
            graphnode.put(node, graph.size()-1);
        }
    }
 public String[] getNode(String node) {
    if (graph.containsKey(node)) {
        Map<String, Integer> edges = graph.get(node);
        if(edges.size()==0)
            return null;
        String[] keys = edges.keySet().toArray(new String[0]);
       // System.out.println(keys);
        return keys;
    } else {
        System.out.println("Node not found");
    return null;} 

 }  
 // 获取当前节点的随机邻居
 public String getRandomNeighbor(String current) {
    String[] neighbors =getNode(current);
    if (neighbors==null)
        return null;
    int randomIndex = new Random().nextInt(neighbors.length);
    return neighbors[randomIndex];

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
    // public String getNode(String source, String destination) {
    //     if ( graph.get(source).containsKey(destination)) {
    //         return source + "->" + destination + " weight: " + graph.get(source).get(destination);
    //     } else {
    //         //hrow new IllegalArgumentException("Edge not found"); // 根据需求抛出异常
    //         return "Edge not found";
    //     }
    // }
    //展示图
    public void displayGraph() {
        System.out.println();
        System.out.println("图："); 
        for (String source : graph.keySet()) {
            System.out.println( source + " --:");
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                System.out.println("  -> " + entry.getKey() + " weight: " + entry.getValue());
            }
        }
    }
    public void displayGraphMatrix() {
        System.out.println();
        System.out.println("邻接矩阵：");
        for (int i=0;i<adjTable.length;i++){
            for (int j=0;j<adjTable.length;j++){
                if (adjTable[i][j]==INF)
                    System.out.print("0"+" ");
                else    
                System.out.print(adjTable[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void displayGraphPicture(String filePath) {
        D3api d3api=new D3api();
        System.out.println();
        System.out.println("图片："); 
        for (String source : graph.keySet()) {
            //System.out.println( source + " --:");
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                //System.out.println("  -> " + entry.getKey() + " weight: " + entry.getValue());
                d3api.addPoints(source,entry.getKey(),String.valueOf(entry.getValue()));
            }
        }
        d3api.show(filePath);
    }
    public void displayPathPicture(String filePath) {
        D3api d3api=new D3api();
        System.out.println();
        System.out.println("图片："); 
        for (String source : graph.keySet()) {
            //System.out.println( source + " --:");
            int i=getNodeIndex(source);
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                //System.out.println("  -> " + entry.getKey() + " weight: " + entry.getValue());
                int j=getNodeIndex(entry.getKey());
                String wei=String.valueOf(entry.getValue());
                if(istempadjTable(i,j)==1) { wei=wei.concat("SIG");}
                d3api.addPoints(source,entry.getKey(),String.valueOf(wei));
            }
        }
        d3api.show(filePath);
    }
    //改成邻接矩阵
    private void adjTable() {
       
        int size=graph.size();
        adjTable=new int[size][size];
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                adjTable[i][j]=INF;    }
            }

        for (String source : graph.keySet()) {
            Map<String, Integer> edges = graph.get(source);
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                int weight=entry.getValue();
                int dest=graphnode.get(entry.getKey());
                adjTable[graphnode.get(source)][dest]=weight;
            }   
        }
    }
    //为了记录ij是否被访问过，用于高亮路径
    public void gettempadjTable() {
        int size=graph.size();
        tempadjTable=new int[size][size];
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                tempadjTable[i][j]=0;    }
            }
        }
    public void settempadjTable(int i,int j) {
        tempadjTable[i][j]=1;
    }
    public int istempadjTable(int i,int j) {
        return tempadjTable[i][j]==1?1:0;
    }
    public int[][] getadjTable() {//返回邻接矩阵,inf表示不存在边
        adjTable();
        
        return adjTable;
}

}
