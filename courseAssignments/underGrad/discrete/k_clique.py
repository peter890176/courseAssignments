#!/usr/bin/env python
# coding: utf-8

# In[ ]:


def bron_kerbosch(G, R=0, P=0, X=0):
    if R==0:
        R = set()
    if P ==0:
        P = set(G.keys())
    if X ==0:
        X = set()
    if len(P)==0 and len(X)==0:
        return [R]
    currr=0
    if len(P) > len(X):
        for aa in P:
            if len(G[aa])>currr:
                currr=int(aa)
                pivot = str(currr)
    else:
        for aa in X:
            if len(G[aa])>currr:
                currr=int(aa)
                pivot = str(currr)
    cliques = []
    for v in P-G[pivot]:
        R_sub = R | {v}
        P_sub = P & G[v]
        X_sub = X & G[v]
        cliques.extend(bron_kerbosch(G, R_sub, P_sub, X_sub))
        P.remove(v)
        X.add(v)
    return cliques


edges = []
G = {}
while True:
    try:
        new_edge = input().split()
        new_tuple =tuple(new_edge)
        edges.append(new_tuple)
        reverse_new_tuple= tuple(reversed(new_tuple))
        edges.append(reverse_new_tuple)
    except:
        break
for key, value in edges:
    if key in G:
        G[key].add(value)
    else:
        G[key] = {value}
cliques = bron_kerbosch(G)
for i, s in enumerate(cliques):
    cliques[i] = {int(x) for x in s}  
cliques.sort(key = len)

curr_len=len(cliques[0])
curr_list = []


for clique in cliques:
    if len(clique)>curr_len:
        print(curr_len)
        
        for max_clique in curr_list:
            counter = 0
            print('{',end='')
            for v in max_clique:
                if counter == 0:
                    print(v,end='')
                    counter = 1
                else:
                    print(',',v,sep='',end='')
            print('}')
        curr_len = len(clique)
        curr_list=[]
        curr_list.append(sorted(clique))
        curr_list.sort()
    else:
        curr_list.append(sorted(clique))
        curr_list.sort()
print(curr_len)

for max_clique in curr_list:
    print('{',end='')
    counter = 0
    for v in max_clique:
        if counter == 0:
            print(v,end='')
            counter = 1
        else:
            print(',',v,sep='',end='')
    print('}')

