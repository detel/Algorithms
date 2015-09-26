import random

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
    
def quickSort( array, low, high ):
    if low < high:
      pivot = partition( array, low, high )
      quickSort( array, low, pivot - 1 )
      quickSort( array, pivot + 1, high )
 
 
def partition( array, low, high ) :
    pivot = low + random.randrange( high - low + 1 )
    array[pivot], array[high] = array[high], array[pivot]
    
    for i in range( low, high ):
      if array[i] <= array[high]:
        array[i], array[low] = array[low], array[i]
        low += 1
 
    array[low], array[high] = array[high], array[low]
    return low

ar = map(int, raw_input().split())
print quickSort(ar, 0, len(ar)-1), quickSort1(ar), quickSort2(ar) 
