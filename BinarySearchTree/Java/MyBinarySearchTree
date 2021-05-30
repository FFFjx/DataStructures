package TREE;

public class BinarySearchTree<AnyType extends Comparable<?super AnyType>> {
    private BinaryNode<AnyType> root;

    private static class BinaryNode<AnyType>{
        BinaryNode(AnyType val){
            this(val,null,null);
        }
        BinaryNode(AnyType val,BinaryNode<AnyType> lc,BinaryNode<AnyType> rc){
            value=val;
            leftchild=lc;
            rightchild=rc;
        }
        AnyType value;
        BinaryNode<AnyType> leftchild;
        BinaryNode<AnyType> rightchild;
    }

    public BinarySearchTree(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }
    public boolean contains(AnyType val){
        return contains(root,val);
    }
    private boolean contains(BinaryNode<AnyType> t,AnyType x){
        if(t==null){
            return false;
        }
        int compareResult=x.compareTo(t.value);
        if(compareResult>0){
            return contains(t.rightchild,x);
        }
        else if(compareResult<0){
            return contains(t.leftchild,x);
        }
        else
            ;
        return true;
    }

    public void insert(AnyType val){
        root=insert(root,val);
    }
    private BinaryNode<AnyType> insert(BinaryNode<AnyType> t,AnyType x){
        if(t==null){
            return new BinaryNode<>(x,null,null);
        }
        int compareResult=x.compareTo(t.value);
        if(compareResult>0){
            t.rightchild=insert(t.rightchild,x);
        }
        else if(compareResult<0){
            t.leftchild=insert(t.leftchild,x);
        }
        else
            ;
        return t;
    }

    public AnyType findMax(){
        return findMax(root).value;
    }
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if(t==null){
            return t;
        }
        else if(t.rightchild==null){
            return t;
        }
        return findMax(t.rightchild);
    }
    public AnyType findMin(){
        return findMax(root).value;
    }
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
        if(t==null){
            return t;
        }
        else if(t.leftchild==null){
            return t;
        }
        return findMax(t.leftchild);
    }

    public void remove(AnyType val){
        root=remove(root,val);
    }
    private BinaryNode<AnyType> remove(BinaryNode<AnyType> t,AnyType x){
        if(t==null){
            System.out.println("The node does not exist");
            return null;
        }
        int compareResult=x.compareTo(t.value);
        if(compareResult>0){
            t.rightchild=remove(t.rightchild,x);
        }
        else if(compareResult<0){
            t.leftchild=remove(t.leftchild,x);
        }
        else if(t.rightchild!=null&&t.leftchild!=null){
            t.value=findMax(t.leftchild).value;
            t.leftchild=remove(t.leftchild,t.value);
        }
        else if(t.rightchild==null){
            t=t.leftchild;
        }
        else if(t.leftchild==null){
            t=t.rightchild;
        }
        else
            t=null;
        return t;
    }
    public void printTree(){
        if(root!=null){
            printTree(root,root.value,0);
        }
    }
    private void printTree(BinaryNode<AnyType> root,AnyType father,int direction){
        if(root!=null){
            if(direction==0){
                System.out.println(root.value+" is root");
            }else{
                String a=(direction==1)?"right" : "left";
                System.out.println(root.value+" is "+father+"'s "+a);
            }
            printTree(root.leftchild, root.value, -1);
            printTree(root.rightchild,root.value,  1);
        }
    }
}
