def merge_sort(array):
	if len(array) < 2:
		return array

	result = []
	mid = len(array)/2
	y,z = merge_sort(array[:mid]),merge_sort(array[mid:])
	i,j = 0,0

	while i < len(y) and j < len(z):
		if y[i] > z[j]:
			result.append(z[j])
			j += 1
		else:
			result.append(y[i])
			i += 1

	result.extend(y[i:])
	result.extend(z[j:])

	return result

print merge_sort(map(int,raw_input().split()))
