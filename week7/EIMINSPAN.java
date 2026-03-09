package week7;

import java.util.*;
import java.io.*;

public class EIMINSPAN {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        InputReader sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = new Vertex[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();
            graph[u].adjacentVertices.add(new Edge(graph[v], w));
            graph[v].adjacentVertices.add(new Edge(graph[u], w));
        }

        System.out.println(prims(graph));

    }

    static long prims(Vertex[] graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((s1, s2) -> Long.compare(s1.weight, s2.weight));
        priorityQueue.add(new Edge(graph[0], 0));
        int count = 0;
        long total = 0;
        while (!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();
            Vertex current = currentEdge.endpoint;
            if (current.visited) {
                continue;
            }
            current.visited = true;
            total += currentEdge.weight;
            count++;
            if (count == graph.length) {
                return total;
            }

            for (Edge edge : current.adjacentVertices) {
                Vertex e = edge.endpoint;
                if (!e.visited) {
                    priorityQueue.add(edge);
                }
            }

        }
        return -1;
    }

    static class Vertex {
        public int id;
        public boolean visited = false;
        public long dist = Long.MAX_VALUE;
        public List<Edge> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        public Vertex endpoint;
        public long weight;

        public Edge(Vertex endpoint, long weight) {
            this.endpoint = endpoint;
            this.weight = weight;
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
