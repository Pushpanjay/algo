#include<bits/stdc++.h>

using namespace std;

//naive approach
void search(char *pat, char *txt){
    int M = strlen(pat);
    int N = strlen(txt);
    for(int i=0;i<=N-M;i++){
        int j;
        for(j=0;j<M;j++){
            if(pat[j]!=txt[i+j]){
                break;
            }
        }
        if(j==M){
            cout<<"Pattern found at "<<i<<endl;
        }
    }
}

void createLPSArray(char *pat, int M, int *lps){
    lps[0]=0;
    int j=0;
    int i=1;
    while(i<M){
        if(pat[i] == pat[j]){
            j++;
            lps[i]=j;
            i++;    
        } else{
            if(j!=0){
                j = lps[j-1];
            } else {
                lps[i]=0;
                i++;
            }
        }
    }
}

void kmpSearch(char *pat, char *txt){
    int M = strlen(pat);
    int N = strlen(txt);
    int lps[M];
    createLPSArray(pat, M, lps);
    int i=0, j=0;
    while(i<N){
        if(txt[i] == pat[j]){
            i++;
            j++;
        }

        if(j==M){
            cout<<"Pattern found at index "<<i-j<<endl;
            j = lps[j-1];
        } else if(i<N && pat[j]!=txt[i]){
            if(j==0){
                i++;
            } else{
                j=lps[j-1];
            }
        }
    }
}

int main(){
    //char txt[] = "ABABDABACDABABCABAB"; 
    //char pat[] = "ABABCABAB"; 
    char txt[] =  "AABAACAADAABAABA";
    char pat[] =  "AABA";
    search(pat, txt);
    // kmpSearch(pat, txt);
    return 0;
}