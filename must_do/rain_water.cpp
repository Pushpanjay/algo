#include <bits/stdc++.h>

using namespace std;

// naive
int maxWater(int *a, int n){
    int res = 0;
    for(int j=1;j<n-1;j++){
        int lM = j;
        int l = j-1;
        while(l>=0){
            if(a[l]>a[lM]){
                lM = l;
            }
            l--;
        }
        
        int rM = j;
        int r = j+1;
        while(r<n){
            if(a[r]>a[rM]){
                rM = r;
            }
            r++;
        }
        res += min(a[lM], a[rM]) - a[j];
    }
    return res;
}

// efficient: precompute leftMax and rightMax for each index
int maxWater1(int *a, int n){
    int res=0;
    int lM[n];
    int rM[n];
    //preCompute leftmax
    lM[0]=a[0];
    for(int i=1;i<n-1;i++){
        lM[i] = max(lM[i-1], a[i]);
    }
    //preCompute rightMax
    rM[n-1]=a[n-1];
    for(int i=n-2;i>=0;i--){
        rM[i] = max(rM[i+1], a[i]);
    }

    for(int i=0;i<n;i++){
        int t = min(lM[i], rM[i]) - a[i];
        if(t>0){
            res+=t;
        }
    }
    return res;
}

// efficient time and constant space
int maxWater2(int *a, int n){
    int res=0;
    int lo=0;
    int hi=n-1;

    int left=0;
    int right=0;

    while(lo<=hi){
        if(a[lo]<a[hi]){
            if(a[lo]>left){
                left=a[lo];
            } else{
                res+= left-a[lo];
            }
            lo++;
        } else{
            if(a[hi]>right){
                right = a[hi];
            } else{
                res+=right-a[hi];
            }
            hi--;
        }
    }
    return res;
}

int main(){
    int a[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
    int n = sizeof(a)/sizeof(a[0]);
    int res = maxWater2(a, n);
    cout<<res<<endl;
    return 0;
}