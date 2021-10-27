package DP;

public class ZeroOnePackage {
    public int[] weight;
    public int[] value;
    public int num;
    public int capacity;
    public int[][] valueMatrix;

    public void initialize(int capacity,int[] value,int[] weight){
        this.capacity = capacity;
        num = value.length;
        this.value = value;
        this.weight = weight;
        valueMatrix = new int[num+1][capacity+1];
        for(int j=0;j<capacity+1;j++){
            valueMatrix[0][j] = 0;
        }
        for(int i=0;i<num+1;i++){
            valueMatrix[i][0] = 0;
        }
    }
    public int findMax(){
        int temp0 = 0;
        int temp1 = 0;
        int maxvalue = 0;
        for(int i=1;i<num+1;i++){
            for(int j=1;j<capacity+1;j++){
                temp0 = valueMatrix[i-1][j];
                if(j>=weight[i-1]){
                    temp1 = valueMatrix[i-1][j-weight[i-1]]+value[i-1];
                    temp0 = (temp0>temp1)?temp0:temp1;
                }
                valueMatrix[i][j] = temp0;
                maxvalue = (maxvalue>temp0)?maxvalue:temp0;
            }
        }
        return maxvalue;
    }
    public void traceBack(int num,int cap){
        boolean[] chooseWhich = new boolean[num+1];
        while (valueMatrix[num][cap] != 0) {
            if(valueMatrix[num][cap] != valueMatrix[num-1][cap]){
                chooseWhich[num] = true;
                cap = cap-weight[num-1];
            }
            else{
                chooseWhich[num] = false;

            }
            num--;
        }
        for(int i=1;i<chooseWhich.length;i++){
            System.out.print(chooseWhich[i]+" ");
        }

    }
    public void printMatrix(){
        for(int i=1;i<num+1;i++){
            for(int j=1;j<capacity+1;j++){
                System.out.print(valueMatrix[i][j]+" ");
            }
        }
    }

    public static void main(String[] args){
        ZeroOnePackage p = new ZeroOnePackage();
        int[] w=new int[]{2,3,4,5};
        int[] v=new int[]{3,4,5,6};
        int cap = 8;
        p.initialize(cap, v, w);
        int max = p.findMax();
        System.out.println(max);
        p.printMatrix();
        p.traceBack(w.length,cap);
    }
}
