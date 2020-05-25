#include<bits/stdc++.h>

using namespace std;

//brute force (n*k)
void printKMax(vector<int> a, int k){

    for(int i=0; i<=a.size()-k;i++){
        int max=0;
        for(int j=i;j<i+k;j++){
            if(max<a[j]){
                max=a[j];
            }
        }
        cout<<max<<" ";
    }
}

//using deque
void printKMax2(vector<int> &a, int k){
    deque<int> dq(k);
    int i;
    for(i=0;i<k;i++){
        while (!dq.empty() && a[dq.back()]<=a[i]){
            dq.pop_back();
        }
        dq.push_back(i);
    }

    for(;i<a.size();i++){
        cout<<a[dq.front()]<<" ";
        // pop out of current window index
        while(!dq.empty() && dq.front()<=(i-k)){
            dq.pop_front();
        }

        while(!dq.empty() && a[dq.back()]<=a[i]){
            dq.pop_back();
        }
        dq.push_back(i);
    }

    cout<<a[dq.front()]<<" ";
}

int main(){
    int arr[] = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
    int n = sizeof(arr)/sizeof(arr[0]);
    vector<int> a(arr, arr+n);
    int k=4;
    printKMax(a,k);
    cout<<endl;
    printKMax2(a,k);
    cout<<endl;
    return 0;
}