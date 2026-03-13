package week7;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIUWBT {
    static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long minDiff = Long.MAX_VALUE;
    static long minID = -1;
    static long bestTW1 = -1;
    static long bestTW2 = -1;
    static long totalWeightTree = 0;
    public static void main(String[] args) {
        int nodes = sc.nextInt();
        Vertex[] graph = unMuGraph(nodes, nodes - 1);
        dfs(graph[0]);
        if (minID == -1) {
            sb.append(minID);
        } else {
            sb.append(minID).append(" ").append(Math.min(bestTW1, bestTW2)).append(" ")
                    .append(Math.max(bestTW1, bestTW2));
        }
        System.out.println(sb);
    }

    static void resetGraph(Vertex[] graph) {
        for (Vertex vertex : graph) {
            vertex.visited = false;
            vertex.totalWeight = vertex.weight;
        }
    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex e : v.adjacentList) {
            if (e.visited == false) {
                dfs(e);
                v.totalWeight += e.totalWeight;
                if (v.adjacentList.size() == 2) {
                    long tw1 = e.totalWeight;
                    long tw2 = totalWeightTree - tw1 - v.weight;
                    long currentDiff = Math.abs(tw1 - tw2);
                    if (currentDiff < minDiff || currentDiff == minDiff && v.id < minID) {
                        minDiff = currentDiff;
                        minID = v.id;
                        bestTW1 = tw1;
                        bestTW2 = tw2;
                    }
                }
            }
        }
    }

    public static Vertex[] unMuGraph(int vertices, int edges) {
        Vertex[] graph = graph1(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[v - 1].adjacentList.add(graph[u - 1]);
            graph[u - 1].adjacentList.add(graph[v - 1]);
        }
        return graph;
    }

    public static Vertex[] graph1(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 1; i <= vertices; i++) {
            long weight = sc.nextLong();
            totalWeightTree += weight;
            graph[i - 1] = new Vertex(i, weight);
        }
        return graph;
    }

    static class Vertex {
        int id;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();
        long weight = 0;
        long totalWeight = 0;

        public Vertex(int id, long weight) {
            this.id = id;
            this.weight = weight;
            this.totalWeight = weight;
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
