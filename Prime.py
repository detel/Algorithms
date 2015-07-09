from math import sqrt

try:
	import psyco
	psyco.full()
except:
	pass

primes = []
#fp = open("/home/deepit/Desktop/hmm",'w')

def get_primes(n):
    
    sieve = [True] * (n/2)

    for i in xrange(3,int(n**0.5)+1,2):
        if sieve[i/2]:
            sieve[i*i/2::i] = [False] * ((n-i*i-1)/(2*i)+1)
    
    return [2] + [2*i+1 for i in xrange(1,n/2) if sieve[i]]
	

primes = get_primes(100000000)
print primes
#qq = fp.write(str(primes))
#fp.close()
