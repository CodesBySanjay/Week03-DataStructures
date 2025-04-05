class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book prev;
    Book next;

    Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.prev = null;
        this.next = null;
    }
}

class Library {
    Book head;
    Book tail;

    void addBookAtBeginning(Book newBook) {
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    void addBookAtEnd(Book newBook) {
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    void addBookAtPosition(Book newBook, int position) {
        if (position <= 1 || head == null) {
            addBookAtBeginning(newBook);
            return;
        }
        Book current = head;
        int index = 1;
        while (current.next != null && index < position - 1) {
            current = current.next;
            index++;
        }
        if (current.next == null) {
            addBookAtEnd(newBook);
        } else {
            newBook.next = current.next;
            newBook.prev = current;
            current.next.prev = newBook;
            current.next = newBook;
        }
    }

    void removeBookById(int bookId) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    void searchBook(String keyword) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(keyword) || current.author.equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + current.title + " by " + current.author);
            }
            current = current.next;
        }
    }

    void updateAvailability(int bookId, boolean newStatus) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = newStatus;
                return;
            }
            current = current.next;
        }
    }

    void displayForward() {
        Book current = head;
        while (current != null) {
            System.out.println(current.title + " by " + current.author + " [" + (current.isAvailable ? "Available" : "Not Available") + "]");
            current = current.next;
        }
    }

    void displayReverse() {
        Book current = tail;
        while (current != null) {
            System.out.println(current.title + " by " + current.author + " [" + (current.isAvailable ? "Available" : "Not Available") + "]");
            current = current.prev;
        }
    }

    int countBooks() {
        int count = 0;
        Book current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBookAtEnd(new Book("Book1", "Author1", "Fiction", 101, true));
        library.addBookAtBeginning(new Book("Book2", "Author2", "Sci-fi", 102, false));
        library.addBookAtPosition(new Book("Book3", "Author3", "History", 103, true), 2);

        library.displayForward();
        System.out.println("Total Books: " + library.countBooks());

        library.searchBook("Author2");

        library.updateAvailability(101, false);
        library.displayReverse();

        library.removeBookById(102);
        library.displayForward();
    }
}
