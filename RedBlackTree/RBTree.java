package TREE;

public class RBTree<T extends Comparable<?super T>> {
    public static class RBNode<T>{
        T value;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;
        boolean color;
        RBNode(T value){
            this(value,null,null,null);
        }
        RBNode(T value,RBNode<T> left,RBNode<T> right,RBNode<T> parent){
            this.value=value;
            this.left=left;
            this.right=right;
            this.parent=parent;
            color=RBTree.RED;
        }
    }
    private static final boolean BLACK=true;
    private static final boolean RED=false;
    public RBNode<T> root;

    public RBTree(){
        root=null;
    }
    public void insert(T value){
        if(root==null){
            root=new RBNode<T>(value);
            root.color=BLACK;//Situation 1: the new node is root.
        }else{
            insert(root,value);
        }
    }
    private void insert(RBNode<T> root,T value){
        RBNode<T> t=root;
        RBNode<T> n=new RBNode<>(value);
        RBNode<T> p=null;
        int cmp;
        while(t!=null){
            p=t;
            cmp=value.compareTo(t.value);
            if(cmp>0){
                t=t.right;
            }else if(cmp<0){
                t=t.left;
            }else{
                return;
            }
        }
        n.parent=p;
        cmp=value.compareTo(p.value);
        if(cmp>0){
            p.right=n;
        }else{
            p.left=n;
        }
        balanceInsert(n);
    }
    private void balanceInsert(RBNode<T> t){
        if(t==root){
            t.color=BLACK;
            return;//情况1：新节点是根
        }
        if(t.parent.color==BLACK){//情况2：新节点的父亲是黑色
            return;
        }
        if(t.parent.color==RED&&getBrother(t)==null&&getBrother(t.parent)==null){//t.parent的子树只有三个节点的情况
            if(t==t.parent.left){
                if(t.parent==t.parent.parent.left){
                    t.parent.color=BLACK;
                    t.parent.parent.color=RED;
                    rotateWithLeftChild(t.parent.parent);
                    return;
                }else{
                    rotateWithLeftChild(t.parent);
                    t.color=BLACK;
                    t.parent.color=RED;
                    rotateWithRightChild(t.parent);
                    return;
                }
            }else{
                if(t.parent==t.parent.parent.right){
                    t.parent.color=BLACK;
                    t.parent.parent.color=RED;
                    rotateWithRightChild(t.parent.parent);
                    return;
                }else{
                    rotateWithRightChild(t.parent);
                    t.color=BLACK;
                    t.parent.color=RED;
                    rotateWithLeftChild(t.parent);
                    return;
                }
            }
        }
        if(t.parent.color==RED&&getBrother(t.parent).color==RED){//情况3：新节点的父亲是红色，父亲的兄弟也是红色
            t.parent.color=BLACK;
            t.parent.parent.color=RED;
            getBrother(t.parent).color=BLACK;
            balanceInsert(t.parent.parent);
        }else if(t.parent.color==RED&&getBrother(t.parent).color==BLACK){
            if(t.parent==t.parent.parent.left&&t==t.parent.left){//情况5：新节点的父亲是红色，父亲的兄弟是黑色，且新节点是其父亲的右儿子
                t.parent.color=BLACK;
                t.parent.parent.color=RED;
                rotateWithLeftChild(t.parent.parent);
            }else if(t.parent==t.parent.parent.left&&t==t.parent.right){//情况4：新节点的父亲是红色，父亲的兄弟是黑色，且新节点是其父亲的左儿子
                rotateWithRightChild(t.parent);
                t.color=BLACK;
                t.parent.color=RED;
                rotateWithLeftChild(t.parent);
            }else if(t.parent==t.parent.parent.right&&t==t.parent.right){
                t.parent.color=BLACK;
                t.parent.parent.color=RED;
                rotateWithRightChild(t.parent.parent);
            }else if(t.parent==t.parent.parent.right&&t==t.parent.left){
                rotateWithLeftChild(t.parent);
                t.color=BLACK;
                t.parent.color=RED;
                rotateWithRightChild(t.parent);
            }
        }
    }
    private RBNode getBrother(RBNode<T> t){//获取兄弟节点
        if(t.parent!=null){
            if(t==t.parent.right){
                return t.parent.left;
            }else if(t==t.parent.left)
                return t.parent.right;
            else{
                return null;
            }
        }else{
            return null;
        }
    }
    public void remove(T value){
        if(findNode(root,value)!=null){
            remove(findNode(root,value));
        }
    }
    private void remove(RBNode<T> t){//先调整，后删除
        if(t.left==null&&t.right==null){//无子则直接删除，若颜色为黑色需要调整
            if(t.parent==null){//如果是根节点
                root=null;
                return;
            }
            if(t.color==BLACK){
                balanceRemove(t);
            }
            if(t==t.parent.left){
                t.parent.left=null;
            }else{
                t.parent.right=null;
            }
            t.parent=null;
            return;
        }else if(t.left==null&&t.right!=null){//左子为空，右子非空，相当于删除右子
            if(t.parent==null){//如果是根节点
                root=t.right;
                root.color=BLACK;
                root.parent=null;
                return;
            }
            RBNode<T> replaceNode=t.right;
            if(replaceNode.color==BLACK){
                balanceRemove(t.right);
            }
            t.value=replaceNode.value;
            if(replaceNode==replaceNode.parent.left){
                replaceNode.parent.left=null;
            }else{
                replaceNode.parent.right=null;
            }
            replaceNode.parent=null;
            return;
        }else if(t.left!=null&&t.right==null){//右子为空，左子非空，相当于删除左子
            if(t.parent==null){//如果是根节点
                root=t.left;
                root.color=BLACK;
                root.parent=null;
                return;
            }
            RBNode<T> replaceNode=t.left;
            if(replaceNode.color==BLACK){
                balanceRemove(t.left);
            }
            t.value=replaceNode.value;
            if(replaceNode==replaceNode.parent.left){
                replaceNode.parent.left=null;
            }else{
                replaceNode.parent.right=null;
            }
            replaceNode.parent=null;
            return;
        }else{//双子非空，找到后继节点，相当于删除后继节点
            RBNode<T> replaceNode=getMIN(t.right);
            T vt=replaceNode.value;
            remove(replaceNode);
            t.value=vt;
        }
    }
    private RBNode<T> findNode(RBNode<T> t,T value){
        if(t==null){//找不到该节点
            System.out.print("Can not find the variable: "+value);
            return null;
        }
        int cmp=value.compareTo(t.value);
        if(cmp>0){
            return findNode(t.right,value);
        }else if(cmp<0){
            return findNode(t.left,value);
        }else{
            ;
        }
        return t;
    }
    private RBNode<T> getMIN(RBNode<T> t){
        if(t.left!=null){
            return getMIN(t.left);
        }else
            ;
        return t;
    }
    private void balanceRemove(RBNode<T> t){
        if(t.color==RED){
            return;
        }
        if(t.parent==null){
            if(t.color==RED){
                t.color=BLACK;
            }
            return;
        }
        if(getBrother(t)==null){//兄弟节点为空说明自身不可能为黑节点（根节点除外），只可能为红色节点，直接删除，本身为红，不可能有红子，更不可能有黑子
            return;
        }//排除t.brother为空
        if(t==t.parent.left){//如果是左子
            if(getBrother(t)!=null&&getBrother(t).color==BLACK){//如果兄弟为黑
                if((getBrother(t).right==null&&getBrother(t).left==null)||(getBrother(t).right.color==BLACK&&getBrother(t).left.color==BLACK)){//兄第的双子为黑或者双子为空
                    getBrother(t).color=RED;
                    if(t.parent.color==RED){
                        t.parent.color=BLACK;
                        return;
                    }
                    balanceRemove(t.parent);
                }else if(getBrother(t).right!=null&&getBrother(t).right.color==RED){//兄弟的右子非空且为红，左子红/黑/空，不关心
                    getBrother(t).color=t.parent.color;
                    t.parent.color=BLACK;
                    getBrother(t).right.color=BLACK;
                    rotateWithRightChild(t.parent);
                }else if((getBrother(t).right==null||getBrother(t).right.color==BLACK)&&getBrother(t).left.color==RED){//兄弟的右子为黑或空，且左子为红
                    getBrother(t).color=RED;
                    getBrother(t).left.color=BLACK;
                    rotateWithLeftChild(getBrother(t));
                    getBrother(t).color=t.parent.color;
                    t.parent.color=BLACK;
                    getBrother(t).right.color=BLACK;
                    rotateWithRightChild(t.parent);
                }
            }else if(getBrother(t)!=null&&getBrother(t).color==RED){//兄弟为红
                getBrother(t).color=BLACK;
                t.parent.color=RED;
                rotateWithRightChild(t.parent);
                balanceRemove(t);
            }
        }else if(t==t.parent.right){//如果是右子
            if(getBrother(t)!=null&&getBrother(t).color==BLACK){//如果兄弟为黑
                if((getBrother(t).right==null&&getBrother(t).left==null)||(getBrother(t).right.color==BLACK&&getBrother(t).left.color==BLACK)){//兄第的双子为黑或者双子为空
                    getBrother(t).color=RED;
                    if(t.parent.color==RED){
                        t.parent.color=BLACK;
                        return;
                    }
                    balanceRemove(t.parent);
                }else if(getBrother(t).left!=null&&getBrother(t).left.color==RED){//兄弟的左子非空且为红，右子红/黑/空，不关心
                    getBrother(t).color=t.parent.color;
                    t.parent.color=BLACK;
                    getBrother(t).left.color=BLACK;
                    rotateWithLeftChild(t.parent);
                }else if((getBrother(t).left==null||getBrother(t).left.color==BLACK)&&getBrother(t).right.color==RED) {//兄弟的左子为黑且右子为红
                    getBrother(t).color = RED;
                    getBrother(t).right.color = BLACK;
                    rotateWithRightChild(getBrother(t));
                    getBrother(t).color = t.parent.color;
                    t.parent.color = BLACK;
                    getBrother(t).left.color = BLACK;
                    rotateWithLeftChild(t.parent);
                }
            }else if(getBrother(t)!=null&&getBrother(t).color==RED){//兄弟为红
                getBrother(t).color=BLACK;
                t.parent.color=RED;
                rotateWithLeftChild(t.parent);
                balanceRemove(t);
            }
        }
    }
    private void rotateWithLeftChild(RBNode<T> y){
        RBNode<T> x=y.left;
        RBNode<T> tmp=x.right;
        y.left=tmp;
        if(x.right!=null){
            x.right.parent=y;
        }
        x.right=y;
        x.parent=y.parent;
        if(y.parent==null){
            root=x;
        }else{
            if(y==y.parent.left){
                y.parent.left=x;
            }else{
                y.parent.right=x;
            }
        }
        y.parent=x;
    }
    private void rotateWithRightChild(RBNode<T> x){
        RBNode<T> y=x.right;
        RBNode<T> tmp=y.left;
        x.right=tmp;
        if(tmp!=null){
            tmp.parent=x;
        }
        y.left=x;
        y.parent=x.parent;
        if(x.parent==null){
            root=y;
        }else{
            if(x==x.parent.left){
                x.parent.left=y;
            }else{
                x.parent.right=y;
            }
        }
        x.parent=y;
    }
    private void print(RBNode<T> tree, T key, int direction) {
        if(tree != null) {
           if(direction==0){
               System.out.printf("%2d(%s) is root\n", tree.value,tree.color==RED?"R":"B");
           }
           else
               System.out.printf("%2d(%s) is %2d's %6s child\n", tree.value, tree.color==RED?"R":"B", key, direction==1?"right" : "left");
           print(tree.left, tree.value, -1);
           print(tree.right,tree.value,  1);
        }
    }
    public void print() {
        if (root != null){
            print(root, root.value, 0);
        }
    }
}
