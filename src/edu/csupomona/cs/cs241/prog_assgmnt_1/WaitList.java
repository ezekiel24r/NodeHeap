package edu.csupomona.cs.cs241.prog_assgmnt_1;

import java.util.Scanner;

/**
 * Created by Eric on 4/20/2015.
 */
public class WaitList {
    public static void main(String[] args){

        NodeHeap<Customer> nHeap = new NodeHeap<>();

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while(choice != 5) {
            //Scanner scan = new Scanner(System.in);
            printMainMenu();
            choice = inputMain();

            switch (choice){
                case 1:
                    Customer temp = new Customer("", 0 , 0);
                    inputPartySize(temp);
                    printPriorityMenu();
                    inputPriority(temp);
                    System.out.print("\n\tEnter a name for this reservation: ");
                    temp.setName(scan.nextLine());
                    nHeap.add(temp);
                    break;
                case 2:
                    if(!nHeap.isEmpty()) {
                        Customer next = nHeap.remove();
                        System.out.println("\n\tThe element with name \"" + next.getName() + "\" has been deleted.");
                    }
                    else
                        System.out.println("\n\tNo one is on the list");
                    break;
                case 3:
                    if(nHeap.isEmpty())
                        System.out.println("\n\tThe list is empty");
                    else {
                        System.out.println("\n\tThe sorted list from min to max priority is: ");
                        Customer[] sortedArr = nHeap.getSortedContents();
                        for (int i = 0; i < sortedArr.length; i++) {
                            System.out.println("\t\t" + sortedArr[i].getName() + ", " + sortedArr[i].getPriority());
                        }
                    }
                    break;
                case 4:
                    if(nHeap.isEmpty())
                        System.out.println("\n\tThe list is empty");
                    else {
                        System.out.println("\n\tThe array heap list is: ");
                        Customer[] arr = nHeap.toArray();
                        for (int i = 0; i < arr.length; i++) {
                            System.out.println("\t\t" + arr[i].getPriority() + ", " + arr[i].getName());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                default:
                    System.out.println("Not a valid choice");
            }
        }
        scan.close();
    }
    public static void printMainMenu(){
        System.out.println("\n\t1. Add a new customer to the queue");
        System.out.println("\t2. Remove the highest priority customer from the queue");
        System.out.println("\t3. - DEBUG - Print the heap according to ascending priority");
        System.out.println("\t4. - DEBUG - Print the heap in its array form");
        System.out.println("\t5. Exit");
    }
    public static void printPriorityMenu(){
        System.out.println("\n\tWhat type of party is reserving a seat?:");
        System.out.println("\t\t1. VIP");
        System.out.println("\t\t2. Advance Call");
        System.out.println("\t\t3. Seniors");
        System.out.println("\t\t4. Veterans");
        System.out.println("\t\t5. Families with children");
        System.out.println("\t\t6. General");
    }

    public static int inputMain(){
        Scanner scan = new Scanner(System.in);
        boolean failed = false;
        int choice = 0;
        do{
            try{
                System.out.print("\n\tEnter choice: ");
                choice = (Integer.parseInt(scan.nextLine()));
                failed = false;
            }catch (Exception e){
                System.out.println("\tNot a valid choice");
                failed = true;
                scan.reset();
            }
        }while(failed);
        return choice;
    }
    public static void inputPartySize(Customer temp){
        Scanner scan = new Scanner(System.in);
        boolean failed = false;
        do{
            try{
                System.out.print("\n\tWhat is the size of the party?: ");
                temp.setPartySize(Integer.parseInt(scan.nextLine()));
                failed = false;
            }catch (Exception e){
                System.out.println("\tNot a valid party size");
                failed = true;
                scan.reset();
            }
        }while(failed);
    }
    public static void inputPriority(Customer temp){
        Scanner scan = new Scanner(System.in);
        boolean failed = false;
        do {
            try {
                System.out.print("\tEnter a choice: ");
                temp.setPriority(computePriority(Integer.parseInt(scan.nextLine()), temp.getPartySize()));
                failed = false;
            } catch (Exception e) {
                System.out.println("\tNot an available choice");
                printPriorityMenu();
                System.out.println("\tTry that again");
                failed = true;
                scan.reset();
            }
        }while(failed);
    }
    public static int computePriority(int in, int partySize) throws Exception{
        if(in > 6 || in <= 0)
            throw new Exception();
        int result = 0;
        if(in == 1)
            result = 7;
        else if(in == 2)
            result = 6;
        else if(in == 3)
            result = 5;
        else if(in == 4)
            result = 4;
        else if(partySize > 4)
            result = 3;
        else if(in == 5)
            result = 2;
        else if(in == 6)
            result = 1;
        return result;
    }
}
