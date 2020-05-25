package prac;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pushpanjay.kumar created on 7/5/20
 */
public class Prac {
    static Map<BigInteger, Integer> map = new HashMap<>();
    static BigInteger count = BigInteger.ZERO;

    public static int sol(BigInteger n) {
        if(map.containsKey(n))
            return map.get(n);
        if(n.mod(BigInteger.valueOf(2l)).equals(BigInteger.ZERO)){
            map.put(n, sol(n.shiftRight(1)) + 1);
        } else{
            map.put(n, Math.min(sol(n.add(BigInteger.ONE).shiftRight(1)), sol(n.subtract(BigInteger.ONE).shiftRight(1))) + 2);
        }
        return map.get(n);
    }

    public static int sol1(BigInteger n) {
        int res=0;
        while(!n.equals(BigInteger.ONE)){
            if(n.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
                n = n.shiftRight(1);
            } else if(n.equals(BigInteger.valueOf(3)) ||
                    ((n.add(BigInteger.ONE).and(n)).compareTo(n.subtract(BigInteger.ONE).and(n.subtract(BigInteger.valueOf(2)))))>0){
                n = n.subtract(BigInteger.ONE);
            } else{
                n = n.add(BigInteger.ONE);
            }
            res++;
        }
        return res;
    }


    private static String gcd(BigInteger a, BigInteger b){
        if(b.equals(BigInteger.ZERO)){
            if(a.equals(BigInteger.ONE))
                return count.subtract(BigInteger.ONE).toString();
            else
                return "Imp";
        }
        count = count.add(a.divide(b));
        return gcd(b,a.mod(b));
    }

    private static String sol2(String x, String y){
        BigInteger a = new BigInteger(x);
        BigInteger b = new BigInteger(y);
        count = BigInteger.ZERO;
        return gcd(a, b);
    }

    public static void main(String[] args) {
        map.put(BigInteger.ONE, 0);
        map.put(BigInteger.valueOf(2), 1);

        BigInteger n = new BigInteger("4");
        System.out.println(sol(n));
        System.out.println(sol1(n));


        System.out.println(sol2("1", "1"));
    }
}
