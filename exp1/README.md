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

这里需要对程序进行改写，通过阅读代码，我们可以知道，这样的一个语法规则是
$$
expr \ :=  \ term \ rest \\
rest \ := \ + \ rest \ | \ - \ rest \\
term \ := 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
$$

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
}
```



