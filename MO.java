import java.io.*;
import java.util.*;

public class MancunianAndMultiplicativeQueries {

    public static InputReader in;
    public static PrintWriter out;
    public static final int MOD = (int)1e9 + 7;
    public static int BLOCK;
    public static long value = 1;
    public static int[] A, FUNC, cnt;
    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);
        
        int N = in.nextInt(),
            C = in.nextInt(),
            Q = in.nextInt();
        BLOCK = (int)Math.sqrt(N);
        A = new int[N+1];
        for (int i = 1; i <= N; i++)    A[i] = in.nextInt();
        FUNC = new int[N+1];
        for (int i = 0; i <= N; i++)    FUNC[i] = in.nextInt();
        cnt = new int[C+1];
        
        Query[] queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            int l = in.nextInt(),
                r = in.nextInt();
            queries[i] = new Query(l, r, i);
        }
        Arrays.sort(queries);
        
        value = pow(FUNC[cnt[0]], C);
        long result = 1;
        int left = 1,
            right = 0;
        for (int i = 0; i < Q; i++) {
            Query curr = queries[i];
            while(left > curr.left) {
                left--;
                add(A[left]);
            }
            while(right < curr.right) {
                right++;
                add(A[right]);
            }
            while(left < curr.left) {
                remove(A[left]);
                left++;
            }
            while(right > curr.right) {
                remove(A[right]);
                right--;
            }
            result = (result*value)%MOD;
        }
        out.println(result);
        
        out.close();
    }
    
    public static void add(int num){
        value = (value*inverseMod(FUNC[cnt[num]]))%MOD;
        cnt[num]++;
        value = (value*FUNC[cnt[num]])%MOD;
     }
    
    public static void remove(int num){
        value = (value*inverseMod(FUNC[cnt[num]]))%MOD;
        cnt[num]--;
        value = (value*FUNC[cnt[num]])%MOD;
     }
      
      public static long inverseMod(long val) {
          return pow(val, MOD-2);
      }
      
      public static long pow(long base, long exp) {
          if(exp == 0)  return 1;
          if(exp == 1)  return base%MOD;
          long val = pow(base, exp/2);
          long result = (val*val)%MOD;
          if((exp&1) != 0)
              result = (result*base)%MOD;
          return result;
      }

      static class Query implements Comparable<Query>{
          int left, right, idx;
          
          public Query(int left, int right, int idx){
              this.left = left;
              this.right = right;
              this.idx = idx;
          }
          
          public int compareTo(Query that){
              if(this.left / BLOCK == that.left / BLOCK)
                  return Integer.compare(this.right, that.right);
              return Integer.compare(this.left, that.left);
          }
     }
      
     static class Node implements Comparable<Node>{
        int u, v;
        long score;
        
        public Node (int u, int v) {
            this.u = u;
            this.v = v;
        }
        
        public void print() {
            System.out.println(u + " " + v + " " + score);
        }
        
        public int compareTo(Node that) {
           return Long.compare(this.score, that.score);
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
