package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class HighestPowerOf2LessEqualToN {

    //tc: O(n)
    static int highestPowerOf2Naive(int n){
        int res=0;
        for(int i=n;i>=1;i--){
            if((i & i-1) == 0){
                res = i;
                break;
            }
        }
        return res;
    }


    static int highestPowerOf2Efficient(int n){
        if(n<1)
            return 0;
        int res=0;
        for(int i=0;i<8*Integer.BYTES;i++){
            int cur = 1 << i;
            if(cur>n) {
                break;
            }

            res = cur;
        }
        return res;
    }

    //log approach, constamt time
    static int highestPowerOf2LogApproach(int n){
        int p = (int)(Math.log(n)/Math.log(2));
        return (int) Math.pow(2,p);
    }

    public static void main(String ...s){
        int n = 10;
        System.out.println(highestPowerOf2Naive(n));
        System.out.println(highestPowerOf2Efficient(n));
        System.out.println(highestPowerOf2LogApproach(n));
    }
}
