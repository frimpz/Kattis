import random
import matplotlib.pyplot as plt
import statistics as stat

x_1 = [1]*1000
x_2 = [1]*1000
y_1 = [1]*1000
y_2 = [1]*1000
for i  in range(1000):
    x_1[i] = random.uniform(-1,1)
    x_2[i] = random.uniform(-1, 1)
    y_1[i] = x_1[i]*x_1[i]
    y_2[i] = x_2[i]*x_2[i]
    plt.ylabel('f(x)')
    plt.xlabel('x')
    plt.title('Result of experiment')
    plt.text(0.75, 0.05, 'g(x)')
    plt.grid(True)
    plt.plot([x_1[i] ,x_2[i] ],[y_1[i] ,y_2[i]])
    plt.plot([-1,1 ], [0,0])
plt.show()


# Function to find expected value
def expected_value(x_1,_x2):
   return (stat.mean(x_1)+stat.mean(x_2))/2

# Function to find bias

def bias(y_1,y_2):
    sum = 0
    for i,j in zip(y_1,y_2):
        sum = sum + (0 - i)*(0 - i)
        sum = sum + (0 - j) * (0 - j)
    return sum/(len(y_1)+len(y_2))

def variance(y_1,y_2):
    sum = 0
    #mn = (y_1[12]+y_2[12])/2
    sum = sum + (y_1[12] - 0) * (y_1[12] - 0)
    sum = sum + (y_2[12] - 0) * (y_2[12] - 0)
    #sum = sum + (0 - mn) * (0 - mn)
    return sum/2

def E_out(y_1,y_2):
    sum = 0
    for i, j in zip(y_1, y_2):
        sum = sum + (y_1[12] - i) * (y_1[12] - i)
        sum = sum + (y_2[12] - j) * (y_2[12] - j)
    return sum / (len(y_1))


print ("Expected Value : ",expected_value(x_1,x_2))
print("Bias : ", bias(y_1,y_2))
print("print variance: ",variance(y_1,y_2))
print("E_out: ",E_out(y_1,y_2))