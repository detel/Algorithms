import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestZigZagSubsequence {
 
    public static void main(String args[]) {
        
            int h[] = new int[]{10, 22, 9, 33, 49, 50, 31, 60};
            int hs[] = h.clone();
            Arrays.sort(hs);
            Map<Integer, Integer> hmap = new HashMap<>();
            for (int i = 0, j = 0; i < h.length; j++) {
                hmap.put(hs[i], j);
                do {
                    ++i;
                } while (i < h.length && hs[i] == hs[i - 1]);
            }
            
            for (int i = 0; i < h.length; i++)
                h[i] = hmap.get(h[i]);
            int m = hmap.size();
            int f1[] = new int[m];
            int f2[] = new int[m];
            int f3[] = new int[m];
            
            int ans = 0;
            for (int i = 0; i < h.length; i++) {
                
                int v = h[i];
                int nf1 = Math.max(getMax(f2, m - v - 1), f3[v]) + 1;
                int nf2 = Math.max(getMax(f1, v), f3[v]) + 1;
                
                updateMax(f1, v, nf1);
                updateMax(f2, m - v - 1, nf2);
                
                ans = Math.max(ans, Math.max(nf1, nf2));
            }
            System.out.println(ans);
        }
        
        static int getMax(int fenwick[], int i) {
            int res = fenwick[i];
            while ((i = (i & (i + 1)) - 1) >= 0)
                res = Math.max(res, fenwick[i]);
            return res;
        }
        
        static void updateMax(int fenwick[], int i, int v) {
            fenwick[i] = Math.max(fenwick[i], v);
            while ((i |= i + 1) < fenwick.length)
                fenwick[i] = Math.max(fenwick[i], v);
        }
    }
