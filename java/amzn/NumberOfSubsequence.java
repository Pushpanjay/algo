package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class NumberOfSubsequence {

    //tc: O(n)
    static int countSubsequences(String s){
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'a'){
                aCount = 2*aCount +1;
            } else if(s.charAt(i) == 'b'){
                bCount  = aCount + 2*bCount;
            } else if(s.charAt(i) == 'c'){
                cCount = bCount + 2*cCount;
            }
        }
        return cCount;
    }

    public static void main(String ...st){
        String s = "abcabc";
        System.out.println(countSubsequences(s));
    }
}
