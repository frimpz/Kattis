import sys
import math

x = []
for i in sys.stdin:
	x.append(i.split())

n = int(x[0][0])	
d = x[1][0:n]
d = [float(i) for i in d]
c = float(x[1][n])
query = x[2:][:]
dx = []

for i in query:
	product = 0 
	weight = 0
	for j,b in zip(i,d):
		product = product + (b*float(j))
		weight = weight + (b*b)
	product = product + c
	weight = math.sqrt(weight)
	dx.append(product/weight)

for i in dx:
	print(i)