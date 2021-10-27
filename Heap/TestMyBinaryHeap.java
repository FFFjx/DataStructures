package Heap;

public class TestMyBinaryHeap {
    public static void main(String[] args) {
        Integer[] testArray=new Integer[]{3,9,12,13,29,24,36,27,54,33};
        for(int i=0;i<testArray.length;i++){
            System.out.print(" "+testArray[i]);
        }
        MyBinaryHeap<Integer> myHeap=new MyBinaryHeap<>(testArray);
        myHeap.insert(6);
        myHeap.insert(11);
        System.out.println();
        myHeap.printArray();
        System.out.println();
        System.out.println(7/2);
    }
}
