Java 8 新特性

Java 8 (又称为 jdk 1.8) 是 Java 语言开发的一个主要版本。 Oracle 公司于 2014 年 3 月 18 日发布 Java 8 ，它支持函数式编程，新的 JavaScript 引擎，新的日期 API，新的Stream API 等。
新特性

Java8 新增了非常多的特性，我们主要讨论以下几个：

一、 Lambda 表达式 − Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中。

二、 方法引用 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

三、默认方法 − 默认方法就是一个在接口里面有了一个实现的方法。

四、新工具 − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。

五、Stream API −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。

六、Date Time API − 加强对日期与时间的处理。

七、Optional 类 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。

八、Nashorn, JavaScript 引擎 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。

一、Java 8 Lambda 表达式

Java 8 新特性 Java 8 新特性

Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。

Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。

使用 Lambda 表达式可以使代码变的更加简洁紧凑。

语法

lambda 表达式的语法格式如下：

(parameters) -> expression

或

(parameters) ->{ statements; }

以下是lambda表达式的重要特征:

可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。

可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。

可选的大括号：如果主体包含了一个语句，就不需要使用大括号。

可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值
