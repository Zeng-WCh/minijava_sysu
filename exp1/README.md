<center>编译原理实验</center>

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

### 目录结构

```shell
.
├── class
│   ├── Postfix
│   │   ├── Lexer.class
│   │   ├── Parser.class
│   │   ├── Postfix.class
│   │   └── Token.class
│   └── PostfixRecursion
│       ├── Lexer.class
│       ├── Parser.class
│       ├── PostfixRecursion.class
│       └── Token.class
├── doc
├── README.md
├── result
│   └── time
│       ├── nonrecursion
│       │   └── 1000
│       │		│
│       │── recursion
│       │   └── *.time
│       │── *.png
│       └── result.txt
├── scripts
│   ├── autotest.py
│   ├── autotimetest.py
│   ├── build.sh
│   ├── doc.sh
│   ├── generate.py
│   ├── run.sh
│   └── timetest.py
├── src
│   ├── Lexer.java
│   ├── Postfix.java
│   ├── PostfixRecursion.java
│   └── Token.java
└── test
    ├── infix
    │   └── *.in
    └── postfix
        └── *.ans
```

目录结构整体与之前的类似

`script`: 存放脚本，由于本次实验存在递归与非递归两种版本的对比，所以 `build.sh` ， `run.sh` 以及 `doc.sh` 都做了修改，需要指明运行的版本，例如，如果需要运行非递归版本，则 `./run.sh Postfix` ，而递归版本则是 `./run.sh PostfixRecursion` 编译脚本同理。而其他的脚本，按照使用顺序进行介绍：

- `generate.py` 用来生成测试样例以及对应后缀表达式的，会将生成的测试写入 `test/infix/*.in` 中，将对应结果写入 `test/postfix/*.ans` 中。其运行时可接受一个参数用于表示生成测试例的长度，例如 `./generate.py 3000` 则会生成含有 3000 个数字的中缀表达式
- `autotest.py` 用来进行标准化测试，其可以接受一个参数，用来指定测试的运行程序，例如 `./autotest.py PostfixRecursion` 则表示对递归版本程序运行标准测试，而不指定情况下默认运行非递归版本，测试前该脚本会自行使用会使用 `build.sh` 脚本重新编译对应程序
- `timetest.py` 用来记录递归和非递归版本耗时的脚本，其会和代码中的一些设计有关，其可以接受一个参数，用来表示测试所用案例的长度，该参数会直接传递给 `./generate.py` 运行，默认为 3500，并会将两个版本的用时分别写入 `result/time/{TESTLEN}/nonrecursion/*.time` 或者是 `result/time/{TESTLEN}/recursion/*.time` 中，根据对应的程序
- `autotimetest.py` 会调用 `timetest.py` 来做不同长度下的用时对比，并会将结果输出到 `result/time/result.txt` 中，其会比较不同长度用例下的平均用时和中间值，同时导出用时的结果图，具体在后面会做详细的介绍

### 类说明

#### Token 类

`Token` 是作为一个枚举类，写在 `Token.java` 中，目的是为了指示不同的 `Token`，例如 `tok_num` 表示的是数字，`tok_plus` 表示的是 `+` 等

#### Lexer 类

`Lexer` 即为词法分析器，其会不断从标准输入流中不断读入字符，并根据字符来针对性的返回所得到的 `Token` 的一个类

#### Postfix 类

使用非递归的方法做的语法解析器以及对应的程序入口类

#### PostfixRecursion 类

使用递归的方法实现的语法解析器以及对应的程序入口

### 实验记录

#### 1. 静态成员和非静态成员

在这个实验中，无论是否删去 `static` 都不会影响程序的正确性，都能够正确的解析输入的表达式并输出，这是因为，在整个程序运行过程中，都只有一个 `Parser` 实例，不存在多个 `Parser` ，静态成员和非静态成员最主要的区别就在于，静态成员是类所共享的，而非静态成员则是实例独占的，由于在程序中只存在一个 `Parser` 实例，因而在这个程序中，静态或者是非静态并不会对程序正确性造成影响。

如何选择声明为静态还是非静态取决于程序的设计，例如一个变量需要所有类共享，或者需要在没有实例的情况下被引用，那么设计为静态则是更好的。

在这里，`Lexer` 类中保留了 `lastChar` (即给定代码的 `lookahead`) 为静态的设定，因为在语法解析中，`Parser` 的后续是构建一个语法分析树，需要用到 `Lexer` 中读到的数据，因而保留了静态的设定

