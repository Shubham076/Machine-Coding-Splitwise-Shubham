import java.util.Arrays;
import java.io.*;
class Debugger{
    void print(Object o) throws IOException{
        if(o.getClass().isArray()){
            String name = o.getClass().getSimpleName();
            if(name.equals("int[]")){
                System.err.print(Arrays.toString((int[])o) + "\n");
            }
            else if(name.equals("char[]")){
                System.err.print(Arrays.toString((char[])o) + "\n");
            }
            else if(name.equals("long[]")){
                System.err.print(Arrays.toString((long[])o) + "\n");
            }
            else if(name.equals("boolean[]")){
                System.err.print(Arrays.toString((boolean[])o) + "\n");
            }
            else if(name.equals("double[]")){
                System.err.print(Arrays.toString((double[])o) + "\n");
            }
            else if(name.equals("float[]")){
                System.err.print(Arrays.toString((float[])o) +  "\n");
            }
            else{
                Object[] obs = (Object[])o;
                for(Object no: obs){
                    System.err.print(no + " ");
                }
                System.err.print("\n");
            }
        }
        else{
            System.err.print(o.toString() + "\n");
        }
    }
} 