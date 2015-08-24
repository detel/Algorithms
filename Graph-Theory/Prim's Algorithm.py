from collections import defaultdict
from Queue import PriorityQueue

N, M, G, S = None, None, None, None

def Prim():
  global N, M, S, G
  Q = PriorityQueue(N*N)
  Q.put((0, S))
  visited = set()
  D = [-1] * N
  D[S] = 0
  
  while not Q.empty():
    cost, x = Q.get()
    visited.add(x)
    for y in G[x]:
      if y not in visited and (D[y] == -1 or D[y] > G[x][y]):
        D[y] = G[x][y]
        Q.put((D[y], y))
        
  filter(lambda x: x!=-1, D)
  return sum(D)
