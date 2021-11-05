package Sort;

import java.util.Arrays;
import java.util.Random;

public class MyQuickSort
{
    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }
    public static void quickSort(int[] arr, int left, int right){
        if(left<right){
            int mid = partition(arr, left, right);
            quickSort(arr, left, mid);
            quickSort(arr, mid+1, right);
        }
    }

    public static int partition(int[] arr, int left, int right){
        int i = left;
        int j = right;
        int tag = arr[left];
        while(i!=j){
            while(i+1<j&&arr[i]<tag){
                i++;
            }
            while(j-1>i&&arr[j]>=tag){
                j--;
            }
            if(arr[j]>=tag) {
                if(arr[i]>tag){
                    i--;
                }
                break;
            }
            swap(arr, i, j);
        }
        return i;
    }

    public static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void quickSortV2(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSortV2(arr, 0, arr.length-1);
    }
    public static void quickSortV2(int[] arr, int left, int right){
        if(left<right){
            int[] mid = partitionV3(arr, left, right);
            quickSortV2(arr, left, mid[0]);
            quickSortV2(arr, mid[1], right);
        }
    }

    public static int partitionV2(int[] arr, int left, int right){
        int tag = arr[left];
        while(left<right){
            while(arr[right]>=tag&&right>left){
                right--;
            }
            arr[left] = arr[right];
            while(arr[left]<tag&&left<right){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = tag;
        return left;
    }

    public static int[] partitionV3(int[] arr, int left, int right){
        int tag = arr[left];
        int i = left-1;
        int j = right+1;
        while(left<j){
            if(arr[left]<tag){
                swap(arr, ++i, left++);
            }else if(arr[left]>tag){
                swap(arr, --j, left);
            }else {
                left++;
            }
        }
        return new int[]{i, j};
    }

    public static void compareTwoMethod(int arrayLength, int bound, int seed){
        int[] list = new int[arrayLength];
        Random ran = new Random(seed);
        for(int i = 0;i<list.length;i++){
            list[i] = ran.nextInt(bound);
        }
        int[] list2 = Arrays.copyOf(list, list.length);
        long start = System.currentTimeMillis();
        quickSort(list);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("elements: "+arrayLength+" bound: 0-"+bound);
        System.out.println("The running time of quickSort is "+(end-start)+"ms");
        for(int i = 0;i<list.length-1;i++){
            if(list[i]>list[i+1]) {
                System.out.print("error in " + i);
            }
        }
        System.out.println();
        start = System.currentTimeMillis();
        quickSortV2(list2);
        end = System.currentTimeMillis();
        System.out.println("The running time of quickSortV2 is "+(end-start)+"ms");
        for(int i = 0;i<list2.length-1;i++){
            if(list2[i]>list2[i+1]) {
                System.out.print("error in " + i);
            }
        }
    }
    public static void main(String[] args){
        compareTwoMethod(500000, 500000,1);
        compareTwoMethod(500000, 100, 1);
    }

}
