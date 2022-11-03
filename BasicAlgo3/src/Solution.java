import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'smellCosmos' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int[] smellCosmos(int[] a, int[] b) {
        int n=a.length;
        int[] p=new int[2*n-1];
        //if (n==1) {p[0]=a[0]*b[0];}
        if (n<=16) {

            int[] product=new int[a.length+b.length-1];

            for(int i=0;i<a.length;i++){
                for(int j=0;j<b.length;j++){
                    product[i+j]+=a[i]*b[j];

                }
            }
            return product;}
        else {
            int m=(int) Math.ceil(n/2);
            int[] s1=new int[m];int[] t1=new int[m];int[] s3=new int[m];int[] t3=new int[m];
            // s=Arrays.copyOfRange(a,0,m);//for(int i: s) {System.out.print("s: "+i+" ");}
            for(int i=0;i<m;i++){s1[i]=a[i];}
            for(int i=0;i<m;i++){t1[i]=b[i];}
            //t=Arrays.copyOfRange(b,0,m);//for(int i: t) {System.out.print("t:"+i+" ");}
            int[] s2=new int[m];int[] t2=new int[m];
            for(int i=m;i<=n-1;i++) {
                s2[i-m]=s1[i-m]+a[i];
                t2[i-m]=t1[i-m]+b[i];
            }
            for(int i=m; i<n;i++){s3[i-m]=a[i];}
            for(int i=m; i<n;i++){t3[i-m]=b[i];}


            int[] p1=smellCosmos(s1,t1);
            int[] p2=smellCosmos(s2,t2);
            int[] p3=smellCosmos(s3,t3);

            int[] p4=new int[2*m];
            for(int i=0;i<2*m-1;i++){p4[i]=p1[i];}
            for(int i=0;i<2*m-1;i++){p[i]=p4[i];}

            //System.arraycopy(Arrays.copyOfRange(p1,0,2*m-1), 0, p, 0,2*m-1);
            p[2*m-1]=0;
            int[] p5=new int[2*n-2*m-1];
            for(int i=0;i<2*n-2*m-1;i++){p5[i]=p3[i];}
            for(int i=2*m;i<2*n-1;i++){p[i]=p5[i-2*m];}
            //System.arraycopy(Arrays.copyOfRange(p3,0,2*n-2*m-1), 0, p,2*m,2*n-2*m-1);
            for(int i=0;i<=2*m-2;i++) {
                p[m+i]=p[m+i]+p2[i]-p1[i];
            }
            for(int i=0;i<=2*n-2*m-2;i++) {
                p[m+i]=p[m+i]-p3[i];
            }

            return p;
        }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int [] a1 = a.stream().mapToInt(Integer::intValue).toArray();
        int [] b1 = b.stream().mapToInt(Integer::intValue).toArray();
        int[] res=Result.smellCosmos(a1, b1);
        List<Integer> result = Arrays.stream(res).boxed().collect(Collectors.toList());


        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}