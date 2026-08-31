public class ResizableStringMap {

    private static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] buckets;
    private int size;

    public ResizableStringMap() {
        buckets = new Entry[3];
        size = 0;
    }

    private int indexFor(String key) {
        return Math.floorMod(
                key.hashCode(),
                buckets.length
        );
    }

    public void put(String key, String value) {

        if (key == null) {
            throw new IllegalArgumentException(
                    "key 不可為 null"
            );
        }

        int index = indexFor(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key.equals(key)) {
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

        if (loadFactor() > 0.75) {
            resize();
        }
    }

    public String get(String key) {

        if (key == null) {
            return null;
        }

        int index = indexFor(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.length;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    private void resize() {

        Entry[] oldBuckets = buckets;

        int newCapacity =
                oldBuckets.length * 2 + 1;

        buckets =
                new Entry[newCapacity];

        for (int i = 0; i < oldBuckets.length; i++) {

            Entry current = oldBuckets[i];

            while (current != null) {

                Entry next = current.next;

                int newIndex =
                        Math.floorMod(
                                current.key.hashCode(),
                                buckets.length
                        );

                current.next = buckets[newIndex];
                buckets[newIndex] = current;

                current = next;
            }
        }
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

        ResizableStringMap map =
                new ResizableStringMap();

        System.out.println(
                "初始 capacity = "
                + map.capacity()
        );

        map.put("A", "Amy");

        System.out.println(
                "加入 A 後 capacity = "
                + map.capacity()
        );

        map.put("B", "Bob");

        System.out.println(
                "加入 B 後 capacity = "
                + map.capacity()
        );

        map.put("C", "Cindy");

        System.out.println(
                "加入 C 後 capacity = "
                + map.capacity()
        );

        System.out.println(
                "size = " + map.size()
        );

        System.out.println(
                "get B = " + map.get("B")
        );

        System.out.println("--------------------");

        map.put("B", "Bobby");

        System.out.println(
                "更新 B = " + map.get("B")
        );

        System.out.println(
                "size = " + map.size()
        );

        System.out.println("--------------------");

        map.bucketReport();
    }
}