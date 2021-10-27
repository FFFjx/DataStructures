package Graph;

public class Vertex1 {
    String name;
    Vertex1 nextVertex;
    int weight;
    int index;

    public Vertex1(String name,int index,int weight){
        this(name,index,weight,null);
    }
    public Vertex1(String name,int index,int weight,Vertex1 nextVertex){
        this.name=name;
        this.index=index;
        this.weight=weight;
        this.nextVertex=nextVertex;
    }
}
