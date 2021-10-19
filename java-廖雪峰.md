# java study

## 安装idea和jdk

jdk的bin放在环境变量中.我的jdk`C:\jdk\jdk-16.0.1_windows-x64_bin\jdk-16.0.1\bin`.jdk:java develop;sdk:software develop;

## 设置字体大小

Setting-> Font 

## 创建package和class

其实创建class就是创建.java文件

idea需要一个out文件来存放.class文件,即编译后的文件.

idea中新建package自动带着src源文件夹,在下边建立`java class`,即.java文件.如果在src下建立新的package,再在新建的package下边新建`java class`, 就会开头加上`package 新建的类`.

导入项目`import`和`open`还是不一样的.如果导入项目就close project.参考:[idea如何import_导入项目_心有谦谦结-CSDN博客_idea import](https://blog.csdn.net/weixin_45764765/article/details/108051759).导入项目的时候可以只有src文件,如果时maven项目,就可以只有src和``pom.xml`文件.

每个项目都有一个.idea,每个包都有一个`iml`,这是普通的java项目,如果时`maven`可能还会有  `pom.xml` .

## 遇到问题:

1. > Cannot start compilation: the output path is not specified for module "happy". Specify the output path in the Project Structure dialog.

   这就需要在加上`out`文件,生成的.class文件放进去.我忘了放参考网站,问题直接csdn即可.