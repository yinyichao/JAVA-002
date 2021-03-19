**通过  javac 命令  编译生成class字节码文件**

**Class文件是一组以8bit为基础的二进制数据**

![图片](https://uploader.shimo.im/f/yYq5QPcr6pdmfDbO.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

class文件中存在以下数据项(该图表参考自《深入Java虚拟机》)：

u4代表4个8bit、u2代表2个8bit、其他以info结尾类型为特殊类型

| 版本 | 名称 | 发行日期 |
| ------ | ------ | ------ |
| JDK 1.0 | Oak(橡树) | 1996-01-23 |
| JDK 1.1 |  | 1997-02-19 |
| JDK 1.1.4 | Sparkler（宝石） | 1997-09-12 |
| JDK 1.1.5 | Pumpkin（南瓜） | 1997-12-13 |
| JDK 1.1.6 | Abigail（阿比盖尔–女子名） | 1998-04-24 |
| JDK 1.1.7 | Brutus（布鲁图–古罗马政治家和将军） | 1998-09-28 |
| JDK 1.1.8 | Chelsea（切尔西–城市名） | 1999-04-08 |
| J2SE 1.2 | Playground（运动场） | 1998-12-04 |
| J2SE 1.2.1 | none（无） | 1999-03-30 |
| J2SE 1.2.2 | Cricket（蟋蟀） | 1999-07-08 |
| J2SE 1.3 | Kestrel（美洲红隼） | 2000-05-08 |
| J2SE 1.3.1 | Ladybird（瓢虫） | 2001-05-17 |
| J2SE 1.4.0 | Merlin（灰背隼） | 2002-02-13 |
| J2SE 1.4.1 | grasshopper（蚱蜢） | 2002-09-16 |
| J2SE 1.4.2 | Mantis（螳螂） | 2003-06-26 |
| Java SE 5.0 (1.5.0) | Tiger（老虎） | 2004-09-30 |
| Java SE 6.0 (1.6.0) | Mustang（野马） | 2006-04 |
| Java SE 7.0 (1.7.0) | Dolphin（海豚） | 2011-07-28 |
| Java SE 8.0 (1.8.0) | Spider（蜘蛛） | 2014-03-18 |
| Java SE 9.0 |  | 2017-09-21 |
| Java SE 10.0 |  | 2018-03-21 |
| Java SE 11.0 |  | 2018-09-25 |
| Java SE 12.0 |  | 2019-03-20 |


| 类型 | 名称 | 数量 |
| ------ | ----- | ------ | ----- | ------ | ----- |
| u4 | magic | 1 |
| u2 | minor_version | 1 |
| u2 | major_version | 1 |
| u2 | constant_pool_count | 1 |
| cp_info | constant_pool | constant_pool_count-1 |
| u2 | access_flags | 1 |
| u2 | this_class | 1 |
| u2 | super_class | 1 |
| u2 | interfaces_count | 1 |
| u2 | interfaces | interfaces_count |
| u2 | fileds_count | 1 |
| field_info | fields | fields_count |
| u2 | methods_count | 1 |
| method_info | methods | methods_count |
| u2 | attribute_count | 1 |
| attribute_info | attributes | attributes_count |

1、魔法数：0XCAFEBABE

2、minor_version：小版本，major_version:大版本 0x33=51为jdk1.7代号

3、constant_pool_count：常量池长度（常量池从1开始计数）

![图片](https://uploader.shimo.im/f/3FDgrvo5G1oEDFz2.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)0x48=72，说明常量池中有71个常量

常量池中主要存放两大类常量：字面量和符号引用。字面量：文本字符串、被声明为final的常量值等。符号引用：1、类和接口的全限定名，2、字段的名称和描述符，3、方法的名称和描述符。

| 常量池中数据项类型 | 名称 | 类型 | 类型描述 |
| :----: | :---- | :----: | :---- | :----: | :---- | :----: | :---- |
| CONSTANT_Utf8_info | tag | u1 | 值为1 |
|    | length | u2 | UTF-8编码的Unicode字符串长度 |
|    | bytes | u1 | 字符串 |
| CONSTANT_Integer_info |tag | u1 | 值为3 |
|    | bytes | u4 | int类型字面值 |
| CONSTANT_Float_info | tag | u1 | 值为4 |
|    | bytes | u4 | float类型字面值 |
| CONSTANT_Long_info | tag | u1 | 值为5 |
|    | bytes | u8 | long类型字面值 |
| CONSTANT_Double_info | tag | u1 | 值为6 |
|    | bytes | u8 | double类型字面值 |
| CONSTANT_Class_info | tag | u1 | 值为7 |
|    | index | u2 | 指向全限定名常量项的索引 |
| CONSTANT_String_info | tag | u1 | 值为8 |
|    | index | u2 | 指向字符串字面量的索引 |
| CONSTANT_Fieldref_info | tag | u1 | 值为9 |
|    | index | u2 | 指向CONSTANT_Class_info的索引 |
|    | index | u2 | 指向CONSTANT_NameAndType_info的索引 |
| CONSTANT_Methodref_info | tag | u1 | 值为10 |
|    | index | u2 | 指向CONSTANT_Class_info的索引 |
|    | index | u2 | 指向CONSTANT_NameAndType_info的索引 |
| CONSTANT_InterfaceMothdref_info | tag | u1 | 值为11 |
|    | index | u2 | 指向CONSTANT_Class_info的索引 |
|    | index | u2 | 指向CONSTANT_NameAndType_info的索引 |
| CONSTANT_NameAndType_info | tag | u1 | 值为12 |
|    | index | u2 | 指向该字段或方法名称常量项的索引 |
|    | index | u2 | 指向该字段或方法描述符常量项的索引 |

0x0A转为10，对应CONSTANT_Methodref_info，0x15、0x25分别指向CONSTANT_Class_info，CONSTANT_NameAndType_info

0x15转为21指向常量池中第21号常量，

![图片](https://uploader.shimo.im/f/g1UZRZhAOCk68r0e.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)对应![图片](https://uploader.shimo.im/f/hYj9DPO5HKHero78.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

0x25转为37指向常量池中第37号常量，

![图片](https://uploader.shimo.im/f/HDCjOMEqNRlB4rDu.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)等。

由于方法、字段等都需要引用CONSTANT_Utf8_info类型，长度为u2，即最大值为65535，故java程序中定义的变量或方法名不能超过64k字符，否则将无法编译。

4、

5、

6、

**通过  javap -c -verbose命令 查看字节码指令**

1）基本信息：

最后修改时间，md5，文件名，版本号：51为jdk1.7，52是jdk1.8

flags：访问标志符

![图片](https://uploader.shimo.im/f/n1I2prjnEFvc0myF.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

2）常量池

![图片](https://uploader.shimo.im/f/nAIXwwT5lZN1ONbc.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

3）类中方法部分

第一个默认无参构造函数

stack：最大操作数栈深度

locals：本地变量的数量

args_size:方法参数的个数

line:对应源代码行号

![图片](https://uploader.shimo.im/f/snVs4B4cMJAVVoaQ.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

4）栈图，jdk1.6后引入，作用是为了提高JVM在类型检查的验证过程的效率

![图片](https://uploader.shimo.im/f/PFcD4MO12ZIQ9eTP.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

