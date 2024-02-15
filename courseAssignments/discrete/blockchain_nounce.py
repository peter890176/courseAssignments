#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import hashlib
number = 0;
nonce = 1;
prehash='0000000000000000000000000000000000000000000000000000000000000000';

lines = []
while True:
    try:
        lines.append(input())
    except:
        break
for line in lines:
    data=str(line)
    hashok = 0;
    number = number+1;
    nonce = 1;
    while(hashok==0):
        d=str(number)+str(nonce)+data+prehash
        x = d.encode('utf-8');
        j=hashlib.sha256(x);
        q = j.hexdigest();
        if (int(q[0],16)+int(q[1],16)+int(q[2],16))==0:
            hashok = 1;
            prehash = str(q);
        nonce = nonce + 1;
print(q)

