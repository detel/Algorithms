def bubble_sort1(array):
	is_sorted = False

	while not is_sorted:
		is_sorted = True
		for i in xrange(len(array)-1):
			if array[i] > array[i+1]:
				is_sorted = False
				array[i+1],array[i] = array[i],array[i+1]

	return array

def bubble_sort2(array):

	for i in xrange(len(array)):
		for j in xrange(i+1,len(array)):
			if array[j] < array[i]:
				array[j],array[i] = array[i], array[j]

	return array

array = map(int,raw_input().split())
print bubble_sort1(array)
print bubble_sort2(array)
