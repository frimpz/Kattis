import sys
import random

d = (int)(sys.stdin.readline())
l = [float(i) for i in sys.stdin.readline().split()]
g = [float(i) for i in sys.stdin.readline().split()]

step_size = 0.5

for i in range(1000):
    w = [1]*d

    for j in range(d):
        w[j] = random.uniform(l[j],g[j])


    error = 100
    old_error = 100
    flag = 0

    while  error > 1:
        try:
            if flag == 1:
                break
            print(' '.join(str(x) for x in w))
            sys.stdout.flush()

            old_error = error

            error = float(sys.stdin.readline())
            dv = sys.stdin.readline().split()

            if abs(old_error-error) <0.005:
                break


            for x,y in enumerate(dv):
                temp = w[x] - float(y)*step_size
                if temp > g[x] or temp < l[x] :
                    flag =1
                    break
                else:
                    w[x] =temp
        except:
            break