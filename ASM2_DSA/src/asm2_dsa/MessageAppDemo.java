/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package asm2_dsa;

/**
 *
 * @author quy
 */

import java.util.Scanner;

public class MessageAppDemo {

    private static final int MAX_MESSAGE_LENGTH = 250;
    
    public static void main(String[] args) {
        
        Queue inputQueue = new Queue();
        Stack sentStack = new Stack(); 
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            
            System.out.println("\nChoose an option:");
            System.out.println("1. Enter number of messages");
            System.out.println("2. Send messages");
            System.out.println("3. View received messages");
            System.out.println("4. Exit");
            
            int choice = getUserChoice(scanner);
            
            switch (choice) {
                case 1:
                    getInputMessages(scanner, inputQueue);
                    break;
                case 2:
                    sendMessages(inputQueue, sentStack);
                    break;
                case 3:  
                    viewReceivedMessages(sentStack);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        
        scanner.close();
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Your choice: ");  
        return Integer.parseInt(scanner.nextLine());
    }

    private static void getInputMessages(Scanner scanner, Queue queue) {
        System.out.print("Number of messages: ");
        int numMessages = Integer.parseInt(scanner.nextLine());
            
        for (int i = 0; i < numMessages; i++) {
            System.out.print("Message " + (i + 1) + ": ");  
            String message = scanner.nextLine();
            queue.enqueue(message);
        }
    }

    private static void sendMessages(Queue queue, Stack stack) {
        System.out.println("\nSending messages:");
        
        while (!queue.isEmpty()) {
            String message = queue.dequeue();
            if (isValidLength(message)) {
                stack.push(message);
                System.out.println("Sent: Message sent" + message);
            } else {
                System.out.println("Message too long. Not sent. "); 
            }
        }
    }

    private static boolean isValidLength(String message) {
        return message.length() <= MAX_MESSAGE_LENGTH; 
    }

    private static void viewReceivedMessages(Stack stack) {
        System.out.println("\nViewing received messages:"); 
        
        while (!stack.isEmpty()) {
            String message = stack.pop();
            System.out.println("Received: " + message);
        }
    }

}



// Rest of the Queue and Stack implementations remain unchanged


// trien khai hang doi queue 
class Queue {

    private static class QueueNode {
      
        String data;
       
        QueueNode next;

        public QueueNode(String data) {
            this.data = data;  
        }

    }

    private QueueNode front;
    private QueueNode rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public void enqueue(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data");
        }
            
        QueueNode newNode = new QueueNode(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }
    
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return front.data;
    }
    
    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
            
        String data = front.data;        
        front = front.next;
            
        if (front == null) {
            rear = null;
        }
            
        return data;
    }
    
    public boolean isEmpty() {
        return front == null; 
    }

}

class Stack {

    private static class StackNode {
      
        String data;
       
        StackNode next;

        public StackNode(String data) {
            this.data = data;
        }
    }
    
    private StackNode top;

    public Stack() {
        top = null;
    }

    public void push(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot push null data");
        }
            
        StackNode newNode = new StackNode(data);  
        newNode.next = top;
        top = newNode;
    }
    
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty"); 
        }
        return top.data;
    }
    
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
            
        String data = top.data; 
        top = top.next;
        return data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
}