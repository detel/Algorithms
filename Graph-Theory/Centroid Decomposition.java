/**
 * http://codeforces.com/contest/342/submission/19874168
 * https://threads-iiith.quora.com/Centroid-Decomposition-of-a-Tree
 */
import java.util.*;
import java.io.*;

public class XeniaAndTree {
    public static InputReader in;
    public static PrintWriter out;

    public static final int MOD = (int) (1e9 + 7);
    public static ArrayList<Integer>[] graph;
    public static int[] centroidTreeParent, centroidTreeLevel, subTreeSize, dist[];
    public static int LOGMAXN;
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);
        
        int n = in.nextInt(),
            m = in.nextInt();
        graph = new ArrayList[n];
        for(int i = 0; i < n; ++i)  graph[i] = new ArrayList<Integer>();
        for(int i = 1; i < n; ++i) {
            int u = in.nextInt() - 1,
                v = in.nextInt() - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // construct centroid tree in P
        centroidTreeParent = new int[n];
        subTreeSize = new int[n];
        centroidTreeLevel = new int[n];
        Arrays.fill(centroidTreeParent, -2);
        decompose(0, -1, n, 0);

        // preprocess needed distances
        LOGMAXN = (int)(Math.log(n)/Math.log(2) + 3);
        dist = new int[LOGMAXN][n];
        for(int i = 0; i < n; ++i)
            computeDist(i, centroidTreeLevel[i], i, -1, 0);
        
        // process queries
        near = new int[n];
        Arrays.fill(near, n);
        StringBuilder result = new StringBuilder();
        update(0);
        while(m-->0)
            if(in.nextInt() == 1)
                update(in.nextInt() - 1);
            else
                result.append(query(in.nextInt() - 1)+"\n");
        
        out.print(result);

        out.close();
    }
    
    /*
     * Update and Query
     */
    public static int[] near;
    public static void update(int v) {
        int u = v,
            lb = centroidTreeLevel[v];
        while(u != -1) {
            near[u] = Math.min(near[u], dist[lb][v]);
            u = centroidTreeParent[u];
            --lb;
        }
    }
    
    public static int query(int v) {
        int u = v,
            lb = centroidTreeLevel[v],
            ans = Integer.MAX_VALUE;
        while(u != -1) {
            ans = Math.min(ans, near[u] + dist[lb][v]);
            u = centroidTreeParent[u];
            --lb;
        }
        return ans;
    }
    
    public static int findSize(int u, int p) {
        int sz = 1;
        for(int i = 0, size = graph[u].size(); i < size; ++i) {
            int v = graph[u].get(i);
            if(v != p && centroidTreeParent[v] == -2)
                sz += findSize(v, u);
        }
        return subTreeSize[u] = sz;
    }

    public static int decompose(int u, int p, int n, int lb) {
        if(p == -1)
            n = findSize(u, -1);
        int idx = -1;
        for(int i = 0, size = graph[u].size(); i < size; ++i) {
            int v = graph[u].get(i);
            if(v != p && centroidTreeParent[v] == -2 && subTreeSize[v] > n / 2) {
                idx = v;
                break;
            }
        }
        if(idx != -1)
            return decompose(idx, u, n, lb);

        //u is centroid, decompose forest
        centroidTreeParent[u] = -1;
        for(int i = 0, size = graph[u].size(); i < size; ++i) {
            int v = graph[u].get(i);
            if(centroidTreeParent[v] == -2)
                centroidTreeParent[decompose(v, -1, n, lb + 1)] = u;
        }
        centroidTreeLevel[u] = lb;
        return u;
    }
    
    /*
     * Distance Preprocessing
     */
    public static void computeDist(int s, int lb, int u, int p, int d) {
        dist[lb][u] = d;
        for(int i = 0, size = graph[u].size(); i < size; ++i) {
            int v = graph[u].get(i);
            if(v != p && centroidTreeLevel[v] >= lb)
                computeDist(s, lb, v, u, d + 1);
        }
    }


    static class Node implements Comparable<Node> {
        int next;
        long dist;

        public Node(int u, int v) {
            this.next = u;
            this.dist = v;
        }

        public void print() {
            out.println(next + " " + dist + " ");
        }

        public int compareTo(Node n) {
            return Integer.compare(-this.next, -n.next);
        }
    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
