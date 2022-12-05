# Queue
Instruction queue exercise



Your challenge is to build a queue for storing instruction messages. Instruction messages are received as strings in the following format.

InstructionMessage <InstructionType> <ProductCode> <Quantity> <UOM> <Timestamp>

The timestamp uses the format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (see SimpleDateFormat). Messages end in a newline character and each field is separated by a space. For example:

	InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z

You should implement the following interface for receiving messages.

public interface MessageReceiver {
    void receive(String message);
}	

This method should build an instance of type InstructionMessage that can then be inserted on a queue, which is an instance of type InstructionQueue. You must write the InstructionMessage and the InstructionQueue classes, together with any supporting classes.

Only valid messages should be enqueued. Message validity is determined according to the rules shown in the validation column below. Any invalid messages should not be enqueued; instead, an exception should be thrown.

Property
Validation
InstructionType
One of A, B, C or D
ProductCode
String of form AB12 (two uppercase letters followed by two digits)
Quantity
Integer, 0 < n
UOM
Integer, 0 <= n < 256
Timestamp
 Unix epoch < t <= current time


The InstructionQueue class should have the following public methods.

Method
Purpose
void enqueue(InstructionMessage message)
Adds a message to the queue
InstructionMessage dequeue()
Removes the highest-priority message from the queue and returns it
InstructionMessage peek()
Returns the highest-priority message from the queue, without removing it
int count()
Returns the number of messages currently on the queue
boolean isEmpty()
Returns true if the queue is empty, false otherwise


Messages should be dequeued in priority order. Message priority is based on the priority calculation shown below.

InstructionType
Priority
A
High
B
Medium
C or D
Low


Messages with the same priority should be returned in first-in, first-out order.
			 
			 
      Valid test messages:
			 
A MZ89 5678 50 1969-03-05T10:04:56.012Z

B SS89 5678 50 2022-04-05T09:04:10.012Z

C XX89 5678 50 2015-11-05T12:04:25.012Z

D ZZ89 5678 50 2017-02-05T10:01:33.012Z

A KK89 5678 50 2010-03-04T10:04:48.012Z


     Invalid test messages:

F MZ89 5678 50 2015-03-05T10:04:56.012Z

A Mx89 5678 50 1980-03-05T10:04:56.012Z

C MZ89 0 50 2022-03-05T10:04:56.012Z

A MZ89 5678 400 2021-03-05T10:04:56.012Z

A MZ89 5678 255 2023-03-05T10:04:56.012Z

A MZ89 5678 180 1969-03-05T10:04:56.012Z

			 



