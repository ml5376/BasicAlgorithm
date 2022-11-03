
import java.io.*;
        import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String s,t;
        Scanner input = new Scanner(System.in);

        s=input.nextLine();
        t=input.nextLine();
        int m=s.length(),n=t.length();
        int[][]DP=new int[m+1][n+1];
        int[][]path=new int[m][n];
        DP[0][0]=0;
        int mismatch=-1;
        int match=2;

        for(int k1=1; k1<=n;k1++) {
            DP[0][k1]=mismatch*k1;
        }
        for(int k2=1; k2<=m;k2++) {
            DP[k2][0]=mismatch*k2;
        }

        for(int i1 =1; i1<m+1; i1++) {
            for(int j1=1; j1<n+1; j1++) {
                int one,two,three;
                if(s.charAt(i1-1)==t.charAt(j1-1)) {

                    DP[i1][j1]=DP[i1-1][j1-1]+2;
                    one=DP[i1][j1];
                }
                else {DP[i1][j1]=DP[i1-1][j1-1]-1; one=DP[i1][j1];}
                two=DP[i1][j1-1]-1; three=DP[i1-1][j1]-1;
                DP[i1][j1]=Math.max(DP[i1][j1], Math.max(DP[i1][j1-1]-1, DP[i1-1][j1]-1));
                if(DP[i1][j1]==one) {path[i1-1][j1-1]=1;}
                else if(DP[i1][j1]==two) {path[i1-1][j1-1]=2;}
                else if(DP[i1][j1]==three){path[i1-1][j1-1]=3;}
            }
        }
        ArrayList<Character>s_after=new ArrayList<>();
        ArrayList<Character>t_after=new ArrayList<>();


        Optimal(s,t,s_after,t_after,path,m-1,n-1);


        System.out.println(DP[m][n]);

        for(Character i: s_after) {System.out.print(i);}
        System.out.println();
        for(Character j: t_after) {System.out.print(j);}

    }
    public static void Optimal(String s,String t,ArrayList<Character>s_after, ArrayList<Character>t_after, int[][]path, int m,int n) {
        while(m>=0 && n>=0) {

            if(path[m][n]==1) {

                if(m>=0) {s_after.add(0, s.charAt(m));}else {s_after.add(0,'_');}
                if(n>=0) {t_after.add(0, t.charAt(n));}else {t_after.add(0,'_');}
                m=m-1;n=n-1;}
            else if(path[m][n]==3) {
                s_after.add(0, s.charAt(m));
                t_after.add(0, '_');
                m=m-1;
            }
            else if(path[m][n]==2){
                s_after.add(0, '_');
                t_after.add(0, t.charAt(n));
                n=n-1;
            }
            if(m<0 && n>=0) {
                for (int i=n;i>=0;i--) {t_after.add(0,t.charAt(i));s_after.add(0,'_');}}
            else if(n<0 && m>=0) {
                for (int i=n;i>=0;i--) {s_after.add(0,s.charAt(i));t_after.add(0,'_');}
            }
        }

    }
}
