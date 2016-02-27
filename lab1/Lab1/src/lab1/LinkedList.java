package lab1;

public class LinkedList {
    
    private DoubleNode first;
    
    private DoubleNode getFirst() {
        return first;
    }
    
    private DoubleNode getLast() {
        DoubleNode current = getFirst();
        
        while(current.next != null) {
            current = current.next;
        }
        
        return current;
    }
    
    public void insertAtBeginning(DoubleNode node) {
        DoubleNode oldFirst = first;
        
        first = node;
        node.next = oldFirst;
        node.previous = null;
        
        if(oldFirst != null) {
            oldFirst.previous = node;
        }
    }
    
    public void insertAtEnd(DoubleNode node) {
        insertAfter(node, getLast());
    }
    
    public DoubleNode removeFromBeginning() {
        remove(first);
        return first;
    }
    
    public DoubleNode removeFromEnd() {
        DoubleNode last = getLast();
        remove(last);
        return last;
    }
    
    public void insertBefore(DoubleNode node, DoubleNode target) {
        DoubleNode oldPrevious = target.previous;
        
        node.next = target;
        node.previous = oldPrevious;
        target.previous = node;
        
        if(oldPrevious != null) {
            oldPrevious.next = node;
        }
    }
    
    public void insertAfter(DoubleNode node, DoubleNode target) {
        DoubleNode oldNext = target.next;
        
        target.next = node;
        node.previous = target;
        node.next = oldNext;
        
        if(oldNext != null) {
            oldNext.previous = node;
        }
    }
    
    public void remove(DoubleNode node) {
        if(node == null) {
            return;
        }
        
        DoubleNode next = node.next;
        DoubleNode prev = node.previous;
        
        if(next != null) {
            next.previous = prev;
        }
        
        if(prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
    }
    
    public void moveToFront(DoubleNode node) {
        remove(node);
        
        if(first != null) {
            first.previous = node;
        }
        
        node.previous = null;
        node.next = first;
        first = node;
    }
    
    public void moveToEnd(DoubleNode node) {
        remove(node);
        
        DoubleNode last = getLast();
        
        last.next = node;
        node.previous = last;
        node.next = null;
    }
    
    public static class DoubleNode {
        public DoubleNode previous, next;
        public Object value;

        public DoubleNode(Object value) {
            this.value = value;
        }
    }
}
