#!/usr/bin/env python
# coding: utf-8

# In[ ]:


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

vertices = sorted(G.keys(), key=len, reverse=True)
color = {}
remain_color = set()

for v in vertices:
        counter = 0
        for i in vertices:
            remain_color.add(counter)
            counter=counter+1
        for u in G[v]:
            if u in color:
                remain_color.discard(color[u])
        color[v] = min(remain_color)

min_color = 0
for key in color:
    min_color =max(color[key],min_color)
min_color = min_color+1

print(min_color)

