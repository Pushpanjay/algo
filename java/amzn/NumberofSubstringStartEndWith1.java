package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class NumberofSubstringStartEndWith1 {
    // tc: O(n) for traversal
    // calculated using combinations
    static int countSubsString(String s){
        int count =0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '1')
                count++;
        }
        return (count * (count-1))/2; // Combination (count C 2)
    }
    public static void main(String[] args) {
        String s = "00100101";
        System.out.println(countSubsString(s));
    }
}
