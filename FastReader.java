import java.io.*;
import java.util.*;
class FastReader{
    BufferedReader br;
    StringTokenizer st;

    public FastReader(){
        br = new BufferedReader(new InputStreamReader(System.in));
    } 

    String Next(){
        while(st == null || !st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(Next());
    }

    float nextFloat(){
        return Float.parseFloat(Next());
    }

    boolean nextBoolean(){
        return Boolean.parseBoolean(Next());
    }

    double nextDouble(){
        return Double.parseDouble(Next());
    }

    long nextLong(){
        return Long.parseLong(Next());
    }

    String nextLine(){
        String str = "";
        try{
            str = br.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }

    String next(){
        return Next();
    }
}