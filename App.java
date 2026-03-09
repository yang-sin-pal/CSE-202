import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class App {
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
        EITREHE1(n);
    }

    static void EITREHE1(int vertices){
        Vertex[] graph = unSiGraph(vertices, vertices - 1);
        dfsPre(graph[0]);

    } 

    static void EICHTTRE(int vertices, int edges) {
        if (isTree(vertices, edges)) {
            sb.append("YES\n");
        } else {
            sb.append("NO\n");
        }
    }

    // kiểm tra đồ thị có phải là cây: liên thông + không có chu trình
    static boolean isTree(int vertices, int edges) {
        Vertex[] graph = unMuGraph(vertices, edges);
        if(edges != vertices - 1){
            return false;
        }else {
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

    // Kiểm tra đồ thị có liên thông
    static boolean isConnected(Vertex vertex, Vertex[] graph) {
        // Lấy 1 đỉnh Duyệt DFS
        // chạy for each lấy các đỉnh ra kiểm tra xem có đỉnh nào chưa được viếng thăm
        // hay không
        // Lý do: Nếu đồ thị liên thông thì từ 1 đỉnh sẽ viếng thăm được tất cả các đỉnh
        // và sẽ không có đỉnh nào chưa được viếng thăm
        dfsPre(vertex);
        for (Vertex vertex2 : graph) {
            if (vertex2.visited == false) {
                return false;
            }
        }
        return true;
    }

    // kiểm tra đồ thị có chu trình trong dfs đỉnh vertex
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

    public static void EIPEOYMK() {

    }

    // Đồ thị vô hướng
    // Bật human graph
    public static void EIFOLTRE(int vertices) {
        Map<String, Vertex> graph = new HashMap<>();
        
        for (int i = 0; i < vertices - 1; i++) {
            String u = sc.next();
            String v = sc.next();
            // Vertex vu = graph.getOrDefault(u, new Vertex(u));
            // Vertex vv = graph.getOrDefault(v, new Vertex(v));
            Vertex vu = graph.computeIfAbsent(u, k -> new Vertex(u));
            Vertex vv = graph.computeIfAbsent(v, k -> new Vertex(v));
            vu.adjacentList.add(vv);
            vv.adjacentList.add(vu);
        }
        String root = sc.next();
        Vertex rootVertex = graph.get(root);
        sortAdjaList(graph.values().toArray(new Vertex[0])); // Sửa
        dfsPreTree(rootVertex, 0);
        System.out.println(sb);
    }

    static int totalVertices = 0; // Start counting vertices
    static int totalDegree = 0; // Start Count degree
    // Đồ thị vô hướng
    // Đỉnh bắt đầu từ 0, bật graph0

    public static void EICONP3(int vertices, int edges) {
        Vertex[] graph = unMuGraph(vertices, edges);
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].visited == false) {
                sb.append(i + " ");
                // bfs(graph[i]);

                totalVertices = 0; // Start counting vertices
                totalDegree = 0; // Start Count degree
                // dfsPre(graph[i]);
                int totalEdges = totalDegree / 2; // Use totalDegree to calculate edges
                sb.append(totalVertices + " "); // print vertices
                sb.append(totalEdges + "\n"); // print edges

            }
        }
        System.out.println(sb);
    }

    // Đồ thị vô hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EICONP1(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].visited == false) {
                sb.append(i + " ");
                bfs(graph[i]);
            }
        }
        System.out.println(sb);
    }

    // Đồ thị vô hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EICONP(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        int count = 0;
        for (Vertex vertex : graph) {
            if (vertex.visited == false) {
                bfs(vertex);
                count++;
            }
        }
        System.out.println(count);
    }

    // Đồ thị vô hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EIUBFS2(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        sortAdjaList(graph);
        bfs(graph[0]);
        System.out.println(sb);
    }

    // Đồ thị có hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    // Cạnh đi từ u tới v
    public static void EIUBFS1(int vertices, int edges) {
        Vertex[] graph = diGraph(vertices, edges);
        sortAdjaList(graph);
        bfs(graph[0]);
        System.out.println(sb);
    }

    public static void bfsTRee(Vertex v, Queue<String> indentQueue){
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v);
        v.visited = true;
        while (queue.size() != 0) {
            Vertex current = queue.poll();
            String indent = indentQueue.poll();
            sb.append(indent).append(current.name).append("\n");
            for (Vertex vertex : current.adjacentList) {
                if (vertex.visited == false) {
                    queue.add(vertex);
                    vertex.visited = true;
                    indentQueue.add(indent + "---");
                }

            }
        }
    }

    // Breath First Search
    // Thích hợp cho việc tìm đường đi ngắn nhất
    public static void bfs(Vertex v) { // Duyệt đồ thị theo chiều rộng
        // Queue<String> indentQueue = new LinkedList<>();
        // indentQueue.add("-");
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v);
        v.visited = true;

        // sb.append(v.id + " "); // in ra các đỉnh khi viếng thăm lần đầu
        // int totalVertices = 1; // Start counting vertices
        // int totalDegree = v.adjacentList.size(); // Start Count degree
        while (queue.size() != 0) {
            Vertex current = queue.poll();
            //  String indent = indentQueue.poll();
            // sb.append(indent).append(current.name).append("\n");
            for (Vertex vertex : current.adjacentList) {
                if (vertex.visited == false) {
                    queue.add(vertex);
                    vertex.visited = true;
                    // sb.append(vertex.id + " "); // in ra các đỉnh khi viếng thăm lần đầu
                    // totalVertices++; // counting vertices
                    // totalDegree += vertex.adjacentList.size(); // Count degree
                    //  indentQueue.add(indent + "---");
                }

            }
        }
        // int totalEdges = totalDegree / 2; // Use totalDegree to calculate edges
        // sb.append(totalVertices + " "); // print vertices
        // sb.append(totalEdges + "\n"); // print edges
    }

    // Đồ thị vô hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EIUDFS2(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        sortAdjaList(graph);
        // dfsPre(graph[0]);
        System.out.println(sb);
    }

    // Đồ thị có hướng
    // Đỉnh bắt đầu từ 0, bật graph0
    // Cạnh đi từ u tới v
    public static void EIUDFS1(int vertices, int edges) {
        Vertex[] graph = diGraph(vertices, edges);
        sortAdjaList(graph);
        // dfsPre(graph[0]);
        System.out.println(sb);
    }

    // Depth First Search preorder
    // Có thể dùng stack để hiện thực hóa DFS
     public static void dfsPreTree(Vertex v, int depth) { // Duyệt đồ thị theo chiều sâu
        v.visited = true;
        sb.append("-");
        for (int i = 0; i < depth; i++) {
            sb.append("---");
        }
        sb.append(v.name + "\n");
        for (Vertex w : v.adjacentList) {
            if (w.visited == false) {
                dfsPreTree(w, depth + 1);
            }
        }
    }
    public static void dfsPre(Vertex v) { // Duyệt đồ thị theo chiều sâu
        v.visited = true;
        for (Vertex w : v.adjacentList) {
            if (w.visited == false) {
                dfsPre(w);
            }
        }
    }

    // Áp dụng đồ thị vô hướng, xem 1 người là 1 đỉnh
    // tìm những người bạn cô đơn (những người có ít hơn số bạn yêu cầu) của môt
    // người(đỉnh) đó.
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EIUSUFBF(int vertices, int edges, int atleast) {
        Vertex[] graph = unSiGraph(vertices, edges);
        for (int i = 0; i < vertices; i++) {
            List<Vertex> termList = graph[i].adjacentList;
            sb.append(i + " ");
            termList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
            for (Vertex vertex : termList) {
                if (vertex.adjacentList.size() < atleast) {
                    sb.append(vertex.id + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // Áp dụng đồ thị vô hướng, xem 1 người là 1 đỉnh, in ra số bạn bè khác giới
    // tính của người(đỉnh) đó.
    // Bật humanGraph1 trong khi tạo đồ thị.
    public static void EIFACEBOOK(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        for (int i = 1; i <= vertices; i++) {
            int count = 0;
            for (Vertex vertex : graph[i - 1].adjacentList) {
                if (!vertex.gender.equals(graph[i - 1].gender)) {
                    count++;
                }
            }
            sb.append(count + " ");
        }
        System.out.println(sb);
    }

    // Áp dụng đồ thị vô hướng, xem 1 người là 1 đỉnh, in ra số bạn (số đỉnh kề) và
    // danh sách bạn của người(đỉnh) đó.
    // Đỉnh bắt đầu từ 0, bật graph0
    public static void EIMKF(int vertices, int edges) {
        Vertex[] graph = unSiGraph(vertices, edges);
        for (int i = 0; i < vertices; i++) {
            List<Vertex> termList = graph[i].adjacentList;
            sb.append(i + " ");
            sb.append(termList.size() + " ");
            termList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
            for (Vertex vertex : termList) {
                sb.append(vertex.id + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // Trong đồ thị có hướng, có đường đi từ a tới b đi qua nhiều nhất 1 đỉnh trung
    // gian không?
    // Đỉnh bắt đầu từ 1, bật graph1
    public static void EIHCON(int vertices, int edges, int query) {
        Vertex[] graph = diGraph(vertices, edges);
        for (int i = 0; i < query; i++) {
            boolean flag = false;
            int a = sc.nextInt();
            int b = sc.nextInt();
            for (Vertex vertex : graph[a - 1].adjacentList) {
                if (vertex.id == b) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                for (Vertex vertex : graph[a - 1].adjacentList) {
                    for (Vertex vertex2 : vertex.adjacentList) {
                        if (vertex2.id == b) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
            if (flag) {
                sb.append("Y\n");
            } else {
                sb.append("N\n");
            }
        }
        System.out.println(sb);
    }

    // Trong ĐA đồ thị vô hướng, bậc của mỗi đỉnh là bao nhiêu?
    // Đỉnh bắt đầu từ 1, bật graph1
    public static void EIUDEG(int vertices, int edges) {
        Vertex[] graph = unMuGraph(vertices, edges);
        for (int i = 0; i < vertices; i++) {
            sb.append(graph[i].adjacentList.size());
            sb.append(" ");
        }
        System.out.println(sb);
    }

    // Trong đồ thị có hướng, b có phải là cạnh kề của a không?
    // Đỉnh bắt đầu từ 1, bật graph1
    public static void EICON(int vertices, int edges, int query) {
        Vertex[] graph = diGraph(vertices, edges);
        for (int i = 0; i < query; i++) {
            boolean flag = false;
            int a = sc.nextInt();
            int b = sc.nextInt();
            for (Vertex vertex : graph[a - 1].adjacentList) {
                if (vertex.id == b) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                sb.append("Y\n");
            } else {
                sb.append("N\n");
            }
        }
        System.out.println(sb);
    }

    // unMuGraph là ĐA đồ thị vô hướng
    public static Vertex[] unMuGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices);// dỉnh bắt đầu từ 0
        // Vertex[] graph = graph1(vertices); //đỉnh bắt đầu từ 1
        // Vertex[] graph = humanGraph1(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // graph1
            // graph[v-1].adjacentList.add(graph[u-1]);
            // graph[u-1].adjacentList.add(graph[v-1]);

            // graph0
            graph[v].adjacentList.add(graph[u]);
            graph[u].adjacentList.add(graph[v]);
        }
        return graph;
    }

    // unSiGraph là đồ thị vô hướng đơn giản
    public static Vertex[] unSiGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices); // dỉnh bắt đầu từ 0
        // Vertex[] graph = graph1(vertices); //đỉnh bắt đầu từ 1
        // Vertex[] graph = humanGraph0(vertices);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // graph0
            if (!graph[u].adjacentList.contains(graph[v])) {
                graph[v].adjacentList.add(graph[u]);
                graph[u].adjacentList.add(graph[v]);
            }

            // graph1
            // if(!graph[u-1].adjacentList.contains(graph[v-1])){
            // graph[v-1].adjacentList.add(graph[u-1]);
            // graph[u-1].adjacentList.add(graph[v-1]);
            // }

        }
        return graph;
    }

    // diGraph là đồ thị có hướng
    public static Vertex[] diGraph(int vertices, int edges) {
        Vertex[] graph = graph0(vertices);
        // Vertex[] graph = graph1(vertices);
        // Vertex[] graph = humanGraph1(vertices);
        for (int i = 0; i < edges; i++) { // graph0 i=0, graph1 i=1
            int u = sc.nextInt();
            int v = sc.nextInt();
            // if(!graph[v-1].adjacentList.contains(graph[u-1])){ //graph1, v -> u
            // graph[v-1].adjacentList.add(graph[u-1]);
            // }

            if (!graph[u].adjacentList.contains(graph[v])) { // graph0, u ->v
                graph[u].adjacentList.add(graph[v]);
            }
        }
        return graph;
    }

    static void sortAdjaList(Vertex[] graph) {
        for (Vertex vertex : graph) {
            vertex.adjacentList.sort((v1, v2) -> {
                return v1.name.compareTo(v2.name);
            });
        }
    }

    public static Vertex[] humanGraph1(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 1; i <= vertices; i++) {
            String gender = sc.next();
            graph[i - 1] = new Vertex(i, gender);
        }
        return graph;
    }

    public static Vertex[] humanGraph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            String gender = sc.next();
            graph[i] = new Vertex(i, gender);
        }
        return graph;
    }

    // graph1 nghĩa là đỉnh bắt đầu từ 1
    public static Vertex[] graph1(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 1; i <= vertices; i++) {
            graph[i - 1] = new Vertex(i);
        }
        return graph;
    }

    // graph0 nghĩa là đỉnh bắt đầu từ 0
    public static Vertex[] graph0(int vertices) {
        Vertex[] graph = new Vertex[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new Vertex(i);
        }
        return graph;
    }

    static class Vertex {
        int id;
        String name;
        String gender;
        boolean visited = false;
        List<Vertex> adjacentList = new ArrayList<>();
        Vertex parent;

        public Vertex(int id) {
            this.id = id;
        }

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public Vertex(String name) {
            this.name = name;
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
