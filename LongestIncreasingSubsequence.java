package DP;

public class LongestIncreasingSubsequence {
    public static int LIS(int[] sequence){
        int maxLIS=0;
        int[] LISubsequence=new int[sequence.length];
        LISubsequence[0]=1;
        for(int i=1;i<sequence.length;i++){
            int temp=1;
            for(int j=i-1;j>=0;j--){
                if(sequence[i]>sequence[j]){
                    temp=(temp>LISubsequence[j]+1)?temp:LISubsequence[j]+1;
                }
            }
            LISubsequence[i]=temp;
            maxLIS=(LISubsequence[i]>maxLIS)?LISubsequence[i]:maxLIS;
        }
        return maxLIS;
    }
    public static void main(String[] args){
        int[] seq1=new int[]{1,4,7,2,5,6,10,3};
        System.out.println(LIS(seq1));
        int[] seq2=new int[]{1,2,3,4,5,6,7,8,10,9};
        System.out.println(LIS(seq2));
        int[] seq3=new int[]{10,9,8,7,6,5,4,3,1};
        System.out.print(LIS(seq3));
    }
}
