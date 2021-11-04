package TREE;

public class My2dTree {
    private static class kdNode{
        double[][] value;
        int thisdim=-1;
        kdNode leftc;
        kdNode rightc;
        kdNode parent;
        kdNode(double[][] value){
            this(value,null,null);
        }
        kdNode(double[][] value,kdNode leftc,kdNode rightc){
            this.value=value;
            this.leftc=leftc;
            this.rightc=rightc;
        }
    }
    kdNode root;

    public void insert(double[][] value){
        insert(value,root);
    }
    private kdNode insert(double[][] value,kdNode t){
        if(t==null){
            return new kdNode(value);
        }
        int dim=t.thisdim;
        int nextdim=-1;
        if(dim==0){
            nextdim=1;
        }else if(dim==1){
            nextdim=0;
        }
        int compareResult=value[0][dim]>t.value[0][dim]?1:-1;
        if(compareResult>0){
            t.rightc=insert(value,t.rightc);
            t.rightc.parent=t;
            t.rightc.thisdim=nextdim;
        }
        else if(compareResult<0){
            t.leftc=insert(value,t.leftc);
            t.leftc.parent=t;
            t.leftc.thisdim=nextdim;
        }
        else
            ;
        return t;
    }

    public void bulitTree(double[][] valueSet){//valueSet is an n*k matrix, n denotes the number of items, k denotes the dimension of the item
        //select which dimension to partition(index)
        int index=finddim(valueSet);
        root=bulitTree(valueSet,index);
    }
    private kdNode bulitTree(double[][] valueSet,int index){
        if(valueSet==null||valueSet.length==0){
            return null;
        }
        int num=valueSet.length;
        int dim=valueSet[0].length;

        //Ascending sort by the dimension of index
        myInsertionSort(valueSet,index);
        double[][] value=new double[1][dim];
        for(int j=0;j<dim;j++){
            value[0][j]=valueSet[num/2][j];
        }
        kdNode root=new kdNode(value);
        root.thisdim=index;
        int pnum=num%2;
        double[][] vSleft;
        double[][] vSright;
        if(pnum==0){
            vSleft=new double[num/2][dim];
            for(int i=0;i<num/2;i++){
                for(int j=0;j<dim;j++){
                    vSleft[i][j]=valueSet[i][j];
                }
            }
            vSright=new double[num/2-1][dim];
            for(int i=num-1;i>num/2;i--){
                for(int j=0;j<dim;j++){
                    vSright[num-i-1][j]=valueSet[i][j];
                }
            }
        }else{
            vSleft=new double[num/2][dim];
            for(int i=0;i<num/2;i++){
                for(int j=0;j<dim;j++){
                    vSleft[i][j]=valueSet[i][j];
                }
            }
            vSright=new double[num/2][dim];
            for(int i=num-1;i>num/2;i--){
                for(int j=0;j<dim;j++){
                    vSright[num-i-1][j]=valueSet[i][j];
                }
            }
        }

        //Adjust the next dimension
        if(index==0){
            index=1;
        }else if(index==1){
            index=0;
        }
        root.leftc=bulitTree(vSleft,index);
        root.rightc=bulitTree(vSright,index);
        if(root.leftc!=null)
            root.leftc.parent=root;
        if(root.rightc!=null)
            root.rightc.parent=root;
        return root;
    }

    //1nnSearch
    public double[][] search1NN(double[][] target){
        return search1NN(target,root);
    }
    private double[][] search1NN(double[][] target,kdNode t){
        kdNode leaf=findLeaf(t,target);
        double curDis=computeDis(leaf,target);
        kdNode curNode=leaf;
        while(leaf!=root){
            double dis=computeDis(leaf.parent,target);
            if(dis<curDis){
                curNode=leaf.parent;
                curDis=dis;
                leaf=findLeaf(getBrother(leaf),target);
            }else{
                leaf=leaf.parent;
            }
        }
        return curNode.value;
    }
    public double[][] searchkNN(double[][] target,int k){//k means the number of NNs
        if(root==null){
            return null;
        }
        int dimension=root.value[0].length;
        double[][] result=new double[k][dimension];
        return searchkNN(target,result,k,root);
    }
    private double[][] searchkNN(double[][] target,double[][] result,int k,kdNode t){
        kdNode leaf=findLeaf(t,target);
        double curDis=computeDis(leaf,target);
        kdNode curNode=leaf;
        while(leaf!=root){
            double dis=computeDis(leaf.parent,target);
            if(dis<curDis){
                curNode=leaf.parent;
                curDis=dis;
                leaf=findLeaf(getBrother(leaf),target);
            }else{
                leaf=leaf.parent;
            }
        }
        return curNode.value;//1NN
        //return result;
    }
    private kdNode getBrother(kdNode t){
        if(t==t.parent.rightc){
            return t.parent.leftc;
        }else
            return t.parent.rightc;
    }
    private double computeDis(kdNode t,double[][] target){//Calculate the distance between target node and current node. Here is Euclidean distance.
        int dim=t.value[0].length;
        double dis=0;
        for(int j=0;j<dim;j++){
            dis+=(target[0][j]-t.value[0][j])*(target[0][j]-t.value[0][j]);
        }
        dis=Math.sqrt(dis);
        return dis;
    }
    private kdNode findLeaf(kdNode t,double[][] target){//Find the nearest leaf node from target node for now.
        int dim=t.thisdim;
        kdNode leaf=t,next=null;
        while(leaf.leftc!=null||leaf.rightc!=null) {
            if (target[0][dim] > leaf.value[0][dim]) {
                next = leaf.rightc;
            } else if (target[0][dim] < leaf.value[0][dim]) {
                next = leaf.leftc;
            } else
                ;
            if (next == null)
                break;
            else {
                leaf = next;
            }
        }
        return leaf;
    }
    private int finddim(double[][] valueSet){//Select the dimension to cut.
        int index=0;
        double max_variance=0;
        int num=valueSet.length;
        for(int j=0;j<2;j++){
            int total=0;
            for(int i=0;i<num;i++){
                total+=valueSet[i][j];
            }
            int mean=total/num;
            double variance=0;
            for(int i=0;i<num;i++){
                variance+=((valueSet[i][j]-mean)*(valueSet[i][j]-mean))/num;
            }
            if(variance>max_variance){
                max_variance=variance;
                index=j;
            }
        }
        return index;
    }
    private void myInsertionSort(double[][] theArray,int index){
        double tmp;
        for(int i=1;i<theArray.length;i++){
            tmp=theArray[i][index];
            for(int k=0;k<i;k++){
                if(theArray[i][index]<theArray[k][index]){
                    for(int j=0;j<theArray[0].length;j++){
                        double tmp2=theArray[i][j];
                        theArray[i][j]=theArray[k][j];
                        theArray[k][j]=tmp2;
                    }
                }
            }
        }
    }


}
