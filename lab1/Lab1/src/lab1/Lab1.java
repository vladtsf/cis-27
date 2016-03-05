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
        ll.insertAtEnd(last);
        System.out.println("1: " + ll);
        ll.insertBefore(new LinkedList.DoubleNode("beforeLast"), last);
        ll.insertAfter(new LinkedList.DoubleNode("afterFirst"), first);
        System.out.println("2: " + ll);
        ll.moveToFront(last);
        ll.moveToEnd(first);
        System.out.println("3: " + ll);
        ll.remove(first);
        ll.removeFromBeginning();
        ll.removeFromEnd();
        System.out.println("4: " + ll);

        System.out.println("2. Stacks - Evaluating Arithmetic Expressions");
        new ArithmeticExpressionEvaluator("(300+23)*(43-21)/(84+7)").evaluate();
        new ArithmeticExpressionEvaluator("(4+8)*(6-5)/((3-2)*(2+2))").evaluate();
        
        System.out.println("3. Union-Find - Maze");
        Maze maze = new Maze(10, 10);
        maze.generate();
        System.out.println(maze);
    }
    
}
