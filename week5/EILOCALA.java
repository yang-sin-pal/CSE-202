package week5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EILOCALA {
    static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    static int longest = 0;
    static int maxVertex = 0;

    public static void main(String[] args) throws IOException {
        int vertices = sc.nextInt();
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }
        for (int i = 0; i < graph.length - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int distance = 1;
            graph[u].addEdge(distance, graph[v]);
            graph[v].addEdge(distance, graph[u]);
        }

        dfs(graph[0], longest);
        for (Vertex vertex : graph) {
            vertex.visited = false;
        }
        Vertex r = graph[maxVertex];
        longest = 0;
        maxVertex = 0;
        dfs(r, longest);
        int smallerID = Math.min(maxVertex, r.id);
        System.out.println(smallerID + " " + longest);
    }

    static void dfs(Vertex v, int distance) {
        if (distance > longest) {
            longest = distance;
            maxVertex = v.id;
        } else if (distance == longest) {
            maxVertex = Math.min(maxVertex, v.id);
        }
        v.visited = true;
        for (Edge adEdge : v.edges) {
            if (!adEdge.endVertex.visited) {
                dfs(adEdge.endVertex, distance + adEdge.length);
            }
        }
    }

    static class Vertex {
        int id;
        boolean visited = false;
        List<Edge> edges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addEdge(int length, Vertex endVertex) {
            edges.add(new Edge(length, endVertex));
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
