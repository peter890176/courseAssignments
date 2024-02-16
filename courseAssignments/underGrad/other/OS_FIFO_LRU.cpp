#include <iostream>
#include <string>
using namespace std;

int main(int argc, char** argv)
{
int frames = atoi(argv[1]);
string str = argv[2];
int counter_lru=0;
int counter_fifo=0;

char* frame_array_lru = new char[frames];
for(int h = 0; h< frames;h++)
{
    frame_array_lru[h]=0;
}

for(int i = 0; i< 12;i++)
{
    int samenum =-1;
    for(int j = 0; j< frames;j++)
    {
        if(str[i]==frame_array_lru[j])
        {
            samenum=j;
        }
    }
    if(samenum!=-1)
    {
        char temp[1] {0};
        temp[0]= frame_array_lru[samenum];
        for(int k = frames-1; k>=0;k--)
        {
            if(k<samenum)
            {
                frame_array_lru[k+1]=frame_array_lru[k];
            }
        }
        frame_array_lru[0]= temp[0];
    }
    else
    {
        counter_lru ++;
        for(int k = frames-1; k>=0;k--)
        {
            if(k>=1)
            {
                frame_array_lru[k]=frame_array_lru[k-1];
            }
            else
            //k==0
            {
                frame_array_lru[k]=str[i];
            }
        }
    }
}

char* frame_array_fifo = new char[frames];
for(int h = 0; h< frames;h++)
{
    frame_array_fifo[h]=0;
}

for(int i = 0; i< 12;i++)
{
    int samenum =-1;
    for(int j = 0; j< frames;j++)
    {
        if(str[i]==frame_array_fifo[j])
        {
            samenum=j;
        }
    }
    if(samenum!=-1)
    {
        continue;
    }
    else
    {
        counter_fifo ++;
        for(int k = frames-1; k>=0;k--)
        {
            if(k>=1)
            {
                frame_array_fifo[k]=frame_array_fifo[k-1];
            }
            else
            {
                frame_array_fifo[k]=str[i];
            }
        }
    }
}
cout<<"FIFO: "<<counter_fifo<<" page faults"<<endl;
cout<<"LRU: "<<counter_lru<<" page faults"<<endl;
}



    
