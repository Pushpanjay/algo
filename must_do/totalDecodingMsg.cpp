#include <bits/stdc++.h>

using namespace std;

int countDecodingDp(char *dg, int n){
    int count[n+1];
    count[0]=1;
    count[1]=1;
    if(dg[0]=='0'){
        return 0;
    }
    for(int i=2;i<=n;i++){
        if(dg[i-1]>'0'){
            count[i] = count[i-1];
        }

        if(dg[i-2] == '1' || (dg[i-2]=='2' && dg[i-1]<'7')){
            count[i] += count[i-2];
        }
    }
    return count[n];
}

int main(){
    char digit[]="1234";
    int n = strlen(digit);
    cout<<countDecodingDp(digit, n)<<endl;
    return 0;
}