package TREE;

import java.util.Random;

public class testMy2dTree {
    public static void main(String[] args){
        //double[][] valueSet={{2,3},{5,4},{9,6},{4,7},{8,1},{7,2}};
        //System.out.print(valueSet[3][1]);
        //my2dTree my2d=new my2dTree();
        //my2d.bulitTree(valueSet);
        //double[][] target={{2,4.5}};
        //double[][] result=my2d.search1NN(target);
        //for(int i=0;i<result[0].length;i++){
        //    System.out.print(result[0][i]+" ");
        //}
        double[][] valueSet2=new double[1000][2];
        double[][] target={{2,4.5}};
        Random r = new Random(1);
        int index_x=0;
        double min_dis=1000;
        for(int i=0;i<valueSet2.length;i++){
            for(int j=0;j<valueSet2[0].length;j++){
                valueSet2[i][j]=r.nextDouble()*100;
            }
            double dis=0;
            for(int k=0;k<valueSet2[0].length;k++){
                dis+=(target[0][k]-valueSet2[i][k])*(target[0][k]-valueSet2[i][k]);
            }
            dis=Math.sqrt(dis);

            if(dis<min_dis){
                min_dis=dis;
                index_x=i;
            }
        }
        double[][] valueSet3=new double[valueSet2.length][valueSet2[0].length];
        for(int i=0;i<valueSet3.length;i++){
            for(int j=0;j<valueSet3[0].length;j++){
                valueSet3[i][j]=valueSet2[i][j];
            }
        }
        my2dTree my2d=new my2dTree();
        my2d.bulitTree(valueSet2);
        double[][] result=my2d.search1NN(target);
        System.out.print("The result of 2dTree:");
        for(int i=0;i<result[0].length;i++){
            System.out.print(result[0][i]+" ");
        }
        System.out.println();
        System.out.print("Ground Truth:");
        for(int i=0;i<valueSet3[0].length;i++){
            System.out.print(valueSet3[index_x][i]+" ");
        }
        System.out.print(index_x+" "+min_dis);
    }
}



