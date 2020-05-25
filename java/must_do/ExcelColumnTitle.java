package amzn.must_do;

/**
 * @author pushpanjay.kumar created on 24/3/20
 */
public class ExcelColumnTitle {

    static void getExcelColumnTitle(int n){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            int r = n%26;
            if(r==0){
                sb.append("Z");
                n = (n/26) -1;
            } else{
                sb.append((char)('A' + r-1));
                n = n/26;
            }
        }
        System.out.println(sb.reverse());
    }

    public static void main(String[] args) {
        getExcelColumnTitle(26);
        getExcelColumnTitle(51);
        getExcelColumnTitle(52);
        getExcelColumnTitle(80);
        getExcelColumnTitle(676);
        getExcelColumnTitle(702);
        getExcelColumnTitle(705);
    }
}
