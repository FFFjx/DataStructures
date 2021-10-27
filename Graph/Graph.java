package Graph;

import java.util.ArrayList;
import LIST.MyArrayQueue;
import LIST.MyArrayStack;

public class Graph {//单向图的DFS,BFS
    public ArrayList<Vertex1> edgeSet;
    public int vernum;
    public int edgnum;
    public String[] vertexSet;
    private static final int DEFAULT_CAPACITY=100;

    public Graph(){
        this(null,null);
    }
    //新建一个图，输入是顶点集和边集
    public Graph(String[] vertexSet,int[][] edgeSet){
        //输入String[] vertexSet转化为Graph里面的String[] vertexSet
        vernum=vertexSet.length;
        int capacity=DEFAULT_CAPACITY;
        while(capacity<vernum){
            capacity=capacity*2+1;
        }
        this.vertexSet=new String[capacity];
        for(int i=0;i<vernum;i++){
            this.vertexSet[i]=vertexSet[i];
        }

        //输入int[][] edgeSet转化为Graph里面的ArrayList<Vertex1>[] edgeSet
        //单向图
        this.edgeSet=new ArrayList<Vertex1>();
        edgnum=0;
        for(int i=0;i<edgeSet.length;i++){
            Vertex1 vertex1=new Vertex1(vertexSet[i],i,0);
            for(int j=edgeSet[0].length-1;j>=0;j--){
                if(edgeSet[i][j]!=0){
                    Vertex1 tmp=new Vertex1(vertexSet[j],j,edgeSet[i][j]);
                    tmp.nextVertex=vertex1.nextVertex;
                    vertex1.nextVertex=tmp;
                    edgnum++;
                }
            }
            this.edgeSet.add(vertex1);
        }
    }
    public void insertVertex(String vername){
        if(vertexSet==null){
            vertexSet=new String[DEFAULT_CAPACITY];
            vertexSet[0]=vername;
            edgeSet=new ArrayList<Vertex1>();
            Vertex1 vertex1=new Vertex1(vertexSet[0],0,0);
            edgeSet.add(vertex1);
            return;
        }
        if(++vernum>vertexSet.length){
            int capacity=vertexSet.length*2+1;
            String[] old=vertexSet;
            vertexSet=new String[capacity];
            for(int i=0;i<vernum-1;i++){
                vertexSet[i]=old[i];
            }
        }
        vertexSet[vernum-1]=vername;
    }
    public void insertEdge(Edge edge){
        if(edge.start<0||edge.end<0||edge.start>=vernum||edge.end>=vernum){
            System.out.println("The start index or the end index out of bounds, "+edge.start+"-"+edge.end+"-"+edge.weight+" insert fails");
            return;
        }
        Vertex1 head=edgeSet.get(edge.start);
        Vertex1 vertex1=new Vertex1(vertexSet[edge.end],edge.end,edge.weight);
        vertex1.nextVertex=head.nextVertex;
        head.nextVertex=vertex1;
        edgnum++;
    }

    public void DeepFirstSearch(){
        boolean[] isVisited=new boolean[vernum];
        int[] path=new int[vernum];
        int startIndex=0;//选择开始检索的vertex的index
        int index=0;//path记录的index
        path[index]=startIndex;
        isVisited[startIndex]=true;
        MyArrayStack<Integer> stack=new MyArrayStack<>();
        stack.push(startIndex);
        while(!stack.isEmpty()){
            Vertex1 tmp=edgeSet.get(stack.top()).nextVertex;
            int sig=0;
            while(tmp!=null){
                if(!isVisited[tmp.index]){
                    stack.push(tmp.index);
                    isVisited[tmp.index]=true;
                    path[++index]=tmp.index;
                    sig=1;
                    break;
                }
                tmp=tmp.nextVertex;
            }
            if(sig==0){//sig=0说明没有邻接点未访问
                stack.pop();//若该点对应所有邻接点都已访问，出栈
            }
        }
        System.out.println();
        System.out.print("DFS路径: ");
        for(int i=0;i<path.length;i++){
            System.out.print(vertexSet[path[i]]+" ");
        }
    }
    public void BreadthFirstSearch(){
        boolean[] isVisited=new boolean[vernum];
        int[] path=new int[vernum];
        int startIndex=0;//选择开始检索的vertex的index
        int index=0;//path记录的index
        path[index]=startIndex;
        isVisited[startIndex]=true;
        MyArrayQueue<Integer> queue=new MyArrayQueue<>();
        queue.enQueue(startIndex);
        while(!queue.isEmpty()){
            Vertex1 tmp=edgeSet.get(queue.peek()).nextVertex;
            int sig=0;
            while(tmp!=null){
                if(!isVisited[tmp.index]){
                    queue.enQueue(tmp.index);
                    isVisited[tmp.index]=true;
                    path[++index]=tmp.index;
                    sig=1;
                    //break;
                }
                tmp=tmp.nextVertex;
            }
            if(sig==0){//sig=0说明没有邻接点未访问
                queue.deQueue();//若该点对应所有邻接点都已访问，出队
            }
        }
        System.out.println();
        System.out.print("BFS路径: ");
        for(int i=0;i<path.length;i++){
            System.out.print(vertexSet[path[i]]+" ");
        }
    }

    //测试
    public static void main(String[] args){
        String[] vertexSet=new String[]{"A","B","C","D","E","F","G","H"};
        int[][] edgeSet=new int[8][8];
        edgeSet[0][1]=1;
        edgeSet[0][2]=2;
        edgeSet[0][5]=3;
        edgeSet[1][5]=4;
        edgeSet[2][1]=5;
        edgeSet[2][3]=6;
        edgeSet[5][7]=7;
        edgeSet[6][1]=8;
        edgeSet[6][2]=5;
        edgeSet[6][4]=3;
        edgeSet[7][6]=6;
        Graph g=new Graph(vertexSet,edgeSet);
        g.BreadthFirstSearch();
        //g.DeepFirstSearch();
    }
}

