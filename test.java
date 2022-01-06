import java.util.*;
import java.io.*;
class test {
    static Debugger dbg;
    static void debug(String n, Object o) throws IOException{
        System.err.print(n.length() > 0 ? n + ": ": "");
        dbg.print(o);
    }
    static class Edge {
        int d;
        int w;
        Edge(int d) {
            this.d = d;
            this.w = 1;
        }
        Edge(int d, int w) {
            this.d = d;
            this.w = w;
        }

        public String toString(){
            return this.d + "";
        }
    } 
    static ArrayList<Edge>[] g;
    static void print(int v){
        for(int i = 0; i < v; i++){
            System.err.print(i + ": ");
            System.err.print(g[i]);
            System.err.println();
        }
        System.err.println();
    }

    static int[] par;
    static int[] size;
    static int[] rank;
    static int find(int n){
        if(par[n] == n){
            return n;
        }
        int temp = find(par[n]);
        return par[n] = temp;
    }

    static boolean union2(int x, int y){
        int lx = find(x);
        int ly = find(y);
        if(lx != ly){
            if(rank[lx] > rank[ly]){
                par[ly] = lx;
            }
            else if(rank[ly] > rank[lx]){
                par[lx] = ly;
            }
            else{
                par[lx] = ly;
                rank[ly]++;
            }
            return true;
        }
        else{
            return false;
        }
    }

    static boolean union(int x, int y){
        int lx = find(x);
        int ly = find(y);
        if(lx != ly){
            if(size[lx] >= size[ly]){
                par[ly] = lx;
                size[lx] += size[ly];
            }
            else if(size[ly] > size[lx]){
                par[lx] = ly;
                size[ly] += size[lx];
            }
            return true;
        }
        else{
            return false;
        }
    }
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception{
        dbg = new Debugger();
        Scanner scn = new Scanner(System.in);
        // int v = scn.nextInt();
        // int e = scn.nextInt();
        // g = new ArrayList[v];
        // for (int i = 0; i < v; i++) {
        //     g[i] = new ArrayList<>();
        // }

        // for (int i = 0; i < e; i++) {
        //     int s = scn.nextInt();
        //     int d = scn.nextInt();
        //     g[s].add(new Edge(d));
        //     g[d].add(new Edge(s));
        // }
        int n = 2;
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = scn.nextInt();
            }
        }
        size = new int[n * n];
        par = new int[n * n];
        rank = new int[n * n];
        for(int i = 0; i < size.length; i++){
            size[i] = 1;
            par[i] = i; 
        }
        
        boolean hasZero = false;
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] == 0){
                    hasZero = true;
                    continue;
                }
                else{
                    int x = i * n + j;
                    for(int[] dir: dirs){
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] == 0){
                            continue;
                        }
                        int y = nx * n + ny;
                        union(x, y);
                    }
                }
            }
        }
        int ans = 1;
        if(!hasZero){
            System.out.println(n * n);
        }
        else{
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(arr[i][j] == 0){
                        int cnt = 0;
                        HashSet<Integer> uparent = new HashSet<>();
                        for(int[] dir: dirs){
                            int nx = i + dir[0];
                            int ny = j + dir[1];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] == 0){
                                continue;
                            }
                            int x = nx * n + ny;
                            int p = find(x);
                            if(!uparent.contains(p)){
                                cnt += size[p];
                                ans = Math.max(cnt + 1, ans);
                                uparent.add(p);
                            }
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}