#!/usr/bin/env python
# coding: utf-8

# In[ ]:


nem = input().split();
n = int (nem[0]);
e = int (nem[1]);
m = str (nem[2]);
x = [ord(a) for a in m]
s = 1;
r = 0;
for i in x:
    if (s != 1):
        print(',', end='');
    r = 1;
    s = e;
    m = i;
    m = m % n;
    while s!=0:
        if (int(s % 2) ==1):
            r=((r*m)%n);
        m=((m*m)%n);
        s=int(s/2);
    print(r, end='');


# In[83]:





# In[ ]:





# In[ ]:




