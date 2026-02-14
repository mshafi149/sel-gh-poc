package core_java;

public class PalindromeBuiltIn {
    public static void main(String[] args) {
        String str = "Amanda";
        StringBuilder palindrome = new StringBuilder(str).reverse();
        System.out.println(palindrome.toString());
    }
}
