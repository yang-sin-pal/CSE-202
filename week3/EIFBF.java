package week3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Queue;

public class EIFBF {
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
    static int countMale;
    static int countFemale;
    static int representativeIndex;

    public static void main(String[] args) throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = unSiGraph1(n, m);
        List<ComponentInfo> components = new ArrayList<>();
        for (Vertex vertex : graph) {
            if (!vertex.visited) {
                BFS(vertex);
                ComponentInfo newComponentInfo = new ComponentInfo(countMale, countFemale, representativeIndex);
                components.add(newComponentInfo);
            }
        }
        components.sort((c1, c2) -> {
            return Integer.compare(c1.representativeIndex, c2.representativeIndex);
        });
        for (ComponentInfo componentInfo : components) {
            sb.append(componentInfo.representativeIndex).append(" ")
                    .append(componentInfo.countMale).append(" ")
                    .append(componentInfo.countFemale).append("\n");
        }
        System.out.println(sb);
    }

    static void BFS(Vertex v) {
        representativeIndex = v.id;
        countMale = 0;
        countFemale = 0;
        if (v.gender.equalsIgnoreCase("Nam")) {
            countMale++;
        } else {
            countFemale++;
        }
        v.visited = true;
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Vertex vertex : current.adjacentList) {
                if (!vertex.visited) {
                    vertex.visited = true;
                    queue.add(vertex);
                    if (vertex.id > representativeIndex) {
                        representativeIndex = vertex.id;
                    }
                    if (vertex.gender.equalsIgnoreCase("Nam")) {
                        countMale++;
                    } else {
                        countFemale++;
                    }
                }
            }
        }
    }

    static Vertex[] unSiGraph1(int vertices, int edges) {
        Vertex[] graph = graph1(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            if (!graph[u].adjacentList.contains(graph[v])) {
                graph[u].adjacentList.add(graph[v]);
                graph[v].adjacentList.add(graph[u]);
            }
        }
        return graph;
    }

    static Vertex[] graph1(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            String gender = sc.next();
            graph[i] = new Vertex(i + 1, gender);
        }
        return graph;
    }

    static class ComponentInfo {
        int countMale;
        int countFemale;
        int representativeIndex;

        public ComponentInfo(int countMale, int countFemale, int representativeIndex) {
            this.countMale = countMale;
            this.countFemale = countFemale;
            this.representativeIndex = representativeIndex;
        }
    }

    static class Vertex {
        int id;
        String gender;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();

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
