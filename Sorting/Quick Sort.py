def quickSort1(ar):
    if len(ar) < 2:
        return(ar)
    
    left, right = [], []
    for i in xrange(1,len(ar)):
        left.append(ar[i]) if ar[i] < ar[0] else right.append(ar[i])
            
    left, right = quickSort(left), quickSort(right)
    result = left + [ar[0]] + right
    
    return result
    
def quickSort2(arr):
    if not arr:
        return []

    pivots = [x for x in arr if x == arr[0]]
    lesser = quickSort([x for x in arr if x < arr[0]])
    greater = quickSort([x for x in arr if x > arr[0]])

    return lesser + pivots + greater


ar = map(int, raw_input().split())
print quickSort1(ar),quickSort2(ar) 
