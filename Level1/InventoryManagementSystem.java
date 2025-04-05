class InventoryRecordManagement{
    static class Node{
        String itemName;
        int itemID;
        int quantity;
        double price;
        Node next;

        public Node(String itemName, int itemID, int quantity, double price){
            this.itemName = itemName;
            this.itemID = itemID;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    Node head = null;

    void addAtBeginning(String itemName, int itemID, int quantity, double price){
        Node node = new Node(itemName, itemID, quantity, price);
        node.next = head;
        head = node;
    }

    void addAtEnd(String itemName, int itemID, int quantity, double price){
        Node node = new Node(itemName, itemID, quantity, price);
        if(head==null) {
            head=node;
            return;
        }
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = node;
    }

    void addAtPosition(int pos, String itemName, int itemID, int quantity, double price){
        if(pos<=1 || head==null){
            addAtBeginning(itemName, itemID, quantity, price);
            return;
        }
        Node node = new Node(itemName, itemID, quantity, price);
        Node temp = head;
        for(int i=1; head!=null && i<pos-1;i++){
            temp = temp.next;
        }
        if(temp==null){
            addAtEnd(itemName, itemID, quantity, price);
            return;
        }
        node.next = temp.next;
        temp.next = node;
    }

    void deleteByID(int itemID){
        if(head==null) return;
        if(head.itemID==itemID){
            head = head.next;
            return;
        }
        Node temp = head;
        while(temp.next != null && temp.next.itemID != itemID){
            temp = temp.next;
        }
        if(temp.next != null){
            temp.next = temp.next.next;
        }
    }

    void updateQuantityByID(int itemID, int newQuantity){
        Node temp = head;
        while(temp != null){
            if(temp.itemID == itemID){
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    Node searchByID(int itemID){
        Node temp = head;
        while(temp != null){
            if(temp.itemID == itemID){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    Node searchByName(String itemName){
        Node temp = head;
        while(temp != null){
            if(temp.itemName.equalsIgnoreCase(itemName)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    double calculateTotalValue(){
        double total = 0;
        Node temp = head;
        while(temp != null){
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        return total;
    }

    void sortByItemName(){
        if(head == null || head.next == null) return;
        head = mergeSortByName(head);
    }

    void sortByPrice(){
        if(head == null || head.next == null) return;
        head = mergeSortByPrice(head);
    }

    Node mergeSortByName(Node head){
        if(head == null || head.next == null) return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = mergeSortByName(head);
        Node right = mergeSortByName(nextOfMid);
        return sortedMergeByName(left, right);
    }

    Node mergeSortByPrice(Node head){
        if(head == null || head.next == null) return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = mergeSortByPrice(head);
        Node right = mergeSortByPrice(nextOfMid);
        return sortedMergeByPrice(left, right);
    }

    Node getMiddle(Node head){
        if(head == null) return head;
        Node slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    Node sortedMergeByName(Node a, Node b){
        if(a == null) return b;
        if(b == null) return a;
        if(a.itemName.compareToIgnoreCase(b.itemName) <= 0){
            a.next = sortedMergeByName(a.next, b);
            return a;
        } else {
            b.next = sortedMergeByName(a, b.next);
            return b;
        }
    }

    Node sortedMergeByPrice(Node a, Node b){
        if(a == null) return b;
        if(b == null) return a;
        if(a.price <= b.price){
            a.next = sortedMergeByPrice(a.next, b);
            return a;
        } else {
            b.next = sortedMergeByPrice(a, b.next);
            return b;
        }
    }
}