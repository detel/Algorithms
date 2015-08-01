def minJumps(numbers):

        jumps,n = 0,len(numbers)
        if n < 2:
                return jumps

        curr,tmp_max,tmp_index = 0,0,0

        while curr<n:
                last = curr
                curr_step = numbers[curr]
                if curr + curr_step >= n-1:
                        jumps += 1
                        return jumps
                for i in xrange(curr+1,curr+1+curr_step):
                        if numbers[i] == 0:
                                continue
                        if numbers[i] + i > tmp_max:
                                tmp_index = i
                                tmp_max = numbers[i] + i

                curr = tmp_index
                if curr != last:
                        jumps += 1
                else:
                        break
        return -1

print minJumps([1,3,6,3,2,3,6,8,9,5])
