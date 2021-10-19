/**
 * @Author: liji
 * @Date: 2021-10-19 19:15:45
 * @Update:
 * @Description:
 */

/**
 * jar包:打包;把分散的.class文件集合在一起;是一个压缩文件.
 * java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello//在.;bin;shared三个下边搜索Hello类
 * java -cp ./hello.jar abc.xyz.Hello//jvm自动在hello.jar中搜索某个类
 * 如何建立jar包:把包变成zip,把zip改成jar即可.或者java -jar hello.jar
 */
//import 包名.类名
public class studyClass {


    public static void main(String[] args) {//这是一个方法.
        System.out.println("hello");
        Person ming = new Person();//class变成instance了.
        //int n =10;//int n 只能放在这里.
        //System.out.println(n);
        ming.name = "Xiao Ming"; // 对字段name赋值
        ming.age = 12; // 对字段age赋值
        System.out.println(ming.name); // 访问字段name

        Person hong = new Person();
        hong.name = "Xiao Hong";
        hong.age = 15;
    }

}
class Person {
    public String name="dd";
    //name = "dd";
    //private String name;//私有,拒绝外部访问.但是内部能访问
    public int age;
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("invalid name");
        }
        this.name = name.strip(); // 去掉首尾空格
    }
    public Person(String name, int age) {//构造方法.
        this.name = name;
        this.age = age;
    }
    //以下为多构造方法
    public Person(String name) {
        this(name, 18); // 调用另一个构造方法Person(String, int)
    }

    public Person() {
        this("Unnamed"); // 调用另一个构造方法Person(String)
    }
    //方法重载:方法名一样,参数不一样.overload.返回值类型通常是相同的.

}
class Book {
    public String name;
    public String author;
    public String isbn;
    public double price;
}
class Student extends Person {//继承Person;无法访问父类的private.可以访问protected.
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:
    private int score;
    //static理解成全局共享变量.调用实例方法必须通过一个实例变量，而调用静态方法则不需要实例变量，通过类名就可以调用
    //我在python用过类似的东西.
    public String hello() {
        return "Hello, " + super.name;//super即Person.
    }
    public Student(String name, int age, int score) {
        super(name,age); // 自动调用父类的构造方法;即Person(name,age)
        this.score = score;
    }

}
//可以用student_good和book组合
class Student_good extends Person {
    protected Book book;
    protected int score;
}
/**
 * 加上@Override可以让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错。
 * public class Student extends Person {
 *     @Override // Compile error!
 *     public void run(String s) {}
 * }
 */