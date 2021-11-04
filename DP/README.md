# Dynamic Programming
## 介绍
动态规划是一类将大问题转化为N个小问题嵌套（或迭代）求解的算法。经典动态规划问题有：01背包问题，最长递增子序列，维特比算法等。  
动态规划的核心是以下三个问题：
- 如何把原问题划分为子问题，即动态规划的状态如何定义。
- 原问题和子问题之间如何转换，即动态规划中如何确定状态转移方程。
- 初始情况的定义，即动态规划中如何定义子问题的初始解。  

## 技术用途
- 01背包问题：给定N个物品的价值$v(i)(1<=i<=N)$和重量$w(i)(1<=i<=N)$，求容量为C的背包能装下的物品最大价值及其对应的物品组合是什么。
- 最长递增子序列：给定一个长度为N的数列，求数值单调递增的子序列的长度最大为多少。即已知数列A，$A=A_1,A_2,...A_n$，求A的任意子序列B
($B=A_k1,A_k2,...,A_kp$)，使B满足$k_1<k_2<...<k_p$且$A_k1<A_k2<...<A_kp$，求p的最大值。
- 维特比算法：维特比算法用于求解HMM(Hidden Markov Model)模型的解码问题，即已知模型和观测序列，求给定观测序列条件下，最可能出现的对应隐藏状态序列。
- HMM(Hidden Markov Model)模型：HMM模型有两个特征：1.是基于序列的，比如时间序列，或者状态序列。2.问题中有两类数据，一类序列数据
是可以观测到的，即观测序列；另一类数据是不能观察到的，即隐藏序列，简称状态序列。对HMM模型进行定义：设Q是所有可能出现的隐藏序列集合，
$Q={q_1,q_2,...,q_N}$，隐藏状态的总数是N，设V是所有可能出现的观测序列集合，$V={v_1,v_2,...,v_M}$，观测状态的总数是M；对于一个长度为
T的序列，I表示对应的隐藏状态序列，$I={i_1,i_2,...,i_T}$，O表示对应的观测状态序列，$O={o_1,o_2,...,o_T}$，其中$\forall i_t \in Q, t=1,2,...,T$
，$\forall o_t \in V, t=1,2,...,T$。HMM做了以下两个假设：1.齐次马尔科夫链假设，即任意时刻的隐藏状态只依赖于它前一时刻的隐藏状态。
时刻t的隐藏状态是$i_t=q_i$，在时刻t+1的状态是$i_t+1=q_j$，则从时刻t到时刻t+1的HMM状态转移概率$a_ij$可以表示为$a_ij=P(i_t+1=q_j|i_t=q_i)$，
这样$a_ij$可以组成马尔科夫链的状态转移矩阵$A=[a_ij]_N \times N$；2.观测独立性假设，即当前时刻的观测状态仅由当前时刻的隐藏状态决定。
时刻t的隐藏状态$i_t=q_j$，对应的观察状态$o_t=v_k$，则在该时刻观测状态$v_k$在隐藏状态$q_j$下生成的概率为$b_j(k)$，满足$b_j(k)=P(o_t=v_k|i_t=q_j)$，
这样$b_j(k)$可以组成观测状态生成概率矩阵$B=[b_j(k)]_N \times M$。除以上两个矩阵之外，我们还需要一组在最初时刻t=1的隐藏状态概率分布$\prod =[\pi (i)]_N$，
其中$\pi (i)=P(i_1=q_i)$。由此，HMM模型可以由一个三元组表示：$\lambda =(A,B,\prod)$

## 方法明细
- 01背包问题：建立并维护一个$(N+1)*(C+1)$的矩阵。定义状态$F(i,j)$为当前背包容量$j(0<=j<=C)$的情况下，前$i(0<=i<=N)$个物品最佳组合对应
的最大价值。考虑到第i件物品可选择装或者不装，若选择装，则$F(i,j)=F(i-1,j-w(i))+v(i)$,则当前最大价值就等于前i-1件物品，在背包容量
为$j-w(i)$的最佳组合对应价值加上第i件物品的价值；若选择不装，则$F(i,j)=F(i-1,j)$。则状态转移方程为$F(i,j)=max{F(i-1,j-w(i))+v(i),F(i-1,j)}$。
初始化边界条件，$F(i,0)=F(0,j)=0$。最后进行回溯，找出最佳组合对应哪些物品。先取$F(N+1,C+1)与F(N,C+1)$作对比，如相等，则说明第N件物品未装，
如不相等，则说明装了第N件物品，跳到$F(N,C+1-w(N))$，循环此过程，直到所有物品都被检索有无装入。
- 最长递增子序列：建立并维护一个长度为N的数组。F(i)表示以A_i为结尾的LIS长度。状态转移方程为F(i)=max{F(j)+1,1}(1<=j<i,A_j<A_i)。
初始状态F(1)=1。
- 维特比算法：定义了两个局部状态用于递推。第一个是在时刻t隐藏状态为i的所有可能的状态转移路径中的概率最大值，记为$\delta_t(i)$，
$\delta_t(i)=max_{i_1,i_2,...,i_t-1}P(i_t=i, i_1,i_2,...,i_t,o_t,o_t-1,...,o_1|\lambda)$，得到$\delta$的递推公式
$\delta_ t+1(i)=max_{1<=j<=N}[\delta _t(j)\dot a_ji]\dot b_i(o_t+1)$；第二个是定义在t时刻状态为i的概率最大的转移路径的第t-1个节点的隐藏状态
，记为$\phi _t(i)$，其表达式可以表示为：$\phi _t(i)=argmax_{1<=j<=N}[\delta _t-1(j)\dot a_ji]$。首先初始化局部状态：
$\delta_1(i)=\pi _i \dot b_i(o_1),i=1,2,...,N$；$\phi _1(i)=0,i=1,2,...,N$，然后使用以上的两个递推式求出每个时刻t的两个局部状态，
直到最后时刻T。最后时刻T最大的$\delta _T(i)$对应的i即为T时刻最有可能出现的隐藏状态，而T-1时刻对应的最有可能出现的隐藏状态则由$\phi _T(i)$得到，
以此类推到最初时刻1。
## 实现结果
### 01背包问题
![测试程序](https://github.com/FFFjx/DataStructures/blob/main/DP/01package_test.png)
![测试结果](https://github.com/FFFjx/DataStructures/blob/main/DP/01package.png)
### 最长递增子序列
![测试程序](https://github.com/FFFjx/DataStructures/blob/main/DP/LIS_test.png)
![测试结果](https://github.com/FFFjx/DataStructures/blob/main/DP/LIS.png)
### 维特比算法
![测试程序](https://github.com/FFFjx/DataStructures/blob/main/DP/Viterbi_test.png)
![测试结果](https://github.com/FFFjx/DataStructures/blob/main/DP/Viterbi.png)