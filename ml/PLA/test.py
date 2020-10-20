import pydot

(graph,) = pydot.graph_from_dot_file('per.dot')
graph.write_png('per.png')