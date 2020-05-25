#include <bits/stdc++.h>

using namespace std;

struct Node{
    int data;
    struct Node *left;
    struct Node *right;
};

Node* newNode(int data){
    Node *node = (Node*)malloc(sizeof(Node));
    node->data = data;
    node->left=NULL;
    node->right=NULL;
    return node;
}

void inOrder(Node *root){
    if(root==NULL){
        return;
    }
    inOrder(root->left);
    cout<<root->data<<" ";
    inOrder(root->right);
}

void preOrder(Node *root){
    if(root==NULL){
        return;
    }
    cout<<root->data<<" ";
    preOrder(root->left);
    preOrder(root->right);
}

void postOrder(Node *root){
    if(root==NULL){
        return;
    }
    postOrder(root->left);
    postOrder(root->right);
    cout<<root->data<<" ";
}

// with extra space
void inOrderIterative1(Node *root){
    map<Node*, int> countMap;
    stack<Node*> st;
    if(root==NULL){
        return;
    }
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        st.pop();

        if(countMap.count(top)){
            int c = countMap[top];
            countMap[top] = ++c;
            if(c==2){
                cout<<top->data<<" ";
                top=NULL;
            }
        } else{
            countMap[top] = 1;
        }

        if(top!=NULL && top->right!=NULL){
            st.push(top->right);
        }
        if(top!=NULL){
            st.push(top);
        }
        if(top!=NULL && top->left!=NULL){
            st.push(top->left);
        }
    }
}

// with extra space
void inOrderIterative2(Node *root){
    stack<Node *> st;
    map<Node*, int> countMap;
    if(root==NULL){
        return;
    }
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        if(countMap.count(top)){
            int c = countMap[top];
            countMap[top] = ++c;
            if(c==2){
                cout<<top->data<<" ";
                if(top->right!=NULL){
                    st.push(top->right);
                }
            } else if(c==3){
                st.pop();
            }
        } else{
            countMap[top] = 1;
            if(top->left!=NULL){
                st.push(top->left);
            }
        }
    }
}

void preOrderIterative2(Node *root){
    stack<Node *> st;
    map<Node*, int> countMap;
    if(root==NULL){
        return;
    }
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        if(countMap.count(top)){
            int c = countMap[top];
            countMap[top] = ++c;
            if(c==2){
                if(top->right!=NULL){
                    st.push(top->right);
                }
            } else if(c==3){
                st.pop();
            }
        } else{
            countMap[top] = 1;
            cout<<top->data<<" ";
            if(top->left!=NULL){
                st.push(top->left);
            }
        }
    }
}

void postOrderIterative2(Node *root){
    stack<Node *> st;
    map<Node*, int> countMap;
    if(root==NULL){
        return;
    }
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        if(countMap.count(top)){
            int c = countMap[top];
            countMap[top] = ++c;
            if(c==2){
                if(top->right!=NULL){
                    st.push(top->right);
                }
            } else if(c==3){
                cout<<top->data<<" ";
                st.pop();
            }
        } else{
            countMap[top] = 1;
            if(top->left!=NULL){
                st.push(top->left);
            }
        }
    }
}

void processLeft(stack<Node *> &st, Node *l){
    
}

void inOrderIterative3(Node *root){
    stack<Node *> st;
    if(root == NULL){
        return;     
    }
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        st.pop();
    }
}

void preOrderIterative3(Node *root){
    if(root==NULL){
        return;
    }
    stack<Node *> st;
    st.push(root);
    while(!st.empty()){
        Node *top = st.top();
        st.pop();
        cout<<top->data<<" ";
        if(top->right!=NULL){
            st.push(top->right);
        }
        if(top->left!=NULL){
            st.push(top->left);
        }
    }
}

int main(){
    Node *root = newNode(1);
    root->left = newNode(2);
    root->right = newNode(3);
    root->left->left = newNode(4);
    root->left->right = newNode(5);
    root->right->left = newNode(6);
    root->right->right = newNode(7);
    root->right->left->right = newNode(8);
    cout<<endl;
    inOrder(root);
    cout<<endl;
    preOrder(root);
    cout<<endl;
    postOrder(root);
    cout<<endl;
    cout<<"Iterative: "<<endl;
    // inOrderIterative1(root);
    inOrderIterative2(root);
    cout<<endl;
    preOrderIterative2(root);
    cout<<endl;
    postOrderIterative2(root);
    cout<<endl;
    cout<<"Iterative without extraSpace: "<<endl;
    inOrderIterative3(root);
    cout<<endl;
    preOrderIterative3(root);
    cout<<endl;
    return 0;
}