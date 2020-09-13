import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class decrypt {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int k = input.nextInt();

        String encrpytedM = input.next();

        String msg = decryptMessage(encrpytedM, k);

        System.out.println(msg);

    }

    public static String decryptMessage(String encrypted, int len) {


        StringBuilder sb = new StringBuilder();

        int count = 0;

        Deque<Character> deque = new ArrayDeque<>();
        int n = encrypted.length();
        if (n < len) {
            return new StringBuilder(encrypted).reverse().toString();
        }
        int left = n % len;
        String ss = "";
        for (char c:
             encrypted.toCharArray()) {

            if (count < len) {
                deque.addFirst(c);
                count++;
            }else {
                break;
            }
            if (count == len) {
                //此时出栈至空
                while (!deque.isEmpty()){
                    Character ch = deque.removeFirst();
                    sb.append(ch);
                }
                count = 0;
            }
        }
        String t = new StringBuilder(encrypted.substring(n - left)).reverse().toString();
        sb.append(t);
        return sb.toString();
    }
}
