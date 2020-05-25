#include <iostream>
#include <vector>

using namespace std;

void disp(vector<int> a){
    cout<<endl;
    for(int i=0;i<a.size();i++){
        cout<<a[i]<<" ";
    }
}

void disp(int *a, int n){
    cout<<endl;
    for(int i=0;i<n;i++){
        cout<<a[i]<<" ";
    }
}

void swap(int *a, int *b){
    int temp = *a;
    *a=*b;
    *b=temp;
}

// O(n^2)
void insertionSort(vector<int>& a){
    for(int i=1;i<a.size();i++){
        int j=i;
        while(j>0 && a[j-1]>a[j]){
            swap(&a[j-1], &a[j]);
            j--;
        }
    }
}

// O(n^2
void selectionSort(vector<int> &a){
    for(int i=0; i<a.size()-1; i++){
        int jMin = i;
        for(int j=i+1;j<a.size();j++){
            if(a[j]<a[jMin]){
                jMin = j;
            }
        }
        if(jMin!=i){
            swap(&a[jMin], &a[i]);
        }
    }
}

void merge(vector<int> &a, int l, int m, int r){
    int n1 = m-l+1;
    int n2 = r-m;
    vector<int> L(n1);
    vector<int> R(n2);

    for(int i=0;i<n1;i++){
        L[i] = a[l+i];
    }
    for(int j=0;j<n2;j++){
        R[j] = a[m+1+j];
    }

    int i=0;
    int j=0;
    int k=l;

    while(i<n1 && j<n2){
        if(L[i]<=R[j]){
            a[k] = L[i];
            i++;
        } else{
            a[k] = R[j];
            j++;
        }
        k++;
    }

    while(i<n1){
        a[k++] = L[i++];
    }
    while(j<n2){
        a[k++] = R[j++];
    }
}

void mergeSort(vector<int> &a, int l, int r){
    if(l<r){
        int m = l+(r-l)/2;
        mergeSort(a, l, m);
        mergeSort(a, m+1, r);
        merge(a, l, m, r);
    }
}

void heapify(int *a, int n, int i){
    int lar = i;
    int l = 2*i+1;
    int r = 2*i+2;
    if(l<n && a[l]>a[lar]){
        lar = l;
    }
    if(r<n && a[r]>a[lar]){
        lar = r;
    }
    if(lar!=i){
        swap(&a[lar], &a[i]);
        heapify(a, n, lar);
    }
}

void heapSort(int *a, int n){
    for(int i=n/2-1;i>=0;i--){
        heapify(a, n, i);
    }
    for(int i=n-1; i>=0; i--){
        swap(&a[0], &a[i]);
        heapify(a, i, 0);
    }
}

int partition(vector<int> &a, int l, int h){
    int i = l-1;
    int x = a[h];
    
    for(int j=l;j<h;j++){
        if(a[j]<=x){
            swap(&a[++i], &a[j]);
        }
    }
    swap(&a[++i], &a[h]);
    return i;
}

void quickSort(vector<int> &a, int l, int h){
    if(l<h){
        int p = partition(a, l, h);
        quickSort(a, l, p-1);
        quickSort(a, p+1, h);
    }
}

int main(){
    int arr[] = {56,34,76,23,876,2,87,86,2,-4};
    int n = sizeof(arr)/sizeof(arr[0]);
    vector<int> a(arr, arr + (n) );
    // insertionSort(a);
    // selectionSort(a);
    // mergeSort(a, 0, a.size()-1);
    // heapSort(arr, n);
    quickSort(a, 0, a.size()-1);
    disp(a);
    cout<<endl;
    return 0;
}