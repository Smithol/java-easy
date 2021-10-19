/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liji
 * @Date: 2021/10/19/19:24
 * @Description:
 */
import java.util.ArrayList;
import java.util.List;
//Integer 之类的在java.lang.Integer中
public class fanXing {
    public static void main(String[] args) {
        // 无编译器警告:
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
// 无强制转型:
        String first = list.get(0);
        String second = list.get(1);
        //number
        /*关于泛型的一些东西.就是把T换成想换的东西.
        List<Number> list2 = new ArrayList<Number>();
        list.add(new Integer(123));
        list.add(new Double(12.34));
        Number first = list.get(0);
        Number second = list.get(1);
        List<Number> list = new ArrayList<Number>();
        // 可以省略后面的Number，编译器可以自动推断泛型类型：
        List<Number> list = new ArrayList<>();

         */
    }
}
