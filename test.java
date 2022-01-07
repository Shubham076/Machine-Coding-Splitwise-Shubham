import java.util.*;
import java.io.*;
class test {
    static Debugger dbg;
    static void debug(String n, Object o) throws IOException{
        System.err.print(n.length() > 0 ? n + ": ": "");
        dbg.print(o);
    }
    static class LRU{
        class Node{
            int k;
            int v;
            Node next;
            Node prev;
            Node(){

            }
            Node(int k, int v){
                this.k = k;
                this.v = v;
            }

            public String toString(){
                return this.k + "=" + this.v + ", ";
            }
        }
        int s;
        HashMap<Integer,Node> map;
        Node head;
        Node tail; 
        LRU(int s){
            map = new HashMap<>();
            this.s = s;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        Node extract(Node n){
            Node left = n.prev;
            Node right = n.next;
            n.prev = n.next = null;
            left.next = right;
            right.prev = left;
            return n;
        }
        void add(Node n){
            Node tm1 = tail.prev;
            tm1.next = n;
            n.next = tail;
            tail.prev = n;
            n.prev = tm1;
        }

        int get(int key){
            if(map.containsKey(key)){
                Node n = map.get(key);
                extract(n);
                add(n);
                return n.v;
            }
            else return -1;
        }

        void put(int k, int v){
            if(map.containsKey(k)){
                Node temp = map.get(k);
                temp.v = v;
                add(temp);
            }
            else{
                if(map.size() == s){
                    Node n = extract(head.next);
                    map.remove(n.k);
                }
                Node n = new Node(k, v);
                map.put(k, n);
                add(n);
            }
        }

        void print(){
            for(Node temp = head; temp != null; temp = temp.next){
                System.out.print(temp);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        LRU c = new LRU(3);
        c.put(1, 10);
        c.put(2, 20);
        c.put(3, 30);
        c.print();
        c.get(1);
        c.put(4, 40);
        c.print();
    }
}