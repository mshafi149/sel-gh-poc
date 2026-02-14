package core_java;

public class ReverseStringBuiltIn {

    public static void main(String[] args) throws InterruptedException {
        String str = "Automation";
        StringBuilder reverseStr = new StringBuilder(str).reverse();
        System.out.println(reverseStr);
    }
}
