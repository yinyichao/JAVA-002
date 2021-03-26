主流jvm虚拟机有oracle的hotspot VM、IBM的J9 VM、Azul 的Zing VM等。

真正接触的就是oracle/openjdk 的hotspot了，IBM的J9许可需要付费。

堆内存的管理是jvm的核心部分，java中将使用的内存大体分为：线程栈（线程独立不共享）和堆内存（所有线程共享）两类，jdk1.7之后将堆内存中的永久代（PermGen）移出了堆内存，成为了非堆的元数据空间（Metaspace），移除原因见1。

![图片](https://uploader.shimo.im/f/lS8X8PZzSrJQ8OSl.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

GC是hotspot中用来管理堆内存的工具，随着计算机硬件的发展，从单核cpu到多核cpu，以及内存容量的指数增长，GC也在不断地发展。从最初的Serial GC（串行）、Parallel GC（并行）、CMS GC（并发、低延迟）、到jdk7的G1、jdk11的ZGC、jdk12的Shenandoah。

由于java中大多数对象都是朝生夕死的，根据此现象，产生了分代收集算法，它根据对象的生存周期，将堆分为**新生代(Young)和老年代(Tenure)**。顾名思义，新生代中存放的都是新创建的对象，在新生代满了之后，就会触发YGC对其进行回收，为了方便回收，又将新生代分为8（Eden）：1（Survivor01）：1(Survivor02)的3份，当Eden区满了之后，就会触发YGC将其中存活的对象复制到s01中，然后清空Eden，在下一次Eden满了之后，就会触发YGC将s01和Eden中存活的对象复制到s02中，在清空s01和Eden，以此类推。当出现s01和s02无法容纳存活对象的时候，会直接将存活对象复制进Old区中，并且当存活对象存活的足够长（经历多次YGC），就可直接晋升到Old区，默认为15次。当Old区的空间不足的时候，就会进行FGC,因为FGC回收的对象较多，会产生较长的延迟，所以应当尽量避免FGC。

SerialGC是串行的jvm垃圾收集器，就是当jvm需要进行垃圾收集的时候，所有jvm应用线程需要进入安全点停止当前的一切操作，GC线程会开始对java堆内存的回收工作，这就是STW。

![图片](https://uploader.shimo.im/f/FukYQimurFn9qyCY.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

ParallelGC是并行GC，可以充分利用当前cpu多核的特性，开启多线程GC进行垃圾回收，但是和SerialGC一样存在无论是YGC还是FGC都需要STW。

![图片](https://uploader.shimo.im/f/nd0o4NeXV85We8qx.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

CMSGC的设计目标是避免在老年代垃圾收集时出现长时间的卡顿，主要通过两种手段来达成此目标： 第一，不对老年代进行整理，而是使用空闲列表(free-lists)来管理内存空间的回收。第二，在**mark-and-sweep**(标记-清除) 阶段的大部分工作和应用线程一起并发执行。 也就是说，在这些阶段并没有明显的应用线程暂停。但值得注意的是，它仍然和应用线程争抢CPU时间。默认情况下，CMS 使用的并发线程数等于CPU核心数的1/4。

只有在初始标记和最终标记的时候，才需要触发STW。![图片](https://uploader.shimo.im/f/EYVzgqwbjDblO3rN.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

G1GC是为了达成可预期停顿时间的指标的GC，它主要将之前的堆内存拆分成了许多region，每个小块，可能一会被定义成 Eden 区，一会被指定为 Survivor区或者Old

区。在逻辑上，所有的Eden区和Survivor区合起来就是年轻代，所有的Old区拼在一起那就是老年代，如图![图片](https://uploader.shimo.im/f/IwceXdKWmXA68SEY.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

这样划分之后，使得 G1不必每次都去收集整个堆空间，而是以增量的方式来进行处理: 每次只处理一部分内存块，称为此次GC的回收集(collection set)。每次GC暂停都会收集所有年轻代的内存块，但一般只包含部分老年代的内存块。

![图片](https://uploader.shimo.im/f/efP1bhTtCeL8SKik.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

由于每次只进行部分回收，可以将延迟控制在很低的时间。

![图片](https://uploader.shimo.im/f/oCnLJCIbwzOJJDmM.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)


ZGC 收集器

Z Garbage Collector，简称 ZGC，是 JDK 11 中新加入的尚在实验阶段的低延迟垃圾收集器。它和 Shenandoah 同属于超低延迟的垃圾收集器，但在吞吐量上比 Shenandoah 有更优秀的表现，甚至超过了 G1，接近了“吞吐量优先”的 Parallel 收集器组合，可以说近乎实现了“鱼与熊掌兼得”。

![图片](https://uploader.shimo.im/f/5cu60mDWNnewmo0n.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

Shenandoah 收集器

Shenandoah 是由 ReadHat 公司独立发展的新型垃圾收集器，并在2014年贡献给了 OpenJDK，并成为 OpenJDK 12 的正式特性之一，但是以 Oracle 公司的尿性，却不愿把它添加到 OracleJDK 中，这也导致了免费开源的 OpenJDK 反而比商业收费的 OracleJDK 功能更多，实属罕见。

![图片](https://uploader.shimo.im/f/QPVKl9uGYCESwApA.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

![图片](https://uploader.shimo.im/f/rUelaiqjXZ795Qc6.png!thumbnail?fileGuid=yTYQRdCYtYhRxwWX)

1、移除永久代是为融合HotSpot JVM与 JRockit VM而做出的努力，因为JRockit没有永久代，不需要配置永久代。

2、元空间是方法区的在HotSpot jvm 中的实现，方法区主要用于存储类的信息、常量池、方法数据、方法代码等。方法区逻辑上属于堆的一部分，但是为了与堆进行区分，通常又叫“非堆”。

3、新生代分类8：1：1，会浪费一定的内存空间用作交换。

