package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    private static Book[] library = getPopulatedBooks();
    private static Console console = new Console();

    public static void main(String[] args) {
        showScreenHome();

    }


    private static Book[] getPopulatedBooks() {

        try {
            FileReader fr = new FileReader("books.txt");
            BufferedReader reader = new BufferedReader(fr);

            Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while ((dataString = reader.readLine()) != null) {

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;
            }
            Book[] bookFinal = Arrays.copyOf(booksTemp, size);

            return bookFinal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private static Book getBookFromEncodedString(String encodedBook) {
        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;

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
            option = console.promptForInt(homeScreenPrompt);
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
        String option;
        String input;
        String name;

        do{
            showAvailableBooks();


            option = console.promptForString(AvailableBooksScreenPrompt);

            if(option.equals("1")){
                showScreenCheckOutBookYes();

            }  else if (option.equals("0")) {
                System.out.println("Returning to Home Screen");
            }
            else {
                System.out.println("Not a valid option, please try again");
            }
        } while(!option.equals("0"));

    }


    private static void showScreenCheckedOutBooks() {

        System.out.println("Here Are The Available Books!");

        String checkOutScreenPrompt = "What would you like to do?: \n" +
                " 1 - Check in Book \n" +
                " 0 - Go Back To The Home Screen \n" +
                "(1, 0): ";


        //Displays checked out books with the name of the person who checked it out
        System.out.println("Checked out Books: ");
        showCheckedOutBooks();
        String name;
        String input;
        int options;


        do{

            input = console.promptForString(checkOutScreenPrompt);

            if(input.equals("1")) {

                int bookId = console.promptForInt("Enter the Book ID ");
                Book theSelectedBook = getBookById(library, bookId);
                assert theSelectedBook != null;

                theSelectedBook.checkIn();
                System.out.printf("You Have Checked In Book ID Number %d Successfully. \n", bookId);

                showCheckedOutBooks();

            }else{
                System.out.println("Book ID not found");
            }

        }while (!input.equals("0"));

    }

    private static void showCheckedOutBooks(){
        System.out.println(Book.getFormattedBookCheckOutHeader());
        for (int i = 0; i < library.length; i++) {
            Book book = library[i];
            if (book.isCheckedOut()) {
                System.out.println(book.getFormattedBookCheckOut());
            }
        }
    }

    private static void showScreenCheckOutBookYes(){


        String name = console.promptForString("What is your name: ");
        System.out.println("Which book do you want to check out?");
        int bookId = console.promptForInt("Enter BookID: ");


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


//        Book[] library = new Book[20];
//
//        library[0] = new Book(1, "978-1-78862-355-1", "Nginx HTTP Server");
//        library[1] = new Book(2, "978-0-321-35668-0", "Clean Code");
//        library[2] = new Book(3, "978-1-4919-1889-0", "Fluent Python");
//        library[3] = new Book(4, "978-1-4919-1880-7", "Python Cookbook");
//        library[4] = new Book(5, "978-0-596-52068-7", "Head First Design Patterns");
//        library[5] = new Book(6, "978-0-13-468599-1", "Effective Java");
//        library[6] = new Book(7, "978-0-13-235088-4", "Clean Architecture");
//        library[7] = new Book(8, "978-1-4919-1881-4", "Learning Python");
//        library[8] = new Book(9, "978-1-59327-599-0", "Automate the Boring Stuff with Python");
//        library[9] = new Book(10, "978-0-262-03384-8", "Introduction to Algorithms");
//        library[10] = new Book(11, "978-1-4919-5609-0", "Designing Data-Intensive Applications");
//        library[11] = new Book(12, "978-1-59327-928-8", "Eloquent JavaScript");
//        library[12] = new Book(13, "978-1-4493-6132-7", "You Don’t Know JS Yet");
//        library[13] = new Book(14, "978-1-4842-0070-4", "Pro Git");
//        library[14] = new Book(15, "978-1-59327-144-2", "Hacking: The Art of Exploitation");
//        library[15] = new Book(16, "978-0-13-110362-7", "The C Programming Language");
//        library[16] = new Book(17, "978-1-59327-733-8", "The Linux Command Line");
//        library[17] = new Book(18, "978-1-4919-1881-4", "Deep Learning with Python");
//        library[18] = new Book(19, "978-1-4493-9306-9", "High Performance Browser Networking");
//        library[19] = new Book(20, "978-1-4919-5246-7", "Kubernetes Up & Running");