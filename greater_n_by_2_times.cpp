#include<bits/stdc++.h>

using namespace std;

int main(){
    int n;
    cin>>n;
    vector<int> a(n);
    for(int i=0;i<n;i++){
        cin>>a[i];
    }

    int count = 1;
    int max = a[0];

    for(int i=1;i<n;i++){
        if(max = a[i]){
            count++;
        } else{
            count--;
            if(count == 0){
                max=a[i];
                count=1;
            }
        }
    }

    cout<<max<<endl;
    return 0;
}