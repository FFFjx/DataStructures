package TREE;

public class testRBTree {
    public static void main(String[] args){
        RBTree<Integer> rb=new RBTree<Integer>();
        //int a[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180};
        int a[] = {6, 65, 62, 90, 92, 58, 76, 71, 84, 9};
        for(int i=0;i<a.length;i++){
            rb.insert(a[i]);
        }
        rb.print();
        /*rb.insert(40);
        rb.insert(30);
        rb.insert(60);
        rb.insert(90);
        rb.insert(70);
        rb.insert(110);
        rb.insert(100);*/
        rb.remove(62);
        rb.remove(90);
        rb.remove(76);
        rb.remove(71);
        rb.remove(9);
        rb.print();
    }
}
