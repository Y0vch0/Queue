
package com.travisperkins.queues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ytodo
 */
public class InstructionMessage implements MessageReceiver {
    
    public String type;
    public String product_code;
    public int quantity;
    public int uom;
    public String time;

    //An instance of class InstructionQueue:
    InstructionQueue q = new InstructionQueue();


    //Empty constructor method:
    InstructionMessage(){ }


    // Constructor
    InstructionMessage(String type, String code, int qty, int uom, String ts){
        this.type = type;
        this.product_code = code;
        this.quantity = qty;
        this.uom = uom;
        this.time = ts;
    }

    //Getter method for type field:
    public String getType(){

        return this.type;
    }

    //Receive method to apply validation rules:
    public void receive(String Message) throws IllegalArgumentException {
        String[] msg = Message.split(" ");
        // Check if type filed  matches A,B,C or D as per validation criteria:
        if (msg[0].matches("A") || msg[0].matches("B") || msg[0].matches("C") || msg[0].matches("D")){

            //Check product code field matches validation rules:
            if (msg[1].length() == 4){
                 String p_code = msg[1];
                char c1 = msg[1].charAt(0);
                char c2 = msg[1].charAt(1);
                int i1 = Integer.parseInt(Character.toString(p_code.charAt(2)));
                int i2 = Integer.parseInt(Character.toString(p_code.charAt(3)));
           
                if (Character.isUpperCase(c1) && Character.isUpperCase(c2)){
                    if ( (i1 == 0 || i1 == 1 || i1 == 2 || i1 == 3 || i1 == 4 || i1 == 5 || i1 == 6 || i1 == 7 || i1 == 8 || i1 == 9)  && ( i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7 || i2 == 8 || i2 == 9 )){

                        //Quantity field check:  (Integer, 0 < n)
                        int qty = Integer.parseInt(msg[2]);
                        if (qty > 0){


                            String uom_s = msg[3];
                            int uom = Integer.parseInt(uom_s);
                            //Check if uom field is within required boundaries (0 <= n < 256):
                            if (uom >= 0 && uom <256){
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                                try {
                                    Date date = formatter.parse(msg[4]);
                                    String stringDate = formatter.format(date);
                                    Date unixEpoch = formatter.parse("1970-01-01T00:00:00.000Z");
                                    // Check if time filed matches validation criteria:
                                    if( msg[4].length() ==  24 && date.after(unixEpoch) && date.before(new Date())){
                                        InstructionMessage im = new InstructionMessage(msg[0], msg[1], qty, uom, stringDate);
                                        q.enqueue(im);
         // ********************************* If validation criteria are not met throw an exception ****************************************
                                    } else{
                                      throw new IllegalArgumentException("data format is not valid.");
                                    }
                                } 
                                catch (ParseException ex) {
                                   ex.printStackTrace();
                                }
                            } else {
                                throw new IllegalArgumentException("uom is not valid.");
                            }
                        } else {
                            throw new IllegalArgumentException("quantity is not valid.");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("product code is not valid.");
                }
            } else {
                throw new IllegalArgumentException("product code is not valid.");
            }
        } else {
            throw new IllegalArgumentException("message type is not valid.");
        }
    }

    
}
