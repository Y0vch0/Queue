

package com.travisperkins.queues;
import java.util.*;
/**
 *
 * @author ytodo
 */
public class Queues {

    public static void main(String[] args) {

        //An instance of class InstructionMessage:
        InstructionMessage IM = new InstructionMessage();


        System.out.println("Enter the instruction message");
        //Get user's input:
        Scanner s = new Scanner(System.in);

        //Input message  #1
        String msg = s.nextLine();

        //Receive message
        IM.receive(msg);

        //Input message  #2
        String msg2 = s.nextLine();

        //Receive message
        IM.receive(msg2);

        //Input message  #3
        String msg3 = s.nextLine();

        //Receive message
        IM.receive(msg3);

        //Input message  #4
        String msg4 = s.nextLine();

        //Receive message
        IM.receive(msg4);

        //Check if queue is empty (true if empty / false if not)
        System.out.println("Checking if the queue is empty: " + IM.q.isEmpty());

        //Show queue content:
        IM.q.display();
        
        System.out.println("Removing the highest-priority message : ");
        //Delete the highest priority message or first-in if all messages have the same priority:
        InstructionMessage msg_del = IM.q.dequeue();
        //Retrieve the message:
        System.out.println(msg_del.type + " " + msg_del.product_code + " " + msg_del.quantity + " " + msg_del.uom + " " + msg_del.time);

        //Show queue content:
        IM.q.display();
        
        System.out.println("Retrieving the new highest-priority message :");
        //Get the highest priority message and display it without removing it:
        InstructionMessage peek = IM.q.peek();
        System.out.println(peek.type + " " + peek.product_code + " " + peek.quantity + " " + peek.uom + " " + peek.time);

        //Display the number of messages left in the queue:
        System.out.println("Number of messages currently on the queue: "+ IM.q.count());

        // Show queue content:
        IM.q.display();
    }
}
