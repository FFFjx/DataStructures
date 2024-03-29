# 树
## 介绍
- 树(Tree)：树是一种可递归的数据结构，其最小单位可以用节点表示。当一棵树非空时，则该树由根节点$r$和0个或多个非空的子树组成，
这些子树的根节点都和该树的根节点$r$连接在一起，每棵子树的根节点都是根节点$r$的儿子节点，根节点$r$是他们的父亲节点。
没有儿子节点的节点称为树叶；具有相同父亲节点的节点称为兄弟节点。从节点$n_1$到$n_k$的路径定义为节点$n_1,n_2,n_3,\cdots,n_k$，
使得对于$1\leq i<k$节点$n_i$是$n_{i+1}$的父亲。这条路径的长是为该路径上边的条数，即$k-1$。对任意节点$n_i$，$n_i$的深度为从根到$n_i$的唯一路径的长。因此根的深度为0。$n_i$的高是从$n_i$到一片树叶的最长路径的长，因此所有树叶的高都为0。
- 二叉查找树(Binary Search Tree)：是一种特殊的二叉树，其要求某个节点的左子节点的值比当前节点小而右子节点的值比当前节点大
（不一定是数值上的小和大，可以自行定义“大”和“小”两种情况如何计算）。其深度的平均值为$O(logN)$，但是其最坏情况可以达到$O(N)$。
- AVL树(AVL Tree)：由于二叉查找树结构的好坏比较依赖于数据插入或删除的顺序，最坏情况下$N$个节点的树的深度是$O(N)$，
因此二叉查找树并不稳定是一棵平衡的树。为了弥补其缺陷，引入AVL树，AVL树是带有平衡条件的二叉查找树，其规定树中任意节点的左右子节点的
高度最多相差1，若发现有不符合上述规则的情况，会对树的结构进行调整，该调整称为旋转。由于任意节点只有两个子节点，因此有下列四种情况
会发生使得一个节点的左右节点的的高度相差2的情况：  
    1.往该节点的左子节点的左子树进行一次插入；  
    2.往该节点的左子节点的右子树进行一次插入；  
    3.往该节点的右子节点的左子树进行一次插入；  
    4.往该节点的右子节点的右子树进行一次插入；  
    其中情况1、4为关于该节点镜像对称，情况2、3为关于该节点镜像对称。
- 红黑树(Red Black Tree)：红黑树类似AVL树，都是针对二叉查找树结构不自调整的缺陷，与AVL树相比，红黑树的自调整方式可以通过非递归的
方式进行调整，情况分类较AVL树更为复杂。红黑树拥有以下4个重要着色的性质：  
    1.每个节点要么是黑色，要么是红色；  
    2.根节点为黑色；  
    3.如果一个节点是红色，那么他的子节点一定是黑色（不可能出现两个连续的红色节点）；  
    4.每个节点出发到一个叶子节点引用的每一条路径必须包含相同数目的黑色节点。  
- KD树(KD Tree)：KD树广泛应用于数据库索引，适用于高维数据的快速查询。KD树是一种空间划分树，把整个空间划分为特定几个部分，
然后在特定的空间内进行相关搜索操作，可以得知，KD树最重要的一种性质就是它可以在高维空间里进行快速搜索。K代表其维度的个数，比较经典
的1-D树有：二叉查找树、红黑树、AVL树。KD树会在树的每一层对数据的某一维度进行划分，因此，构建一棵KD树的关键就在于
1.如何选取每一层基于哪个维度对子空间进行划分；2.如何选定每一层的根节点的数值。
## 技术用途
- 操作系统里面的文件目录结构。
- 英文单词查字典。
## 方法实现
### 二叉查找树
- 增(insert)：向一颗二叉查找树中插入一个新值，若树的根为空，则该新值对应的节点为树的根节点；若插入一棵非空的树中，
则该新值对应的节点一定是树叶节点，沿着树的根节点按照二叉查找树的规则向下搜索，直至找到一个空节点，把新值插入至该空节点中，
二叉查找树的插入完成。
- 删(remove)：向一颗二叉查找树中删除一个值，沿着树的根节点按照二叉查找树的规则向下搜索，若找不到该值，则什么也不做，删除完成；
若找到了该值，该值对应的节点可能的情况分为三种：1.该节点的左右子节点皆空，即该节点为树叶节点，这种情况直接删除该节点；
2.该节点的左右子节点中有一个为空，则把该节点直接替换为其非空的子节点；3.该节点的左右子节点皆非空，则首先找到该节点的后继节点/前驱节点
（后继节点即是某节点右子树中的最小节点，前驱节点即是某节点左子树中的最大节点），把后继节点/前驱节点的值赋给当前节点，然后删除其后继
节点/前驱节点，其后继节点/前驱节点的左右子节点不可能皆非空，所以可以转化为情况1.2.处理。
- 改(set)：在一棵二叉查找树中，将一个旧值改为一个新值，可以直接调用删除旧值，然后调用插入新值来处理；若采用直接在旧值的位置上替换
新值的这种方法，将会非常难以处理且低效，下面以一个例子来说明：  
![BST-set](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/BSTset-example.jpg)  
如图所示，在一棵给定的二叉查找树中，想要把旧值8对应的节点该值成为80，若采用替换的方式，则该树不符合二叉查找树的定义，需要将其调整。
若只观察根节点为16对应的子树，则只需把27置换为根，16为27的左子，80为27的右子，则该子树满足二叉查找树的定义，但是此时根节点为30的
子树又需进行调整，一直递归到树的根节点42处还需要调整，这样的调整方法太低效，很有可能出现改一个节点的值，导致整棵树的结构发生大变，
时间花费上升。因此，采用先删除，后插入的方法，只有删除会对树结构进行小部分调整，插入新值不需对树结构进行调整。
- 查(contains)：二叉查找树的查值是插入和删除操作的基础，只需用查找的值与每棵子树的根节点进行对比，若值比根节点大则递归地查找根节点的
右子树，若小则递归地查找根节点的左子树，若值相等则返回TRUE，若找不到则返回FALSE。
### AVL树
![AVL-singleRotation](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/AVL-1.jpg)   
- 单旋转：右旋/左旋(rightRotation/leftRotation)：以右旋为例，针对第1种情况，如上图所示，在插入前，$k_2$节点的左子树和右子树高度相差1，
已经平衡了。但是往$k_1$的左子树进行一次插入后，打破了平衡条件，$k_2$节点的左子树和右子树高度相差2。在这种情况下，我们可以使用一次精妙的
单旋转来使树恢复平衡，那就是调换$k_1$和$k_2$的位置。我们可以观察到$k_1$是$k_2$的左子节点，因此$k_2$>$k_1$，换位后，$k_2$变成了
$k_1$的右子节点，子树$X$和$Z$不变，而子树$Y$则接到了$k_2$的左子节点上，由于调整前$k_1$是$k_2$的左子节点，因此$k_1$的右子树$Y$中
所有的节点都比$k_1$大，而都比$k_2$小，调整后，大小关系并未产生冲突。此种旋转称为右旋，而对称的情况4则称为左旋。  
![AVL-doubleRotation](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/AVL-2-1.jpg)   
- 双旋转：左右旋/右左旋(doubleLRRotation/doubleRLRotation)：以左右旋为例，针对第2种情况，如上图所示，在插入前，$k_2$节点的左子树
和右子树高度相差1，已经平衡了。但是往$k_1$的右子树进行一次插入后，打破了平衡条件，$k_2$节点的左子树和右子树高度相差2。我们尝试对
$k_2$做一次左旋，发现失败，新根$k_1$的左子树和右子树高度还是相差2。在这种情况下，$k_1$作为新的根并不能解决问题，此时选择最深的子树
$Y$的根$k_3$作为整棵树的新根，先对$k_1$作一个左旋，然后对$k_2$作一个右旋，此时树可以平衡，问题解决，如下图所示。  
![AVL-doubleRotation](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/AVL-2-2.jpg)   
- 增(insert)：由于AVL树本质上也是一棵二叉查找树，因此确定插入位置的操作与二叉查找树一致，不一致的地方是，如果插入完毕后，需要从插入
的节点开始一直回溯到根节点，如果其中发现有节点不符合AVL树的规则，则需要进行调整。
- 删(remove) 改(set) 查(contains)：由于AVL树本质上也是一棵二叉查找树，查值并不涉及树结构的变动，因此代码跟二叉查找树的一致，这里不再赘述。删除操作确定
删除位置和寻找替代节点的方法也与二叉查找树一致，不一致的地方是，在删除操作结束后，需要对树的结构进行判断是否需要调整，调整方式与
AVL树的插入一致，这里也不再赘述。改值操作的情况亦与二叉查找树一致，在旧值的位置上进行直接替换新值，最坏的情况下需要从树的最左端调整
到树的最优端，方法效率低，不如直接用删除+插入的操作进行替换。
### 红黑树
- 增(insert)：由于红黑树本身是一棵二叉查找树，所以红黑树插入一个新节点的位置和二叉查找树一样，且新节点着色默认为红色，若着色黑色，
该节点所在路径比其他路径多出一个黑色，难以调整；插入完毕后检查是否违反了上述4个着色原则，若有则通过旋转和变色来进行调整。  
    红黑树的插入调整分为5种情况讨论：  
    1.插入的新节点是根节点，调整方案：把根节点染黑；  
    2.插入的新节点的父节点为黑色，插入的新节点为红色，也不会违反原则4，调整方案：无需任何调整；  
    3.插入的新节点的父节点为红色，违反了原则3，新节点的爷节点必为黑色，且其叔节点也为红色，调整方案：将父节点和叔节点染黑，把爷节点染红，
    然后递归地对爷节点进行调整判断，因为爷节点的红色可能跟爷节点的父节点的颜色冲突；  
    4.插入的新节点的父节点为红色，其叔节点为黑色，且新节点是其父节点的右子，调整方案：对新节点的父节点进行左旋，变成情况5，接下来按情况5进行处理；  
    5.插入的新节点的父节点为红色，其叔节点为黑色，且新节点是其父节点的左子，调整方案：对新节点的爷节点和父节点互换颜色，并对爷节点进行右旋；
- 删(remove)：红黑树的删除和二叉查找树相似，也分为双子皆空，一子空一子非空，双子非空三种大的情况，在红黑树的删除操作中，我们先定位
真正需要删除的节点（为什么说真正需要删除呢，因为双子非空的情况下，在结构上实际上是删除了当前节点的前驱/后继节点，然后把前驱/后继节点
的值复制到当前节点而已，一子空一子非空的情况下同），若真正删除节点的颜色为红，则直接删除，无需调整，若真正删除节点的颜色为黑，
则我们先调整该节点的结构，最后再把黑色节点删去。  
红黑树的删除调整分为5种情况讨论：（以待删除节点是其父节点的左子节点为例）  
    1.删除的节点是根节点，调整方案：不调整，若自底向上进行调整时把根节点染红了，则把根节点染黑；  
    2.删除的节点的兄弟节点为黑，其左右子节点皆黑或皆空，调整方案：把兄弟节点染红；  
    3.删除的节点的兄弟节点为黑，其右子节点为红，左子节点黑红空不关心，调整方案：待删除节点的父节点和兄弟节点颜色互换，兄弟节点的
    右子染黑，然后对父节点进行左旋；  
    4.删除的节点的兄弟节点为黑，其左子节点为红，右子节点为黑或空，调整方案：待删除节点的兄弟节点染红，兄弟节点的左子染黑，然后对
    兄弟节点进行右旋，此时得到情况3，按情况3处理；  
    5.删除的节点的兄弟节点为红，调整方案：待删除节点的父节点染红，兄弟节点染黑，然后对父节点进行左旋，得到情况2，按情况2处理；
### KD树
- findDim(dataSet)：找到当前切分的维度，对于每一维度的数据，计算他们在该维度上的方差，认为方差大的那一维数据比较分散。
- bulitTree(dataSet)：构建KD树，首先按照每一维度数据的方差进行排序，KD树的层就基于这个维度的序列进行选择维度划分子空间；至于如何选择根节点，
普遍认为选取在该维度的中值，可以把数据分为数量相当的两部分。
- getBrother(kdNode t)：获取t节点的兄弟节点。
- computeDis(kdNode t, target)：计算当前节点t与目标点target的距离大小，这里用的是欧几里得距离。
- findLeaf(kdNode t, target)：从当前节点t开始往下遍历，找到距离目标点最近的叶子节点。
- search1NN(target)：最邻近搜索的目的是找出KD树里面距离目标数据点最近的数据点，此距离可以使用曼哈顿距离，欧几里得距离等等，
甚至可以自定义距离的计算公式，这里用的是欧几里得距离。算法的过程如下：首先在KD树中找到距离目标数据点最近的一个叶子节点，计算该最小
距离并记录，然后从该叶子节点向上回溯，分别计算目标数据点与回溯节点之间的距离，若回溯节点的距离较当前最小距离小，则搜索该回溯节点下，
没有搜索过的另外那个子树，因为目标点离该回溯点较近表示未搜索过的另外那棵子树中，可能存在距离更近的节点，重复以上操作，直至回溯到根节点为止。
## 运行结果展示
### 二叉查找树
创建一棵空树，然后按顺序插入42，30，60，16，36，8，27，32，40，50，70；然后删除30（双子非空），16（有一子非空），32（双子皆空），
最后查找30，16，42，50，100
![BSTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/BSTtest.png)  
根据测试程序运行结果上画出二叉查找树下图，对比插入删除规则发现程序无误。
![BSTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/BSTresult.jpg)  
### AVL树
![AVLTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/AVLtest.png)  
测试程序运行结果如上图所示，将上图结果画出如下图所示。按顺序从小到大插入一串序列，若是二叉查找树的情况下会出现下图的情况1，树严重不平衡，
但使用AVL树后，得到的结果为下图的2，明显已经取得较为平衡的状态；若使用二叉查找树删除树根的左子树所有节点，则会出现下图中4的情况，
树又变得十分不平衡，而使用AVL树可以取得下图的3的结果，与4对比可以明显发现，AVL树在删除节点后会进行平衡操作。
![AVLTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/AVLtest.jpg)  
### 红黑树
新建一棵红黑树，插入网上生成的随机数6， 65， 62， 90， 92， 58， 76， 71， 84， 9，删除：62， 90， 76， 71， 9  
![RBTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/RBTinsert.png)  
![RBTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/RBTremove.png)  
 使用可视化程序展示最终树的结构如上两图所示，检查图中结构并没有违反红黑树4条原则的任意一条，整体结构也算得上平衡，结果无误。
### 2d树
这里实现的是一棵2D树，首先使用random种子随机生成1000个二维的数据点，然后随机给出目标点\{2,4.5\}，遍历所有数据点，计算出距离目标点
最近的一个数据点作为GroundTruth验证最后最邻近算法结果的正确性；然后使用这1000个二维的数据点构建一棵2D树，并求出目标点\{2,4.5\}在树中的最邻近数据点。
![2dTree](https://github.com/FFFjx/DataStructures/blob/main/Tree/pic/KDTtest.png)  
观察运行结果，程序运行结果与GroundTruth得出的结果的数据点一致，结果无误。