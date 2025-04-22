package com.pluralsight;

public class Main {
    public static void main(String[] args) {


       Book book = new Book(1, "dsjkdhaskjdhaks", "The Book Title");
       book.checkOut("Tony");
       book.checkIn();
    }
}