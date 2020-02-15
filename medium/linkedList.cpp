#include <iostream>

using namespace std;

struct Node{
	int data;
	Node *next;
};

Node* newNode(int d){
	Node *newNode = (Node*)malloc(sizeof(Node));
	newNode->data = d;
	newNode->next=NULL;
	return newNode;
}

void disp(Node *node){
	cout<<"\n";
	while(node){
		cout<<node->data<<"->";
		node = node->next;
	}
}

int main(){
	Node *node = newNode(5);
	disp(node);
	cout<<endl;
	return 0;
}
