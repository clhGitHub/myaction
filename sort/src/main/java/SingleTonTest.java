import java.util.HashMap;

/**
 * Create by longhchen on  2021-03-09 0:27
 */
public class SingleTonTest {
    public static void main(String[] args) {
        Bank instance = Bank.getInstance();
    }


}
// 、饿汉式   线程安全
class Bank{
    //1.私有化构造器
    private Bank(){}

    //2、创建内部类对象
    private static Bank instance = new Bank();

    //3、提供公共的静态方法  ，返回内部类
    public static Bank getInstance(){
        return   instance;
    }
}

//、懒汉式
class Order{
    // 1、私有化构造器
    private Order(){};

    //2、创建内部类
    private static Order instance = null;

    //3、返回当前类的对象
    public static Order getInstance(){
        // 方式一：
       /* synchronized (Bank.class){
            if(instance == null){
                instance = new Order();
            }
            return instance;
        }*/
       //方式二：
        if(instance == null){
            synchronized (Bank.class){
                if(instance == null){
                    instance = new Order();
                }

                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            }
        }

        return instance;

    }
}
