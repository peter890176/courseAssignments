#include<map>
#include<string>
#include <fstream>
using namespace std;

struct xyid 
{
	int yl;
	int xr;
	int yh;
	int id;
};

int main(int argc, char ** argv)
{
	ifstream input;
	input.open(argv[1], ios::in);
	ofstream output;
	output.open(argv[2], ios::out);
	multimap<int, xyid> map1;
	multimap<int, string> map2;
	multimap<int, string> map3;
	multimap<int, string> map4;

	char cha;
	int inter;
	char r = 'r';
	int a,b,c,d,e;

	while (input >> cha)
	{
		if (cha == 'I')
		{
			input >> cha;
			r = cha;
			input >> inter;
			e = inter;
			input >> inter;
			a = inter;
			input >> inter;
			b = inter;
			input >> inter;
			c = inter;
			input >> inter;
			d = inter;
			map1.insert(pair<int, xyid>({ a }, { b,c,d,e }));
		}
		if (cha == 'O')
		{
			output << cha << endl;
			input >> inter;
			a = inter;
			input >> inter;
			b = inter;
			input >> inter;
			c = inter;
			input >> inter;
			d = inter;
			e = 99999;
			map1.insert(pair<int, xyid>({ a }, { b,c,d,e }));
			for (auto it = map1.begin(); it != map1.end() && it->first <= a;it++)
			{
				if ((a >= it->first) && (b >= it->second.yl) && (c <= it->second.xr) && (d <= it->second.yh)&&(it->second.id !=99999))
				{
					if ((a - (it->first)) < 20) { map2.insert(pair<int, string>({ it->second.id }, { " violate" })); }
					else if ((b - (it->second.yl)) < 20) { map2.insert(pair<int, string>({ it->second.id }, { " violate" })); }
					else if (((it->second.xr) - c) < 20) { map2.insert(pair<int, string>({ it->second.id }, { " violate" })); }
					else if (((it->second.yh) - d) < 20) { map2.insert(pair<int, string>({ it->second.id }, { " violate" })); }
					else { map2.insert(pair<int, string>({ it->second.id }, { "" })); }
					
				}
			}
			for (auto it = map2.begin(); it != map2.end();it++) 
			{
				output << r << it->first << it->second << endl;
			}
			map2.clear();
			typedef multimap<int, xyid>::iterator iterator;
			pair<iterator, iterator> iterpair = map1.equal_range(a);
			iterator it = iterpair.first;
			for (; it != iterpair.second; it++)
			{
				if ((a == it->first) && (b == it->second.yl) && (c == it->second.xr) && (d == it->second.yh))
				{
					map1.erase(it);
					break;
				}
			}
		}

		if (cha == 'S')
		{
			output << cha << endl;
			input >> inter;
			a = inter;
			input >> inter;
			b = inter;
			input >> inter;
			c = inter;
			input >> inter;
			d = inter;
			e = 999;
			typedef multimap<int, xyid>::iterator iterator;
			pair<iterator, iterator> iterpair = map1.equal_range(a);
			iterator it = iterpair.first;
			for (; it != iterpair.second; it++)
			{
				if ((a == it->first) && (b == it->second.yl) && (c == it->second.xr) && (d == it->second.yh))
				{
					{ map3.insert(pair<int, string>({ it->second.id }, { "r" })); }
				}
			}

			for (auto it = map3.begin(); it != map3.end();it++)
			{
				output << "r" << it->first << endl;
			}
			map3.clear();
		}
		if (cha == 'D')
		{
			input >> inter;
			a = inter;
			input >> inter;
			b = inter;
			input >> inter;
			c = inter;
			input >> inter;
			d = inter;
			e = 999;
			typedef multimap<int, xyid>::iterator iterator;
			pair<iterator, iterator> iterpair = map1.equal_range(a);
			iterator it = iterpair.first;
			for (; it != iterpair.second; it++)
			{
				if ((a == it->first) && (b == it->second.yl) && (c == it->second.xr) && (d == it->second.yh))
				{
					map1.erase(it);
					break;
				}
			}
		}
		if (cha == 'A')
		{
			output << cha << endl;
			input >> inter;
			a = inter;
			input >> inter;
			b = inter;
			int minn = 2147483647;
			typedef multimap<int, xyid>::iterator iterator;
			pair<iterator, iterator> iterpair = map1.equal_range(a);
			iterator it = iterpair.first;
			for (; it != iterpair.second; it++)
			{
				if ((a == it->first) && (b == it->second.yl))
				{
					minn = min((it->second.xr + it->second.yh), minn);
				}
			}
			iterpair = map1.equal_range(a);
			it = iterpair.first;
			for (; it != iterpair.second; it++)
			{
				if (b ==it->second.yl && minn == (it->second.xr + it->second.yh))
				{
					{ map4.insert(pair<int, string>({ it->second.id }, { "r" })); }
				}
			}
			for (auto it = map4.begin(); it != map4.end();it++)
			{
				output << "r" << it->first << endl;
			}
			map4.clear();
			}
	}
	input.close();
	output.close();
}
	
	