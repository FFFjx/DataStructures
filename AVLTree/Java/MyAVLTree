package TREE;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private static class AVLNode<T>{
        AVLNode(T val){
            value=val;
            leftchild=null;
            rightchild=null;
            height=0;
        }
        AVLNode(T val,AVLNode lc,AVLNode rc){
            value=val;
            leftchild=lc;
            rightchild=rc;
            height=0;
        }
        T value;
        AVLNode<T> leftchild;
        AVLNode<T> rightchild;
        int height;
    }
    public AVLTree(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }
    public boolean contains(T val){
        return contains(root,val);
    }
    private boolean contains(AVLNode<T> t,T x){
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
    public void insert(T val){
        root=insert(root,val);
    }
    private AVLNode<T> insert(AVLNode<T> t,T val){
        if(t==null){
            return new AVLNode<>(val);
        }
        int compareResult=val.compareTo(t.value);
        if(compareResult>0){
            t.rightchild=insert(t.rightchild,val);
        }
        else if(compareResult<0){
            t.leftchild=insert(t.leftchild,val);
        }
        else
            ;
        return balance(t);
    }
    public void remove(T val){
        root=remove(root,val);
    }
    private AVLNode<T> remove(AVLNode<T> t,T val){
        if(t==null){
            return null;
        }
        int compareResult=val.compareTo(t.value);
        if(compareResult>0){
            t.rightchild=remove(t.rightchild,val);
        }
        else if(compareResult<0){
            t.leftchild=remove(t.leftchild,val);
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
        return balance(t);
    }
    public AVLNode<T> findMax(){
        return findMax(root);
    }
    private AVLNode<T> findMax(AVLNode<T> t){
        if(t==null){
            return t;
        }
        else if(t.rightchild==null){
            return t;
        }
        return findMax(t.rightchild);
    }
    private int height(AVLNode<T> t){
        if(t==null){
            return -1;
        }
        return t.height;
    }
    private AVLNode<T> balance(AVLNode t){
        if(t==null){
            return t;
        }
        if(height(t.leftchild)-height(t.rightchild)>1){
            if(height(t.leftchild.leftchild)>=height(t.leftchild.rightchild)){
                t=rotateWithLeftChild(t);
            }
            else {
                t = doubleRotateWithLeftChild(t);
            }
        }
        else {
            if (height(t.rightchild) - height(t.leftchild) > 1) {
                if (height(t.rightchild.rightchild) >= height(t.rightchild.leftchild)) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleRotateWithRightChild(t);
                }
            }
        }
        t.height=Math.max(height(t.leftchild),height(t.rightchild))+1;
        return t;
    }

    private AVLNode<T> rotateWithLeftChild(AVLNode<T> k2){
        AVLNode<T> k1=k2.leftchild;
        k2.leftchild=k1.rightchild;
        k1.rightchild=k2;
        k2.height=Math.max(height(k2.leftchild),height(k2.rightchild))+1;
        k1.height=Math.max(height(k1.leftchild),height(k2))+1;
        return k1;
    }
    private AVLNode<T> rotateWithRightChild(AVLNode<T> k2){
        AVLNode<T> k1=k2.rightchild;
        k2.rightchild=k1.leftchild;
        k1.leftchild=k2;
        k2.height=Math.max(height(k2.leftchild),height(k2.rightchild))+1;
        k1.height=Math.max(height(k1.rightchild),height(k2))+1;
        return k1;
    }
    private AVLNode<T> doubleRotateWithLeftChild(AVLNode<T> k3){
        k3.leftchild=rotateWithRightChild(k3.leftchild);
        return rotateWithLeftChild(k3);
    }
    private AVLNode<T> doubleRotateWithRightChild(AVLNode<T> k3){
        k3.leftchild=rotateWithLeftChild(k3.rightchild);
        return rotateWithRightChild(k3);
    }
    public void printTree(){
        if(root!=null){
            printTree(root,root.value,0);
        }
    }
    private void printTree(AVLNode<T> root,T father,int direction){
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
