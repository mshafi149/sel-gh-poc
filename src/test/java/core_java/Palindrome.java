package core_java;

public class Palindrome {
    public static void main(String[] args) {
        String str = "madam";
        String reverse = "";
        for (int i = str.length()-1; i >=0; i--) reverse += str.charAt(i);
        System.out.println(reverse);
        if(str.equals(reverse)){
            System.out.println("It is a palindrome : " + reverse);
        }else {
            System.out.println("It is not a palindrome: " + reverse);
        }
    }
}
