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

### HJ29 字符串加解密
用这种方式来完成边界值9和z和Z替换为0，a和A，很酷
(48 + (code - 48) % 10)
(97 + (code - 65) % 26)
(65 + (code - 97) % 26)

### HJ31 单词倒排
用toString方法可以将StringBuilder转化为String
Collections.reverse(output) 可以让ArrayList实例倒序
正则表达式表达字母[A-Za-z]
str.split("[^A-Za-z]");
到底什么时候能直接用正则表达式？

### HJ41 称砝码
基本思想是用HashSet存储结果和去重
至于输入的方式使用Scanner类调用nextInt方法，能直接接受数值

### HJ45 名字的漂亮度
难得写了一次最优解法，使用一个数组存储出现次数即可
Arrays.sort(record);
数组**全部填充**：Arrays.fill(record, 0);

### HJ50 四则运算 未能解决
通过入栈出栈能解决括号的识别问题。
如何计算呢？

### HJ73 计算日期到天数转换
常规方法计算，要注意每四年为一个闰年，但是整百年，需要满足每400年为一个闰年
也可以直接调用Calendar类c1.set(y, m-1, d);c1.get(Calendar.DAY_OF_YEAR);
[Calendar类](https://blog.csdn.net/lfsf802/article/details/50616263)

### HJ84 统计大写字母个数
简单暴力，也可以用正则表达式将其他字符替换为空

### HJ3 明明的随机数
利用HashSet简单实现。storage.toArray(output)，output得是声明了长度的基本引用类型数组。
[TreeSet类](https://www.yiibai.com/java/java_treeset_class.html) 自然升序的set，有很多导航的api
[Iterator类迭代器](https://www.runoob.com/java/java-iterator.html) next,haveNext和remove三个方法 用于ArrayList和HashSet等集合
Iterator<String> it = sites.iterator();

### HJ7 取近似值
强制转型double转int会直接丢弃小数点后面的
### HJ8 合并表记录
TreeHashMap结构能按照key升序来存储数据
### HJ9 提取不重复的整数
使用LinkedHashMap结构快速去重和顺序输出。
### HJ12 字符串反转
### HJ13 句子逆序

### HJ15 求int型正整数在内存中存储时1的个数
Integer类有两个方法，toBinaryString方法转化为二进制数字符串，bitCount方法直接计算转化成二进制后1的个数
[Integer类](https://www.cnblogs.com/ysocean/p/8075676.html)
**位运算**
> 左移( << )、右移( >> ) 、无符号右移( >>> )
> 正数右移，高位用0补，负数右移，高位用1补，当负数使用无符号右移时，用0进行补位(自然而然的，就由负数变成了正数了)
> 位与( & ) 都是1，那么结果为1，否则为0
> 位或( | ) 有一个1，那么结果为1，否则为0
> 位异或( ^ ) 两数相反，那么结果为1，否则为0
> 位非( ~ ) 取反

### HJ34 图片整理

### HJ40 统计字符
Character类方法isLetter();isDigit();isSpaceChar();
[Character类](https://www.runoob.com/java/java-character.html)
line.replaceAll("[A-Z]+|[a-z]+",""); **+**表示至少一个

### HJ46 截取字符串
### HJ59 找出字符串中第一个只出现一次的字符

### HJ65 查找两个字符串a,b中的最长公共子串
TreeMap需要倒序排序时，可以使用new TreeMap<>(Comparator.reverseOrder());
TreeMap需要按照特定顺序排序时，使用new Comparator<String>() 并重写compare方法，见HJ65
比较器
也可以使用dp方法

### HJ92 在字符串中找出连续最长的数字串
需要详细了解dp(动态优化)算法到底如何使用
可以存位置，也可以存字符

### HJ26 字符串排序
二维的ArrayList
> ArrayList<ArrayList<character>> cutPlan = new ArrayList<>(); 
> c.get(i)).get(j);

Arrays.sort()源码也还是使用for循环赋值
> for (int i = 0; i <= n; i++) {
Arrays.fill(f[i], Integer.MAX_VALUE);
}

忽略字符大小写排序
> letters.sort(new Comparator<Character>() {
public int compare(Character o1, Character o2) {
return Character.toLowerCase(o1) - Character.toLowerCase(o2);
}
})

### HJ36 字符串加密
Character[] password = storage.toArray(new Character[0]);可以将LinkedHashSet转化为数组
ArrayList<Character> list = new ArrayList<>(set);这个可以将LinkedHashSet转化成ArrayList

### HJ62 查找输入整数二进制中1的个数
使用while ( null != temp )可以判断Scanner类有没有下一个输入

### HJ91 走方格的方案数
对dp(动态优化)算法开始理解[dp算法理解](https://zhuanlan.zhihu.com/p/91582909)
> 第一步：定义数组元素的含义
> 第二步：定义数组元素之间的关系式
> 第三步：找出初始值

### HJ97 记负均正
暴力算法，注意int / int是没有小数的
Math.round(sum*10.0/positive)/10.0);Math.round函数为四舍五入，可以利用*10.0之后再/10.0保留一位小数

### HJ94 记票统计
无

### HJ106 字符逆序
StringBuilder可以调用reverse()方法使字符串倒序
[StringBuilder](https://www.runoob.com/java/java-stringbuffer.html)
StringBuilder可以直接使用String构建，也可以直接当作String输出
> StringBuilder sb = new StringBuilder(br.readLine());

### HJ102 字符统计（key和value都需要排序）
使用TreeMap能把key按照ASCII码升序排序
需要对values进行遍历找到最大值max
循环max次，再循环遍历keySet，进行二次排序

### HJ80 整型数组合并

### HJ32 密码截取（需要进一步学习）
回文子串，两种可能，aba和aa依次考虑，可以更优雅的使用指针
>while(i,r) {i++,r--;}

可以使用动态规划，Boolean dp[i][j] 表示从第i个字符到第j个字符是回文子串

### HJ35 蛇形矩阵
**等差累加公式**：(首项+末项)*项数/2
[二维数组](http://c.biancheng.net/view/916.html)
对二维数组的第一维度单独赋值，依次对对角线赋值
对二维数组的输出，遍历第一维度，输出每个第一维度的第二个维度
```
            for (int i = 0; i < n; i++) {result[i] = new int[n - i];//数组第i行有n-i个元素
                for (int j = 0; j < i + 1; j++) { result[i - j][j] = t; }}   //对第i个对角线赋值     
            for (int[] a : result) {
                for (int a1 : a)System.out.print(a1);}
```

### HJ100 等差数列

### HJ96 表示数字
暴力算法需要考虑到每一种条件，详细，可以优化
正则表达配合replaceAll
**$1**表示找到的组1
> input.replaceAll("([0-9]+)", "*$1*")

### HJ75 公共子串计算（待理解）
暴力算法，双循环
双指针算法（待实现）
动态规划算法（待实现）

### HJ101 输入整型数组和排序标识，对其元素按照升序或降序进行排序
treeSet转化为数组
Integer[] temp = allSet.toArray(new Integer[] {});

### HJ37 统计每个月兔子的总数
dp算法，找规律
>       dp[0] = 1;
        dp[1] = 1;
        dp[2] = dp[1] + dp[0];
        dp[3] = dp[2] + dp[1];
        dp[4] = dp[3] + dp[2];

### HJ30 字符串合并处理
Integer.parseInt(String,radix); radix参数指示转化为特定进制的整数
String hexString = Integer.toHexString(n).toUpperCase();//n为int，可转成16进制字符串大写

### HJ58 输入n个整数，输出其中最小的k个
典型的top k算法
性能上：堆 > 选择 > 冒泡 > api排序 仅实现api排序

### HJ85 最长回文子串 参考HJ32
### HJ108 求最小公倍数
暴力算法，相乘为最大，最小为累加
递归，累加自身
### HJ105 记负均正II

### HJ99 自守数
自守数是指一个数的平方的尾数等于该数自身的自然数
平方后相减再取余10的n次方为0
获取一个整数是**几位数的方法**：
> nums = (int) Math.log10(i) + 1;

### HJ33 整数与IP地址间的转换
正则匹配中的特殊字符的转义匹配需要用双斜杠\\
str.split("\\.");
很酷的想法，增加8位二进制等于整体*256
反过来减少8位二进制等于整体%256
用substring(0, length - 1)来进行格式控制，避免使用if判断特殊情况

### HJ86 求最大连续bit数
\>>是有符号位移 >>>是无符号位移

### HJ53 杨辉三角的变形
找规律，从第三位开始 2 3 2 4 位置是偶数
暴力会导致溢出

### HJ56 完全数计算
取余运算为0则为因数，同时只需要遍历到n/2就可以了

### HJ81 字符串字符匹配
用HashSet保存要对比的字符串，能大大降低时间复杂度

### HJ76 尼科彻斯定理
高中的我们看到这个题的一瞬间，一定会觉得这是个弱智题。然而现在的我，想了好久。。
题目的意思是已知等差数列和 Sn为 m^3，项数n为m，公差d为2，求首项a1

### HJ38 求小球落地5次后所经历的路程和第5次反弹的高度