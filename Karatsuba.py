#x,y are strings
def karatsuba(x,y):

	len1 = len(x)
	len2 = len(y)

	if len1 <=10 or len2 <=10 :
		return int(x)*int(y)

	if len1 > len2:
		y = y.rjust(len1,'0')
		l = len1
	else:
		x = x.rjust(len2,'0')
		l = len2

	mid = l/2
	x0 = x[:mid] 
	x1 = x[mid:]
	y0 = y[:mid]
	y1 = y[mid:]

	z0 = karatsuba(x0,y0)
	z2 = karatsuba(x1,y1)
	z1 = karatsuba(x0,y1)+karatsuba(x1,y0)

	return ((10**l)*z0 + (10**((l)/2))*z1 + z2)
