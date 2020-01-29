// subarray with maximum sum
// brute force; generate sum of all subarray, then find maximum sum
#include <bits/stdc++.h>

using namespace std;
//TC:O(n^3)
int main(){
    int a[]={-4,1,3,-2,6,2,-1,4,-7};
    int n=sizeof(a)/sizeof(a[0]);
    int max=0;
    int left=-1;
    int right=-1;
    for(int i=0;i<n;i++){
        for(int j=i;j<n;j++){
            int cur=0;
            for(int k=i;k<=j;k++){
                cur+=a[k];
            }
            if(cur>max){
                max=cur;
                left=i;
                right=j;
            }
        }
    }
    cout<<max<<endl;
    cout<<left<<", "<<right<<endl;
    return 0;
}