package TREE;

public class testAVLTree {
    public static void main(String[] args){
        AVLTree<Integer> tree=new AVLTree<Integer>();
        int a[] = {10,20,30,40,50,60,70,80,90};
        for(int i=0;i<a.length;i++){
            tree.insert(a[i]);
        }
        tree.printTree();
        tree.remove(30);
        tree.remove(20);
        tree.remove(10);
        tree.printTree();
        System.out.println(tree.contains(30));
        System.out.println(tree.contains(60));
        System.out.println(tree.contains(42));
        System.out.println(tree.contains(80));
        System.out.println(tree.contains(100));
    }
}
