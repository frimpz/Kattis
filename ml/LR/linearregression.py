import sys
from statistics import mean as mn

inp = []
for i in sys.stdin:
    inp.append(i.split())

n = int(inp[0][0])
d = int(inp[0][1])
y = [float(i[d]) for i in inp[1:][:n]]
x = []
w = [0.5]*(d+1)

step_size = 0.1

for i in inp[1:][:n]:
    temp = [1.0]
    for j in i[:d]:
        temp.append(float(j))
    x.append(temp)


def calculateError(x,y,w,n):
    error = 0
    for i, j in enumerate(x):
        h_x = 0
        for pos,item in enumerate(j):
            h_x += item*w[pos]
        error += (h_x - y[i])*(h_x - y[i])
    error /= n
    return error

def calculateDer(x,y,w,n,d):
    der = w
    for dv in range(d+1):
        sum =0
        for pos,item in enumerate(x):
            h_x = 0
            for i,j in enumerate(item):
                h_x = h_x + (w[i]*j)
            sum = sum + (h_x-y[pos])*item[dv]
        sum = sum/n
        der[dv] = der[dv] - step_size*sum
    return der


error = calculateError(x,y,w,n)
w = calculateDer(x, y, w, n, d)

counter = 0
while error > 0.1:
    newError = calculateError(x, y, w, n)
    if newError > error:
        step_size = step_size/2
        error = newError
        w = calculateDer(x, y, w, n, d)
    else:
        error = newError
        w = calculateDer(x, y, w, n, d)

    counter += 1

for i in w:
    print(round(i,5),end=" ")