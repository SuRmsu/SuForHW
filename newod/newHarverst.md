# NEW OD 题库
## OD3 字符串重新排列  流
给定一个字符串s，s包括以空格分隔的若干个单词，请对s进行如下处理后输出：
1、单词内部调整：对每个单词字母重新按字典序排序
2、单词间顺序调整：
1）统计每个单词出现的次数，并按次数降序排列
2）次数相同，按单词长度升序排列
3）次数和单词长度均相同，按字典升序排列

请输出处理后的字符串，每个单词以一个空格分隔。

解法：流 + HashMap记录出现次数
## 0D6 不含101的数 暴力 / DP
题目描述
小明在学习二进制时，发现了一类不含 101的数，也就是：

将数字用二进制表示，不能出现 101 。
现在给定一个整数区间 [l,r] ，请问这个区间包含了多少个不含 101 的数？

输入描述
输入的唯一一行包含两个正整数 l， r（ 1 ≤ l ≤ r ≤ 10^9）。

输出描述
输出的唯一一行包含一个整数，表示在 [l,r] 区间内一共有几个不含 101 的数。
解法： String.toBinaryString() 方法可暴力解出；按位dp可优化

## OD8
题目描述
给定一个数组nums，将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中，组内元素和的最小值。

输入描述
第一行输入 m
接着输入m个数，表示此数组nums
数据范围：1<=m<=50, 1<=nums[i]<=50

输出描述
最小拆分数组和
解法：回溯 / 二分查找
平分组总和最少，能分成多少组sum / count，再看能不能分成这几组

## OD39 DP
题目描述
小明每周上班都会拿到自己的工作清单，工作清单内包含 n 项工作，每项工作都有对应的耗时时间（单位 h）和报酬，工作的总报酬为所有已完成工作的报酬之和，那么请你帮小明安排一下工作，保证小明在指定的工作时间内工作收入最大化。

输入描述
输入的第一行为两个正整数 T，n。
T 代表工作时长（单位 h， 0 < T < 1000000），
n 代表工作数量（ 1 < n ≤ 3000）。
接下来是 n 行，每行包含两个整数 t，w。
t 代表该工作消耗的时长（单位 h， t > 0），w 代表该项工作的报酬。

输出描述
输出小明指定工作时长内工作可获得的最大报酬。
解法：经典01背包问题，DP即可

## LC698 花费为和相同的子集合 回溯
给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
详见：https://blog.csdn.net/qfc_128220/article/details/127761308
解法：回溯，用桶来看是否能装下，先排序，再依次放，知道球放完为止
参数：原始数组、分为几组、当前球的索引、目标总和、桶的数组

## LC416 DP / 回溯
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
解法：嗯 回溯
dp：dp[i][j] 选择前i个产品，其和能否达到j

## OD8 等和子数组最小和 回溯
给定一个数组nums，将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中，组内元素和的最小值。
解法：回溯
最多能分为n组，n从最大个数依次递减

## OD8_2 最大平分数组
给定一个数组nums，可以将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中， 最大的平分组个数。
解法：同上，返回的是组数
注：不从大到小排不行

## OD24 星际篮球争霸赛 回溯
在星球争霸篮球赛对抗赛中，最大的宇宙战队希望每个人都能拿到MVP，MVP的条件是单场最高分得分获得者。
可以并列所以宇宙战队决定在比赛中尽可能让更多队员上场，并且让所有得分的选手得分都相同，
然而比赛过程中的每1分钟的得分都只能由某一个人包揽。
解法：同上，在一定的序列中找到最多等和序列
需注意：必须从大到小进行排列

## OD26 日志首次上报最多积分 dp
日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。

如果上报太频繁，会对服务端造成压力;
如果上报太晚，会降低用户的体验；
如果一次上报的条数太多，会导致超时失败。
为此，项目组设计了如下的上报策略：

每成功上报一条日志，奖励1分
每条日志每延迟上报1秒，扣1分
积累日志达到100条，必须立即上报
给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
解法：
dp[i] 在第i时刻上报能获得的最大分数
dp[i] 和 dp[i - 1]  : 1 累积到100，必须提交， 2 未累积到100，提交当前的，扣除前所有时刻的， 3 前一时刻的最大值
扣分单独算

## OD31 最多获得的短信条数 dp
某云短信厂商，为庆祝国庆，推出充值优惠活动。
现在给出客户预算，和优惠售价序列，求最多可获得的短信总条数。
输入描述
第一行客户预算M，其中 0 ≤ M ≤ 10^6
第二行给出售价表， P1, P2, … Pn , 其中 1 ≤ n ≤ 100 ,
Pi为充值 i 元获得的短信条数。1 ≤ Pi ≤ 1000 , 1 ≤ n ≤ 100

输出描述
最多获得的短信条数
解法：
dp[i][j] 用j钱，买前i个物品，可重复，最多能得到的价值
买不起当前的，dp[i][j] = dp[i - 1][j]
买得起当前的，买当前的：dp[i-1][j - table[i]] + 当前值 和 不买的取最大值dp[i - 1][j] 这是01背包
完全背包：得先初始化买第一个物品的所有的钱的情况,这里是不同的地方，然后先遍历物品，再遍历钱都可以
**待优化为一维dp数组！！！**

## OD45 查找重复代码 dp
小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助小明找出重复的代码。
重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
注：如果不存在公共子串，返回空字符串

输入描述
输入的参数text1, text2分别表示两行代码

输出描述
输出任一最长公共子串

解法：
1. 双指针，但要注意边界条件，即两个字符串分别遍历到最后一个位置
2. dp
dp[i][j] 字符串a的前i个字符和字符串b的前j个字符，最大的字串数量 
正斜上方表示两个字符串的上一个字符是否相同