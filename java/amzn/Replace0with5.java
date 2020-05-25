package amzn;

/**
 * @author pushpanjay.kumar created on 22/3/20
 */
public class Replace0with5 {
    static int replace0with5(int n) {
        return n + calculateAddingValue(n);
    }

    static int calculateAddingValue(int n){
        int result = 0;
        int place=1;
        if(n==0){
            result+=5*place;
        }
        while(n>0){
            if(n%10==0){
                result+=5*place;
            }
            place*=10;
            n/=10;
        }
        return result;
    }

    static int replace0with5Rec(int n){
        if(n==0){
            return 0;
        }
        int r = n%10;
        if(r==0){
            r=5;
        }
        return replace0with5Rec(n/10) * 10 + r;
    }

    static int replace0with5RecBase(int n){
        if (n==0)
            return 5;
        return replace0with5Rec(n);
    }

    public static void main(String[] args) {
        System.out.println(replace0with5(109));
        System.out.println(replace0with5RecBase(105602509));
    }


}
