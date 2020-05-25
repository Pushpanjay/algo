#include <bits/stdc++.h>
#define MAX_CHAR 256

using namespace std;

int kthNonRepeating(string s, int k){
  int n = s.length();
  int count[MAX_CHAR];
  int index[MAX_CHAR];
  
  for(int i=0;i<MAX_CHAR;i++){
    count[i]=0;
    index[i]=n;
  }
  
  for(int i=0;i<n;i++){
    char x = s[i];
    count[x]++;
    if(count[x]==1){
      index[x]=i;
    }
    if(count[x]==2){
      index[x]=n;
    }
  }
  
  sort(index, index+MAX_CHAR);
  return (index[k-1]!=n)?index[k-1]:-1;
}

int main(){
  string str = "geeksforgeeks";
  int k = 4;
  int res = kthNonRepeating(str, k);
  if(res==-1){
    cout<<"Not found";
  } else{
    cout<<str[res]<<endl; 
  }
  return 0;
}