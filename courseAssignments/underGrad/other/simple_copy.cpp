#include <iostream>
#include <fstream>
#include <string>
using namespace std;

bool fileExists(const char *fileName)
{
    ifstream infile(fileName);
    return infile.good();
}

int main(int argc, char** argv)
{
    if(argc!=3)
    {
        cout<<"Usage: ./simple-cp SOURCE DEST"<<endl;
    }
    else
    {
        ifstream input;
        input.open(argv[1], ios::in);

        


        if (!input.is_open())
        {
            cout<<
"Error: cannot stat ‘file1’: No such file or directory"
            <<endl;
        }


        else
        {
            ofstream output;
            output.open(argv[2], ios::out);
            string str;
            if (!output.is_open())
            {
                cout<<
"Error: cannot create regular file ‘dir/file2’: No such file or directory"
                <<endl;
            }
            else
            {
            while(getline(input,str))
            {
                output << str << endl;
            }
            }
        }
    }
    return 0;
}