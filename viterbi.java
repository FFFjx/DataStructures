package DP;

public class viterbi {
    private String[] state_obs;
    private String[] state_hidden;
    //private int num_obs;
    private int num_hidden;
    private double[][] pro_hST;//The probability of hidden state transfer
    private double[][] pro_obs;//The probability of observation
    private double[] initialState;//Pi
    public viterbi(String[] state_obs,String[] state_hidden,double[][] A,double[][] B,double[] Pi){
        this.state_obs=state_obs;
        this.state_hidden=state_hidden;
        //num_obs=state_obs.length;
        num_hidden=state_hidden.length;
        pro_hST=A;
        pro_obs=B;
        initialState=Pi;
    }
    public String[] computeViterbi(String[] seq_obs){
        double[][] thelta=new double[num_hidden][seq_obs.length];
        int[][] seq_hidden=new int[num_hidden][seq_obs.length];
        String[] path_hidden=new String[seq_obs.length];

        //thelta&seq_hidden for moment0
        int index=0;
        for(int x=0;x<state_obs.length;x++){
            if(seq_obs[0]==state_obs[x]){
                index=x;
            }
        }
        for(int i=0;i<num_hidden;i++){
            //int index=Arrays.binarySearch(state_obs,seq_obs[i]);
            thelta[i][0]=initialState[i]*pro_obs[i][index];
        }
        for(int i=0;i<num_hidden;i++){
            seq_hidden[i][0]=0;
        }

        //thelta&seq_hidden for the rest moment
        int max_index=0;
        for(int j=1;j<seq_obs.length;j++){
            double max_thelta=0;
            max_index=0;
            index=0;
            for(int x=0;x<state_obs.length;x++){
                if(seq_obs[j]==state_obs[x]){
                    index=x;
                }
            }
            //int index=Arrays.binarySearch(state_obs,seq_obs[j]);
            for(int i=0;i<num_hidden;i++){
                double pro_max=0;
                double pro_now=0;
                for(int k=0;k<num_hidden;k++){
                    pro_now=thelta[k][j-1]*pro_hST[k][i];
                    if(pro_now>pro_max){
                        seq_hidden[i][j]=k;
                        pro_max=pro_now;
                    }
                }
                thelta[i][j]=pro_max*pro_obs[i][index];

                //for each moment j, get the max thelta and record its index(using for tracing back)
                if(thelta[i][j]>max_thelta){
                    max_thelta=thelta[i][j];
                    max_index=i;
                }
            }
        }

        //trace back
        String temp=state_hidden[max_index];
        path_hidden[path_hidden.length-1]=temp;
        for(int i =seq_obs.length-2;i>=0;i--){
            max_index=seq_hidden[max_index][i+1];
            path_hidden[i]=state_hidden[max_index];
        }

        for(int i=0;i<num_hidden;i++){
            for(int j=0;j<seq_obs.length;j++){
                System.out.print(thelta[i][j]+" ");
            }
        }
        return path_hidden;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] state_obs={"hong","bai"};
        String[] state_hidden={"hezi1","hezi2","hezi3"};
        double[][] A={{0.5,0.2,0.3},{0.3,0.5,0.2},{0.2,0.3,0.5}};//The probability of hidden state transfer
        double[][] B={{0.5,0.5},{0.4,0.6},{0.7,0.3}};//The probability of observation
        double[] Pi={0.2,0.4,0.4};
        viterbi v=new viterbi(state_obs,state_hidden,A,B,Pi);
        String[] seq_obs={"hong","bai","hong"};
        String[] result=v.computeViterbi(seq_obs);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}
