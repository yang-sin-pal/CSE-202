package week4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIBIRTHDAY {
    static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        int k = sc.nextInt();
        EIBIRTHDAY(n, m, d, k);
    }

    static void EIBIRTHDAY(int vertices, int edges, int current, int nextDays) {
        Vertex[] graph = unSiGraph(vertices, edges);
        int until = current + nextDays;
        for (Vertex vertex : graph) {
            if (until < 366) {
                if (vertex.birthday >= current && vertex.birthday <= until) {
                    vertex.hasBirthDay = true;
                }
            } else {
                if (vertex.birthday >= current && vertex.birthday <= 365) {
                    vertex.hasBirthDay = true;
                }
                if (vertex.birthday >= 0 && vertex.birthday <= (until - 365)) {
                    vertex.hasBirthDay = true;
                }
            }
        }
        for (Vertex vertex : graph) {
            int count = 0;
            for (Vertex vertex2 : vertex.adjacentList) {
                if (vertex2.hasBirthDay) {
                    count++;
                }
            }
            sb.append(count + " ");
        }
        System.out.println(sb);
    }


   // unSiGraph là đồ thị vô hướng đơn giản
    public static Vertex[] unSiGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices); // dỉnh bắt đầu từ 0
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // graph0
            if (!graph[u].adjacentList.contains(graph[v])) {
                graph[v].adjacentList.add(graph[u]);
                graph[u].adjacentList.add(graph[v]);
            }
        }
        return graph;
    }

    public static Vertex[] graph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            int birthday = sc.nextInt();
            graph[i] = new Vertex(i, birthday);
        }
        return graph;
    }

    static class Vertex {
        int id;
        int birthday;
        boolean hasBirthDay = false;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();

        public Vertex(int id, int birthday) {
            this.id = id;
            this.birthday = birthday;
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
