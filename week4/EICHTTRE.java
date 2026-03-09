package week4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EICHTTRE {
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
        int testcases = sc.nextInt();
        for (int i = 0; i < testcases; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            EICHTTRE(n, m);
        }
        System.out.println(sb);
    }

    static void EICHTTRE(int vertices, int edges) {
        if (isTree(vertices, edges)) {
            sb.append("YES\n");
        } else {
            sb.append("NO\n");
        }
    }

    static boolean isTree(int vertices, int edges) {
        Vertex[] graph = unMuGraph(vertices, edges);
        if (edges != vertices - 1) {
            return false;
        } else {
            if (isConnected(graph[0], graph)) {
                return true;
            }
            return false;
        }
        // if (hasCircuit(graph[0])) {
        //     return false;
        // } else {
        //     if (isConnected(graph[0], graph)) {
        //         return true;
        //     }
        //     return false;
        // }
    }

    static boolean isConnected(Vertex vertex, Vertex[] graph) {
        // Lấy 1 đỉnh Duyệt DFS (hasCircuit đã duyệt nên không cần duyệt lại )
        // chạy for each lấy các đỉnh ra kiểm tra xem có đỉnh nào chưa được viếng thăm
        // hay không
        // Lý do: Nếu đồ thị liên thông thì từ 1 đỉnh sẽ viếng thăm được tất cả các đỉnh
        // và sẽ không có đỉnh nào chưa được viếng thăm. Nếu có đỉnh chưa viếng thăm thì là đồ thị không liên thông 

        dfsPre(vertex);
        for (Vertex vertex2 : graph) {
            if (vertex2.visited == false) {
                return false;
            }
        }
        return true;
    }
     public static void dfsPre(Vertex v) { // Duyệt đồ thị theo chiều sâu
        v.visited = true;
        for (Vertex w : v.adjacentList) {
            if (w.visited == false) {
                dfsPre(w);
            }
        }
    }

    // static boolean hasCircuit(Vertex vertex) {
    //     vertex.visited = true;
    //     for (Vertex adj : vertex.adjacentList) {
    //         if (!adj.visited) {
    //             adj.parent = vertex;
    //             boolean result = hasCircuit(adj);
    //             if (result) {
    //                 return true;
    //             }
    //         } else if (adj.visited && adj != vertex.parent) {
    //             return true; // có chu trình đi qua adj, vertex
    //         }
    //     }
    //     return false;
    // }

    public static Vertex[] unMuGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[v].adjacentList.add(graph[u]);
            graph[u].adjacentList.add(graph[v]);
        }
        return graph;
    }

    public static Vertex[] graph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new Vertex(i);
        }
        return graph;
    }

    static class Vertex {
        int id;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();
        Vertex parent;

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
