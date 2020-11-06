package cinema;

class TheaterRoom{
    int numberOfRows;
    int seatsPerRow;
    char[][] seats;
    int numberOfPurchasedTickets;
    int currentIncome;

    final int lowPrice = 8;
    final int highPrice = 10;
    final int smallRoomUpperLimit = 60;

    TheaterRoom(int numberOfRows, int seatsPerRow )
    {
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new char[numberOfRows][seatsPerRow];
        this.numberOfPurchasedTickets = 0;

        fillEmptyTheaterRoom();
    }

    private void fillEmptyTheaterRoom() {
        for(int i = 0; i < numberOfRows; i++) {
            for(int j = 0;j < seatsPerRow; j++) {
                seats[i][j] = 'S';
            }
        }
    }

    public void printLayout() {
        System.out.println("Cinema:");
        printHeader();
        printRows();
    }

    private void printHeader() {
        System.out.print(" ");
        for(int j = 0; j < seatsPerRow; j++) {
            System.out.print(" " + (j + 1));
        }
        System.out.print("\n");
    }

    private void printRows() {
        for(int i = 0; i < numberOfRows; i++) {
            System.out.print(i+1); //print row number
            fillRowWithSeats(i);
            System.out.print("\n");
        }
    }

    private void fillRowWithSeats(int which) {
        for(int j = 0; j < seatsPerRow; j++) {
            System.out.print(" " + seats[which][j]);
        }
    }

    public boolean seatSoldAsExistsAndFree(int row, int seat) {
        if(seatDoesNotExist(row, seat)) {
            System.out.println("Wrong input!");
            return false;
        }
        else if(seatAlreadySold(row, seat)) {
            System.out.println("That ticket has already been purchased");
            return false;
        } else {
            storeSeatSold(row, seat);
            return true;
        }
    }

    private boolean seatDoesNotExist(int row, int seat) {
        return row > numberOfRows || row < 1 || seat > seatsPerRow || seat < 1;
    }

    private boolean seatAlreadySold(int row, int seat) {
        return seats[row - 1][seat - 1] == 'B';
    }

    private void storeSeatSold(int row, int seat) {
        seats[row - 1][seat - 1] = 'B';
        this.numberOfPurchasedTickets++;
        printTicketPrice(row);
    }

    public void printTicketPrice(int selectedRow) {
        int ticketPrice = getTicketPrice(selectedRow);
        this.currentIncome += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice );
    }

    private int getTicketPrice(int selectedRow) {
        return (numberOfRows * seatsPerRow > smallRoomUpperLimit && selectedRow > numberOfRows / 2) ? lowPrice : highPrice;
    }

    public void printStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%% %n", getSoldPercentage());
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateTotalIncome());
    }

    float getSoldPercentage()
    {
        return (float) numberOfPurchasedTickets / (float)(numberOfRows * seatsPerRow) * 100.0f;
    }
    int calculateTotalIncome(){
        if(this.numberOfRows * this.seatsPerRow <= smallRoomUpperLimit)
            return this.numberOfRows * this.seatsPerRow * highPrice;
        else
        {
            //division will cut the fracture part
            int expensiveHalf = this.numberOfRows / 2;
            // on odd, the lower int part will result in this being the higher int part
            int cheapHalf = this.numberOfRows - expensiveHalf;
            return (expensiveHalf * seatsPerRow * highPrice + cheapHalf * seatsPerRow * lowPrice);
        }
    }
}
