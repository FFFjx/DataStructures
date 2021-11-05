package Sort;

import java.util.Arrays;
import java.util.Random;

public class MyInsertionSort {
    public static void myInsertionSort(int [] theArray){
        int tmp;
        for(int i=1;i<theArray.length;i++){
            tmp=theArray[i];
            for(int j=0;j<i;j++){
                if(theArray[i]<theArray[j]){
                    theArray[i]=theArray[j];
                    theArray[j]=tmp;
                    tmp=theArray[i];
                }
            }
        }
    }
    public static void myInsertionSortV2(int [] theArray){
        int tmp;
        for(int i=1;i<theArray.length;i++){
            tmp=theArray[i];
            for(int j=i-1;j>=0&&tmp<theArray[j];j--){
                theArray[j+1]=theArray[j];
                if(j==0||tmp>=theArray[j-1]){
                    theArray[j]=tmp;
                    break;
                }
            }
        }
    }
    public static void insertionSort(int [] theArray){
        int j;
        for(int i=1;i<theArray.length;i++){
            int tmp=theArray[i];
            for(j=i;j>0&&tmp<theArray[j-1];j--){
                theArray[j]=theArray[j-1];
            }
            theArray[j]=tmp;
        }
    }
    //test & evaluate
    public static void main(String[] args) {
        int[] test1=new int[50000];
        Random r=new Random(7);
        for(int i=0;i<test1.length;i++){
            test1[i]=r.nextInt(100);
            System.out.print(test1[i]+" ");
        }
        int[] test2= Arrays.copyOf(test1, test1.length);
        int[] test3= Arrays.copyOf(test1, test1.length);
        long ST=System.currentTimeMillis();
        myInsertionSortV2(test1);
        long ET=System.currentTimeMillis();
        long time=ET-ST;
        System.out.println();
        System.out.println("The time of running myInsertionSortV2 is "+time+"ms");
        ST=System.currentTimeMillis();
        myInsertionSort(test2);
        ET=System.currentTimeMillis();
        time=ET-ST;
        System.out.println("The time of running myInsertionSort is "+time+"ms");
        ST=System.currentTimeMillis();
        insertionSort(test3);
        ET=System.currentTimeMillis();
        time=ET-ST;
        System.out.println("The time of running InsertionSort is "+time+"ms");
        for(int i=0;i<test1.length;i++){
            System.out.print(" "+test1[i]);
        }
        System.out.println();
        for(int i=0;i<test2.length;i++){
            System.out.print(" "+test2[i]);
        }
    }
}
