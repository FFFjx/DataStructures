package Heap;

import java.util.Arrays;

public class MyBinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private AnyType[] theArray;
    private static int DEFAULT_SIZE=10;
    private int theSize;

	public MyBinaryHeap(){
	    this(DEFAULT_SIZE);
	}
	public MyBinaryHeap(int capacity){
	    theArray = (AnyType[]) new Comparable[capacity+1];
	    theSize = 0;
	}
    public MyBinaryHeap(AnyType[] theArray){//Using a given array to construct the heap
        theSize = theArray.length;
        this.theArray = (AnyType[]) new Comparable[(theSize+2)*11/10];
        int i = 1;
        for(AnyType item:theArray){
            this.theArray[i++] = item;
        }
        buildHeap();
    }

    private void buildHeap(){
	    for(int i=theSize/2;i>0;i--){
	        percolateDown(i);
        }
    }
    private void percolateDown(int hole){
	    int child = 2*hole;
	    AnyType tmp = theArray[hole];

	    for(;hole*2<=theSize;hole = child){
	        child = hole*2;
	        if(child!=theSize&&theArray[child+1].compareTo(theArray[child])<0){
	            child++;
            }
	        if(theArray[child].compareTo(tmp)<0){
	            theArray[hole] = theArray[child];
            }else{
	            break;
            }
        }
	    theArray[hole] = tmp;
    }
    public void insert(AnyType x){
        if(theSize>theArray.length-1){
            expand(2*theArray.length+1);
        }
        int hole = ++theSize;
        for(theArray[0]=x;x.compareTo(theArray[hole/2])<0;hole/=2){
            theArray[hole] = theArray[hole/2];
        }
        theArray[hole]=x;
    }
    public AnyType findMin(){
        if(isEmpty()){
            return null;
        }else
            return theArray[1];
    }
    public AnyType deleteMin(){
        AnyType min=findMin();
        theArray[1] = theArray[theSize--];
        percolateDown(1);
        return min;
    }
    public boolean isEmpty(){
        return theSize==0;
    }
    public void makeEmpty(){
        for(int i=0;i<theArray.length;i++){
            theArray[i]=null;
        }
        theSize=0;
    }
    public void printArray(){
	    if(isEmpty()){
	        System.out.println("The heap is empty.");
	        return;
        }
        for(int i=1;i<theArray.length;i++){
            System.out.print(" "+theArray[i]);
        }
    }

    private void expand(int capacity){
        AnyType[] oldArray=theArray;
        theArray=Arrays.copyOf(oldArray,capacity);//Create new array
        for(int i=1;i<oldArray.length;i++){//Copy
            theArray[i]=oldArray[i];
        }
    }
}
