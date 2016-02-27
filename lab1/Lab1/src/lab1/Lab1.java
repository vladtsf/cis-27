package lab1;

import static lab1.LinkedList.DoubleNode;

public class Lab1 {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        
        DoubleNode first = new DoubleNode("first");
        DoubleNode last = new DoubleNode("last");
        
        ll.insertAtBeginning(first);
        ll.insertAtEnd(last);
        ll.insertBefore(new LinkedList.DoubleNode("beforeLast"), last);
        ll.insertAfter(new LinkedList.DoubleNode("afterFirst"), first);
        ll.moveToFront(last);
        ll.moveToEnd(first);
        ll.remove(first);
        ll.removeFromBeginning();
        ll.removeFromEnd();
        System.out.println(ll);
    }
    
}
