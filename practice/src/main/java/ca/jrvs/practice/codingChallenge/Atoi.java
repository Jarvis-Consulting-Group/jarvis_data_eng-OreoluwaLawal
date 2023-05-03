package ca.jrvs.practice.codingChallenge;

public class Atoi {
    public int myAtoi(String s){

        String val = s.trim();
        boolean isNeg = false;
        int finalAns = 0;
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < val.length(); i++){
            if(i == 0 && (val.charAt(i) == '-' || val.charAt(i) == '+')){
               // ans.append(String.valueOf(val.charAt(i)));
                if(val.charAt(i) == '-') isNeg = true;
                if(val.charAt(i) == '+') isNeg = false;
            }
            else if(String.valueOf(val.charAt(i)).matches("\\d")){
                ans.append(String.valueOf(val.charAt(i)));
            }
            else{
                break;
            }
        }
        if(s.length() == 0 || ans.length() == 0){
            return 0;
        }
        try{
           finalAns = Integer.parseInt(ans.toString());
//           if(finalAns < Math.pow(-2, 31)){
//               return (int) Math.pow(-2, 31);
//           }
//           else if(finalAns > Math.pow(2, 31) - 1){
//               return (int) Math.pow(2, 31) - 1;
//           }
        }
        catch (NumberFormatException e){
            if(isNeg) return (int) Math.pow(-2, 31);
            else  return (int) Math.pow(2, 31) - 1;
            // throw new RuntimeException(e);
        }

        return finalAns;
    }
}
