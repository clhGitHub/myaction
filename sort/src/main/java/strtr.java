/**
 * Create by longhchen on  2021-02-28 15:30
 */
public class strtr {
    public static void main(String[] args) {
        System.out.println(strtrMethod("abcdcef","ac","AC"));
    }

    public static String strtrMethod(String str,String from,String to){
        char[] array1 = from.toCharArray();
        char[] array2 = to.toCharArray();
        for (int i = 0; i < array1.length ; i++) {
            str = str.replace(array1[i],array2[i]);
        }
        return  str;

    }
}
