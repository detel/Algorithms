from fractions import gcd
import random

def brent(N):
        if N%2==0:
                return 2
        y,c,m = random.randint(1, N-1),random.randint(1, N-1),random.randint(1, N-1)
        g,r,q = 1,1,1
        while g==1:             
                x = y
                for i in range(r):
                        y = ((y*y)%N+c)%N
                k = 0
                while (k<r and g==1):
                        ys = y
                        for i in range(min(m,r-k)):
                                y = ((y*y)%N+c)%N
                                q = q*(abs(x-y))%N
                        g = gcd(q,N)
                        k = k + m
                r = r*2
        if g==N:
                while True:
                        ys = ((ys*ys)%N+c)%N
                        g = gcd(abs(x-ys),N)
                        if g>1:
                                break
         
        return g

def factorize(num):     #valid for all num<10^18
        factors = [[1,1]]
        for i in xrange(2,1000000):
                if num%i == 0:
                        count = 0
                        while num%i == 0:
                                num/= i
                                count += 1
                        factors.append([i,count])
        if num == 1:
                return factors
        i = brent(num)
        if num%i == 0:
                count = 0
                while num%i == 0:
                        num/= i
                        count += 1
                factors.append([i,count])
        if num != 1:
                factors.append([i,count])
        return factors

print factorize(123456789987654321)
