package lab1;

import lab1.ArithmeticExpressions.InfixExpression;
import lab1.ArithmeticExpressions.PostfixExpression;
import static lab1.LinkedList.DoubleNode;

public class Lab1 {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        
        DoubleNode first = new DoubleNode("first");
        DoubleNode last = new DoubleNode("last");
        
//        ll.insertAtBeginning(first);
//        ll.insertAtEnd(last);
//        System.out.println("1: " + ll);
//        ll.insertBefore(new LinkedList.DoubleNode("beforeLast"), last);
//        ll.insertAfter(new LinkedList.DoubleNode("afterFirst"), first);
//        System.out.println("2: " + ll);
//        ll.moveToFront(last);
//        ll.moveToEnd(first);
//        System.out.println("3: " + ll);
//        ll.remove(first);
//        ll.removeFromBeginning();
//        ll.removeFromEnd();
//        System.out.println("4: " + ll);

        // 300 23 + 43 21 - * 84 7 + /
        System.out.println(new PostfixExpression(new InfixExpression("(300+23)*(43-21)/(84+7)")));
        
        // 4 8 + 6 5 - * 3 2 - 2 2 + * /
        System.out.println(new PostfixExpression(new InfixExpression("(4+8)*(6-5)/((3-2)*(2+2))"))); 
        
        // 3
        System.out.println(new InfixExpression("(4+8)*(6-5)/((3-2)*(2+2))").evaluate());
    }
    
}
