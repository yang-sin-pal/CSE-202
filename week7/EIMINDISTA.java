package week7;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;

public class EIMINDISTA {
    static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public static void main(String[] args) {
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int length = sc.nextInt();
            graph[u].addEdge(length, graph[v]);
            graph[v].addEdge(length, graph[u]);
        }
        dijkstra(graph[0]);
    }
    static void dijkstra(Vertex source){
        PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> Long.compare(v1.shortestDist, v2.shortestDist));
        source.shortestDist = 0;
        queue.add(source);
        
        while (!queue.isEmpty()) {
            Vertex cuVertex = queue.remove();
            
            for (Edge adEdge : cuVertex.edges) {
                Vertex endVertex = adEdge.endVertex;
                long tempDist = adEdge.length + cuVertex.shortestDist;
                if (tempDist < endVertex.shortestDist) {
                    endVertex.shortestDist = tempDist;
                    endVertex.preVertex = cuVertex;
                    Vertex clone = new Vertex(endVertex.id);
                    clone.shortestDist = endVertex.shortestDist;
                    clone.preVertex = endVertex.preVertex;
                    queue.add(clone);
                }

            }
        }
    }
    static class Vertex {
        int id;
        List<Edge> edges = new ArrayList<>();
        long shortestDist = Long.MAX_VALUE;
        Vertex preVertex = null;

        public Vertex(int id) {
            this.id = id;
        }

        public void addEdge(int distance, Vertex preVertex) {
            edges.add(new Edge(distance, preVertex));
        }
    }

    static class Edge {
        int length;
        Vertex endVertex;

        public Edge(int length, Vertex endVertex) {
            this.length = length;
            this.endVertex = endVertex;
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}
