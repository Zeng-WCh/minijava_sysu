<center><font size=5>EX 3</font></center>
<center>20308003 曾伟超</center>

### 配置试运行

由于之前有使用过 GNU Bison，对于这类工具较为熟悉，文件中的 `calc.cup` 是提供的例子，编写的 `gen.sh` 可以用来测试，通过输入 `./gen.sh calc.cup`，会自动生成对应的 `parser` 和 `symbol` 文件到 `src` 目录下，这里就不再赘述

### 编写文法

具体见 `src/oberon.cup`，这里由于与 JavaCUP 进行协作，对配置进行了修改，例如不再需要手动定义符号，JavaCUP 会自行的生成一个 `symbol` 的文件用于用作符号的定义；同时参考文档，其在 `Symbol` 实例化的时候采取了工厂模式，即不应该手动的 `new Symbol`，而是通过一个 `symbolFactory.newSymbol` 的方式来进行实例化，这点可以在 `oberon.flex` 中看到，同时由于 `Symbol` 和 `java_cup.runtime.Symbol` 重名，所以在 `oberon.flex` 中使用了 `java_cup.runtime.Symbol` 的方式来进行引入，同时由于要实现工厂模式，需要在 `oberon.flex` 中将其作为 `Symbol` 的实现，同时也需要在 JavaCUP 中进行配置，即通过 `-interface` 的方式，将 `Symbol` 生成为一个 `interface` 接口类，具体可以见 `gen.sh` 里对于 `cup` 的处理

同时，为了能够实现更为精准的语法报错，需要在 `cup` 文件中预先定义一些常见的错误，这样可以能够更加精确的进行错误的分类和处理，而对于语义错误，这里暂时不在 `cup` 中进行分析，而是先通过语法分析构建 `AST` (抽象语法树)，在后面的时候进行语义的分析，这样做可以更加精细化的处理语义的错误

### 比较生成工具

GNU Bison 也是之前曾经有使用过，对其稍有了解，其和 JavaCUP 一样，是一个 LALR(1) 的语法分析器生成工具，但是在文档中，GNU Bison 还可以支持生成其它更加复杂的语法分析器，比如 LR(1)、GLR、IELR、SLR 等等，但是这些都是在文档中提到的，我本身在之前只用其生成过 LALR(1) 的语法分析器，对于其它的一些 LR 文法，如 GLR, IRLR 等也不太了解，同时受制于时间，这里就不再深入研究了。

这两者 (JavaCUP 和 GNU Bison) 的区别主要在于

1. JavaCUP 面向 Java 而 GNU Bison 面向 C/C++
2. 配置文件的区别，GNU Bison 的文件格式类似 flex 的文件格式，每个部分之间通过 `%%` 进行分割，也都是由定义，规则，用户自定义代码组成，而 JavaCUP 的文件则是没有显式的分割，具体的部分这里不做赘述，可以参考文档
3. 语法规则符号不用，GNU Bison 使用 `:` 来表示推导，而 JavaCUP 使用 `::=` 表示推导
4. 一些预定义符号的差别，例如 GNU Bison 中表示推导右部的第一项是通过 `$1` 来表示，而 Java CUP 则是通过给其命名，例如 `expr ::= expr: e1 PLUS expr: e2`, 这里的 `e1` 和 `e2` 就是给右部的第一项和第三项命名，这样在后面的代码中就可以通过 `e1` 和 `e2` 来进行表示

JavaCUP, GNU Bison 这两者和 JavaCC 一个最明显的差别，就是支持的文法的差别，如上面所说，JavaCUP, GNU Bison 都是一个自底向上的语法分析器生成，而 JavaCC 采取的是自顶向下的分析，其默认生成的是 LL(1) 的语法分析器，当然，在某些情况下也会生成 LL(k) 的分析，具体的情况可以参考文档；总之，最明显的差别就是在语法分析器的生成上，JavaCC 生成的是 LL(1) 的语法分析器，而 JavaCUP, GNU Bison 生成的是 LALR(1) 的语法分析器，自然所接受的输入文件也是有差别的，JavaCC 所能接受的最多为 LL(k) 的文法，而 JavaCUP, GNU Bison 所能接受的包括 LALR(1) 的文法，这也是为什么 JavaCC 生成的语法分析器比较简单，而 JavaCUP, GNU Bison 生成的语法分析器比较复杂的原因，这和理论课上所学的相一致

### 参考文档

1. GNU Bison Introduction: https://www.gnu.org/software/bison/manual/html_node/Introduction.html

2. Java CC Introduction: https://javacc.github.io/javacc/#introduction

3. Java CUP Documents: http://www2.cs.tum.edu/projects/cup/docs.php