import java.util.ArrayList;
class UserNode{
    int userID;
    String name;
    int age;
    ArrayList<Integer> friendIDs;
    UserNode next;

    UserNode(int userID, String name, int age){
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new ArrayList<>();
        this.next = null;
    }
}

class SocialMediaList{
    UserNode head;

    void addUser(int id, String name, int age){
        UserNode newUser = new UserNode(id, name, age);
        if(head==null) head = newUser;
        else{
            UserNode temp = head;
            while(temp.next!=null) temp = temp.next;
            temp.next = newUser;
        }
    }

    void addFriend(int userID1, int userID2){
        UserNode user1 = findUserByID(userID1);
        UserNode user2 = findUserByID(userID2);

        if(user1 != null && user2 != null){
            if(!user1.friendIDs.contains(userID2)) user1.friendIDs.add(userID2);
            if(!user2.friendIDs.contains(userID1)) user2.friendIDs.add(userID1);
        }
    }

    void removeFriend(int userID1, int userID2){
        UserNode user1 = findUserByID(userID1);
        UserNode user2 = findUserByID(userID2);

        if(user1 != null && user2 != null){
            user1.friendIDs.remove(Integer.valueOf(userID2));
            user2.friendIDs.remove(Integer.valueOf(userID1));
        }
    }

    void findMutualFriends(int userID1, int userID2){
        UserNode user1 = findUserByID(userID1);
        UserNode user2 = findUserByID(userID2);

        if(user1 != null && user2 != null){
            System.out.println("Mutual Friends:");
            for(int id : user1.friendIDs){
                if(user2.friendIDs.contains(id)){
                    System.out.println("User ID: " + id);
                }
            }
        }
    }

    void displayFriends(int userID){
        UserNode user = findUserByID(userID);
        if(user != null){
            System.out.println(user.name + "'s Friends: " + user.friendIDs);
        }
    }

    void countFriends(){
        UserNode temp = head;
        while(temp != null){
            System.out.println(temp.name + " has " + temp.friendIDs.size() + " friend(s).");
            temp = temp.next;
        }
    }

    UserNode findUserByID(int id){
        UserNode temp = head;
        while(temp != null){
            if(temp.userID == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    UserNode findUserByName(String name){
        UserNode temp = head;
        while(temp != null){
            if(temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }
}

public class SocialMediaConnections {
    public static void main(String[] args) {
        SocialMediaList list = new SocialMediaList();
        list.addUser(1, "Alice", 22);
        list.addUser(2, "Bob", 23);
        list.addUser(3, "Charlie", 24);

        list.addFriend(1, 2);
        list.addFriend(1, 3);
        list.displayFriends(1);
        list.displayFriends(2);

        list.findMutualFriends(1, 2);
        list.removeFriend(1, 2);
        list.displayFriends(1);
        list.countFriends();
    }
}