from math import sqrt

def phi(num):
	result = num

	for i in xrange(2,int(sqrt(num))+1):
		if num%i == 0:
			while num%i == 0:
				num /= i
			result -= result/i

	if num>1:
		result -= result/num

	return result

for x in xrange(1,10):
	print phi(x)
