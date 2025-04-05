public class MovieManagementSystem {
    static class MovieNode {
        String title;
        String director;
        int year;
        double rating;
        MovieNode prev;
        MovieNode next;

        MovieNode(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
        }
    }

    MovieNode head = null;
    MovieNode tail = null;

    void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 1 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        MovieNode newNode = new MovieNode(title, director, year, rating);
        MovieNode temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
    }

    void deleteByTitle(String title) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                if (temp == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                return;
            }
            temp = temp.next;
        }
    }

    void searchByDirector(String director) {
        MovieNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equals(director)) {
                System.out.println(temp.title + ", " + temp.director + ", " + temp.year + ", " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found for director: " + director);
    }

    void searchByRating(double rating) {
        MovieNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + ", " + temp.director + ", " + temp.year + ", " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating: " + rating);
    }

    void updateRating(String title, double newRating) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        MovieNode temp = head;
        while (temp != null) {
            System.out.println(temp.title + ", " + temp.director + ", " + temp.year + ", " + temp.rating);
            temp = temp.next;
        }
    }

    void displayBackward() {
        MovieNode temp = tail;
        while (temp != null) {
            System.out.println(temp.title + ", " + temp.director + ", " + temp.year + ", " + temp.rating);
            temp = temp.prev;
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem mms = new MovieManagementSystem();
        mms.addAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        mms.addAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        mms.addAtPosition(2, "Dunkirk", "Christopher Nolan", 2017, 7.9);
        mms.displayForward();
        mms.updateRating("Interstellar", 9.0);
        mms.searchByDirector("Christopher Nolan");
        mms.deleteByTitle("Inception");
        mms.displayBackward();
    }
}