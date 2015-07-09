from sys import stdin

def josephus(num,k):
	if num <= 1:
		return num
	prev = 1
	for i in xrange(2,num+1):
		prev = ((prev+k-1)%i+1)
	return prev

while True:
	N,D = map(int,stdin.readline().split())
	if N == 0 and D == 0:
		break
	print josephus(N,D)
