package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Book[] library = getPopulatedBooks();

    public static void main(String[] args) {
        showScreenHome();

    }


    private static Book[] getPopulatedBooks() {
        Book[] library = new Book[20];

        library[0] = new Book(1, "978-1-78862-355-1", "Nginx HTTP Server");
        library[1] = new Book(2, "978-0-321-35668-0", "Clean Code");
        library[2] = new Book(3, "978-1-4919-1889-0", "Fluent Python");
        library[3] = new Book(4, "978-1-4919-1880-7", "Python Cookbook");
        library[4] = new Book(5, "978-0-596-52068-7", "Head First Design Patterns");
        library[5] = new Book(6, "978-0-13-468599-1", "Effective Java");
        library[6] = new Book(7, "978-0-13-235088-4", "Clean Architecture");
        library[7] = new Book(8, "978-1-4919-1881-4", "Learning Python");
        library[8] = new Book(9, "978-1-59327-599-0", "Automate the Boring Stuff with Python");
        library[9] = new Book(10, "978-0-262-03384-8", "Introduction to Algorithms");
        library[10] = new Book(11, "978-1-4919-5609-0", "Designing Data-Intensive Applications");
        library[11] = new Book(12, "978-1-59327-928-8", "Eloquent JavaScript");
        library[12] = new Book(13, "978-1-4493-6132-7", "You Don’t Know JS Yet");
        library[13] = new Book(14, "978-1-4842-0070-4", "Pro Git");
        library[14] = new Book(15, "978-1-59327-144-2", "Hacking: The Art of Exploitation");
        library[15] = new Book(16, "978-0-13-110362-7", "The C Programming Language");
        library[16] = new Book(17, "978-1-59327-733-8", "The Linux Command Line");
        library[17] = new Book(18, "978-1-4919-1881-4", "Deep Learning with Python");
        library[18] = new Book(19, "978-1-4493-9306-9", "High Performance Browser Networking");
        library[19] = new Book(20, "978-1-4919-5246-7", "Kubernetes Up & Running");


        return library;
    }

    private static void showAvailableBooks(){
        int i;
        System.out.println(Book.getFormattedBookTextHeader());
        for (i = 0; i < library.length; i++) {
            Book book = library[i];
            if(!book.isCheckedOut()){
                System.out.println(book.getFormattedBookText());
            }

        }

    }

    private static void showScreenHome() {

        String homeScreenPrompt = "Welcome to the library!\n" +
                "Please select an option from the following: \n" +
                " 1 - Show Available Books \n" +
                " 2 - Show Checked Out Books \n" +
                " 0 - Exit App \n" +
                "(1, 2, 0):";
        int option;

        do{
            System.out.print(homeScreenPrompt);
            option = scanner.nextInt();
            scanner.nextLine();
            if(option == 1){
                showScreenAvailableBooks();
            } else if (option == 2)  {
                showScreenCheckedOutBooks();

            } else if (option == 0) {
                System.out.println("Exiting the library, have a nice day");
            }
            else {
                System.out.println("Not a valid option, please try again");
            }
        } while(option != 0);
    }


    private static void showScreenAvailableBooks2(){
        //greet user to this menu
        System.out.println("AvailableBooks");

        // Show all the books that are currently available
        //showAvailableBooks();
        // if yes - prompt for which book and who will check it out then modify data to reflect current state of app

        //keep asking until a no

        // oh no, exit to main.
    }
    private static void showScreenAvailableBooks() {
        String AvailableBooksScreenPrompt = "Here Are The Available Books!\n" +
                "What would you like to do?: \n" +
                " 1 - Check Out A Book \n" +
                " 0 - Return to Home Screen \n" +
                "(1, 0): ";

        int chooseBook;
        int option;
        String input;
        String name;

        do{
            showAvailableBooks();
            System.out.print(AvailableBooksScreenPrompt);
            option = scanner.nextInt();
            scanner.nextLine();

            if(option == 1){
                showScreenCheckOutBookYes();

            }  else if (option == 0) {
                System.out.println("Returning to Home Screen");
            }
            else {
                System.out.println("Not a valid option, please try again");
            }
        } while(option != 0);

    }


    private static void showScreenCheckedOutBooks() {

        System.out.println("Here Are The Available Books!");

        String checkOutScreenPrompt = "What would you like to do?: \n" +
                " 1 - Check in Book \n" +
                " 0 - Go Back To The Home Screen \n" +
                "(1, 0): ";


        //Displays checked out books with the name of the person who checked it out
        System.out.println("Checked out Books: ");
        for (int i = 0; i < library.length; i++) {
            Book book = library[i];
            if (book.isCheckedOut()) {
                System.out.println(book.getFormattedBookCheckOut());
            }
        }
        String name;
        String input;
        int options;


        do{
            System.out.print(checkOutScreenPrompt);
            input = scanner.nextLine();

            if(input.equals("1")) {
                System.out.print("Enter the Book ID ");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                Book theSelectedBook = getBookById(library, bookId);
                assert theSelectedBook != null;

                theSelectedBook.checkIn();
                System.out.printf("You have Checked In Book ID Number %d Successfully. \n", bookId);

                //Re-displays Books that are checked out
                for (int i = 0; i < library.length; i++) {
                    Book book = library[i];
                    if (book.isCheckedOut()) {
                        System.out.println(" " + book.getId() + " " + book.getTitle() + " " + book.getIsbn() + " " + book.getCheckedOutTo());
                    }
                }

            }else{
                System.out.println("Book ID not found");
            }

        }while (!input.equals("0"));

    }

    private static void showScreenCheckOutBookYes(){
        String name;
        System.out.print("Enter your Name: ");
        name = scanner.nextLine();

        System.out.println("Which book do you want to check out?");
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book theSelectedBook = getBookById(library, bookId);
        assert theSelectedBook != null;

        theSelectedBook.checkOut(name.trim().toUpperCase());

        System.out.printf("%s you have checkout out successfully.\n", name.trim().toUpperCase());

    }

    private static Book getBookById(Book[] books, int id){
        for(Book book : books){
            if( book.getId() == id){
                return book;
            }
        }
        return null;
    }


}


