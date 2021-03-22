**遇到问题整理：**

在处理（x=255-x）的时候，先是读取xlass中字节，再对每个字节进行修改

**我的读取方法：**

```plain
try(FileInputStream fis = new FileInputStream("")){
    int length = 0;
    byte[] bytes = new byte[1024];
    StringBuffer sb = new StringBuffer();
    while((length = fis.read(bytes,0,bytes.length))!=-1){
        sb.append(new String(bytes,0,length));
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```
**我的转换方法：**
```plain
buf[i] = (byte) (255 - buf[i]);
```
报错：Incompatible magic value 4022320623 in class file Hello
我发现是0xCAFEBABE 出错，马上意识到是转换后的字节码不对

最开始的猜想是由于255-buf[i]这步在java中从byte提升到int在转回byte导致数据出错，

我加入了打印的确发现数据在转换时和预想的不同，发现buf[i]很多是负数，并且通过计算后在转换成byte的值也有很多是负数。

**Java****中的数据类型是有符号的，最高位是符号位，****byte****的大小是****8bits****，****byte****的范围是****[-128,127]**

改为：

```plain
(255 - buf[i] & 0xff) & 0xff
```
数据对了，但是转成byte又会变成负数，我就直接把int数据存入string了，报错依旧
当我打开转码后的class文件，发现int存入string后，每一位数字单独占据8bit，相当于原来202用8bit直接表示，变成了，2用8表示、0又用8表示、2又用8表示。

这时候我突然想到可能是string在中间转换的问题，我直接用数组接收文件中的所有数据，然后转换，也不考虑byte负数的问题，因为计算机读的是2进制代码，有无符号应该不影响

```plain
try (FileInputStream fis = new FileInputStream(file)) {
    //获取文件大小字节
    int length = fis.available();
    //读取文件字节到一个数组中
    int bytesRead = 0;
    int bytesToRead = length;
    input = new byte[bytesToRead];
    while (bytesRead < bytesToRead) {
        int result = fis.read(input, bytesRead, bytesToRead - bytesRead);
        if (result == -1) {
            break;
        }
        bytesRead += result;
    }
    return deCode(input);
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```
转换代码改回去
```plain
buf[i] = (byte) (255 - buf[i]);
```
这次成功加载class
总结：其实我们印象中的202，在byte中打印出来是-54（有符号），对于程序去加载字节码是没有影响的。反倒是byte数组在转换成string后再转回byte数组后会影响数据。

![图片](https://uploader.shimo.im/f/SL4uLxkCvfUneVYR.png!thumbnail?fileGuid=R8wGQhd9ckQR6yct)

