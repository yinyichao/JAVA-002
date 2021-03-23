```plain
java -XX:PrintGCDetails -XX:PrintGCDateStamps -XX:UseSerialGC -Xlonggc:gc.demo1.log -Xms128m -Xmx128m com.yins.test.week02.GCLogAnalysis
```
Java HotSpot(TM) 64-Bit Server VM (25.171-b11) for windows-amd64 JRE (1.8.0_171-b11), built on Mar 28 2018 16:06:12 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 12484812k(3667552k free), swap 22058784k(6415808k free)

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC

2021-03-23T10:42:32.350+0800: 0.118: [GC (Allocation Failure) 2021-03-23T10:42:32.350+0800: 0.118: [DefNew: 34522K->4352K(39296K), 0.0068348 secs] 34522K->11742K(126720K), 0.0070316 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]

**GC (Allocation Failure):**无法在堆上分配对象导致GC

**[DefNew: 34522K->4352K(39296K), 0.0068348 secs] ：**DefNew代表年轻代GC（YGC），从34522K->4352K，年轻代堆内存回收了34522-4352=30170

**34522K->11742K(126720K), 0.0070316 secs]：**34522K->11742K：代表整个堆内存回收了：34522-11742=22780，说明从年轻代晋升至老年代：30170-22780=7390

**[Times: user=0.00 sys=0.00, real=0.01 secs] ：**user代表本次gc垃圾回收线程消耗的cpu时间，sys代表本次gc操作系统消耗cpu时间，real代表本次gc的停顿时间

.......

2021-03-23T10:42:32.457+0800: 0.225: [GC (Allocation Failure) 2021-03-23T10:42:32.457+0800: 0.225: [DefNew: 39292K->39292K(39296K), 0.0000125 secs]2021-03-23T10:42:32.457+0800: 0.225: [Tenured: 82919K->38285K(87424K), 0.0068661 secs] 122211K->38285K(126720K), [Metaspace: 2837K->2837K(1056768K)], 0.0069479 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]

**[Tenured: 82919K->38285K(87424K), 0.0068661 secs]  ：**Tenured代表老年代GC（FGC）**，**82919K->38285K：老年代堆内存回收了82919-38285=44634

**122211K->38285K(126720K):**代表整个堆内存回收了：122211-38285=83926,说明年轻代回收了83926-44634=39292，清空了年轻代

......

```plain
java -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+UseParallelGC -XX:+PrintGCDateStamps  -Xloggc:log.dome2.log com.yins.test.week02.GCLogAnalysis
```
Java HotSpot(TM) 64-Bit Server VM (25.171-b11) for windows-amd64 JRE (1.8.0_171-b11), built on Mar 28 2018 16:06:12 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 12484812k(3910184k free), swap 22058784k(6055488k free)

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC

2021-03-23T11:23:56.135+0800: 0.136: [GC (Allocation Failure) [PSYoungGen: 33093K->5119K(38400K)] 33093K->6988K(125952K), 0.0022099 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T11:23:56.231+0800: 0.232: [Full GC (Ergonomics) [PSYoungGen: 5873K->0K(29184K)] [ParOldGen: 78498K->39250K(87552K)] 84372K->39250K(116736K), [Metaspace: 2837K->2837K(1056768K)], 0.0078514 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]

**ParallelGC 和 SerialGC类似，只是Parallel是并行，Serial是串行，所以**

real 约等于 user + system /GC线程数

```plain
java -Xms128m -Xmx128m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo3.log com.yins.test.week02.GCLogAnalysis
```
Java HotSpot(TM) 64-Bit Server VM (25.171-b11) for windows-amd64 JRE (1.8.0_171-b11), built on Mar 28 2018 16:06:12 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 12484812k(4004056k free), swap 22058784k(6367648k free)

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:MaxNewSize=44740608 -XX:MaxTenuringThreshold=6 -XX:NewSize=44740608 -XX:OldPLABSize=16 -XX:OldSize=89477120 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC

2021-03-23T14:31:21.160+0800: 0.138: [GC (Allocation Failure) 2021-03-23T14:31:21.161+0800: 0.139: [ParNew: 34894K->4339K(39296K), 0.0034038 secs] 34894K->10668K(126720K), 0.0036408 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

**ygc使用ParNew 垃圾回收器**

2021-03-23T14:31:21.337+0800: 0.315: [GC (CMS Initial Mark) [1 CMS-initial-mark: 45521K(87424K)] 50405K(126720K), 0.0001078 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.337+0800: 0.315: [CMS-concurrent-mark-start]

2021-03-23T14:31:21.338+0800: 0.316: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.338+0800: 0.316: [CMS-concurrent-preclean-start]

2021-03-23T14:31:21.338+0800: 0.316: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.338+0800: 0.316: [CMS-concurrent-abortable-preclean-start]

2021-03-23T14:31:21.342+0800: 0.320: [GC (Allocation Failure) 2021-03-23T14:31:21.342+0800: 0.320: [ParNew: 39292K->4341K(39296K), 0.0024154 secs] 84813K->59864K(126720K), 0.0024706 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.350+0800: 0.327: [GC (Allocation Failure) 2021-03-23T14:31:21.350+0800: 0.327: [ParNew: 39275K->4349K(39296K), 0.0024350 secs] 94798K->69871K(126720K), 0.0024899 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.358+0800: 0.335: [GC (Allocation Failure) 2021-03-23T14:31:21.358+0800: 0.335: [ParNew: 39293K->4350K(39296K), 0.0022843 secs] 104815K->79194K(126720K), 0.0023523 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.365+0800: 0.343: [GC (Allocation Failure) 2021-03-23T14:31:21.365+0800: 0.343: [ParNew: 39294K->4351K(39296K), 0.0037157 secs] 114138K->89225K(126720K), 0.0037736 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.369+0800: 0.346: [CMS-concurrent-abortable-preclean: 0.001/0.031 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]

2021-03-23T14:31:21.369+0800: 0.347: [GC (CMS Final Remark)

[YG occupancy: 4639 K (39296 K)]

2021-03-23T14:31:21.369+0800: 0.347: [Rescan (parallel) , 0.0000964 secs]

2021-03-23T14:31:21.369+0800: 0.347: [weak refs processing, 0.0000074 secs]

2021-03-23T14:31:21.369+0800: 0.347: [class unloading, 0.0002544 secs]

2021-03-23T14:31:21.370+0800: 0.347: [scrub symbol table, 0.0003082 secs]

2021-03-23T14:31:21.370+0800: 0.347: [scrub string table, 0.0000816 secs]

[1 CMS-remark: 84874K(87424K)] 89513K(126720K), 0.0007936 secs]

[Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.370+0800: 0.347: [CMS-concurrent-sweep-start]

2021-03-23T14:31:21.370+0800: 0.348: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T14:31:21.370+0800: 0.348: [CMS-concurrent-reset-start]

2021-03-23T14:31:21.370+0800: 0.348: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

**CMS-initial-mark：**（STW）初始标记、只标记GC Root能直接关联的对象

**CMS-concurrent-mark：**并发标记，跟运行线程同时进行，进行GC Roots Tracing

**CMS-concurrent-preclean**：并发预清理，记录并发标记阶段，发生改变的对象

**CMS-concurrent-abortable-preclean：**可取消的并发预清理，尝试在Final Remark之前多做一些工作

**Final Remark：**（STW）最终标记，将修正并发标记中发生变动的对象引用

**[Rescan (parallel) , 0.0000964 secs]：**重新扫描

**[weak refs processing, 0.0000074 secs]：**处理弱引用

**[class unloading, 0.0002544 secs]：**卸载类

**[scrub symbol table, 0.0003082 secs]：**处理符号表

**[scrub string table, 0.0000816 secs]：**清理内联字符串对应的string tables

**CMS-concurrent-sweep：**并发清理不再使用的对象

**CMS-concurrent-reset：**并发重置

```plain
java -Xms128m -Xmx128m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo4.log com.yins.test.week02.GCLogAnalysis
```
Java HotSpot(TM) 64-Bit Server VM (25.171-b11) for windows-amd64 JRE (1.8.0_171-b11), built on Mar 28 2018 16:06:12 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 12484812k(4576452k free), swap 22058784k(6328940k free)

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation

2021-03-23T11:44:22.556+0800: 0.250: [GC pause (G1 Evacuation Pause) (young), 0.0097719 secs]

[Parallel Time: 9.6 ms, GC Workers: 4]

[GC Worker Start (ms): Min: 249.7, Avg: 249.7, Max: 249.8, Diff: 0.1]

[Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.1, Sum: 0.7]

[Update RS (ms): Min: 0.0, Avg: 2.3, Max: 9.3, Diff: 9.3, Sum: 9.3]

[Processed Buffers: Min: 0, Avg: 0.5, Max: 1, Diff: 1, Sum: 2]

[Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[Object Copy (ms): Min: 0.1, Avg: 0.7, Max: 1.0, Diff: 0.9, Sum: 2.9]

[Termination (ms): Min: 0.0, Avg: 6.3, Max: 8.4, Diff: 8.4, Sum: 25.2]

[Termination Attempts: Min: 1, Avg: 17.0, Max: 28, Diff: 27, Sum: 68]

[GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[GC Worker Total (ms): Min: 9.5, Avg: 9.5, Max: 9.6, Diff: 0.1, Sum: 38.2]

[GC Worker End (ms): Min: 259.3, Avg: 259.3, Max: 259.3, Diff: 0.0]

[Code Root Fixup: 0.0 ms]

[Code Root Purge: 0.0 ms]

[Clear CT: 0.0 ms]

[Other: 0.1 ms]

[Choose CSet: 0.0 ms]

[Ref Proc: 0.0 ms]

[Ref Enq: 0.0 ms]

[Redirty Cards: 0.0 ms]

[Humongous Register: 0.0 ms]

[Humongous Reclaim: 0.0 ms]

[Free CSet: 0.0 ms]

[Eden: 10.0M(10.0M)->0.0B(74.0M) Survivors: 1024.0K->2048.0K Heap: 14.0M(128.0M)->7161.8K(128.0M)]

[Times: user=0.00 sys=0.00, real=0.01 secs]

**[GC pause (G1 Evacuation Pause) (young), 0.0097719 secs]**– G1转移暂停，纯

年轻代模式; 只清理年轻代空间。这次暂停在JVM启动之后 181 ms 开始，持续的系统时间为

0.0097719 秒 ，也就是 9.7ms 。

**[Parallel Time: 9.6 ms, GC Workers: 4]******– 表明后面的活动由8个 Worker 线程并行执

行，消耗时间为9.6毫秒(real time)

**[Code Root Fixup: 0.0 ms]**– 释放用于管理并行活动的内部数据，一般都接近于零。这个 过程是串行执行的。

**[Code Root Purge: 0.0 ms]**– 清理其他部分数据，也是非常快的，如非必要基本上等于零。也是串行执行的过程。

**[Other: 0.1 ms]**– 其他活动消耗的时间，其中大部分是并行执行的。

**[Eden: 10.0M(10.0M)->0.0B(74.0M)**– 暂停之前和暂停之后，Eden 区的使用量/总容量。

**Survivors: 1024.0K->2048.0K**– GC暂停前后，存活区的使用量。

**Heap: 14.0M(128.0M)->7161.8K(128.0M)]**– 暂停前后，整个堆内存的使用量与总容量。

**[Times: user=0.00 sys=0.00, real=0.01 secs]**– GC事件的持续时间。

**[GC Worker Start (ms): Min: 249.7, Avg: 249.7, Max: 249.8, Diff: 0.1]**– GC的worker线程开始启动时，相对于 pause 开始时间的毫秒间隔。如果 Min 和 Max 差别很大，则表明本机其他进程所使用的线程数量过多，挤占了GC的可用 CPU时间。

**[Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.1, Sum: 0.7]**– 用了多长时间来扫描堆外内存(non-heap)的 GC ROOT，如 classloaders，JNI引用，JVM系统ROOT等。后面显示了运行时间，“Sum” 指的是CPU时间。

**[Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]**– 扫描实际代码中的 root 用了多长时间：例如线程栈中的局部变量。

**[Object Copy (ms): Min: 0.1, Avg: 0.7, Max: 1.0, Diff: 0.9, Sum: 2.9]**– 用了多长时间来拷贝回收集中的存活对象。

**[Termination (ms): Min: 0.0, Avg: 6.3, Max: 8.4, Diff: 8.4, Sum: 25.2]**– GC的worker线程用了多长时间来确保自身可以安全地停止，在这段时间内什么也不做，完成后GC线程就终止运行了，所以叫终止等待时间。

**[Termination Attempts: Min: 1, Avg: 17.0, Max: 28, Diff: 27, Sum: 68]**– GC的worker 线程尝试多少次 try 和 teminate。如果worker发现还有一些任务没处理完，则这一次尝试就是失败的，暂时还不能终止

**[GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]**– 其他的小任务， 因为时间很短，在GC日志将他们归结在一起。

**[GC Worker Total (ms): Min: 9.5, Avg: 9.5, Max: 9.6, Diff: 0.1, Sum: 38.2]**– GC的worker 线程工作时间总计。

**[GC Worker End (ms): Min: 259.3, Avg: 259.3, Max: 259.3, Diff: 0.0]**– GC的worker 线程完成作业时刻，相对于此次GC暂停开始时间的毫秒数。通常来说这部分数字应该大致相等，否则就说明有太多的线程被挂起，很可能是因为“坏邻居效 应(noisy neighbor)" 所导致的。

2021-03-23T11:44:22.632+0800: 0.325: [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0026086 secs]

[Parallel Time: 2.4 ms, GC Workers: 4]

[GC Worker Start (ms): Min: 324.9, Avg: 325.0, Max: 325.0, Diff: 0.1]

[Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.1, Sum: 0.7]

[Update RS (ms): Min: 0.1, Avg: 0.1, Max: 0.1, Diff: 0.0, Sum: 0.3]

[Processed Buffers: Min: 1, Avg: 2.0, Max: 3, Diff: 2, Sum: 8]

[Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[Object Copy (ms): Min: 2.0, Avg: 2.0, Max: 2.1, Diff: 0.1, Sum: 8.2]

[Termination (ms): Min: 0.0, Avg: 0.0, Max: 0.1, Diff: 0.1, Sum: 0.2]

[Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]

[GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[GC Worker Total (ms): Min: 2.3, Avg: 2.4, Max: 2.4, Diff: 0.1, Sum: 9.5]

[GC Worker End (ms): Min: 327.3, Avg: 327.3, Max: 327.3, Diff: 0.0]

[Code Root Fixup: 0.0 ms]

[Code Root Purge: 0.0 ms]

[Clear CT: 0.0 ms]

[Other: 0.2 ms]

[Choose CSet: 0.0 ms]

[Ref Proc: 0.0 ms]

[Ref Enq: 0.0 ms]

[Redirty Cards: 0.0 ms]

[Humongous Register: 0.0 ms]

[Humongous Reclaim: 0.0 ms]

[Free CSet: 0.0 ms]

[Eden: 42.0M(50.0M)->0.0B(44.0M) Survivors: 7168.0K->8192.0K Heap: 96.5M(128.0M)->53.0M(128.0M)]

[Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T11:44:22.635+0800: 0.328: [GC concurrent-root-region-scan-start]

2021-03-23T11:44:22.635+0800: 0.328: [GC concurrent-root-region-scan-end, 0.0006983 secs]

2021-03-23T11:44:22.635+0800: 0.328: [GC concurrent-mark-start]

2021-03-23T11:44:22.636+0800: 0.329: [GC concurrent-mark-end, 0.0009950 secs]

2021-03-23T11:44:22.636+0800: 0.329: [GC remark 2021-03-23T11:44:22.636+0800: 0.329: [Finalize Marking, 0.0001166 secs] 2021-03-23T11:44:22.637+0800: 0.330: [GC ref-proc, 0.0000764 secs] 2021-03-23T11:44:22.637+0800: 0.330: [Unloading, 0.0004865 secs], 0.0008922 secs]

[Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T11:44:22.637+0800: 0.330: [GC cleanup 65M->61M(128M), 0.0004134 secs]

[Times: user=0.00 sys=0.00, real=0.00 secs]

2021-03-23T11:44:22.638+0800: 0.331: [GC concurrent-cleanup-start]

2021-03-23T11:44:22.638+0800: 0.331: [GC concurrent-cleanup-end, 0.0000241 secs]

2021-03-23T11:44:22.648+0800: 0.341: [GC pause (G1 Evacuation Pause) (mixed), 0.0018018 secs]

[Parallel Time: 1.6 ms, GC Workers: 4]

[GC Worker Start (ms): Min: 341.2, Avg: 341.2, Max: 341.3, Diff: 0.1]

[Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.0, Sum: 0.6]

[Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.1, Diff: 0.1, Sum: 0.1]

[Processed Buffers: Min: 0, Avg: 1.3, Max: 2, Diff: 2, Sum: 5]

[Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.1, Diff: 0.1, Sum: 0.2]

[Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[Object Copy (ms): Min: 1.1, Avg: 1.2, Max: 1.2, Diff: 0.1, Sum: 4.7]

[Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.4]

[Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]

[GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]

[GC Worker Total (ms): Min: 1.5, Avg: 1.5, Max: 1.6, Diff: 0.1, Sum: 6.1]

[GC Worker End (ms): Min: 342.7, Avg: 342.7, Max: 342.8, Diff: 0.1]

[Code Root Fixup: 0.0 ms]

[Code Root Purge: 0.0 ms]

[Clear CT: 0.0 ms]

[Other: 0.2 ms]

[Choose CSet: 0.0 ms]

[Ref Proc: 0.1 ms]

[Ref Enq: 0.0 ms]

[Redirty Cards: 0.0 ms]

[Humongous Register: 0.0 ms]

[Humongous Reclaim: 0.0 ms]

[Free CSet: 0.0 ms]

[Eden: 3072.0K(3072.0K)->0.0B(40.0M) Survivors: 7168.0K->1024.0K Heap: 71.8M(128.0M)->56.7M(128.0M)]

[Times: user=0.00 sys=0.00, real=0.00 secs]

并发标记完成之后，G1将执行一次混合收集(mixed collection)，不只清理年轻代，还将一部分老年代区域也加入到 collection set 中。

当MixGC赶不上对象产生的速度的时候就退化成Full GC，这一点是需要重点调优的地方。



