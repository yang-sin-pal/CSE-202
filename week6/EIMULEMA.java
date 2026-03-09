package week6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIMULEMA {
    static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int vertices = sc.nextInt();
        int edges = vertices - 1;
        Vertex[] graph = diGraph(vertices, edges);
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) { // Check for the root
                dfs(vertex);
            }
        }
        for (Vertex vertex : graph) {
            sb.append(vertex.id).append(" ").append(vertex.commission).append("\n");
        }
        System.out.println(sb);
    }

    public static int dfs(Vertex v) {
        v.visited = true;
        v.commission = (int) (v.revenue * 0.15);
        for (Vertex w : v.adjacentList) {
            if (w.visited == false) {
                int getCommission = dfs(w);
                v.commission += getCommission;
            }
        }
        return (int) (v.commission / 2);
    }

    public static Vertex[] diGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (!graph[u].adjacentList.contains(graph[v])) {
                graph[u].adjacentList.add(graph[v]);
                graph[v].inDegree++; // count in degree for root checking
            }
        }
        return graph;
    }

    public static Vertex[] graph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new Vertex(i);
            graph[i].revenue = sc.nextInt(); // Add revenue
        }
        return graph;
    }

    static class Vertex {
        int id;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();
        int revenue = 0;
        int commission = 0;
        int inDegree = 0;

        public Vertex(int id) {
            this.id = id;
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
