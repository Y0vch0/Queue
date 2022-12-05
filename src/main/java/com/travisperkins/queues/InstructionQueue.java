
package com.travisperkins.queues;
import java.util.*;
/**
 *
 * @author ytodo
 */
public class InstructionQueue {
    //An instance of class InstructionMessage
    LinkedList<InstructionMessage> messages;

    //Constructor method:
    InstructionQueue(){

        this.messages = new LinkedList<InstructionMessage>();
    }

    //Enqueue method to add messages to the queue:
    void enqueue(InstructionMessage message){
        if (this.messages.isEmpty()){
            this.messages.add(message);
        } else {
            this.messages.addFirst(message);
        }
     }

    //Helper method to display the content of the queue:
    void display(){
        
        System.out.println("List of messages : ");
        for (int i = 0; i < this.messages.size(); i++){
            System.out.println("message : " + 
                    this.messages.get(i).type + " " + 
                    this.messages.get(i).product_code + " " +
                    this.messages.get(i).quantity + " " + 
                    this.messages.get(i).uom + " " + 
                    this.messages.get(i).time);
        }
    }

    //Dequeue method to remove the highest priority or the first-in message:
    InstructionMessage dequeue(){
       int index = -1;
        for (int i = 0; i < this.messages.size(); i++){
            //type "A" case:
            if (this.messages.get(i).getType().matches("A"))
                index = i;
        }
        //If above criteria is not met, index remains -1, check next in line (by priority)
        if (index == -1)
             for (int j = 0; j < this.messages.size(); j++){
                 //type "B" case:
                if (this.messages.get(j).getType().matches("B"))
                        index = j;
        }
        //If above criteria is not met, index remains -1, check next in line (by priority)
        if (index == -1)
             for (int k = 0; k < this.messages.size(); k++){
                 //type "C" case:
                if (this.messages.get(k).getType().matches("C"))
                    index = k;
        }
        //If above criteria is not met, index remains -1, check next in line (by priority)
        if (index == -1)
             for (int l = 0; l < this.messages.size(); l++){
                 //type "D" case:
                if (this.messages.get(l).getType().matches("D"))
                    index = l;
        }
        //Remove the message:
        return this.messages.remove(index);
    }

    //Peek method - returns the highest-priority message from the queue, without removing it
    InstructionMessage peek(){
        //Default index => first -in message
        int idx = -1;
    // Loop through the messages in the queue and check their type attribute in order to pick the highest priority one:

        // type "A" case:
        for (int g = 0; g < this.messages.size(); g++){
            if (this.messages.get(g).getType().matches("A"))
                idx = g;
        }
        //type "B" case:
        if (idx == -1)
            for (int k = 0; k < this.messages.size(); k++){
                if (this.messages.get(k).getType().matches("B"))
                    idx = k;
        }
        //type "C" case:
        if (idx == -1)
            for (int b = 0; b < this.messages.size(); b++){
                if (this.messages.get(b).getType().matches("C"))
                    idx = b;
        }
        // type "D" case:
        if (idx == -1)
            for (int c = 0; c < this.messages.size(); c++){
                if (this.messages.get(c).getType().matches("D"))
                    idx = c;
        }
        //Return the message
        return this.messages.get(idx);
    }    
    
    

     // Return the size of queue:
    int count(){  
        return this.messages.size();   
    }

    // Check if queue is empty or not:
    boolean isEmpty(){return this.count() == 0 ? true : false;}
}
