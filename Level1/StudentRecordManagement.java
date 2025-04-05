class StudentRecordManagement {
    static class StudentNode {
        int rollNumber;
        String name;
        int age;
        String grade;
        StudentNode next;

        StudentNode(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    StudentNode head = null;

    void addAtBeginning(int roll, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(roll, name, age, grade);
        newNode.next = head;
        head = newNode;
    }

    void addAtEnd(int roll, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(roll, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 1 || head == null) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        StudentNode newNode = new StudentNode(roll, name, age, grade);
        StudentNode temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addAtEnd(roll, name, age, grade);
            return;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    void deleteByRoll(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    void searchByRoll(int roll) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Found: " + temp.rollNumber + ", " + temp.name + ", " + temp.age + ", " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    void updateGrade(int roll, String newGrade) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
    }

    void displayAll() {
        StudentNode temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + ", " + temp.name + ", " + temp.age + ", " + temp.grade);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();
        srm.addAtBeginning(1, "Alice", 20, "A");
        srm.addAtEnd(2, "Bob", 21, "B");
        srm.addAtPosition(2, 3, "Charlie", 22, "C");
        srm.displayAll();
        srm.updateGrade(2, "A+");
        srm.searchByRoll(2);
        srm.deleteByRoll(1);
        srm.displayAll();
    }
}