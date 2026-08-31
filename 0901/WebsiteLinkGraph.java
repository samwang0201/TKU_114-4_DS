import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WebsiteLinkGraph {

    private final Map<String, Set<String>> graph;

    public WebsiteLinkGraph() {
        graph = new HashMap<String, Set<String>>();
    }

    public boolean addPage(String page) {

        if (page == null || page.trim().isEmpty()) {
            return false;
        }

        page = page.trim();

        if (graph.containsKey(page)) {
            return false;
        }

        graph.put(page, new HashSet<String>());
        return true;
    }

    public boolean addLink(String from, String to) {

        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (from.isEmpty() || to.isEmpty()) {
            return false;
        }

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (graph.get(from).contains(to)) {
            return false;
        }

        graph.get(from).add(to);

        return true;
    }

    public List<String> outgoingLinks(String page) {

        List<String> result =
                new ArrayList<String>();

        if (page == null) {
            return result;
        }

        page = page.trim();

        if (!graph.containsKey(page)) {
            return result;
        }

        result.addAll(
                new TreeSet<String>(
                        graph.get(page)
                )
        );

        return result;
    }

    public int incomingCount(String page) {

        if (page == null) {
            return 0;
        }

        page = page.trim();

        if (!graph.containsKey(page)) {
            return 0;
        }

        int count = 0;

        for (Set<String> links : graph.values()) {

            if (links.contains(page)) {
                count++;
            }
        }

        return count;
    }

    public List<String> pagesWithNoIncoming() {

        List<String> result =
                new ArrayList<String>();

        TreeSet<String> pages =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String page : pages) {

            if (incomingCount(page) == 0) {
                result.add(page);
            }
        }

        return result;
    }

    public List<String> pagesWithNoOutgoing() {

        List<String> result =
                new ArrayList<String>();

        TreeSet<String> pages =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String page : pages) {

            if (graph.get(page).isEmpty()) {
                result.add(page);
            }
        }

        return result;
    }

    public void printReport() {

        TreeSet<String> pages =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String page : pages) {

            System.out.println(
                    page
                    + " 傳出連結："
                    + outgoingLinks(page)
            );

            System.out.println(
                    page
                    + " 傳入計數："
                    + incomingCount(page)
            );

            System.out.println("--------------------");
        }

        System.out.println(
                "無傳入頁面："
                + pagesWithNoIncoming()
        );

        System.out.println(
                "無傳出頁面："
                + pagesWithNoOutgoing()
        );
    }

    public static void main(String[] args) {

        WebsiteLinkGraph web =
                new WebsiteLinkGraph();

        web.addPage("Home");
        web.addPage("About");
        web.addPage("News");
        web.addPage("Shop");
        web.addPage("Contact");

        web.addLink("Home", "About");
        web.addLink("Home", "News");
        web.addLink("Home", "Shop");

        web.addLink("News", "Home");
        web.addLink("Shop", "Contact");

        web.addLink("About", "Contact");

        web.printReport();
    }
}