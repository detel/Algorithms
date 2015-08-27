def maximum_subarray_sum(l):
    max_so_far = l[0]
    curr_max = l[0]
    
    for i in range(1,len(l)):
        curr_max = max(l[i], curr_max+l[i])
        max_so_far = max(max_so_far, curr_max)
    
    print max_so_far

for i in range(input()):
    maximum_subarray_sum(map(int,raw_input().split()))
