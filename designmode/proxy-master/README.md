# proxy
jdk动态代理&amp;cglib动态代理

一、首先说一下JDK中的动态代理：
JDK中的动态代理是通过反射类Proxy以及InvocationHandler回调接口实现的,
但是，JDK中所要进行动态代理的类必须要实现一个接口，也就是说只能对该类所实现接口中定义的方法进行代理,
这在实际编程中具有一定的局限性，而且使用反射的效率也并不是很高。

二、使用CGLib实现：
使用CGLib实现动态代理，完全不受代理类必须实现接口的限制,
而且CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，比使用Java反射效率要高。
唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
