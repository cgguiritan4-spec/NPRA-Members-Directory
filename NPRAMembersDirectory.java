import java.util.Scanner;
import java.util.regex.Pattern;

public class NPRAMembersDirectory {

    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;

    static String[] names = new String[MAX];
    static String[] contacts = new String[MAX];
    static String[] addresses = new String[MAX];
    static int[] hogs = new int[MAX];

    static int count = 0;

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = getMenuChoice();

            switch (choice) {
                case 1: addMember(); break;
                case 2: searchMemberByName(); break;
                case 3: editMember(); break;
                case 4: displayAllMembers(); break;
                case 5: computeAverageHogs(); break;
                case 6: System.out.println("Program exited. Salamat!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void displayMenu() {
        System.out.println("\n--- NPRA MEMBERS DIRECTORY SYSTEM ---");
        System.out.println("1. Add Member");
        System.out.println("2. Search Member by Name");
        System.out.println("3. Edit Member Information");
        System.out.println("4. Display All Members");
        System.out.println("5. Compute Average Number of Hogs");
        System.out.println("6. Exit Program");
        System.out.print("Enter choice: ");
    }

    static int getMenuChoice() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter number only: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static void addMember() {
        sc.nextLine();

        if (count >= MAX) {
            System.out.println("Directory full!");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine();
        if (!validateName(name)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.print("Contact Number: ");
        String contact = sc.nextLine();
        if (!validateContactNumber(contact)) {
            System.out.println("Invalid contact number!");
            return;
        }

        System.out.print("Address: ");
        String address = sc.nextLine();
        if (!validateAddress(address)) {
            System.out.println("Invalid address!");
            return;
        }

        System.out.print("Number of Hogs: ");
        int hog = sc.nextInt();

        names[count] = name;
        contacts[count] = contact;
        addresses[count] = address;
        hogs[count] = hog;

        count++;
        System.out.println("Member added successfully!");
    }

    static void searchMemberByName() {
        sc.nextLine();
        System.out.print("Enter name to search: ");
        String search = sc.nextLine();

        boolean found = true;

        System.out.println("NAME  CONTACT  ADDRESS  HOGS");
        System.out.println("--------------------------------------");

        for (int i = 0; i < count; i++) {
            if (names[i].toLowerCase().contains(search.toLowerCase())) {
                displayMember (i);
                found = false;
            }
        }

        if (!found) {
            System.out.println("Member not found.");
        }
    }

    static void editMember() {
        sc.nextLine();
        System.out.print("Enter name to edit: ");
        String search = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (names[i].equalsIgnoreCase(search)) {

                System.out.print("New Contact Number: ");
                contacts[i] = sc.nextLine();

                System.out.print("New Address: ");
                addresses[i] = sc.nextLine();

                System.out.print("New Number of Hogs: ");
                hogs[i] = sc.nextInt();

                System.out.println("Member information updated!");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    static void displayAllMembers() {
        if (count == 0) {
            System.out.println("No records available.");
            return;
        }

        System.out.println("NAME | CONTACT | ADDRESS | HOGS");
        System.out.println("--------------------------------------");

        for (int i = 0; i < count; i++) {
            displayMember(i);
        }
    }

    static void computeAverageHogs() {
        if (count == 0) {
            System.out.println("No members available.");
            return;
        }

        int total = 0;
        for (int i = 0; i < count; i++) {
            total += hogs[i];
        }

        double average = (double) total / count;
        System.out.println("Average number of hogs: " + average);
    }

    static void displayMember(int i) {
        System.out.println(
            names[i] + " | " +
            contacts[i] + " | " +
            addresses[i] + " | " +
            hogs[i]
        );
    }

    static boolean validateName(String name) {
        return Pattern.matches("[A-Za-z ]+", name);
    }

    static boolean validateContactNumber(String contact) {
        return Pattern.matches("09\\d{9}", contact);
    }

    static boolean validateAddress(String address) {
        return address.length() >= 5;
    }
}