package week3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EIBIPARTITE {
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

    public static void main(String[] args) throws IOException {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int vertices = sc.nextInt();
            int edges = sc.nextInt();
            Vertex[] graph = new Vertex[vertices];
            for (int j = 0; j < vertices; j++) {
                graph[j] = new Vertex(j);
            }
            for (int j = 0; j < edges; j++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph[u].adjenctionList.add(graph[v]);
                graph[v].adjenctionList.add(graph[u]);
            }
            boolean isBipartite = true;
            for (Vertex vertex : graph) {
                if (!vertex.visited) {
                    isBipartite = bfsCheckBipartite(vertex);
                    if (!isBipartite) {
                        break;
                    }
                }
            }
            if (isBipartite == true) {
                sb.append("Yes").append("\n");
            } else {
                sb.append("No").append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Vertex {
        int id;
        int color;
        boolean visited = false;
        List<Vertex> adjenctionList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

    }

    static boolean bfsCheckBipartite(Vertex v) {
        Queue<Vertex> queue = new ArrayDeque<>();
        v.visited = true;
        v.color = 1;
        queue.add(v);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Vertex vertex : current.adjenctionList) {
                if (vertex.visited == false) {
                    if (current.color == 1) {
                        vertex.color = 2;
                    } else {
                        vertex.color = 1;
                    }
                    queue.add(vertex);
                    vertex.visited = true;
                } else if (vertex.color == current.color) {
                    return false;
                }
            }
        }
        return true;
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
