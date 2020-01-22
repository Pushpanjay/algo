#include <bits/stdc++.h>
#define NO_OF_CHAR 256

using namespace std;

int firstNonReapeating(char *s){
    pair<int, int> count[NO_OF_CHAR];
    for(int i=0;s[i];i++){
        (count[s[i]].first)++;
        count[s[i]].second=i;
    }
    int res=INT_MAX;
    for(int i=0;i<NO_OF_CHAR;i++){
        if(count[i].first==1){
            res = min(res, count[i].second);
        }
    }
    return res;
}

int main(){
    char str[] = "geeksforgeeks";
    int index = firstNonReapeating(str);
    if(index==INT_MAX){
        cout<<"No non-repeating integer found";
    } else{
        cout<<"First non-repeating is "<<str[index]<<endl;
    }
    return 0;
}