package Graph;

import java.util.ArrayList;
import java.util.List;

public class Prim {//有向图的最小生成树
    public String[] vertex;
    public int vernum;
    public int edgenum;
    public int[][] edges;

    public Prim(String[] vertex,int[][] edges){//新建，处理输入数据
        this.vertex=vertex;
        vernum=vertex.length;
        edgenum=0;
        this.edges=new int[edges.length][edges[0].length];
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges[0].length;j++){
                if(edges[i][j]>0){
                    this.edges[i][j]=edges[i][j];
                    this.edges[j][i]=edges[i][j];
                    edgenum++;
                }
            }
        }
    }
    public void prim(List<Integer> temp){
        if(temp.size()==vernum)
            return;
        int MinWeight = 100;
        int i=0;
        int x=0,y=0;
        for (i=0;i<temp.size();i++){
            int t1 = temp.get(i);
            for (int j=0;j<edges[t1].length;j++){
                if(edges[t1][j]<MinWeight && edges[t1][j]>0 && !temp.contains(j)){
                    MinWeight = edges[t1][j];
                    x = temp.get(i);
                    y = j;
                }
            }
        }
        temp.add(y);
        System.out.println(vertex[x]+"-"+vertex[y]+"-"+MinWeight);;

        prim(temp);
    }
    //test
    public static void main(String[] args){
        String[] vertexSet=new String[]{"v1","v2","v3","v4","v5","v6","v7"};
        int[][] edgs=new int[7][7];
        edgs[0][1]=2;
        edgs[0][2]=4;
        edgs[0][3]=1;
        edgs[1][3]=3;
        edgs[2][5]=5;
        edgs[2][3]=2;
        edgs[3][6]=4;
        edgs[4][6]=6;
        edgs[5][6]=1;
        Prim p=new Prim(vertexSet,edgs);
        List<Integer> a = new ArrayList<>();
        a.add(0);
        p.prim(a);
    }
}
