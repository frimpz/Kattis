import sys
import random
import math

inp = []
for i in sys.stdin:
    inp.append(i.split())

n = int(inp[0][0])
d = int(inp[0][1])
y = [float(i[d]) for i in inp[1:][:n]]
x = []
for i in inp[1:][:n]:
    temp = [1.0]
    for j in i[:d]:
        temp.append(float(j))
    x.append(temp)

step_size = 0.01



def calculateError(w,x,y):
    error_sum = 0
    for i,j in enumerate(y):
        the_e =0
        for k,l in enumerate(w):
            the_e = the_e + l*x[i][k]
        x_x = math.exp(y[i]*the_e)
        error_sum = error_sum + math.log1p(x_x)
    return error_sum/len(y)


def calculateGradient(w,x,y):
    gradient = w = [1]*(len(w))
    for i in range(n):

    for i,j in enumerate(w):
        sum =0
        for b,a in enumerate(x):
            top = a[i]*y[b]
            print(j*a[i])
            down = 1+ math.exp(y[b]*j*a[i])
            sum = sum + (top/down)
        gradient[i] = (-1/len(y))*sum
    return gradient

for i in range(d):
    #w = [1] * (d + 1)
    w = [0.1,0.2,0,0.1]

    #for j in range(d+1):

     #   w[j] = random.uniform(1,100)

    #error = calculateError(w,x,y)
    #gradient = calculateGradient(w, x, y)

    counter =0
    flag = 0
    while(counter<1):
        if flag == 1:
            break
        for j in range(d + 1):
            error = calculateError(w, x, y)
            gradient = calculateGradient(w, x, y)
            temp = w[j] - gradient[j] * step_size
            if temp > (2*math.pow(10,9)) or temp < (-2*math.pow(10,9)):
                flag = 1
                break
            else:
                w[j] = temp
            counter+=1


for i in w:
    print(i, end=" ")
