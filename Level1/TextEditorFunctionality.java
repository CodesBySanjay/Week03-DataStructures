class Node {
    String text;
    Node prev;
    Node next;

    Node(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

public class TextEditorFunctionality {
    private Node head;
    private Node current;
    private int size;
    private final int MAX_HISTORY = 10;

    public void type(String newText) {
        Node newNode = new Node(newText);

        if (head == null) {
            head = newNode;
            current = head;
            size = 1;
            return;
        }

        current.next = newNode;
        newNode.prev = current;
        current = newNode;

        Node temp = current;
        int count = 1;
        while (temp.prev != null) {
            temp = temp.prev;
            count++;
            if (count > MAX_HISTORY) {
                head = head.next;
                head.prev = null;
                break;
            }
        }
        size = Math.min(size + 1, MAX_HISTORY);
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo operations available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo operations available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.text);
        } else {
            System.out.println("Editor is empty.");
        }
    }

    public static void main(String[] args) {
        TextEditorFunctionality editor = new TextEditorFunctionality();
        editor.type("Hello");
        editor.type("Hello World");
        editor.type("Hello World!");
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
        editor.undo();
        editor.undo();
        editor.displayCurrentState();
    }
}