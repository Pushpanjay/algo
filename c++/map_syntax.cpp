#include<iostream>
#include<map>
#include<unordered_map>
// #include<>

using namespace std;

struct Node{
  int x,y;
};

int main(){
  map<int, Node> c;
  c[2]={4,5};
  for(auto it=c.begin();it!=c.end();it++){
    cout<<it->first<<" "<<it->second.y;
  }

  unordered_map<int, int>c;
}