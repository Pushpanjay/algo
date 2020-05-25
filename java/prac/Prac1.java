package prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pushpanjay.kumar created on 9/5/20
 */


class Pos{
    int i, j;
    Pos(int i, int j){
        this.i = i;
        this.j = j;
    }
}

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
 }

public class Prac1 {

    private static int solUtil(int n, int h){
        if(h>n)
            return 0;
        if((n==3 && h==1) || (n==4 && h==1) || (n==h)){
            return 1;
        }

        int res=0;
        while(h<=n){
            res+=solUtil(n-h, h+1);
            h++;
        }
        return res;
    }

    public static ArrayList<ArrayList<Integer>> generateMatrix(int s) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int count=1;
        int i;
        int k=0;
        int l=0;
        int m=s;
        int n=s;
        int[][] temp = new int[n][n];

        while(k<m && l<n){
            for(i=l;i<n;i++){
                temp[k][i] = count++;
            }
            k++;

            for(i=k;i<m;i++){
                temp[i][n-1] = count++;
            }
            n--;

            if(k<m) {
                for (i = n - 1; i >= l; --i) {
                    temp[m - 1][i] = count++;
                }
                m--;
            }

            if(l<n) {
                for (i=m-1;i>=k;--i){
                    temp[i][l] = count++;
                }
                l++;
            }
        }

        for(int[] r:temp){
            ArrayList<Integer> sub = new ArrayList<>();
            for(int v: r){
                sub.add(v);
            }
            res.add(sub);
        }

        return res;
    }

    private static int sol(int n){
        return solUtil(n, 1);
    }

    public static ArrayList<ArrayList<Integer>> solve(int n) {
        ArrayList<ArrayList<Integer>> pTri = new ArrayList<>();
        if(n==0){
            return pTri;
        }
        ArrayList<Integer> fRow = new ArrayList<>();
        fRow.add(1);
        pTri.add(fRow);
        if(n==1){
            return pTri;
        }

        for(int l=2;l<=n;l++){
            ArrayList<Integer> row = new ArrayList<>();
            ArrayList<Integer> prev = pTri.get(l-1-1);
            for(int i=0;i<l;i++){
                int v = i>=prev.size()?0:prev.get(i);
                v+= (i-1)<0?0:prev.get(i-1);
                row.add(v);
            }

            pTri.add(row);
        }

        return pTri;
    }

    public static ArrayList<Integer> getRow(int n) {
        ArrayList<Integer> row = new ArrayList<>();
        for(int i=0;i<=n;i++){
            row.add(0);
        }

        row.set(0, 1);
        if(n==0){
            return row;
        }
        for(int l = 1;l<=n;l++){

            for(int j=l;j>=0;j--){
                int v = (j == l)?0:row.get(j);
                v+= (j-1)<0?0:row.get(j-1);
                row.set(j, v);
            }

        }
        return row;
    }

    public static ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int s = a.size();

        for(int c=0;c<s;c++){
            int r=0;
            ArrayList<Integer> row = new ArrayList<>();
            int pc=c;
            while(pc>=0 && r<s){
                row.add(a.get(r).get(pc));
                pc--;
                r++;
            }
            res.add(row);
        }

        for(int r=1;r<s;r++){
            int c=s-1;
            ArrayList<Integer> row = new ArrayList<>();
            int pr=r;
            while(pr<s && c>=0){
                row.add(a.get(pr).get(c));
                pr++;
                c--;
            }
            res.add(row);
        }
        return res;
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> a) {

        int n = a.size();
        int c=1;
        int s=0;

        for(int i=n-1;i>=0;i--){
            s = a.get(i) + c;
            c=s/10;
            s = s%10;
            a.set(i, s);
        }
        if(c!=0){
            a.add(0, c);
        }

        for(int i=0;i<a.size();i++){
            if(a.get(0) != 0){
                break;
            }
            if(a.get(0) == 0){
                a.remove(0);
            }
        }
        return a;
    }

    private static void manageSum(int x, int y, long s, Map<Long, Pos> m){
        if(y<x)
            return;

        if(m.containsKey(s)){
            Pos prev = m.get(s);
            int prevLen = prev.j - prev.i + 1;
            int curLen = y - x + 1;
            if(prevLen == curLen){
                if(x < prev.i){
                    m.put(s, new Pos(x, y));
                }
            } else{
                if(curLen > prevLen){
                    m.put(s, new Pos(x, y));
                }
            }
        } else{
            m.put(s, new Pos(x, y));
        }
    }

    public static ArrayList<Integer> maxset(ArrayList<Integer> a) {
        Map<Long, Pos> m = new HashMap<>();
        int n = a.size();
        long s = 0l;
        int x = 0;
        for(int i=0;i<n;i++){
            if(a.get(i)<0){
                if(i==0){
                    x = i+1;
                    continue;
                }
                manageSum(x, i-1, s, m);
                x = i+1;
                s=0l;
            } else{
                s+=(long)a.get(i);
                if(i == n-1){
                    manageSum(x, i, s, m);
                }
            }
        }
        s = 0l;
        for(Map.Entry<Long, Pos> e: m.entrySet()){
            if(e.getKey()>s){
                s = e.getKey();
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        Pos p = m.get(s);
        if(p == null)
            return null;
        for(int i = p.i;i<=p.j;i++){
            res.add(a.get(i));
        }
        return res;
    }

    public static int solve(ArrayList<Integer> a) {
        Collections.sort(a);
        int n = a.size();
        for(int i=0;i<n;i++){
            if((n-1-i) == a.get(i)){
                if(i<n-1){
                    if(!a.get(i + 1).equals(a.get(i))) {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{ -4, 7, 5, 3, 5, -4, 2, -1, -9, -8, -3, 0, 9, -7, -4, -10, -4, 2, 6, 1, -2, -3, -1, -8, 0, -8, -7, -3, 5, -1, -8, -8, 8, -1, -3, 3, 6, 1, -8, -1, 3, -9, 9, -6, 7, 8, -6, 5, 0, 3, -4, 1, -10, 6, 3, -8, 0, 6, -9, -5, -5, -6, -3, 6, -5, -4, -1, 3, 7, -6, 5, -8, -5, 4, -3, 4, -6, -7, 0, -3, -2, 6, 8, -2, -6, -7, 1, 4, 9, 2, -10, 6, -2, 9, 2, -4, -4, 4, 9, 5, 0, 4, 8, -3, -9, 7, -8, 7, 2, 2, 6, -9, -10, -4, -9, -5, -1, -6, 9, -10, -1, 1, 7, 7, 1, -9, 5, -1, -3, -3, 6, 7, 3, -4, -5, -4, -7, 9, -6, -2, 1, 2, -1, -7, 9, 0, -2, -2, 5, -10, -1, 6, -7, 8, -5, -4, 1, -9, 5, 9, -2, -6, -2, -9, 0, 3, -10, 4, -6, -6, 4, -3, 6, -7, 1, -3, -5, 9, 6, 2, 1, 7, -2, 5 };
        ArrayList<Integer> d = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            d.add(a[i]);
        }
        System.out.println(solve(d));
    }
}
