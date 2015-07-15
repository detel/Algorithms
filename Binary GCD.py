def gcd_bin(u, v):
    u, v = abs(u), abs(v)
    if u < v:
        u, v = v, u
    if v == 0:
        return u

    k = 1
    while u & 1 == 0 and v & 1 == 0:
        u >>= 1
        v >>= 1
        k <<= 1
 
    t = -v if u & 1 else u
    while t:
        while t & 1 == 0:
            t >>= 1
        if t > 0:
            u = t
        else:
            v = -t
        t = u - v
    return u*k

print gcd_bin(2334680,789785665)
