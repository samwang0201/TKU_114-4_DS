public class RecursiveDigitReport {

    static int digitSum(int n) {

        n = Math.abs(n);

        if (n < 10) {
            return n;
        }

        return n % 10 + digitSum(n / 10);
    }

    static int digitCount(int n) {

        n = Math.abs(n);

        if (n < 10) {
            return 1;
        }

        return 1 + digitCount(n / 10);
    }

    static int countDigit(int n, int target) {

        n = Math.abs(n);
        target = Math.abs(target);

        if (target > 9) {
            return 0;
        }

        if (n < 10) {

            if (n == target) {
                return 1;
            }

            return 0;
        }

        int count = 0;

        if (n % 10 == target) {
            count = 1;
        }

        return count + countDigit(n / 10, target);
    }

    static void printReport(int n) {

        System.out.println("數字：" + n);
        System.out.println("數位總和：" + digitSum(n));
        System.out.println("數位個數：" + digitCount(n));
        System.out.println(
                "數字 0 出現次數：" + countDigit(n, 0)
        );
        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        printReport(50205);
        printReport(0);
        printReport(-731);
    }
}