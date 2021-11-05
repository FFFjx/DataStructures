package Sort;

import java.util.Arrays;
import java.util.Random;

public class MyShellSort {
    public static void myShellSort(int[] theArray){
        for(int gap=theArray.length/2;gap!=0;gap=gap/2){
            int j;
            for(int i=gap;i<theArray.length;i++){
                int tmp=theArray[i];
                for(j=i-gap;j>=0&&theArray[j]>tmp;j-=gap){
                    theArray[j+gap]=theArray[j];
                }
                theArray[j+gap]=tmp;
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
    public static void main(String[] args){
        int[] ss=new int[50000];
        Random r=new Random(1);
        for(int i=0;i<ss.length;i++){
            ss[i]=r.nextInt(100);
            //System.out.print(ss[i]+" ");
        }
        int[] ss2= Arrays.copyOf(ss, ss.length);
        long ST=System.currentTimeMillis();
        myShellSort(ss);
        long ET=System.currentTimeMillis();
        long time=ET-ST;
        System.out.println();
        System.out.println("The running time of ShellSort is "+time+"ms");
        ST=System.currentTimeMillis();
        insertionSort(ss2);
        ET=System.currentTimeMillis();
        time=ET-ST;
        System.out.println("The time of running InsertionSort is "+time+"ms");
        System.out.println();
        for(int i = 0;i<ss.length-1;i++){
            if(ss[i]>ss[i+1]) {
                System.out.print("error in " + i);
            }
        }
    }
}
