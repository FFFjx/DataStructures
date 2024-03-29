#include<iostream>
using namespace std;

template<class T>
struct TreeNode
{
public:
	TreeNode<T>* leftchild;
	TreeNode<T>* rightchild;
	T value;
	TreeNode()
	{}
	TreeNode(const T &val,TreeNode<T>* leftchild, TreeNode<T>* rightchild)
	{
		this->value = val;
		this->leftchild = leftchild;
		this->rightchild = rightchild;
	}
};

template<class T>
class BinarySearchTree
{
public:
	BinarySearchTree();
	~BinarySearchTree();

	bool isEmpty();
	void insert(const T& val);
	bool remove(const T& val);
	bool contains(const T& val);
	void preOrder();
	void inOrder();
	void postOrder();
	void printTree();

private:
	TreeNode<T>* root;

	TreeNode<T>* findNode(const T& val, TreeNode<T>* node);
	TreeNode<T>* insert(const T& val, TreeNode<T>* node);
	TreeNode<T>* findMin(TreeNode<T>* node);
	TreeNode<T>* findMax(TreeNode<T>* node);
	void preOrder(TreeNode<T>* node);
	void inOrder(TreeNode<T>* node);
	void postOrder(TreeNode<T>* node);
	void freeMemory(TreeNode<T>* node);
	void printTree(TreeNode<T>* node,T father,int direction);
};

template<class T>
BinarySearchTree<T>::BinarySearchTree()
{
	root = NULL;
}

template<class T>
BinarySearchTree<T>::~BinarySearchTree()
{
	if (root==NULL)
	{
		return;
	}
	freeMemory(root);
}

template<class T>
bool BinarySearchTree<T>::isEmpty()
{
	return root == NULL;
}

template<class T>
void BinarySearchTree<T>::insert(const T& val)
{
	root = insert(val, root);
}

template<class T>
bool BinarySearchTree<T>::remove(const T& val)
{
	if (root == NULL)
	{
		cout << "The tree is empty" << endl;
		return false;
	}
	//if (val==root->value) 
	//{
		//root = NULL;
		//return true;
	//}
	TreeNode<T>* rNode = root;
	TreeNode<T>* rNodep = root;
	while (rNode!=NULL)
	{
		if (val == rNode->value)
		{
			break;
		}
		else if (val > rNode->value)
		{
			rNodep = rNode;
			rNode = rNode->rightchild;
		}
		else if (val < rNode->value) 
		{
			rNodep = rNode;
			rNode = rNode->leftchild;
		}
	}
	if (rNode == NULL)//cannot find the node with value equals to val
	{
		return false;
	}
	if (rNode->leftchild == NULL && rNode->rightchild == NULL)
	{
		if (rNode == rNodep->leftchild)
		{
			rNodep->leftchild = NULL;
		}
		else
		{
			rNodep->rightchild = NULL;
		}
		delete rNode;
		rNode = NULL;
	}
	else if (rNode->leftchild == NULL)
	{
		if (rNode == rNodep->leftchild)
		{
			rNodep->leftchild = rNode->rightchild;
		}
		else
		{
			rNodep->rightchild = rNode->rightchild;
		}
		delete rNode;
		rNode = NULL;
	}
	else if(rNode->rightchild == NULL)
	{
		if (rNode == rNodep->leftchild)
		{
			rNodep->leftchild = rNode->leftchild;
		}
		else
		{
			rNodep->rightchild = rNode->leftchild;
		}
		delete rNode;
		rNode = NULL;
	}
	else//rNode.leftchild!=NULL&&rNode.rightchild!=NULL
	{
		TreeNode<T>* replaceNode = findMax(rNode->leftchild);//or findMin(rNode->rightchild)
		T replaceValue = replaceNode->value;
		remove(replaceValue);
		rNode->value = replaceValue;
	}
	return true;
}

template<class T>
bool BinarySearchTree<T>::contains(const T& val)
{
	return findNode(val,root) != NULL;
}

template<class T>
void BinarySearchTree<T>::preOrder()
{
	preOrder(root);
}

template<class T>
void BinarySearchTree<T>::inOrder()
{
	inOrder(root);
}

template<class T>
void BinarySearchTree<T>::postOrder()
{
	postOrder(root);
}

template<class T>
void BinarySearchTree<T>::printTree()
{
	if (root != NULL)
	{
		printTree(root, root->value, 0);
	}
}

template<class T>
TreeNode<T>* BinarySearchTree<T>::findNode(const T& val, TreeNode<T>* node)
{
	if (node == NULL)
	{
		return NULL;
	}
	if (val == node->value)
	{
		;
	}
	else if (val > node->value)
	{
		node->rightchild = findNode(val, node->rightchild);
	}
	else
	{
		node->leftchild = findNode(val, node->leftchild);
	}
	return node;
}

template<class T>
TreeNode<T>* BinarySearchTree<T>::insert(const T& val, TreeNode<T>* node)
{
	if (node==NULL)
	{
		return new TreeNode<T>(val, NULL, NULL);
	}
	if (val == node->value)
	{
		;
	}
	else if (val > node->value)
	{
		node->rightchild = insert(val, node->rightchild);
	}
	else
	{
		node->leftchild = insert(val, node->leftchild);
	}
	return node;
}

template<class T>
TreeNode<T>* BinarySearchTree<T>::findMin(TreeNode<T>* node)
{
	if (node == NULL)
	{
		return NULL;
	}
	if (node->leftchild != NULL)
	{
		return findMin(node->leftchild);
	}
	else
	{
		return node;
	}
}

template<class T>
TreeNode<T>* BinarySearchTree<T>::findMax(TreeNode<T>* node)
{
	if (node == NULL)
	{
		return NULL;
	}
	if (node->rightchild != NULL)
	{
		return findMin(node->rightchild);
	}
	else
	{
		return node;
	}
}

template<class T>
void BinarySearchTree<T>::preOrder(TreeNode<T>* node)
{
	if (node != NULL)
	{
		cout << node->value << ' ';
		preOrder(node->leftchild);
		preOrder(node->rightchild);
	}
}

template<class T>
void BinarySearchTree<T>::inOrder(TreeNode<T>* node)
{
	if (node != NULL)
	{
		inOrder(node->leftchild);
		cout << node->value << ' ';
		inOrder(node->rightchild);
	}
}

template<class T>
void BinarySearchTree<T>::postOrder(TreeNode<T>* node)
{
	if (node != NULL)
	{
		postOrder(node->leftchild);
		postOrder(node->rightchild);
		cout << node->value << ' ';
	}
}

template<class T>
void BinarySearchTree<T>::freeMemory(TreeNode<T>* node)
{
	if (node==NULL)
	{
		return;
	}
	if (node->leftchild != NULL)
	{
		freeMemory(node->leftchild);
	}
	if (node->rightchild != NULL)
	{
		freeMemory(node->rightchild);
	}

	delete node;
	node = NULL;
}

template<class T>
void BinarySearchTree<T>::printTree(TreeNode<T>* node, T father, int direction)
{
	if (node != NULL)
	{
		if (direction == 0)
		{
			cout << root->value << " is root";
			cout << "\n";
		}
		else
		{
			string a = (direction == 1) ? "right" : "left";
			cout << node->value << " is " << father << "'s " << a;
			cout << "\n";
		}
		printTree(node->leftchild, node->value, -1);
		printTree(node->rightchild, node->value, 1);
	}
}

int main(void)
{
	//BinarySearchTree<int> myBST; 可以采用两种新建方式，第一种新建对象，第二种新建指针，如使用第一种需将下面的“->”改成“.”
	BinarySearchTree<int>* myBST = new BinarySearchTree<int>();
	myBST->insert(42);
	myBST->insert(30);
	myBST->insert(60);
	myBST->insert(16);
	myBST->insert(36);
	myBST->insert(8);
	myBST->insert(27);
	myBST->insert(32);
	myBST->insert(40);
	myBST->insert(50);
	myBST->insert(70);
	cout << "\n";
	myBST->printTree();
	myBST->remove(30);
	myBST->remove(16);
	myBST->remove(32);
	myBST->printTree();
	return 0;
}
//遗留问题：1.折构函数可能有问题
