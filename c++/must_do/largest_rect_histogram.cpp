#include <bits/stdc++.h>

using namespace std;


int getMaxArea(int *h, int n){
    int max_area=0;
    stack<int> s;
    int i=0;
    while(i<n){
        if(s.empty() || h[s.top()]<=h[i]){
            s.push(i);
            i++;
        } else{
            int tp = s.top();
            s.pop();
            int temp_area = h[tp] * (s.empty()?i:i-s.top()-1);
            if(temp_area>max_area){
                max_area=temp_area;
            }
        }
    }
    return max_area;
}

int main(){
    int h[] = {1,2,2,1};
    int n = sizeof(h)/sizeof(h[0]);
    cout<<getMaxArea(h, n)<<endl;
    return 0;
}