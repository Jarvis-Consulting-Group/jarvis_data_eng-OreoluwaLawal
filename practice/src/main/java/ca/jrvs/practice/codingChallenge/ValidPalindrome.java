package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {
    /**
     *  Big O: O(n), iteration is done to access the string characters
     */
    public boolean isPalindrome(String s) {
        String word = s.toLowerCase();
        String result = word.replaceAll("\\W","");

        for(int j = 0; j < result.length() / 2; j++){
            if(result.charAt(j) != result.charAt(result.length() - 1 - j)){
                return false;
            }
        }

        return true;
    }
    public boolean isValid(String val){
        String str = val.toLowerCase().replaceAll("\\W", "");
       int start = 0;
       int end = val.length() - 1;
       return checkPalindrome(str, start, end);
    }

    public boolean checkPalindrome(String str, int a, int b){

            if(a > b){
                return false;
            }
            if(str.charAt(a) != str.charAt(b)){
                return false;
            }
            else {
                a++;
                b--;
               return checkPalindrome(str, a, b);
            }

    }

}
