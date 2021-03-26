**测试环境：win10系统  cpu 4核4线程 内存12g 测试工具SuperBenchmarker**

**测试服务对象为专网服务器首页**

**测试结果并不如预期，推测内网延迟是影响RPS的关键原因**

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 1 -N 10

Starting at 2021-3-26 17:05:11

[Press C to stop the test]

187     (RPS: 12.8)

---------------Finished!----------------

Finished at 2021-3-26 17:05:25 (took 00:00:14.7196432)

Status 200:    187

RPS: 16.9 (requests/second)

Max: 98ms

Min: 35ms

Avg: 48.1ms

50%   below 47ms

60%   below 49ms

70%   below 50ms

80%   below 52ms

90%   below 55ms

95%   below 59ms

98%   below 68ms

99%   below 81ms

99.9%   below 98ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 2 -N 10

Starting at 2021-3-26 17:05:35

[Press C to stop the test]

316     (RPS: 21.8)

---------------Finished!----------------

Finished at 2021-3-26 17:05:50 (took 00:00:14.5820141)

Status 200:    316

RPS: 28.4 (requests/second)

Max: 123ms

Min: 35ms

Avg: 59.3ms

50%   below 60ms

60%   below 62ms

70%   below 65ms

80%   below 68ms

90%   below 74ms

95%   below 78ms

98%   below 92ms

99%   below 99ms

99.9%   below 123ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 3 -N 10

Starting at 2021-3-26 17:06:08

[Press C to stop the test]

486     (RPS: 33.6)

---------------Finished!----------------

Finished at 2021-3-26 17:06:22 (took 00:00:14.5191724)

487     (RPS: 33.6)                     Status 200:    487

RPS: 44.1 (requests/second)

Max: 161ms

Min: 27ms

Avg: 59ms

50%   below 53ms

60%   below 57ms

70%   below 63ms

80%   below 68ms

90%   below 84ms

95%   below 107ms

98%   below 118ms

99%   below 129ms

99.9%   below 161ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 4 -N 10

Starting at 2021-3-26 17:06:31

[Press C to stop the test]

583     (RPS: 39.7)

---------------Finished!----------------

Finished at 2021-3-26 17:06:45 (took 00:00:14.7365973)

Status 200:    583

RPS: 52.6 (requests/second)

Max: 214ms

Min: 28ms

Avg: 66.1ms

50%   below 62ms

60%   below 67ms

70%   below 73ms

80%   below 78ms

90%   below 89ms

95%   below 100ms

98%   below 127ms

99%   below 149ms

99.9%   below 214ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 5 -N 10

Starting at 2021-3-26 17:06:53

[Press C to stop the test]

543     (RPS: 37)

---------------Finished!----------------

Finished at 2021-3-26 17:07:07 (took 00:00:14.6887202)

544     (RPS: 37.1)                     Status 200:    544

RPS: 49.2 (requests/second)

Max: 259ms

Min: 48ms

Avg: 89.1ms

50%   below 82ms

60%   below 89ms

70%   below 97ms

80%   below 107ms

90%   below 122ms

95%   below 135ms

98%   below 170ms

99%   below 185ms

99.9%   below 259ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 6 -N 10

Starting at 2021-3-26 17:07:16

[Press C to stop the test]

541     (RPS: 36.6)

---------------Finished!----------------

Finished at 2021-3-26 17:07:31 (took 00:00:14.8213952)

545     (RPS: 36.9)                     Status 200:    545

RPS: 49.3 (requests/second)

Max: 316ms

Min: 57ms

Avg: 107.9ms

50%   below 99ms

60%   below 108ms

70%   below 118ms

80%   below 129ms

90%   below 149ms

95%   below 169ms

98%   below 210ms

99%   below 223ms

99.9%   below 316ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 7 -N 10

Starting at 2021-3-26 17:07:40

[Press C to stop the test]

549     (RPS: 38.3)

---------------Finished!----------------

Finished at 2021-3-26 17:07:54 (took 00:00:14.3496259)

553     (RPS: 38.6)                     Status 200:    553

RPS: 49.9 (requests/second)

Max: 421ms

Min: 46ms

Avg: 122.9ms

50%   below 111ms

60%   below 121ms

70%   below 131ms

80%   below 147ms

90%   below 174ms

95%   below 208ms

98%   below 238ms

99%   below 265ms

99.9%   below 421ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 8 -N 10

Starting at 2021-3-26 17:08:00

[Press C to stop the test]

576     (RPS: 40.2)

577     (RPS: 40.2)                     ---------------Finished!----------------

Finished at 2021-3-26 17:08:14 (took 00:00:14.3935083)

581     (RPS: 40.4)                     Status 200:    581

RPS: 52.6 (requests/second)

Max: 444ms

Min: 59ms

Avg: 135ms

50%   below 125ms

60%   below 132ms

70%   below 142ms

80%   below 156ms

90%   below 189ms

95%   below 224ms

98%   below 254ms

99%   below 311ms

99.9%   below 444ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 16 -N 10

Starting at 2021-3-26 17:08:28

[Press C to stop the test]

569     (RPS: 39.7)

---------------Finished!----------------

Finished at 2021-3-26 17:08:42 (took 00:00:14.3586116)

576     (RPS: 40.2)                     Status 200:    576

RPS: 51.9 (requests/second)

Max: 717ms

Min: 122ms

Avg: 277.6ms

50%   below 263ms

60%   below 274ms

70%   below 287ms

80%   below 305ms

90%   below 355ms

95%   below 438ms

98%   below 504ms

99%   below 551ms

99.9%   below 717ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 32 -N 10

Starting at 2021-3-26 17:08:56

[Press C to stop the test]

576     (RPS: 40.3)

---------------Finished!----------------

Finished at 2021-3-26 17:09:10 (took 00:00:14.3137507)

605     (RPS: 42.3)                     Status 200:    605

RPS: 54.7 (requests/second)

Max: 1278ms

Min: 250ms

Avg: 536.9ms

50%   below 510ms

60%   below 538ms

70%   below 562ms

80%   below 601ms

90%   below 662ms

95%   below 790ms

98%   below 905ms

99%   below 939ms

99.9%   below 1278ms

C:\Users\yins>sb -u[http://10.1.0.116:8091/platform/index](http://10.1.0.116:8091/platform/index?fileGuid=9k38jWQWJgTp6T8v)-c 4 -N 10

Starting at 2021-3-26 17:09:16

[Press C to stop the test]

565     (RPS: 39.7)

---------------Finished!----------------

Finished at 2021-3-26 17:09:30 (took 00:00:14.2738470)

Status 200:    565

RPS: 51.1 (requests/second)

Max: 183ms

Min: 36ms

Avg: 68.2ms

50%   below 64ms

60%   below 69ms

70%   below 74ms

80%   below 80ms

90%   below 93ms

95%   below 102ms

98%   below 132ms

99%   below 155ms

99.9%   below 183ms

















































































































































































# 





