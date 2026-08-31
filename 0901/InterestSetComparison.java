import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class InterestSetComparison {

    public static Set<String> union(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<String>();

        if (first != null) {
            result.addAll(first);
        }

        if (second != null) {
            result.addAll(second);
        }

        return result;
    }

    public static Set<String> intersection(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<String>();

        if (first == null || second == null) {
            return result;
        }

        result.addAll(first);
        result.retainAll(second);

        return result;
    }

    public static Set<String> firstOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<String>();

        if (first == null) {
            return result;
        }

        result.addAll(first);

        if (second != null) {
            result.removeAll(second);
        }

        return result;
    }

    public static Set<String> secondOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<String>();

        if (second == null) {
            return result;
        }

        result.addAll(second);

        if (first != null) {
            result.removeAll(first);
        }

        return result;
    }

    public static void main(String[] args) {

        Set<String> first = new HashSet<String>();
        first.add("Music");
        first.add("Dance");
        first.add("Movie");
        first.add("Game");

        Set<String> second = new HashSet<String>();
        second.add("Movie");
        second.add("Game");
        second.add("Travel");
        second.add("Reading");

        System.out.println(
                "first = " + new TreeSet<String>(first)
        );

        System.out.println(
                "second = " + new TreeSet<String>(second)
        );

        System.out.println("--------------------");

        System.out.println(
                "union = "
                + new TreeSet<String>(
                        union(first, second)
                )
        );

        System.out.println(
                "intersection = "
                + new TreeSet<String>(
                        intersection(first, second)
                )
        );

        System.out.println(
                "first-only = "
                + new TreeSet<String>(
                        firstOnly(first, second)
                )
        );

        System.out.println(
                "second-only = "
                + new TreeSet<String>(
                        secondOnly(first, second)
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "原 first = "
                + new TreeSet<String>(first)
        );

        System.out.println(
                "原 second = "
                + new TreeSet<String>(second)
        );
    }
}