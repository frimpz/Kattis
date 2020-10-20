import sys
from statistics import mean as mn

inp = []
for i in sys.stdin:
    inp.append(i.split())

n = int(inp[0][0])
d = int(inp[0][1])
y = [float(i[d]) for i in inp[1:][:n]]
w = [0]*(d+1)

x = []
for i in range(d):
    x.append([float(row[i]) for row in inp[1:][:n]])


mean_y = mn(y)
w = [mean_y]
for pos,item in enumerate(x):
    mean_x = mn(item)
    sum = 0
    var = 0
    variance = 0
    for j,i in enumerate(item):
        sum = sum + (i-mean_x)*(y[j]-mean_y)
        var = var + (i-mean_x)*(i-mean_x)
    w.append(sum/var)
    w[0] = w[0] - ((sum/var)*mean_x)

for i in w:
    print(round(i,5),end=" ")

