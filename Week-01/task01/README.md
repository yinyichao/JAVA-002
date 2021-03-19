**通过  javac 命令  编译生成class字节码文件**

**Class文件是一组以8bit为基础的二进制数据**

![图片](https://uploader.shimo.im/f/yYq5QPcr6pdmfDbO.png!thumbnail?fileGuid=dp9dtgdHKHG8rvJp)

class文件中存在以下数据项(该图表参考自《深入Java虚拟机》)：

u4代表4个8bit、u2代表2个8bit、其他以info结尾类型为特殊类型

| 类型 | 名称 | 数量 |
| ------ | ------ | ------ |
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
| ------ | ------ | ------ | ------ |
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

4、access_flags

|名称|值|含义|
|-----|-----|-----|
|ACC_PUBLIC|0x0001|是否public类型|
|ACC_FINAL|0x0010|是否final，只有类可设置|
|ACC_SUPER|0x0020|允许invokespecial字节码指令，jdk1.2之后编译出来的类标志为真|
|ACC_INTERFACE|0x0200|接口|
|ACC_ABSTRACT|0x0010|是否abstract类型|
|ACC_SYNTHETIC|0x0400|类并非由用户代码产生|
|ACC_ANNOTATION|0x2000|注解|
|ACC_ENUM|0x4000|枚举|

5、this_class、super_class、interfaces指向常量池的索引

6、fields(字段表集合） ：包括类级变量或实例级变量，不包括在方法内部声明的变量。

|类型|名称|数量|
|-----|-----|-----|
|u2|access_flags|1|
|u2|name_index|1|
|u2|descriptor_index|1|
|u2|attributes_count|1|
|attribute_info|attributes|attributes_count|

|名称|值|含义|
|-----|-----|-----|
|ACC_PUBLIC|0x0001|public|
|ACC_PRIVATE|0x0002|private|
|ACC_PROTECTED|0x0004|protected|
|ACC_STATIC|0x0008|static|
|ACC_FIANL|0x0010|final|
|ACC_VOLATILE|0x0040|volatile|
|ACC_TRANSIENT|0x0080|transient|
|ACC_SYNTHETIC|0x0100|是否由编译器自动生成|
|ACC_ENUM|0x0400|enum|

name_index 代表字段简单名称（指向常量池）

descriptor  代表方法的描述符（指向常量池）

|标识字符|含义|
|-----|-----|
|B|byte|
|C|char|
|D|double|
|F|float|
|I|int|
|J|long|
|S|short|
|Z|boolean|
|V|void|
|L|对象类型，如Ljava/lang/Object;|
|[|数组类型，如 [Ljava/lang/String;|

7、methods（方法表集合）

|类型|名称|数量|
|-----|-----|-----|
|u2|access_flags|1|
|u2|name_index|1|
|u2|descriptor_index|1|
|u2|attributes_count|1|
|attribute_info|attributes|attributes_count|

|名称|值|含义|
|-----|-----|-----|
|ACC_PUBLIC|0x0001|public|
|ACC_PRIVATE|0x0002|private|
|ACC_PROTECTED|0x0004|protected|
|ACC_STATIC|0x0008|static|
|ACC_FIANL|0x0010|final|
|ACC_SYNCHRONIZED|0x0020|synchronized|
|ACC_BRIDGE|0x0040|是否由编译器自动生成桥接方法|
|ACC_VARARGS|0x0080|方法是否接受不定参数|
|ACC_NATIVE|0x0100|native|
|ACC_ABSTRACT|0x0400|abstract|
|ACC_STRICT|0x0800|strictfp|
|ACC_SYNTHETIC|0x1000|是否由编译器自动生成|

8、attributes（属性表集合）

|名称|使用位置|含义|
|-----|-----|-----|
|Code|方法表|java代码编译成的字节码指令|
|ConstantValue|字段表|final关键字定义的常量值|
|Deprecated|类、方法表、字段表|被声明为deprecated的方法和字段|
|Exceptions|方法表|方法抛出的异常|
|InnerClasses|类文件|内部类列表|
|LineNumberTable|Code属性|java源码的行号与字节码指令的对应关系|
|LocalVariableTable|Code属性|方法的局部变量描述|
|SourceFile|类文件|源文件名称|
|Synthetic|类、方法表、字段表|标识方法或字段为编译器自动生成的|

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

