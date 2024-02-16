#!/usr/bin/env python
# coding: utf-8

# In[ ]:


nec = input().split();
n = int (nec[0]);
e = int (nec[1]);
c = nec[2];
d = c.split(',');
x = [int(a) for a in d];
if(n % 2 == 0):
    a = int(n / 2);
    b = int(n / a);
else:
    i = 3;
    while(i < n):
        if (n % i == 0):
            a = i;
            b = int(n / i);
            i = n;
        else:
            i = i + 2;
p = a - 1;
q = b - 1;
u = p * q;
j = 1;
while(((u*j+1)%e)!=0):
    j=j+1;
d = int(1+u*j)/e

for i in x:
    r = 1;
    s = d;
    f = i % n;
    while s!=0:
        if (int(s % 2) ==1):
            r=((r*f)%n);
        f=((f*f)%n);
        s=int(s/2);
    print(chr(r), end='');

