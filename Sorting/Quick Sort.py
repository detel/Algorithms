def quickSort(ar):
    if len(ar) < 2:
        return(ar)
    
    left, right = [], []
    for i in xrange(1,len(ar)):
        left.append(ar[i]) if ar[i] < ar[0] else right.append(ar[i])
            
    left, right = quickSort(left), quickSort(right)
    result = left + [ar[0]] + right
    
    return result 

ar = map(int, raw_input().split())
print quickSort(ar)
