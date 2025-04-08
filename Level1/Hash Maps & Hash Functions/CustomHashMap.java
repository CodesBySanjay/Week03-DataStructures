import java.util.LinkedList;

public class CustomHashMap {
    private static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 10;
    private LinkedList<Entry>[] map;

    public CustomHashMap() {
        map = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            map[i] = new LinkedList<>();
        }
    }

    private int getIndex(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        for (Entry entry : map[index]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        map[index].add(new Entry(key, value));
    }

    public Integer get(int key) {
        int index = getIndex(key);
        for (Entry entry : map[index]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(int key) {
        int index = getIndex(key);
        map[index].removeIf(entry -> entry.key == key);
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(11, 30);
        System.out.println(map.get(1));
        System.out.println(map.get(11));
        map.remove(1);
        System.out.println(map.get(1));
    }
}
