#include<bits/stdc++.h>
#define MAX 50

using namespace std;

void printString(int n){
  char s[MAX];
  int i=0;
  
  while(n>0){
    int rem = n%26;
    if(rem==0){
      s[i++]='Z';
      n = n/26 -1;
    } else{
      s[i++]= rem-1 + 'A';
      n/=26;
    }
  }
  s[i]='\0';
  reverse(s, s+strlen(s));
  cout<<s<<endl;
}

int main(){
  printString(26); 
  printString(51); 
  printString(52); 
  printString(80); 
  printString(676); 
  printString(702); 
  printString(705); 
  return 0;
}