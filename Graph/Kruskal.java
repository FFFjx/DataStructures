package Graph;

public class Kruskal {//无向图的最小生成树
    Edge[] edgeHeap;
    String[] vertex;
    public Kruskal(String[] vertex,Edge[] edges){//新建，处理输入数据
        this.vertex=vertex;
        //build edge heap
        for(int i=edges.length/2-1;i>=0;i--){
            adjustHeap(edges,i,edges.length);
        }
        edgeHeap=edges;
    }
    public static void adjustHeap(Edge[] arr,int i,int len){
        int min=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<len&&arr[left].weight<arr[min].weight){
            min=left;
        }
        if(right<len&&arr[right].weight<arr[min].weight){
            min=right;
        }
        if(min!=i){
            swap(arr,i,min);
            adjustHeap(arr,min,len);
        }
    }
    public static void swap(Edge[] arr, int i, int j) {
        Edge temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void kruskal(){
        int[] root=new int[vertex.length];
        Edge[] mst=new Edge[edgeHeap.length-1];
        int index=0;
        for(int i=0;i<root.length;i++){
            root[i]=-1;
        }
        //heap sort(min-heap, grade down)
        for(int i=edgeHeap.length-1;i>=0;i--){
            swap(edgeHeap,i,0);
            adjustHeap(edgeHeap,0,i);
            int index_start=edgeHeap[i].start;
            int index_end=edgeHeap[i].end;

            int root_start=findRoot(root,index_start);
            int root_end=findRoot(root,index_end);
            if(root_start!=root_end){//root不同，说明没有成环
                root[root_end]=root_start;
                mst[index++]=edgeHeap[i];
            }
        }
        System.out.println("The MST path is:");
        for(int i=0;i<mst.length;i++){
            if(mst[i]!=null){
                System.out.println(vertex[mst[i].start]+" "+vertex[mst[i].end]+" "+mst[i].weight);
            }
        }
    }
    public int findRoot(int[] root,int index){
        if(root[index]<0){
            return index;
        }else
            ;
        return findRoot(root,root[index]);
    }
    //test
    public static void main(String[] args){
        /*String[] vertexSet=new String[]{"0","1","2","3","4","5","6","7","8"};
        Edge[] edgs=new Edge[14];
        edgs[0]=new Edge(0,1,4);
        edgs[1]=new Edge(0,7,8);
        edgs[2]=new Edge(1,2,8);
        edgs[3]=new Edge(1,7,11);
        edgs[4]=new Edge(2,3,7);
        edgs[5]=new Edge(2,8,2);
        edgs[6]=new Edge(2,5,4);
        edgs[7]=new Edge(3,5,14);
        edgs[8]=new Edge(3,4,9);
        edgs[9]=new Edge(4,5,10);
        edgs[10]=new Edge(5,6,2);
        edgs[11]=new Edge(6,7,1);
        edgs[12]=new Edge(6,8,6);
        edgs[13]=new Edge(7,8,7);
        Kruskal ks=new Kruskal(vertexSet,edgs);
        ks.kruskal();*/
        String[] vertexSet2=new String[]{"v1","v2","v3","v4","v5","v6","v7"};
        Edge[] edgs2=new Edge[9];
        edgs2[0]=new Edge(0,1,2);
        edgs2[1]=new Edge(0,2,4);
        edgs2[2]=new Edge(0,3,1);
        edgs2[3]=new Edge(1,3,3);
        edgs2[4]=new Edge(2,5,5);
        edgs2[5]=new Edge(2,3,2);
        edgs2[6]=new Edge(3,6,4);
        edgs2[7]=new Edge(4,6,6);
        edgs2[8]=new Edge(5,6,1);
        Kruskal ks2=new Kruskal(vertexSet2,edgs2);
        ks2.kruskal();
    }
}
