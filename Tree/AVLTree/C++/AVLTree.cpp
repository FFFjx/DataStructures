#include<iostream>
using namespace std;

template<class T>
struct avlTreeNode
{
public:
	avlTreeNode<T>* leftchild;
	avlTreeNode<T>* rightchild;
	T value;
	int height;
	avlTreeNode()
	{}
	avlTreeNode(const T& val, avlTreeNode<T>* leftchild, avlTreeNode<T>* rightchild)
	{
		this->value = val;
		this->leftchild = leftchild;
		this->rightchild = rightchild;
		height = 0;
	}
};

template<class T>
class AVLTree
{
public:
	AVLTree();
	~AVLTree();

	bool isEmpty();
	void insert(const T& val);
	bool remove(const T& val);
	bool contains(const T& val);
	void preOrder();
	void inOrder();
	void postOrder();
	void printTree();

private:
	avlTreeNode<T>* root;

	avlTreeNode<T>* findNode(const T& val, avlTreeNode<T>* node);
	avlTreeNode<T>* insert(const T& val, avlTreeNode<T>* node);
	avlTreeNode<T>* findMin(avlTreeNode<T>* node);
	avlTreeNode<T>* findMax(avlTreeNode<T>* node);
	void preOrder(avlTreeNode<T>* node);
	void inOrder(avlTreeNode<T>* node);
	void postOrder(avlTreeNode<T>* node);
	void freeMemory(avlTreeNode<T>* node);
	void printTree(avlTreeNode<T>* node, T father, int direction);
};

template<class T>
AVLTree<T>::AVLTree()
{
	root = NULL;
}

template<class T>
AVLTree<T>::~AVLTree()
{
	if (root == NULL)
	{
		return;
	}
	freeMemory(root);
}

template<class T>
bool AVLTree<T>::isEmpty()
{
	return root == NULL;
}

template<class T>
void AVLTree<T>::insert(const T& val)
{
	root = insert(val, root);
}

template<class T>
bool AVLTree<T>::remove(const T& val)
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
	avlTreeNode<T>* rNode = root;
	avlTreeNode<T>* rNodep = root;
	while (rNode != NULL)
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
	else if (rNode->rightchild == NULL)
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
		avlTreeNode<T>* replaceNode = findMax(rNode->leftchild);//or findMin(rNode->rightchild)
		T replaceValue = replaceNode->value;
		remove(replaceValue);
		rNode->value = replaceValue;
	}
	return true;
}

template<class T>
bool AVLTree<T>::contains(const T& val)
{
	return findNode(val, root) != NULL;
}

template<class T>
void AVLTree<T>::preOrder()
{
	preOrder(root);
}

template<class T>
void AVLTree<T>::inOrder()
{
	inOrder(root);
}

template<class T>
void AVLTree<T>::postOrder()
{
	postOrder(root);
}

template<class T>
void AVLTree<T>::printTree()
{
	if (root != NULL)
	{
		printTree(root, root->value, 0);
	}
}

template<class T>
avlTreeNode<T>* AVLTree<T>::findNode(const T& val, avlTreeNode<T>* node)
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
avlTreeNode<T>* AVLTree<T>::insert(const T& val, avlTreeNode<T>* node)
{
	if (node == NULL)
	{
		return new avlTreeNode<T>(val, NULL, NULL);
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
avlTreeNode<T>* AVLTree<T>::findMin(avlTreeNode<T>* node)
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
avlTreeNode<T>* AVLTree<T>::findMax(avlTreeNode<T>* node)
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
void AVLTree<T>::preOrder(avlTreeNode<T>* node)
{
	if (node != NULL)
	{
		cout << node->value << ' ';
		preOrder(node->leftchild);
		preOrder(node->rightchild);
	}
}

template<class T>
void AVLTree<T>::inOrder(avlTreeNode<T>* node)
{
	if (node != NULL)
	{
		inOrder(node->leftchild);
		cout << node->value << ' ';
		inOrder(node->rightchild);
	}
}

template<class T>
void AVLTree<T>::postOrder(avlTreeNode<T>* node)
{
	if (node != NULL)
	{
		postOrder(node->leftchild);
		postOrder(node->rightchild);
		cout << node->value << ' ';
	}
}

template<class T>
void AVLTree<T>::freeMemory(avlTreeNode<T>* node)
{
	if (node == NULL)
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
void AVLTree<T>::printTree(avlTreeNode<T>* node, T father, int direction)
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
	//AVLTree<int> myAVLTree; 可以采用两种新建方式，第一种新建对象，第二种新建指针，如使用第一种需将下面的“->”改成“.”
	AVLTree<int>* myAVLTree = new AVLTree<int>();
	myAVLTree->insert(42);
	myAVLTree->insert(30);
	myAVLTree->insert(60);
	myAVLTree->insert(16);
	myAVLTree->insert(36);
	myAVLTree->insert(8);
	myAVLTree->insert(27);
	myAVLTree->insert(32);
	myAVLTree->insert(40);
	myAVLTree->insert(50);
	myAVLTree->insert(70);
	cout << "\n";
	myAVLTree->printTree();
	myAVLTree->remove(30);
	myAVLTree->remove(16);
	myAVLTree->remove(32);
	myAVLTree->printTree();
	return 0;
}
//遗留问题：1.部分index的异常没有检验
//2.类的书写格式问题
