这个是从规则中心抽取出来的东西，在runtime动态编译java代码，并用URLClassLoader加载并进行新的java代码。

通过URLClassLoader的隔离性，实现同一个类多个版本的隔离，以及新功能的发布。


/**
 * 知识点1：
 * 对与多个定义的URLClassLoader，如果一个URLClassLoader加载了一个class；第二URLClassLoader如果再次尝试加载这个类,<br>
 * 会重新加载这个类，而不是复用前面的类定义。原因是第一个URLClassLoader的类空间对第二个URLClassLoader不可见，具体和ClassLoader<br>
 * 的加载机制有关（parent优先）.Root Bootstrap(null)--> Extension ExtClassLoader(sun.misc.Launcher$ExtClassLoader)--><br>
 * -->System AppClassLoader（sun.misc.Launcher$AppClassLoader）--> User defined ClassLoader<br>
 * 
 * 知识点2：
 * URLClassLoader显示主动加载一个类，不会自动加载这个类的内部类；在后续用到的时候，会通过这个URLClassLoader自动加载内部类<br>
 * 
 * @author mike
 * @version $Id: TestJavaDynamicCompile.java, v 0.1 2014年6月12日 上午9:10:50 mike Exp $
 */
 
 