#include <iostream>
#include <vector>

// using cumulative sum
using namespace std;

int main(){
    int a[]={-4,1,3,-2,6,2,-1,4,-7};
    int n=sizeof(a)/sizeof(a[0]);
    int max=0;
    int left=-1;
    int right=-1;
    vector<int> sum(n,0);
    sum[0]=a[0];
    for(int i=1;i<n;i++){
        sum[i]=sum[i-1]+a[i];
    }
    
    for(int i=0;i<n;i++){
        for(int j=i;j<n;j++){
            int cur = sum[j];
            if(i>0){
                cur-=sum[i-1];
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