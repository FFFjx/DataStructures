# 散列表
## 介绍
- 散列函数：给定一个关键字，将其映射到散列表的某一单元上。基本形式：hash(key)=value。理想的散列函数应该是计算简单，且可以保证任何
两个不同的关键字映射到不同的数值。但实际上关键字是无限的，而单元的数量是有限的，因此需要找到一个散列函数，可以在单元之间均匀地
分配关键字。
- 散列表：意义在于使用散列函数分散、储存、区分不同的关键字。优点：能够以常数时间O(1)进行插入、查找和删除。当选择了一个散列函数后，
如何解决两个关键字散列到同一单元的情况（该情况称为冲突），决定了散列表的分类。
- 线性探测散列表：使用数组构建散列表，解决冲突的方式为，当发现新的关键字散列到某一单元，而该单元上已存在其他关键字时，往该位置的
后方探测新的单元，直到找到空单元为止。
- 分离链接散列表：采用数组+链表的方式构造散列表，数组中存放的对象为链表，解决冲突的方式为，将散列到同一个单元上的所有元素保留在一个链表中。
## 技术用途
- 使用插入、删除、查找（根据关键字查找，而非根据位置查找）较多的表可以应用散列表，如某登录系统的用户名存储表，登陆时需要先查找该
用户名（关键字）是否在已注册的用户库中。
## 方法实现
### 线性探测散列表
- findPos(key)：使用散列函数计算关键字对应的单元，若该单元为空或占用该单元的是该关键字，返回该单元的索引，若单元非空且占用此单元
的是其他关键字，则向该单元的后方探测新单元，直到找到空单元为止，返回空单元的索引。
- insert(key)：调用findPos(key)找到可以插入该关键字的单元索引并插入，插入后若散列表的长度（即散列表储存的关键字的个数）达到数组
长度的一半，则调用rehash()重构散列表。
- remove(key)：调用findPos(key)找到该关键字对应的单元索引，将其设置为未激活（相当于软删除）。
- contains(key)：调用findPos(key)找到该关键字对应的单元索引，若该单元未激活，则返回False，其余情况返回True。
- rehash()：建立一个长度是原有数组长度两倍的新数组，把旧数组储存的关键字插入到新数组中。
### 分离链接散列表
- insert(key)：调用散列函数找到该关键字对应的单元索引并插入单元对应的链表中，插入后若散列表的长度（即散列表储存的关键字的个数）
达到数组长度的一半，则调用rehash()重构散列表。
- remove(key)：调用散列函数找到该关键字对应的单元索引，在单元对应的链表中删除该关键字。
- contains(key)：调用散列函数找到该关键字对应的单元索引，在该单元对应的链表中查找该关键字，若能找到则返回True，其余情况返回False。
- rehash()：建立一个长度是原有数组长度两倍的新数组，把旧数组储存的关键字插入到新数组中。
## 运行时间对比
