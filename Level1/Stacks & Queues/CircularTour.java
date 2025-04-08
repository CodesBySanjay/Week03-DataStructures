import java.util.LinkedList;
import java.util.Queue;

class CircularTour{
    public static void main(String[] args) {
        int[] petrol = {3,6,9,12,15,4,10};
        int[] distance = {1,8,4,7,11,8,6};
        int n = petrol.length;

        Queue<Integer> queue = new LinkedList<>();
        int start = 0;
        int currentPetrol = 0;
        int count = 0;

        while (start < n) {
            queue.clear();
            currentPetrol = 0;
            count = 0;

            int i = start;
            while (count < n) {
                currentPetrol += petrol[i] - distance[i];
                queue.add(i);

                if (currentPetrol < 0) break;

                i = (i + 1) % n;
                count++;
            }

            if (count == n && currentPetrol >= 0) {
                System.out.println("Start at pump: " + start);
                return;
            } else {
                start++;
            }
        }

        System.out.println("Tour not possible");
    }
}