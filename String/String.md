# String
char使用Unicode 占用两个字节
String是引用类型，不可变，其内部是char[]数组

只能使用**equals**方法而不能使用==

**contains**方法：
是否包含子串
**indexOf**方法：
**lastIndexOf**方法：
**startsWith**方法：
**endsWith**方法：
**substring**方法：截取子串 important
> (2),截取第三位及以后
> (2,4)，截取第三位到第五位

**trim**方法：去除字符串首尾空白字符，包括空格，\t \r \n 返回的是新的字符串
**strip**方法：同trim，也会删除中文的空格字符
**isEmpty**：判断是否为空
**isBlank**：判断是否为空白字符串
**replace**：替换字符串
> s.replace('l','w'); 将所有l替换为w

**正则表达式替换**：**replaceAll**

**split**方法：分割字符串，参数也是正则表达式，传出String数组
**join**方法：用指定的字符串**链接**字符串**数组**
**formatted和format方法**：格式化字符串
> String.format("hi %S","surm"); //hi surm
> s.formatted("Surm");
> %s 显示字符串 可以显示任何数据类型
> %d 显示整数
> %x 显示十六进制整数
> %f 显示浮点数  %.2f表示显示两位小数

**valueOf(char[])**：类型转换为字符串
**Integer.parseInt/Boolean.parseBoolean**:将字符串转化为其他类型

**toCharArray**：String转化为char数组
**new String(char[])**：char数组转化为String，不会互相影响 

编码转化内容：暂时不考虑



