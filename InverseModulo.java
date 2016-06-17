/*
 *  Calculating modulo inverse in O(N)
 *  http://ideone.com/mlL5F2 
 *  https://www.youtube.com/watch?v=LE9zBhLap-U
 */

public class nCr {
    
    public static int MOD = 1000000007,
                      MAX = 1000000;
    public static long[] inv, fact, invFact;
    public static void main(String[] args) {
        inv = new long[MAX + 1];
        fact = new long[MAX + 1];
        invFact = new long[MAX + 1];
        
        inv[1] = 1;
        for(int i = 2; i <= MAX; i++)
            inv[i] = (MOD -(MOD/i)*inv[MOD%i]%MOD)%MOD;   // i^-1 mod p
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = (fact[i-1]*i)%MOD;                    // i! mod p
            invFact[i] = (invFact[i-1]*inv[i])%MOD;     // (i!)^-1 mod p
        }
    }

    public static long _nCr(int n, int r) {
        return (fact[n]*(invFact[r]*invFact[n-r])%MOD)%MOD;
    }
}
