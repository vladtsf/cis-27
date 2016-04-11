package lab1;

import Maze.*;
import lab1.ArithmeticExpressions.*;
import static lab1.LinkedList.*;

public class Lab1 {

    public static void main(String[] args) {
        System.out.println("1. Linked Lists - Dequeue");
        LinkedList ll = new LinkedList();
        
        DoubleNode first = new DoubleNode("first");
        DoubleNode last = new DoubleNode("last");
        
        ll.insertAtBeginning(first);
        System.out.println("insertAtBeginning(first): " + ll);
        ll.insertAtEnd(last);
        System.out.println("insertAtEnd(last): " + ll);
        ll.insertBefore(new LinkedList.DoubleNode("beforeLast"), last);
        System.out.println("insertBefore(new LinkedList.DoubleNode(\"beforeLast\"), last): " + ll);
        ll.insertAfter(new LinkedList.DoubleNode("afterFirst"), first);
        System.out.println("insertAfter(new LinkedList.DoubleNode(\"afterFirst\"), first): " + ll);
        ll.moveToFront(last);
        System.out.println("moveToFront(last): " + ll);
        ll.moveToEnd(first);
        System.out.println("moveToEnd(first): " + ll);
        ll.remove(first);
        System.out.println("remove(first): " + ll);
        ll.removeFromBeginning();
        System.out.println("removeFromBeginning(): " + ll);
        ll.removeFromEnd();
        System.out.println("removeFromEnd(): " + ll);

        System.out.println("\n2. Stacks - Evaluating Arithmetic Expressions");
        new ArithmeticExpressionEvaluator("(300+23)*(43-21)/(84+7)").evaluate();
        new ArithmeticExpressionEvaluator("(4+8)*(6-5)/((3-2)*(2+2))").evaluate();
        
        System.out.println("\n3. Union-Find - Maze");
        Maze maze = new Maze(10, 10);
        maze.generate();
        System.out.println(maze);
    }
    
}
