import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SocialNetworkGraph {

    private final Map<String, Set<String>> graph;

    public SocialNetworkGraph() {
        graph = new HashMap<String, Set<String>>();
    }

    public boolean addUser(String user) {

        if (user == null || user.trim().isEmpty()) {
            return false;
        }

        user = user.trim();

        if (graph.containsKey(user)) {
            return false;
        }

        graph.put(
                user,
                new HashSet<String>()
        );

        return true;
    }

    public boolean addFriend(String user1, String user2) {

        if (user1 == null || user2 == null) {
            return false;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (user1.isEmpty()
                || user2.isEmpty()
                || user1.equals(user2)) {
            return false;
        }

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)) {
            return false;
        }

        if (graph.get(user1).contains(user2)) {
            return false;
        }

        graph.get(user1).add(user2);
        graph.get(user2).add(user1);

        return true;
    }

    public boolean removeFriend(
            String user1,
            String user2) {

        if (user1 == null || user2 == null) {
            return false;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)) {
            return false;
        }

        if (!graph.get(user1).contains(user2)) {
            return false;
        }

        graph.get(user1).remove(user2);
        graph.get(user2).remove(user1);

        return true;
    }

    public List<String> commonFriends(
            String user1,
            String user2) {

        List<String> result =
                new ArrayList<String>();

        if (user1 == null || user2 == null) {
            return result;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)) {
            return result;
        }

        Set<String> common =
                new TreeSet<String>(
                        graph.get(user1)
                );

        common.retainAll(
                graph.get(user2)
        );

        result.addAll(common);

        return result;
    }

    public List<String> friendsOf(String user) {

        List<String> result =
                new ArrayList<String>();

        if (user == null) {
            return result;
        }

        user = user.trim();

        if (!graph.containsKey(user)) {
            return result;
        }

        result.addAll(
                new TreeSet<String>(
                        graph.get(user)
                )
        );

        return result;
    }

    public List<String> isolatedUsers() {

        List<String> result =
                new ArrayList<String>();

        TreeSet<String> sortedUsers =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String user : sortedUsers) {

            if (graph.get(user).isEmpty()) {
                result.add(user);
            }
        }

        return result;
    }

    public void printGraph() {

        TreeSet<String> users =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String user : users) {

            System.out.println(
                    user
                    + " -> "
                    + new TreeSet<String>(
                            graph.get(user)
                    )
            );
        }
    }

    public static void main(String[] args) {

        SocialNetworkGraph network =
                new SocialNetworkGraph();

        network.addUser("Amy");
        network.addUser("Bob");
        network.addUser("Cindy");
        network.addUser("David");
        network.addUser("Eric");

        System.out.println(
                "Amy-Bob："
                + network.addFriend("Amy", "Bob")
        );

        System.out.println(
                "Amy-Cindy："
                + network.addFriend("Amy", "Cindy")
        );

        System.out.println(
                "Bob-Cindy："
                + network.addFriend("Bob", "Cindy")
        );

        System.out.println(
                "Bob-David："
                + network.addFriend("Bob", "David")
        );

        System.out.println(
                "重複 Amy-Bob："
                + network.addFriend("Amy", "Bob")
        );

        System.out.println("--------------------");

        network.printGraph();

        System.out.println("--------------------");

        System.out.println(
                "Amy 的好友："
                + network.friendsOf("Amy")
        );

        System.out.println(
                "Amy 與 Bob 的共同好友："
                + network.commonFriends(
                        "Amy",
                        "Bob"
                )
        );

        System.out.println(
                "孤立用戶："
                + network.isolatedUsers()
        );

        System.out.println("--------------------");

        System.out.println(
                "解除 Amy-Cindy："
                + network.removeFriend(
                        "Amy",
                        "Cindy"
                )
        );

        System.out.println(
                "再次解除 Amy-Cindy："
                + network.removeFriend(
                        "Amy",
                        "Cindy"
                )
        );

        System.out.println("--------------------");

        network.printGraph();
    }
}