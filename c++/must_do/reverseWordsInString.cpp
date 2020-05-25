#include <bits/stdc++.h>

using namespace std;

void reverse(char *begin, char *end){
    char temp;
    while(begin<end){
        temp=*begin;
        *begin++=*end;
        *end--=temp;
    }
}

void reverseWord(string *s){
    char* word_begin = &(*s)[0];
    char *temp=&(*s)[0];
    while(*temp){
        temp++;
        if(*temp=='\0'){
            reverse(word_begin, temp-1);
        } else if(*temp=='.'){
            reverse(word_begin, temp-1);
            word_begin=temp+1;
        }
    }
    reverse(&(*s)[0], temp-1);
}

int main(){
    string s;
    getline(cin, s);
    reverseWord(&s);
    cout<<endl;
    cout<<s;
    return 0;
}