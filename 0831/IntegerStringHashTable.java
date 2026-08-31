public class IntegerStringHashTable {

    private static class Entry {
        int key;
        String value;
        Entry next;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int size;

    public IntegerStringHashTable() {
        buckets = new Entry[5];
        size = 0;
    }

    private int indexFor(int key) {
        return Math.floorMod(key, buckets.length);
    }

    public void put(int key, String value) {

        int index = indexFor(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key == key) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Entry newEntry =
                new Entry(key, value);

        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;
    }

    public String get(int key) {

        int index = indexFor(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key == key) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public boolean containsKey(int key) {

        int index = indexFor(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key == key) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean remove(int key) {

        int index = indexFor(key);

        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {

            if (current.key == key) {

                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {

        for (int i = 0; i < buckets.length; i++) {

            System.out.print(
                    "bucket " + i + "："
            );

            Entry current = buckets[i];

            if (current == null) {
                System.out.println("[]");
                continue;
            }

            System.out.print("[");

            boolean first = true;

            while (current != null) {

                if (!first) {
                    System.out.print(", ");
                }

                System.out.print(
                        current.key
                        + "="
                        + current.value
                );

                first = false;
                current = current.next;
            }

            System.out.println("]");
        }
    }

    public static void main(String[] args) {

        IntegerStringHashTable table =
                new IntegerStringHashTable();

        table.put(1, "Amy");
        table.put(6, "Bob");
        table.put(11, "Cindy");
        table.put(2, "David");
        table.put(-4, "Eric");

        System.out.println(
                "size = " + table.size()
        );

        System.out.println(
                "get 6 = " + table.get(6)
        );

        System.out.println(
                "contains 11 = "
                + table.containsKey(11)
        );

        System.out.println(
                "contains 99 = "
                + table.containsKey(99)
        );

        System.out.println("--------------------");

        System.out.println("更新 key 6");

        table.put(6, "Bobby");

        System.out.println(
                "get 6 = " + table.get(6)
        );

        System.out.println(
                "size = " + table.size()
        );

        System.out.println("--------------------");

        System.out.println(
                "remove 11 = "
                + table.remove(11)
        );

        System.out.println(
                "remove 99 = "
                + table.remove(99)
        );

        System.out.println(
                "size = " + table.size()
        );

        System.out.println("--------------------");

        table.bucketReport();
    }
}