
from numpy import array
from numpy.linalg import inv
from matplotlib import pyplot
data = array([
	[0.05, 1.2, 0.12],
	[0.18, 1.5, 0.22],
	[0.31, 1.4, 0.35],
	[0.42, 0.9, 0.38],
	[0.5, 0.98, 0.49],
	
	])
X, y = data[:,1], data[:,2]
X = X.reshape((len(X), 1))
# linear least squares
b = inv(X.T.dot(X)).dot(X.T).dot(y)
print(b)
# predict using coefficients
yhat = X.dot(b)
# plot data and predictions
pyplot.scatter(X, y)
pyplot.plot(X, yhat, color='red')
pyplot.show()