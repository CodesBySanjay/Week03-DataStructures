public class CountingSort_StudentAges {
    public static void main(String[] args) {
        int[] ages = {12, 15, 11, 10, 14, 13, 15, 12, 11, 18, 17};

        countingSort(ages);

        for (int age : ages) {
            System.out.print(age + " ");
        }
    }

    static void countingSort(int[] arr) {
        int max = 18;
        int min = 10;
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int age : arr) {
            count[age - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
}
