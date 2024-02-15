#include<map>
#include<string>
#include <fstream>
#include <vector>
#include <array>

using namespace std;
struct xyid
{
	int id1;
	int x1;
	int y1;
	int id2;
	int x2;
	int y2;
};

int main(int argc, char** argv)
{
	ifstream input;
	input.open(argv[1], ios::in);
	ofstream output;
	output.open(argv[2], ios::out);
	int inte;
	input >> inte;
	int number_nodes = inte;
	int* arrx = new int[number_nodes];
	int* arry = new int[number_nodes];
	int* arrid = new int[number_nodes];
	int id1=0;
	int x1 = 0;
	int y1 = 0;
	int id2 = 0;
	int x2 = 0;
	int y2 = 0;
	int dist = 0;

	for (int i = 1; i <= number_nodes; i++)
	{
		int x;
		input >> x;
		arrx[i - 1] = x;
		int y;
		input >> y;
		arry[i - 1] = y;
		int id = i;
		arrid[i - 1] = id - 1;
	};
	/*
	for (int i = 0; i < number_nodes; i++)
	{
		output <<arrx[i]<<" ";
	};
	output << endl;
	for (int i = 0; i < number_nodes; i++)
	{
		output << arry[i] << " ";
	};
	output << endl;
	for (int i = 0; i < number_nodes; i++)
	{
		output << arrid[i] << " ";
	};
	*/
		 
	multimap<int, xyid> mapdist;
	for (int i = 1; i <= number_nodes; i++)
	{
		id1 = arrid[i - 1];
		x1 = arrx[i - 1];
		y1 = arry[i - 1];

		for (int j = 1; j <= number_nodes; j++)
		{
			if (i!= j && i<j)
			{
				id2 = arrid[j - 1];
				x2 = arrx[j - 1];
				y2 = arry[j - 1];
				dist = abs(x1 - x2) + abs(y1 - y2);
				mapdist.insert(pair<int, xyid>({ dist }, { id1,x1,y1,id2,x2,y2 }));
			}
			else
			{
				true;
			}
		}
	};
	/*
	for (auto it = mapdist.begin(); it != mapdist.end();it++) 
	{
		output <<it->first<<" " << it->second.id1<<" " << it->second.x1 << " " << it->second.y1 << " " << it->second.id2 << " " << it->second.x2 << " " << it->second.y2 << endl;
	}
	*/
	
	while (input >> inte)
	{
		id1 = inte;
		x1 = arrx[inte];
		y1 = arry[inte];
		int inte2;
		input >> inte2;
		id2 = inte2;
		x2 = arrx[inte2];
		y2 = arry[inte2];
		dist = abs(x1 - x2) + abs(y1 - y2);

		for (auto it = mapdist.begin(); it != mapdist.end();it++)
		{
			if (it->first == dist && id1 == it->second.id1 && id2 == it->second.id2)
			{
				mapdist.erase(it);
				mapdist.insert(pair<int, xyid>({ -5 }, { id1,x1,y1,id2,x2,y2 }));
			}
		}
	};
	/*
	for (auto it = mapdist.begin(); it != mapdist.end();it++)
	{
		output << it->first << " ";
		output << it->second.id1 << " ";
		output << it->second.x1 << " ";
		output << it->second.y1 << " ";
		output << it->second.id2 << " ";
		output << it->second.x2 << " ";
		output << it->second.y2 << endl;
	}
	*/

	int* arroutput = new int[1+ (number_nodes-1)*2];
	for (int i = 0;i <= ((number_nodes - 1) * 2); i++)
	{
		arroutput[i] = -1;
	}
	arroutput[0] = 0;

	int* arrset = new int[number_nodes];
	for (int i = 0; i < number_nodes; i++)
	{
		arrset[i] = -1;
	}


	/*
	for (int i = 0; i<=((number_nodes - 1) * 2); i++)
	{
		output << arroutput[i] << " ";
	};
	output << endl;

	for (int i = 0; i < number_nodes;  i++)
	{
		output << arrset[i] << " ";
	};
	output << endl;*/
	
	int counter_output = 1;
	int counter_edge = 0;
	for (auto it = mapdist.begin(); it != mapdist.end();it++)
	{
		if (counter_edge == (number_nodes - 1))
		{
			output << arroutput[0] << endl;
			break;
		}

		else if (arrset[it->second.id1] == -1 && arrset[it->second.id2] == -1)
		{
			if (it->first == -5)
			{
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arrset[it->second.id1] = it->second.id1;
				arrset[it->second.id2] = it->second.id1;
			}
			else
			{
				arroutput[counter_output] = it->second.id1;
				arroutput[counter_output + 1] = it->second.id2;
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arroutput[0] = arroutput[0] + it->first;
				arrset[it->second.id1] = it->second.id1;
				arrset[it->second.id2] = it->second.id1;
			}
		}
		else if ((arrset[it->second.id1] != -1) && (arrset[it->second.id2] == -1))
		{
			if (it->first == -5)
			{
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arrset[it->second.id1] = arrset[it->second.id1];
				arrset[it->second.id2] = arrset[it->second.id1];
			}
			else
			{
				arroutput[counter_output] = it->second.id1;
				arroutput[counter_output + 1] = it->second.id2;
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arroutput[0] = arroutput[0] + it->first;
				arrset[it->second.id1] = arrset[it->second.id1];
				arrset[it->second.id2] = arrset[it->second.id1];
			}
		}
		else if ((arrset[it->second.id1] == -1) && (arrset[it->second.id2] != -1))
		{
			if (it->first == -5)
			{
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arrset[it->second.id1] = arrset[it->second.id2];
				arrset[it->second.id2] = arrset[it->second.id2];
			}
			else
			{
				arroutput[counter_output] = it->second.id1;
				arroutput[counter_output + 1] = it->second.id2;
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arroutput[0] = arroutput[0] + it->first;
				arrset[it->second.id1] = arrset[it->second.id2];
				arrset[it->second.id2] = arrset[it->second.id2];
			}
		}

		else if ( (arrset[it->second.id1] != -1) && (arrset[it->second.id2] != -1) && (arrset[it->second.id1] != arrset[it->second.id2]))
		{
			if (it->first == -5)
			{
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				
				/*
				for (int k = number_nodes; k >=0 ;k--)
				{
					if (arrset[k] == arrset[it->second.id2])
						arrset[k] = arrset[it->second.id1];
				}
				*/

				for (int k = 0; k < number_nodes;k++)
				{
					if (arrset[k] == arrset[it->second.id2])
						arrset[k] = arrset[it->second.id1];
				}
			}
			else
			{
				arroutput[counter_output] = it->second.id1;
				arroutput[counter_output + 1] = it->second.id2;
				counter_output = counter_output + 2;
				counter_edge = counter_edge + 1;
				arroutput[0] = arroutput[0] + it->first;
				
				/*
				for (int k = number_nodes; k >=0;k--)
				{
					output << k;
					if (arrset[k] == arrset[it->second.id2])
						arrset[k] = arrset[it->second.id1];

				}
				*/
				
				for (int k = 0; k < number_nodes;k++)
				{
					output << k;
					if (arrset[k] == arrset[it->second.id2])
						arrset[k] = arrset[it->second.id1];
					
				}
				


			}
		}
		
		else if ( (arrset[it->second.id1] != -1) && (arrset[it->second.id2] != -1) && (arrset[it->second.id1] == arrset[it->second.id2]) )
		{
		output << "Nothing" << endl;
		}
		
		else 
		{
		output << "error";
		}

		output << "set: ";
		for (int i = 0; i<number_nodes;i++) 
		{
			output<<arrset[i]<< " ";
		}
		output << endl;
		output << "output: ";
		for (int i = 0; i <= ((number_nodes - 1)  *2 ); i++)
		{
			output << arroutput[i] << " ";
		}
		output << endl;
		output << endl;
		
	}

	/*
	for (int i = 0; i < number_nodes;i++)
	{
		output << arrset[i] << " ";
	}
	output << endl;
	for (int i = 0; i <= ((number_nodes - 1) * 2); i++)
	{
		output << arroutput[i] << " ";
	}
	output << endl;
	*/
	
	for (int l = 1; l <= (number_nodes-1)*2;l++)
	{
		if (arroutput[l]!=-1)
		{
			output << arroutput[l] << " ";
			l = l + 1;
			if (arroutput[l] != -1) 
			{
			output << arroutput[l] << endl;
			}
		}
	}
	
	
	input.close();
	output.close();
}