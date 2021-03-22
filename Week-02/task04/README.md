**jps -l**

查询pid

![图片](https://uploader.shimo.im/f/wrafXvAbJr2rnjwp.png!thumbnail?fileGuid=D3pK8QWRGvttjVQy)

**jstat -gcutil pid 250 100**

百分比显示jvm各区信息、和gc信息，250为250毫秒一次，100为显示100次

![图片](https://uploader.shimo.im/f/B5tu9aThN25TAX8j.png!thumbnail?fileGuid=D3pK8QWRGvttjVQy)

**jmap -histo pid > a.log**

查看堆中对象统计信息

![图片](https://uploader.shimo.im/f/fbNKWyOjuZeEQEfr.png!thumbnail?fileGuid=D3pK8QWRGvttjVQy)

**jmap -dump：format=b,file=f1 pid**

生成堆转储文件

**jhat f1**

查看堆转储文件

![图片](https://uploader.shimo.im/f/U8F2d8VewIjRVRaW.png!thumbnail?fileGuid=D3pK8QWRGvttjVQy)

通过7000端口进行查看

**jstack pid > a.log**

查看线程快照

![图片](https://uploader.shimo.im/f/v8e9tjrVqOIzojmC.png!thumbnail?fileGuid=D3pK8QWRGvttjVQy)

