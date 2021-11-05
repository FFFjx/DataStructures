# 图论
## 介绍
- 图(Graph，一般用$G$表示)：由顶点集$V$和边集$E$组成，$G=(V,E)$。边集$E$中的每一个元素都由一对来自顶点集$V$的点对组成，如边集$E$
中的一对点对$(v,w)$,其中$v,w \in V$。图可以分为有向图和无向图，有向图对边集$E$中的点对有顺序要求，无向图对边集$E$中的点对无顺序要求，
如在有向图中，$(v,w)$和$(w,v)$代表两条不同的边；而在无向图中，$(v,w)$和$(w,v)$代表相同的边。  
如果一幅无向图的任意一个顶点都存在一条连向其他任意顶点的路径，则称图是连通的；若一幅有向图也有上述的性质，则称该图是强连通的，
若一幅有向图在去掉每条边的方向后的基础图有上述性质，则称该图是弱连通的。  
一幅图可以简单地用一个数组$B$来储存顶点集$V$，用一个二维数组$A$来表示的边集$E$，对于边$(v,w)$，可置$A[index(v)][index(w)]=true$
或者等于该边的权重，$index(v)$为顶点$v$在数组$B$中的下标。$B$的空间需求为$\theta (|V|)$，$A$的空间需求为$\theta (|V|^2)$，
所以该组合的空间需求为$\theta (|V|^2)$。这种表示方法比较简单，但是更适合比较稠密$(|E|=\theta (|V|^2))$的图。对于比较稀疏的图，
使用邻接表来表示一幅图会更加适合。邻接表使用一个List来储存顶点集$V$中的所有顶点，而每一个顶点都使用类似链表的结构把该顶点临接的
所有其他顶点连接起来。邻接表的空间需求为$\theta (|E|+|V|)$。
- 图的遍历(Graph Traversal)：关于图论的后续相关算法，有许多都需要依次访问图中的所有或部分顶点，以找出符合条件的顶点；
常用的两种遍历图的方法分别是广度优先搜索(Breath First Search)和深度优先搜索(Deep First Search)。
- 广度优先搜索(Breath First Search)：广度优先搜索优先搜索某个顶点的所有邻接点，直到该顶点的所有邻接点都被搜索到，才算完成该点的访问，
然后从刚完成访问的顶点的任意邻接点开始搜索，直到全部顶点都访问完毕。
- 深度优先搜索(Deep First Search)：深度优先搜索优先沿着某个顶点的某个未访问的邻接点去访问，若遇到不存在未访问邻接点的顶点，
则回退到发现该顶点的上一个顶点继续搜索，直到全部顶点都访问完毕，同时也会回退到最开始出发的那个顶点。
- 最小生成树(Minimum Spanning Tree)：给定一幅无向图$G=(V,E)$，$(u,v)$代表连接顶点$u$与顶点$v$的边，$w(u,v)$代表此边的权重，
若存在$T$为$E$的子集，且无形成环，使得$w(T)$最小，则此$T$为$G$的最小生成树。
- 网络流问题(Network Flow Problem)：给定一幅有向图$G=(V,E)$，边权$c_{v,w}$表示该边的容量，有两顶点$s,t\in V$，$s$称为源点或发点，
$t$称为收点，任意边$(v,w)$允许通过的流量不能超过$c_{v,w}$。除了$s$和$t$的其他顶点，总流入量必须等于总流出量，最大流问题就是确定
在图$G$中从$s$出发到终点$t$可流过的最大流量。
## 技术用途
- 最小生成树：村庄修路问题，给定N个村庄和M条连接两个村庄之间的待修道路，要求制定一个修路计划，使得任意两个村庄之间可以直接或间接的
方式通过道路往来，但建设道路的成本很高，且各个村庄之间建设道路的费用不同，因此另一目标是使得修路的总成本最小。
- 最小生成树：某房子有N处用电器以及M条连接两个用电器的电线及其所需长度，现在需要给这所房子安装电路（假设没有其他的电路约束），
使得电线可以连接所有用电器，求如何铺设电线可以使得所需电线最短。
- 网络流问题：自来水供水问题，已知自来水厂$s$供水至最终用户$t$之间，流经许多管道和中间节点，管道的大小不一所以每根水管允许通过的
最大流量也不同，假设中间节点不消耗水流量，求从自来水厂发送，能达到最终用户的最大流量。
## 方法实现
### 图论基础
- 顶点类(Vertex)：记录顶点名、当前顶点作为头部顶点时邻接的下一个顶点、当前权重（作为头部的顶点权重为0，作为一条边被指向的顶点的权重为该边的权重）、
顶点在Graph里面的下标四项内容。
- 边类(Edge)：记录开始顶点、结束顶点以及边的权重。
- 图(Graph)：实现邻接表形式的图类：  
Graph(String[] vertexSet,int[][] edgeSet)：vertexSet储存顶点名，edgeSet是邻接矩阵形式的边，遍历edgeSet，将每条边的终点以上面
Vertex类的形式存入对应的起点的ArrayList中；verteSet直接复制到类中。  
insertVertex(String vername)：往图的顶点集中插入新的顶点。  
insertEdge(Edge edge)：往图的边集中插入新的边。
### 图的遍历
- BreadthFirstSearch(Graph g)：首先从图的顶点集中选择一个顶点作为起始顶点，建立一个队列(Queue)，该顶点首先入队，若队列非空，循环以下操作：
访问队头的全部邻接顶点并入队，若该顶点的全部邻接顶点都被访问过，则该顶点出队，直至队列为空。出队顺序即为广度优先遍历的顺序。
- DeepFirstSearch(Graph g)：首先从图的顶点集中选择一个顶点作为起始顶点，建立一个栈(Stack)，该顶点首先入栈，若栈非空，循环以下操作：
访问栈顶的任一邻接顶点并压栈，若某顶点的全部邻接顶点都被访问过，则该顶点出栈，直至栈为空。出栈顺序即为深度优先遍历的顺序。
### 最小生成树
- Prim算法：给定一幅无向图$G=(V,E)$，首先随机选取一个顶点$u\in V$，把该顶点加入一个候选集$K$中，然后取出符合条件的边
$(u,v),u\in K,v\notin K$中权值最小的一条$(u',v')$,把$v'$加入到候选集$K$中。重复上述操作，直到图$G$所有的顶点都加入到了候选集$K$中，
此时所有取出的边构成的集合就是图$G$的最小生成树。
- Kruskal算法：该算法是一种贪婪的策略，按照边权的大小，每次选取剩余边中边权最小的一条边，加入到最小生成树中，若该边使得最小生成树不产生环，
则选定该边，否则舍弃该边，按照这种策略判断所有边是否应该添加到最小生成树中，最后得到最小生成树。
### 网络流问题
- EdmondsKarp算法：维护另一幅图$G_r$，称为残余图，其图上的边表示对于每条边还可以通过的流量，等于边的容量减去该边当前的流量。
使用广度/深度优先搜索从$s$出发进行搜索，在残余图$G_r$中寻找一条从$s$出发到$t$的增广路径，若可以找到，则该路径上最小的边值为该路径
可以通过的最大流量，在残余图中沿着求得路径减去当前流量，并在反向的路径上添加一条反向边，其流量等于上述求得增广路径的最大流量，
添加该反向边的意义是允许算法“后悔”。直到在残余图$G_r$中找不到新的增广路径，算法完成。
## 运行结果展示
### 图的遍历
![BFS](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/BFS&DFS-example.jpg)  
给定一幅有向图$G=(V,E)$，如上图展示所示，该图共有8个顶点，11条边，边权标注在对应的边上，顶点的index标注在顶点旁，求该图的广度优先
搜索的顶点访问顺序。  
![BFS](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/BFSresult.png)  
BFS的运行结果如上图所示，最终该图的BFS顺序为A B C F D H G E  
![BFS](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/BFSresult.jpg)  
根据运行结果画出该图的BFS搜索顺序如上图所示，对比发现无误。  
![DFS](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/DFSresult.png)  
DFS的运行结果如上图所示，最终该图的DFS顺序为A B F H G C D E  
![DFS](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/DFSresult.jpg)  
根据运行结果画出该图的DFS搜索顺序如上图所示，对比发现无误。
### 最小生成树
![MST](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/Kruskal-example.jpg)  
给定一幅无向图$G=(V,E)$，如上图展示所示，该图共有7个顶点，9条边，边权标注在对应的边上，使用Prim算法和Kruskal算法分别求该图的最小生成树：
![Prim](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/Primresult.png)  
Prim算法程序运行结果如上图所示。  
![Prim](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/Kruskalresult.jpg)  
根据运行结果画出该图的最小生成树如上图所示，对比发现最小生成树以及选取边的顺序无误。  
![Kruskal](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/Kruskalresult.png)  
Kruskal算法程序运行结果如上图所示。  
![Kruskal](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/Kruskalresult.jpg)  
根据运行结果画出该图的最小生成树如上图所示，对比发现最小生成树以及选取边的顺序无误。
### 网络流问题
![NFP](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/EK-example.jpg)  
给定一幅有向图$G=(V,E)$，如上图展示所示，该图包含发点$s$和收点$t$共有6个顶点，8条边，边对应的容量标注在对应的边上，使用EK算法求
该图的最大流量以及流量的方案：  
![NFP](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/EKresult.png)  
运行EdmondsKarp算法，可求出该图的最大流量为5个单位。  
![NFP](https://github.com/FFFjx/DataStructures/blob/main/Graph/pic/EKresult.jpg)  
根据运行结果画出该图的最大流方案如上图所示，对比发现最大流方案的选取边以及最大流量无误。

