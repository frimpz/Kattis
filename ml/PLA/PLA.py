import sys

x = []
for i in sys.stdin:
    x.append(i.split())

n = x[0][0]
d = int(x[0][1])
x = x[1:]
learningrate = 0.1


def train(e, x):
    index = 1
    while index < len(w):
        w[index] = float(w[index]) + learningrate * e * float(x[index - 1])
        index += 1
    w[0] = float(w[0]) + (learningrate * e)


w = [6] * (d + 1)
w[0] = -20

isSeperated = False
counter = 1
while counter <= 30 and isSeperated == False:
    misclassified = 0
    isSeperated = True
    previous = w
    for i in x:
        sum = w[0]
        for position, item in enumerate(i):
            if position % (d + 1) == d:
                desiredy = int(item)
            else:
                sum = sum + w[position + 1] * float(item)
        if (sum < 0):
            predictedy = -1
        else:
            predictedy = 1
        error = desiredy - predictedy
        if (error != 0):
            misclassified += 1
            isSeperated = False
            last = len(i) - 1
            train(error, i[0:last])
    if (counter == 1):
        pocket = w
        pocketError = misclassified
    elif (misclassified < pocketError):
        pocket = previous
        pocketError = misclassified
    counter += 1
for i in pocket:
    print(i, end=" ")