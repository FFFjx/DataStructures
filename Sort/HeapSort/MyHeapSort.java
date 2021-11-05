package Sort;

import java.util.Arrays;
import java.util.Random;

public class MyHeapSort {
    public static void myHeapSort(int[] theArray){
        //buildHeap
        for(int i=theArray.length/2-1;i>=0;i--){
            adjustHeap(theArray,i,theArray.length);
        }
        for(int i=theArray.length-1;i>0;i--) {
            if (theArray[i] > theArray[(i - 1) / 2]) {
                System.out.println("Wrong at " + i);
            }
        }
        for(int i=theArray.length-1;i>0;i--){
            swap(theArray,i,0);
            adjustHeap(theArray,0,i);
        }
    }
    public static void adjustHeap(int[] arr,int i,int len){
        int largest=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<len&&arr[left]>arr[largest]){
            largest=left;
        }
        if(right<len&&arr[right]>arr[largest]){
            largest=right;
        }
        if(largest!=i){
            swap(arr,i,largest);
            adjustHeap(arr,largest,len);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void insertionSort(int[] theArray){
        int j;
        for(int i=1;i<theArray.length;i++){
            int tmp=theArray[i];
            for(j=i;j>0&&tmp<theArray[j-1];j--){
                theArray[j]=theArray[j-1];
            }
            theArray[j]=tmp;
        }
    }
    public static void main(String[] args){
        int[] ss=new int[50000];
        Random r=new Random(1);
        for(int i=0;i<ss.length;i++){
            ss[i]=r.nextInt(100);
            //System.out.print(ss[i]+" ");
        }
        int[] ss2= Arrays.copyOf(ss, ss.length);
        long ST=System.currentTimeMillis();
        myHeapSort(ss);
        long ET=System.currentTimeMillis();
        long time=ET-ST;
        System.out.println();
        System.out.println("The time of running HeapSort is "+time+"ms");
        ST=System.currentTimeMillis();
        insertionSort(ss2);
        ET=System.currentTimeMillis();
        time=ET-ST;
        System.out.println("The time of running InsertionSort is "+time+"ms");
        for(int i=0;i<ss.length-1;i++){
            if(ss[i]>ss[i+1]) {
                System.out.print("error in " + i);
            }
        }
    }
}
