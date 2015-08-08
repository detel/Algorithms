from bisect import bisect_left

def binary_search(a, x, lo=0, hi=None):   
    hi = hi if hi is not None else len(a)    
    pos = bisect_left(a,x,lo,hi)          
    return (pos if pos != hi and a[pos] == x else -1)
