public class RecursiveTextTools {

    static String reverse(String text) {

        if (text == null) {
            return null;
        }

        if (text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1))
                + text.charAt(0);
    }

    static boolean isPalindrome(String text) {

        if (text == null) {
            return false;
        }

        return isPalindromeHelper(
                text,
                0,
                text.length() - 1
        );
    }

    static boolean isPalindromeHelper(
            String text,
            int left,
            int right) {

        while (left < right
                && text.charAt(left) == ' ') {
            left++;
        }

        while (left < right
                && text.charAt(right) == ' ') {
            right--;
        }

        if (left >= right) {
            return true;
        }

        char leftChar =
                Character.toLowerCase(
                        text.charAt(left)
                );

        char rightChar =
                Character.toLowerCase(
                        text.charAt(right)
                );

        if (leftChar != rightChar) {
            return false;
        }

        return isPalindromeHelper(
                text,
                left + 1,
                right - 1
        );
    }

    static int countCharacter(
            String text,
            char target) {

        if (text == null) {
            return 0;
        }

        return countCharacterHelper(
                text,
                target,
                0
        );
    }

    static int countCharacterHelper(
            String text,
            char target,
            int index) {

        if (index == text.length()) {
            return 0;
        }

        int count = 0;

        if (text.charAt(index) == target) {
            count = 1;
        }

        return count
                + countCharacterHelper(
                        text,
                        target,
                        index + 1
                );
    }

    static void test(String text) {

        System.out.println(
                "原字串：" + text
        );

        System.out.println(
                "reverse：" + reverse(text)
        );

        System.out.println(
                "Palindrome："
                + isPalindrome(text)
        );

        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        test("");

        test("A");

        test("Level");

        test("Never odd or even");

        test("Java");

        System.out.println(
                "banana 中 a 的數量："
                + countCharacter(
                        "banana",
                        'a'
                )
        );
    }
}