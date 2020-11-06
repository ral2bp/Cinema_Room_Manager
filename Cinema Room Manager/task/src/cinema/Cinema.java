package cinema;

import java.util.Scanner;

public class Cinema {
    final static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        TheaterRoom room = createTheaterRoom();

        while(true) {
            printMenu();

            int input = s.nextInt();

            switch (input) {
                case 1:
                    room.printLayout();
                    break;
                case 2:
                    boolean saleSuccessful = false;
                    do {
                        System.out.println("Enter a row number:");
                        int selectedRow = s.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int selectedSeat = s.nextInt();
                        saleSuccessful = room.seatSoldAsExistsAndFree(selectedRow, selectedSeat);
                    }
                    while(saleSuccessful == false);
                    break;
                case 3:
                    room.printStatistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    static TheaterRoom createTheaterRoom() {
        System.out.println("Enter the number of rows:");
        int numberOfRows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = s.nextInt();

        return new TheaterRoom(numberOfRows, seatsPerRow);
    }

    static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }



}
