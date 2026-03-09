package week3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EIFBF2 {
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
    static List<Integer> vertexList = new ArrayList<>();
    static int countMale;
    static int countFemale;

    public static void main(String[] args) throws IOException {
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            String gender = sc.next();
            graph[i] = new Vertex(i + 1, gender);
        }
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            graph[u].adjacentList.add(graph[v]);
            graph[v].adjacentList.add(graph[u]);
        }
        for (Vertex vertex : graph) {
            if (!vertex.visited) {
                bfs(vertex);
                for (int id : vertexList) {
                    Vertex curvertex = graph[id - 1];
                    curvertex.countMale = countMale;
                    curvertex.countFemale = countFemale;
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            Vertex vertex = graph[i];
            sb.append(vertex.id).append(" ")
                    .append(vertex.countMale).append(" ")
                    .append(vertex.countFemale).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        vertexList.clear();
        countMale = 0;
        countFemale = 0;
        v.visited = true;
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(v);
        vertexList.add(v.id);
        if (v.gender.equalsIgnoreCase("Nam")) {
            countMale++;
        } else {
            countFemale++;
        }
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Vertex vertex : current.adjacentList) {
                if (!vertex.visited) {
                    vertex.visited = true;
                    queue.add(vertex);
                    vertexList.add(vertex.id);
                    if (vertex.gender.equalsIgnoreCase("Nam")) {
                        countMale++;
                    } else {
                        countFemale++;
                    }
                }
            }
        }
    }

    static class Vertex {
        int id;
        String gender;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();
        int countMale = 0;
        int countFemale = 0;

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
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
