<center><font size = 5>编译原理实验</font></center>

<center>20308003 曾伟超</center>

### 运行环境

系统：Debian 11 (bullseye)

Java 环境：

```shell
openjdk 11.0.18 2023-01-17
OpenJDK Runtime Environment (build 11.0.18+10-post-Debian-1deb11u1)
OpenJDK 64-Bit Server VM (build 11.0.18+10-post-Debian-1deb11u1, mixed mode)
```

Java 编译器：

```shell
javac 11.0.18
```

### 文件结构

### 实验结论

#### 1. 静态成员和非静态成员

在这个实验中，无论是否删去 `static` 都不会影响程序的正确性，都能够正确的解析输入的表达式并输出，这是因为，在整个程序运行过程中，都只有一个 `Parser` 实例，不存在多个 `Parser` ，静态成员和非静态成员最主要的区别就在于，静态成员是类所共享的，而非静态成员则是实例独占的，由于在程序中只存在一个 `Parser` 实例，因而在这个程序中，静态或者是非静态并不会对程序正确性造成影响。

如何选择声明为静态还是非静态取决于程序的设计，例如一个变量需要所有类共享，或者需要在没有实例的情况下被引用，那么设计为静态则是更好的，在这里，保留了静态的设定，因为在语法解析中，`Parser` 的后续是构建一个语法分析树，需要用到 `Parser` 中读到的数据，因而保留了静态的设定

#### 2. 消除尾递归

首先先是对源程序做测试，为了方便生成一个较大的表达式，使用了 `scripts/generate.py` 来生成

```java
void rest() throws IOException {
    if (lookahead == '+') {
        match('+');
        term();
        System.out.write('+');
        rest();
    } else if (lookahead == '-') {
        match('-');
        term();
        System.out.write('-');
        rest();
    } else {
        // do nothing with the input
    }
}
```

以上是存在尾递归的

之后进行改写，如下

```java
void rest() throws IOException {
    while (lookahead == '+' || lookahead == '-') {
        if (lookahead == '+') {
            match('+');
            term();
            System.out.write('+');
        } else if (lookahead == '-') {
            match('-');
            term();
            System.out.write('-');
        } else {
            // do nothing with the input
        }
    }
}
```

首先使用尾递归写法，进行测试，使用的测试脚本为 `scripts/recursive.sh`，得到的案例时间为

```shell

real	0m0.073s
user	0m0.076s
sys	0m0.000s

real	0m0.067s
user	0m0.071s
sys	0m0.013s

real	0m0.056s
user	0m0.070s
sys	0m0.000s

real	0m0.066s
user	0m0.071s
sys	0m0.010s

real	0m0.098s
user	0m0.062s
sys	0m0.052s

real	0m0.054s
user	0m0.059s
sys	0m0.009s

real	0m0.062s
user	0m0.078s
sys	0m0.000s

real	0m0.054s
user	0m0.058s
sys	0m0.010s

real	0m0.077s
user	0m0.096s
sys	0m0.000s

real	0m0.065s
user	0m0.062s
sys	0m0.020s

```

之后是消除尾递归的，时间如下

```shell

real	0m0.060s
user	0m0.062s
sys	0m0.012s

real	0m0.058s
user	0m0.073s
sys	0m0.000s

real	0m0.063s
user	0m0.063s
sys	0m0.018s

real	0m0.067s
user	0m0.069s
sys	0m0.011s

real	0m0.056s
user	0m0.063s
sys	0m0.008s

real	0m0.053s
user	0m0.050s
sys	0m0.016s

real	0m0.054s
user	0m0.050s
sys	0m0.016s

real	0m0.055s
user	0m0.060s
sys	0m0.008s

real	0m0.060s
user	0m0.065s
sys	0m0.009s

real	0m0.062s
user	0m0.064s
sys	0m0.016s

real	0m0.065s
user	0m0.075s
sys	0m0.009s

real	0m0.062s
user	0m0.081s
sys	0m0.000s

real	0m0.060s
user	0m0.068s
sys	0m0.010s

real	0m0.056s
user	0m0.060s
sys	0m0.010s

real	0m0.055s
user	0m0.068s
sys	0m0.000s

real	0m0.054s
user	0m0.068s
sys	0m0.000s

real	0m0.053s
user	0m0.066s
sys	0m0.000s

real	0m0.052s
user	0m0.044s
sys	0m0.022s

real	0m0.053s
user	0m0.059s
sys	0m0.008s

real	0m0.058s
user	0m0.061s
sys	0m0.011s

```

在这里，我们主要关注的是 `user` 行，表示了程序在用户态运行所用的时间，因为主要的 `parse` 并不涉及到内核态（虽然 I/O 操作需要切换到内核态），但是递归阶段的 I/O 并不是主要的，因而比较 `user` 是可行的，从而，得到如下的表格

| 序号 | 递归 | 非递归 |
| :--: | :----------: | :--------------: |
| 1 | 0.047s | 0.062s |
| 2 | 0.060s | 0.073s |
| 3 | 0.055s | 0.063s |
| 4 | 0.058s | 0.069s |
| 5 | 0.041s | 0.063s |
| 6 | 0.050s | 0.050s |
| 7 | 0.050s | 0.050s |
| 8 | 0.054s | 0.060s |
| 9 | 0.057s | 0.065s |
| 10 | 0.059s | 0.064s |
| 11 | 0.055s | 0.075s |
| 12 | 0.074s | 0.081s |
| 13 | 0.055s | 0.068s |
| 14 | 0.059s | 0.060s |
| 15 | 0.067s | 0.068s |
| 16 | 0.067s | 0.068s |
| 17 | 0.067s | 0.066s |
| 18 | 0.060s | 0.044s |
| 19 | 0.046s | 0.059s |
| 20 | 0.052s | 0.061s |

从上面的数据可以看到，消除尾递归后的用时有了显著的改进，大部分样例用时都显著缩短了

从理论上分析，`parse` 的过程实际上需要的是对字符串从左往右进行一次遍历操作，但是在采用递归的方式中，相当于不断的对一个子串做 `parse` 动作，也就是采用了递归树的方式来做，树高为字符串的长度，每次在当前节点消耗一个字符，然后将字串传递给后续节点，但是这里两者之间的时间复杂度更多的看是常数差别，即 $O(mn), m为一个常数$ 中， $m$ 差距所带来的时间复杂度差距

#### 3. 改写程序

这里需要对程序进行改写，通过阅读代码，我们可以知道，这样的一个语法规则是
$$
expr \ :=  \ term \ rest \\
rest \ := \ + \ rest \ | \ - \ rest \\
term \ := 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
$$

主要的想法是，实现一个完整的四则运算计算器，包含加减乘除，
