package week6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

public class EIUSEFI2 {
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

    public static void main(String[] args) throws IOException {
        HashMap<String, Vertex> graph = new HashMap<>();
        int vertices = sc.nextInt();
        int edges = vertices - 1;
        for (int i = 0; i < edges; i++) {
            String u = sc.next();
            String v = sc.next();
            Vertex uu = graph.getOrDefault(u, new Vertex(u));
            graph.put(u, uu);
            if (graph.get(v) == null) {
                Vertex vv = new Vertex(v);
                graph.put(v, vv);
            }
            graph.get(u).adjenctionList.add(graph.get(v));
            graph.get(v).adjenctionList.add(graph.get(u));

        }
        String root = sc.next();
        String keyWord = sc.next();
        for (Vertex vertex : graph.values()) {
            vertex.adjenctionList.sort((v1, v2) -> {
                return v1.name.compareToIgnoreCase(v2.name);
            });
            if (!vertex.name.equals(root) && vertex.adjenctionList.size() == 1) {
                vertex.isFile = true;
            }
        }

        dfs(graph.get(root), keyWord);
        System.out.println(sb);

    }

    static class Vertex {
        String name;
        List<Vertex> adjenctionList = new ArrayList<>();
        boolean visited = false;
        boolean isFile = false;
        int countWantedFile = 0;

        public Vertex(String name) {
            this.name = name;
        }

    }

    static int dfs(Vertex v, String keyWord) {
        v.visited = true;
        if (v.isFile) {
            if (v.name.contains(keyWord)) {
                return 1;
            }
        } else {
            for (Vertex check : v.adjenctionList) {
                if (check.visited == false) {
                    int foundFile = dfs(check, keyWord);
                    v.countWantedFile += foundFile;
                }
            }
            if (v.countWantedFile > 0) {
                sb.append(v.name).append(" ").append(v.countWantedFile).append("\n");
            }
        }
        return v.countWantedFile;
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
