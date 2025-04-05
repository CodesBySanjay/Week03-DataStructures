public class TaskScheduler {
    static class TaskNode {
        int taskId;
        String taskName;
        int priority;
        String dueDate;
        TaskNode next;

        TaskNode(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
        }
    }

    TaskNode head = null;
    TaskNode current = null;

    void addAtBeginning(int id, String name, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(id, name, priority, dueDate);
        if (head == null) {
            head = newNode;
            head.next = head;
            current = head;
            return;
        }
        TaskNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        newNode.next = head;
        temp.next = newNode;
        head = newNode;
    }

    void addAtEnd(int id, String name, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(id, name, priority, dueDate);
        if (head == null) {
            head = newNode;
            head.next = head;
            current = head;
            return;
        }
        TaskNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
    }

    void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        TaskNode newNode = new TaskNode(id, name, priority, dueDate);
        TaskNode temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    void removeById(int id) {
        if (head == null) return;
        TaskNode temp = head, prev = null;
        do {
            if (temp.taskId == id) {
                if (temp == head) {
                    if (head.next == head) {
                        head = null;
                        current = null;
                        return;
                    }
                    TaskNode last = head;
                    while (last.next != head) {
                        last = last.next;
                    }
                    head = head.next;
                    last.next = head;
                    if (current == temp) current = head;
                } else {
                    prev.next = temp.next;
                    if (current == temp) current = prev.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    void viewCurrentTask() {
        if (current != null) {
            System.out.println(current.taskId + ", " + current.taskName + ", " + current.priority + ", " + current.dueDate);
        } else {
            System.out.println("No tasks available.");
        }
    }

    void moveToNextTask() {
        if (current != null) {
            current = current.next;
        }
    }

    void displayAll() {
        if (head == null) return;
        TaskNode temp = head;
        do {
            System.out.println(temp.taskId + ", " + temp.taskName + ", " + temp.priority + ", " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int priority) {
        if (head == null) return;
        TaskNode temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println(temp.taskId + ", " + temp.taskName + ", " + temp.priority + ", " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No tasks with priority: " + priority);
    }

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        ts.addAtBeginning(1, "Write report", 2, "2025-04-10");
        ts.addAtEnd(2, "Submit assignment", 1, "2025-04-12");
        ts.addAtPosition(2, 3, "Prepare slides", 3, "2025-04-15");
        ts.displayAll();
        ts.viewCurrentTask();
        ts.moveToNextTask();
        ts.viewCurrentTask();
        ts.searchByPriority(1);
        ts.removeById(1);
        ts.displayAll();
    }
}