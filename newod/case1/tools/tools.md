## TreeMap类如何按照值的大小排序
// 创建一个原始的TreeMap
TreeMap<Integer, String> map = new TreeMap<>();
map.put(1, "A");
map.put(2, "B");
map.put(3, "C");
map.put(4, "D");
map.put(5, "E");

// 创建一个List来存放entrySet()
List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());

// 使用自定义的比较器类来对list进行排序
Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
    @Override
    public int compare(Map.Entry<Integer, String> o1,
                       Map.Entry<Integer, String> o2) {
        // 按照值降序排列
        return o2.getValue().compareTo(o1.getValue());
    }
});

// 创建一个新的TreeMap，并用排序后的list初始化它
TreeMap<Integer, String> sortedMap = new TreeMap<>();
for (Map.Entry<Integer, String> entry : list) {
    sortedMap.put(entry.getKey(), entry.getValue());
}

// 输出结果
System.out.println(sortedMap);

## // 入度


## 并查集
并查集是一种树型的数据结构，用于处理一些不相交集合的合并及查询问题。

并查集的思想是用一个数组表示了整片森林（parent），树的根节点唯一标识了一个集合，我们只要找到了某个元素的的树根，就能确定它在哪个集合里。

## pattern和matcher
String reg = "(\\d+)((CNY)|(JPY)|(HKD)|(EUR)|(GBP)|(fen)|(cents)|(sen)|(eurocents)|(pence))";
Pattern p = Pattern.compile(reg);
Matcher m = p.matcher(s);
m.find();
m.group(1);
m.group(2);

## 一个比较sq的流使用
        Integer[][] operations =
                Arrays.stream(str.substring(1, str.length() - 1).split("(?<=]), (?=\\[)"))
                        .map(
                                s ->
                                        Arrays.stream(s.substring(1, s.length() - 1).split(", "))
                                                .map(Integer::parseInt)
                                                .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

## 通过正则分组
cell1.matches("^-?\\d+$")
cell.split("[\\=\\+\\-]")
static Pattern p = Pattern.compile("^([A-Z])(\\d+)$");
Matcher m1 = p.matcher(start);
int col_start = m1.group(1).charAt(0);

## 生成树
为什么生成树只能由n-1条边呢？

因为少一条边，则生成树就无法包含所有顶点。多一条边，则生成树就会形成环。

而生成树最重要的两个特性就是：

1、包含所有顶点

2、无环

了解了生成树概念后，我们就可以进一步学习最小生成树了。

我们回头看看无向连通图，可以发现每条边都有权重值，比如v1-v2权重值是6，v3-v6权重值是4。

最小生成树指的是，生成树中n-1条边的权重值之和最小。

那么如何才能准确的找出一个无向连通图的最小生成树呢？

有两种算法：Prim算法和Kruskal算法。

Prim算法是基于顶点找最小生成树。Kruskal是基于边找最小生成树。