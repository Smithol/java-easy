/**
 * 这种注释类型不错
 *
 *文件名字和java类名字一致.
 *一个程序的基本单位是类.
 *
 * 基本类型:整数,小数,char,boolean;其他的都是引用,像指针了.

 */
import java.util.Arrays;
public class Hello{
    public static void main(String[] args){//快捷键是psvm.
        System.out.println("hello liji");
        int x = 1;
        System.out.println(x);//快捷键是sout.
        final double PI = 3.14; // PI是一个常量;使用final定义常量.
        StringBuffer sb = new StringBuffer();//可以简写为如下
        //var sb = new StringBuffer();
        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        // 排序前:
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] > ns[j+1]) {
                    // 交换ns[j]和ns[j+1]:
                    int tmp = ns[j];
                    ns[j] = ns[j+1];
                    ns[j+1] = tmp;
                }
            }
        }
        // 排序后:
        System.out.println(Arrays.toString(ns));
        //以下函数必须先javac Main java;然后java Main -version;才行;Main是文件名,每个人都不一样.和python一样.
        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("v 1.0");
                break;
            }
        }

    }
    public class fruit{

    }
}