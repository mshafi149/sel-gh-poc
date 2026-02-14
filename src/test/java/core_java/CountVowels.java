package core_java;

public class CountVowels {
    public static void main(String[] args) {
        String str = "owiajgpwqjgpwjp'asj'pajga";
        String vowel = "aeiouAEIOU";
        int cons =0;
        int vowelCount = 0;
        for (char c : str.toCharArray()) {
            if (vowel.indexOf(c) != -1) {
                vowelCount++;
        }else  {
            cons++;
        }

        }
        System.out.println("Cons count :" + +cons);
        System.out.println("Vowel count :"+vowelCount);
    }
}
