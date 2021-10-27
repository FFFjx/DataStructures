package Graph;

import LIST.MyArrayQueue;

public class EdmondsKarp {
    int[][] flow;
    String[] vertex;
    public EdmondsKarp(String[] vertexName,int[][] edgeSet){//指定源点的index为0，终点的index为n-1
        this.vertex=vertexName;
        flow=new int[edgeSet.length][edgeSet[0].length];
        for(int i = 0;i < flow.length;i++){
            flow[i]=edgeSet[i].clone();
        }
    }
    public void EdmondsKarp(){
        int sum_flow=0;
        int[][] graphFlow=new int[flow.length][flow[0].length];
        for(int i = 0;i < flow.length;i++){
            graphFlow[i]=flow[i].clone();
        }
        boolean[] isVisited=new boolean[vertex.length];
        MyArrayQueue<Integer> queue=new MyArrayQueue<>();
        int[] temp_flow=new int[vertex.length];
        int[] p=new int[vertex.length];
        p[0]=-1;
        while(true){
            //找到一条增广路径
            for(int i=0;i<isVisited.length;i++){
                isVisited[i]=false;
            }
            for(int i=0;i<temp_flow.length;i++){
                temp_flow[i]=0;
            }
            temp_flow[0]=Integer.MAX_VALUE;
            queue.enQueue(0);
            isVisited[0]=true;
            while(!queue.isEmpty()){
                int i=queue.peek();
                for(int j=0;j<graphFlow[0].length;j++){
                    if(isVisited[j]==false&&graphFlow[i][j]>0){
                        isVisited[j]=true;
                        p[j]=i;
                        queue.enQueue(j);
                        if(temp_flow[i]>=graphFlow[i][j]){
                            temp_flow[j]=graphFlow[i][j];
                        }else{
                            temp_flow[j]=temp_flow[i];
                        }
                    }
                }
                queue.deQueue();
            }
            if(temp_flow[temp_flow.length-1]==0){//若找不到增广路径，则跳出循环，EK算法结束
                break;
            }
            sum_flow+=temp_flow[temp_flow.length-1];
            for(int i=temp_flow.length-1;i>0;i=p[i]){//更新流量，添加反向边
                graphFlow[p[i]][i]-=temp_flow[temp_flow.length-1];
                graphFlow[i][p[i]]+=temp_flow[temp_flow.length-1];
            }
        }
        System.out.println("The largest flow of this graph is "+sum_flow);
        for(int i = 0;i < flow.length;i++){
            for(int j = 0;j < flow[0].length;j++){
                int finalFlow=flow[i][j]-graphFlow[i][j];
                if(finalFlow>0){
                    System.out.println(vertex[i]+"-"+vertex[j]+"-"+finalFlow);
                }
            }
        }
    }
    //测试
    public static void main(String[] args){
        String[] vertexSet=new String[]{"s","a","b","c","d","t"};
        int[][] edgeSet=new int[6][6];
        edgeSet[0][1]=3;
        edgeSet[0][2]=2;
        edgeSet[1][2]=1;
        edgeSet[1][3]=3;
        edgeSet[1][4]=4;
        edgeSet[2][4]=2;
        edgeSet[3][5]=2;
        edgeSet[4][5]=4;
        EdmondsKarp ek=new EdmondsKarp(vertexSet,edgeSet);
        ek.EdmondsKarp();
    }
}
