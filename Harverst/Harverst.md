# 刷题中遇到的杂七杂八的知识点
## 8.21 
### HJ1 字符串最后一个单词的长度
length是Array的一个固定属性
length()方法是String的一个静态方法
字符串判断是否为空，一个是字符是否为空，一个是字符串未指向任何地方
**Scanner类**用于扫描命令行输入，包含hasNext和hasNextLine两种判断方式
> a.next只会保存空格之前的字符串
> a.nextLine会保存整行的内容
split返回的是字符串数组

### HJ2 计算某字符出现的次数
BufferedReader也有readline和read方法
Character.toLowCase变小写，toUpperCase变大写
大写字母的utf-8编码比小写字母的少32 ‘a’ = (char) ('A' + 32)

### HJ4 字符串分隔
substring单参数，返回之后的字符串，双参数是返回当前的字符串

### HJ5 进制转换
char和int可以直接转换
int的范围是2的31次方-1,即2147483647
**48到57**是数字**0到9**
**65到90**是大写字母**A到Z**
**97到122**是小写字母**a到z**
[ascii](http://c.biancheng.net/c/ascii/)

### HJ10 字符个数统计
去重统计，可以使用一维位图，使用**一位**来进行记录，也可以存入hash中，利用hash的特性进行统计
[HashSet类](https://www.runoob.com/manual/jdk11api/java.base/java/util/HashSet.html)
[BitSet类](https://www.runoob.com/java/java-bitset-class.html)
[ArrayList类](https://www.runoob.com/java/java-arraylist.html)

### HJ11 数字颠倒
[StringBuffer和StringBuilder类](https://www.runoob.com/java/java-stringbuffer.html) 前者线程安全
方法一：传统的对每个字符串进行字符反转
方法二：用%10获得多余的0，用/10去除最后一位

### HJ12 字符串反转
同HJ11的方法一

### HJ14 字符串排序
直接调用Arrays.sort()函数
使用**PriorityQueue**类，会按照字符串顺序排优先级，并按优先级最高依次输出[优先队列](https://www.cainiaojc.com/java/java-priorityqueue.html)
自己实现自己写一个冒泡排序吗？

### HJ17 坐标移动
暴力算法，用split分割字符串，暴力字符串匹配
使用正则表达式进行匹配，例如[WASD][0-9]{1,2} 
[正则表达式](https://www.liaoxuefeng.com/wiki/1252599548343744/1304066080636961)
> . 可以匹配任意字符
> \d 匹配整数字符 \D匹配非数字
> \w 字符、数字、下划线 \W
> \s 空格和tab \S
> *	任意个数字符	空，A，AA，AAA，……
> + 至少1个字符	A，AA，AAA，……
> ?	0个或1个字符	空，A
> {3}	指定个数字符	AAA
> {2,3}	指定范围个数字符	AA，AAA
> {2,}	至少n个字符	AA，AAA，
> {0,3}	最多n个字符	空，A，AA，AAA

### HJ19 简单错误记录
困难题目，但其实是比较简单的
HashMap是保存键值对的，但是不会保存顺序[HashMap](https://www.runoob.com/java/java-hashmap.html)
LinkedHashMap会以链表方式保存键值对，适合用于循环输入 [LinkedHashMap](https://www.cainiaojc.com/java/java-linkedhashmap.html)
获取字符串**切分后的最后一个**，用substring和lastIndexOf配合，或者使用正则匹配 \\分隔符
判断字符串**是否为空**应用：tempInput != null && !tempInput.equals("")
判断输入是否**停止**了：(tempInput = bf.readLine()) != null && !tempInput.equals("")

### HJ20 密码验证合格程序
用String记录总是会申请新地址和抛弃旧地址，会导致浪费
比较可以用正则匹配，也可以直接转化成char进行大于小于的对比，目前哪个更好还未知
正则匹配需要使用Pattern类，还没能完全弄懂正则匹配

### HJ21 简单密码
字符其实不用记住具体的ascii的数字值，直接用字符比较就行
而且可以用字符相减来获得差值

### HJ23 删除字符串中出现次数最少的字符
HashMap的用法，getOrDefault(ch, 0)找不到就返回第二个参数的值
判断大小可以使用Math.min方法，不需要自己写

