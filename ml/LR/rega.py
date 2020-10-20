import numpy as np
import matplotlib.pyplot as plt


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

xx = []
xx.append([1.0]*n)
for i in range(d):
    xx.append([float(row[i]) for row in inp[1:][:n]])


def sigmoid(scores):
    return 1 / (1 + np.exp(-scores))

def log_likelihood(features, target, weights):
    scores = np.dot(features, weights)
    ll = np.sum( target*scores - np.log(1 + np.exp(scores)) )
    return ll


def logistic_regression(features, target,xx, num_steps, learning_rate, add_intercept=False):


    #if add_intercept:
     #   intercept = np.ones((features.shape[0], 1))
      #  features = np.hstack((intercept, features))

    print(features)
    print(target)

    #weights = np.zeros(features.shape[1])
    weights = [0] * (d + 1)

    for step in range(num_steps):
        scores = np.dot(features, weights)
        predictions = sigmoid(scores)

        # Update weights with gradient
        output_error_signal = target - predictions
        gradient = np.dot(xx, output_error_signal)
        weights += learning_rate * gradient

        # Print log-likelihood every so often
        if step % 10000 == 0:
            print
            log_likelihood(features, target, weights)

    return weights


weights = logistic_regression(x, y,xx,
                    num_steps = 300000, learning_rate = 5e-5, add_intercept=True)

print(weights)