package TREE;

public class testBST {
    public static void main(String[] args){
        BinarySearchTree<Integer> tree1=new BinarySearchTree<Integer>();
        int a[] = {42,30,60,16,36,8,27,32,40,50,70};
        for(int i=0;i<a.length;i++){
            tree1.insert(a[i]);
        }
        tree1.printTree();
        tree1.remove(30);
        tree1.remove(16);
        tree1.remove(32);
        tree1.printTree();
        System.out.println(tree1.contains(30));
        System.out.println(tree1.contains(16));
        System.out.println(tree1.contains(42));
        System.out.println(tree1.contains(50));
        System.out.println(tree1.contains(100));
    }
}
