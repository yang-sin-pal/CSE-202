package week2;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EICONP3 {
static StringBuilder sb = new StringBuilder();
    static InputReader sc;
    static {
        try {
            sc = new InputReader(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        countConnectedComponent(n, m);
    }

    public static void countConnectedComponent(int vertices, int edges) {
         Vertex[] graph = unMuGraph(vertices, edges);
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].visited == false) {
                sb.append(i + " ");
                bfs(graph[i]); // Configure bfs method to count vertices and calculate edges
            }
        }
        System.out.println(sb);
    }

    public static void bfs(Vertex v) { // Duyệt đồ thị theo chiều rộng
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v);
        v.visited = true;
        int totalVertices = 1; // Start counting vertices
        int totalDegree = v.adjacentList.size(); // Start Count degree
        while (queue.size() != 0) {
            Vertex current = queue.poll();
            for (Vertex vertex : current.adjacentList) {
                if (vertex.visited == false) {
                    queue.add(vertex);
                    vertex.visited = true;
                    totalVertices++; // counting vertices
                    totalDegree += vertex.adjacentList.size(); // Count degree
                }
                
            }
        }
        int totalEdges = totalDegree/2; // Use totalDegree to calculate edges
        sb.append(totalVertices + " "); // print vertices
        sb.append(totalEdges + "\n"); // print edges
    }

    public static Vertex[] unMuGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices);// dỉnh bắt đầu từ 0
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[v].adjacentList.add(graph[u]);
            graph[u].adjacentList.add(graph[v]);
        }
        return graph;
    }

    // đỉnh bắt đầu từ 0
    public static Vertex[] graph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new Vertex(i);
        }
        return graph;
    }

    static class Vertex {
        int id;
        String gender;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;

        }

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
