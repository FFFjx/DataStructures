package Graph;

import LIST.MyArrayQueue;

public class EK_BFS {
    int[][] flow;
    String[] vertexName;
    public EK_BFS(String[] vertexName, int[][] edgeSet){//指定源点的index为0，终点的index为n-1
        this.vertexName=vertexName;
        flow=edgeSet;
    }
    public void EdmondsKarp(){
        int sum_flow=0;
        //boolean[] isVisited=new boolean[vertexName.length];
        MyArrayQueue<Integer> queue=new MyArrayQueue<>();
        int[] temp_flow=new int[vertexName.length];
        int[] p=new int[vertexName.length];
        p[0]=-1;
        while(true){
            //找到一条增广路径
            for(int i=1;i<p.length;i++){
                p[i]=0;
            }
            for(int i=0;i<temp_flow.length;i++){
                temp_flow[i]=0;
            }
            temp_flow[0]=Integer.MAX_VALUE;
            queue.enQueue(0);
            //isVisited[0]=true;
            while(!queue.isEmpty()){
                int i=queue.peek();
                int sig=0;
                for(int j=0;j<flow[0].length;j++){
                    if(flow[i][j]>0&&flow[i][j]>temp_flow[j]){
                        //isVisited[j]=true;
                        p[j]=i;
                        queue.enQueue(j);
                        if(temp_flow[i]>=flow[i][j]){
                            temp_flow[j]=flow[i][j];
                        }else{
                            temp_flow[j]=temp_flow[i];
                        }
                        sig=1;
                        break;
                    }
                }
                if(sig==0){
                    queue.deQueue();
                }
            }
            if(temp_flow[temp_flow.length-1]==0){//若找不到增广路径，则跳出循环，EK算法结束
                break;
            }
            sum_flow+=temp_flow[temp_flow.length-1];
            for(int i=temp_flow.length-1;i>0;i=p[i]){//更新流量，添加反向边
                flow[p[i]][i]-=temp_flow[temp_flow.length-1];
                flow[i][p[i]]+=temp_flow[temp_flow.length-1];
            }
        }
        System.out.println("The largest flow of this graph is "+sum_flow);
    }
    //测试
    public static void main(String[] args){
        String[] vertexSet=new String[]{"0","1","2","3","4","5"};
        int[][] edgeSet=new int[6][6];
        edgeSet[0][1]=3;
        edgeSet[0][2]=2;
        edgeSet[1][2]=1;
        edgeSet[1][3]=3;
        edgeSet[1][4]=4;
        edgeSet[2][4]=2;
        edgeSet[3][5]=2;
        edgeSet[4][5]=4;
        EK_BFS EK=new EK_BFS(vertexSet,edgeSet);
        EK.EdmondsKarp();
    }
}