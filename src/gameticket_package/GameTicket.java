package gameticket_package;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

class MyExecption extends Exception {
    /**
     * Applying the user-defined Exceptions
     * Applying Overriding
     */
    @Override
    public String toString() {
        return "ID is Not Found";
    }
}

class Customer {
    private int CustomerId, betedValue, winChosenTeam;
    private Vector<Game> reservedGames = new Vector<Game>();

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    public int getWinChosenTeam() {
        return winChosenTeam;
    }

    public void setWinChosenTeam(int winChosenTeam) {
        this.winChosenTeam = winChosenTeam;
    }

    public int getBetedValue() {
        return betedValue;
    }

    public void setBetedValue(int betedValue) {
        this.betedValue = betedValue;
    }

    public Vector<Game> getReservedGames() {
        return reservedGames;
    }

    public Customer(int customerId) {
        this.CustomerId = customerId;
        betedValue = 0;
    }

    /**
     * This function displays the customer reserved matches
     * Here is one of SOLID principles in action which is single responsibility
     */
    public void displayReservedMatches() {
        if (this.reservedGames.size() == 0)
            System.out.println("you did not reserve any matches");
        else {
            for (int i = 0; i < this.reservedGames.size(); i++) {
                System.out.println("Game Info");
                System.out.println("Teams: " + reservedGames.elementAt(i).getTeam1() + " VS " + reservedGames.elementAt(i).getTeam2());
                System.out.println("Location: " + reservedGames.elementAt(i).getLocation());
                System.out.println("Date: " + reservedGames.elementAt(i).getDate());
            }
        }
    }
}

abstract class Game implements GameInterface {
    /**
     * Applying Abstract class and  Different Access modifiers
     */
    private String location, team1, team2;
    private LocalDateTime date;
    private int gameCode, firstClass, secondClass, thirdClass, fSeatsNum = 4, sSeatsNum = 4, tSeatsNum = 4;
    private static Scanner scanner = new Scanner(System.in);
    final static LocalDateTime now = LocalDateTime.now();

    Seat seats[] = new Seat[fSeatsNum + sSeatsNum + tSeatsNum];

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
        this.gameCode = gameCode;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }

    public int getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(int secondClass) {
        this.secondClass = secondClass;
    }

    public int getThirdClass() {
        return thirdClass;
    }

    public void setThirdClass(int thirdClass) {
        this.thirdClass = thirdClass;
    }

    public int getfSeatsNum() {
        return fSeatsNum;
    }

    public void setfSeatsNum(int fSeatsNum) {
        this.fSeatsNum = fSeatsNum;
    }

    public int getsSeatsNum() {
        return sSeatsNum;
    }

    public void setsSeatsNum(int sSeatsNum) {
        this.sSeatsNum = sSeatsNum;
    }

    public int gettSeatsNum() {
        return tSeatsNum;
    }

    public void settSeatsNum(int tSeatsNum) {
        this.tSeatsNum = tSeatsNum;
    }

    public Game(String location, String team1, String team2, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        this.location = location;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.gameCode = gameCode;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.thirdClass = thirdClass;
        for (int i = 0; i < 4; i++) {
            seats[i] = new Seat(i + 1, 10);
            seats[i].setAvailability(false);
        }
        for (int i = 4; i < 8; i++) {
            seats[i] = new Seat(i + 1, 20);
            seats[i].setAvailability(false);
        }
        for (int i = 8; i < 12; i++) {
            seats[i] = new Seat(i + 1, 30);
            seats[i].setAvailability(false);
        }
    }

    /**
     * gameReserve and displayAndReserve : this method reserves the required seat to the customer
     */
    static void displayAndReserve(Game[] game, int gameIndex, int start, int end, Customer[] customers, int id) {
        if (game[gameIndex].gettSeatsNum() <= 0) {
            System.out.println("sorry this category is full");
        } else {
            System.out.println("these are the available seats in this category choose your seat");
            for (int k = start; k < end; k++) {
                if (!game[gameIndex].seats[k].getAvailability()) {
                    System.out.println(k + 1);
                }
            }
            int chosenSeat = -1;
            try {
                chosenSeat = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("invalid input");
                scanner.reset();
                scanner.next();
            }
            if (!game[gameIndex].seats[chosenSeat - 1].getAvailability()) {
                game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
                game[gameIndex].settSeatsNum(game[gameIndex].gettSeatsNum() - 1);
                System.out.println("Game reserved Successfully");
                game[gameIndex].seats[chosenSeat - 1].setCustomerID(id);
                customers[id].getReservedGames().addElement(game[gameIndex]);
            } else
                System.out.println("invalid, seat is reserved or not found");
        }
    }

    static void gameReserve(Customer customers[], int id, Game game[], int gameIndex) {
        if (game[gameIndex].getfSeatsNum() + game[gameIndex].getsSeatsNum() + game[gameIndex].gettSeatsNum() > 0) {
            System.out.println("Game Info");
            System.out.println("Teams: " + game[gameIndex].getTeam1() + " VS " + game[gameIndex].getTeam2());
            System.out.println("Location: " + game[gameIndex].getLocation());
            System.out.println("Date: " + game[gameIndex].getDate());
            System.out.println("------------------------------------------");
            System.out.println("Please select the desired category");
            System.out.println("third category for " + game[gameIndex].getThirdClass() + " $ enter 1");
            System.out.println("second category for " + game[gameIndex].getSecondClass() + " $ enter 2");
            System.out.println("first category for " + game[gameIndex].getFirstClass() + " $ enter 3");
            int inClass = -1;
            try {
                inClass = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("invalid input");
                scanner.reset();
                scanner.next();
            }
            boolean canReserve = true;
            for (int k = 0; k < customers[id].getReservedGames().size(); k++) {
                if (ChronoUnit.MINUTES.between(customers[id].getReservedGames().elementAt(k).getDate(), now) <= 90) {
                    System.out.println("Sorry you can't reserve two games at the same time");
                    canReserve = false;
                }
            }
            if (canReserve) {
                if (inClass == 1)
                    displayAndReserve(game, gameIndex, 0, 4, customers, id);
                else if (inClass == 2)
                    displayAndReserve(game, gameIndex, 4, 8, customers, id);
                else if (inClass == 3)
                    displayAndReserve(game, gameIndex, 8, 12, customers, id);
            }
        } else
            System.out.println("Sorry this game is Full");
    }

    /**
     * game cancel method : cancels the required seat in the required game
     *
     * @param customers      the customers object
     * @param id             customer id who wants to cancel the game
     * @param game           the game object
     * @param gameIndex      the index of the game we want to cancel the seat in
     * @param inreservedSeat the seat number we want to cancel
     */
    public void gameCancel(Customer customers[], int id, Game game[], int gameIndex, int inreservedSeat) {
        boolean foundSeat = false;
        for (int j = 0; j < 12; j++) {
            if ((game[gameIndex].seats[j].getSeatNum() == inreservedSeat) && (game[gameIndex].seats[j].getCustomerID() == id)) {
                foundSeat = true;
                if (game[gameIndex].seats[j].getAvailability()) {
                    if (Math.abs(ChronoUnit.DAYS.between(game[gameIndex].getDate(), now)) <= 3) {
                        System.out.println("Sorry you can not cancel your reservation because the game will start in less than 3 days");
                    } else {
                        game[gameIndex].seats[j].setAvailability(false);
                        if (j >= 0 && j < 4) {
                            game[gameIndex].settSeatsNum(game[gameIndex].gettSeatsNum() + 1);
                        } else if (j >= 4 && j < 8) {
                            game[gameIndex].setsSeatsNum(game[gameIndex].getsSeatsNum() + 1);
                        } else
                            game[gameIndex].setfSeatsNum(game[gameIndex].getfSeatsNum() + 1);
                        //game[gameIndex].setSeatsNum(game[gameIndex].getSeatsNum() + 1);
                        for (int i = 0; i < customers[id].getReservedGames().size(); i++) {
                            if (customers[id].getReservedGames().elementAt(i).getGameCode() == game[gameIndex].getGameCode()) {
                                customers[id].getReservedGames().remove(i);
                                break;
                            }
                        }
                        game[gameIndex].seats[inreservedSeat].setCustomerID(0);
                        System.out.println("you canceled your reservation");
                    }
                } else {
                    System.out.println("Sorry you did not reserve this seat");
                }
            }
        }
        if (!foundSeat) {
            System.out.println("Seat number is invalid or not reserved by this user");
        }
    }

    /**
     * gameUpgrade : this method upgrades the seat for the customer
     */
    static void displayAndUpgrade(Game[] game, int gameIndex, int start, int end, Customer[] customers, int id) {
        System.out.println("these are the available seats in this category choose your seat");
        for (int k = start; k < end; k++) {
            if (!game[gameIndex].seats[k].getAvailability()) {
                System.out.println(k + 1);
            }
        }
        int chosenSeat = scanner.nextInt();
        game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
        game[gameIndex].seats[chosenSeat - 1].setCustomerID(id);
    }

    static void gameUpgrade(Game game[], int gameIndex, int inreservedSeat, int id, Customer[] customers) {

        boolean foundSeat = false, upgraded = false;
        for (int j = 0; j < 12; j++) {
            if ((game[gameIndex].seats[j].getSeatNum() == inreservedSeat) && (game[gameIndex].seats[j].getCustomerID() == id)) {
                foundSeat = true;
                if (game[gameIndex].seats[j].getAvailability()) {
                    if (j >= 0 && j < 4) {
                        System.out.println("choose 2nd category for 20 $ enter 1 or 1st category for 30 $ enter 2");
                        int inClass = -1;
                        try {
                            inClass = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("invalid input");
                        }
                        if (inClass == 1) {
                            if (game[gameIndex].getsSeatsNum() <= 0) {
                                System.out.println("sorry this category is full");
                            } else {
                                displayAndUpgrade(game, gameIndex, 4, 8, customers, id);
                                upgraded = true;
                            }
                        } else if (inClass == 2) {
                            if (game[gameIndex].getfSeatsNum() <= 0) {
                                System.out.println("sorry this category is full");
                            } else {
                                displayAndUpgrade(game, gameIndex, 8, 12, customers, id);
                                upgraded = true;
                            }
                        }
                    } else if (j >= 4 && j < 8) {
                        System.out.println("choose 1st category for 30 $ enter 1");
                        int inClass = scanner.nextInt();
                        if (inClass == 1) {
                            if (game[gameIndex].gettSeatsNum() <= 0) {
                                System.out.println("sorry this category is full");
                            } else {
                                displayAndUpgrade(game, gameIndex, 8, 12, customers, id);
                                upgraded = true;
                            }
                        }
                    } else
                        System.out.println("Sorry you can not downgrade your ticket");
                } else {
                    System.out.println("Sorry you did not reserve this seat");
                }
                if (upgraded) {
                    game[gameIndex].seats[j].setAvailability(false);
                }
            }
        }
        if (!foundSeat) {
            System.out.println("Seat number is invalid or not reserved by this user");
        }
    }

    /**
     * Applying final and Static method
     * gamefound :before applying any reservation actions we must search for the requested game to ensure it's existence
     *
     * @param game object
     * @return index if the game is found else it returns 0
     */
    final static int gameFound(Game game[]) {
        System.out.println("enter game code");
        int inGameCode = -1;
        /**
         * Applying Java-defined Exception
         */
        try {
            inGameCode = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("invalid input");
            scanner.reset();
            scanner.next();
        }
        for (int i = 1; i <= 2; i++) {
            if (game[i].getGameCode() == inGameCode) {
                return i;
            }
        }
        return 0;
    }
}

class InternationalGame extends Game {
    /**
     * Applying Inheritance
     */
    public InternationalGame(String location, String team1, String team2, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        super(location, team1, team2, date, gameCode, firstClass, secondClass, thirdClass);
    }
}

class NationalGame extends Game {

    /**
     * Applying Inheritance
     */
    public NationalGame(String location, String team1, String team2, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        super(location, team1, team2, date, gameCode, firstClass, secondClass, thirdClass);
    }
}

interface GameInterface {
    /**
     * Applying the interface concept
     */
    void gameCancel(Customer customers[], int id, Game game[], int gameIndex, int inreservedSeat);
}

class Seat {

    private int seatNum, seatPrice, customerID = 0;
    private boolean availability;

    public Seat(int seatNum, int seatPrice) {
        this.seatNum = seatNum;
        this.seatPrice = seatPrice;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(int seatPrice) {
        this.seatPrice = seatPrice;
    }


    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

public class GameTicket {
    /**
     * final data members and static data members
     */
    static Scanner scanner = new Scanner(System.in);
    final static LocalDateTime now = LocalDateTime.now();

    public static void main(String[] args) throws MyExecption {
        Game[] game = new Game[3];
        Customer[] customers = new Customer[100];
        /**
         * initializing the customers array
         */
        for (int i = 0; i < 100; i++) {
            customers[i] = new Customer(500);
        }
        /**
         * here you can edit the time to check the timing validation for canceling the reservation and reserving the same match at the same time
         */
        LocalDateTime then = LocalDateTime.of(2020, Month.JUNE, 10, 18, 00);
        /**
         * Applying Polymorphism
         */
        game[1] = new NationalGame("NadiAljazeraa", "AlAhly", "AlZamalek", then, 1, 30, 20, 10);
        game[2] = new InternationalGame("CairoStadium", "Egypt", "Ghana", now, 2, 30, 20, 10);
        while (true) {
            System.out.println("Welcome to Game Ticket Reservation\nThe available matches:");
            System.out.println("Game\t\t\t\t\tGame code\t\t\tavailability");
            for (int i = 1; i <= 2; i++) {
                if (game[i].getfSeatsNum() + game[i].getsSeatsNum() + game[i].gettSeatsNum() > 0)
                    System.out.println(game[i].getTeam1() + " VS " + game[i].getTeam2() + "\t\t\t" + game[i].getGameCode() + "\t\t\t\tOpened");
                else
                    System.out.println(game[i].getTeam1() + " VS " + game[i].getTeam2() + "\t\t\t" + game[i].getGameCode() + "\t\t\t\tFull");
            }
            System.out.println("------------------------------------------------------");
            System.out.println("To check the available seats for specific game enter 1");
            System.out.println("To register enter 2\nTo login enter 3\nTo exit the system enter 0");
            int inputNum = -1;
            try {
                inputNum = scanner.nextInt();
            } catch (Exception e) {
                scanner.reset();
                scanner.next();
                System.out.println("invalid input");
            }

            if (inputNum == 1) {
                int gameIndex = Game.gameFound(game);
                if (gameIndex != 0) {
                    if (game[gameIndex].getfSeatsNum() + game[gameIndex].getsSeatsNum() + game[gameIndex].gettSeatsNum() > 0) {
                        System.out.println("these are the available seats");
                        for (int j = 0; j < 12; j++) {
                            if (!game[gameIndex].seats[j].getAvailability())
                                System.out.print(j + 1 + " ");
                        }
                    } else
                        System.out.println("Sorry this game is Full");
                } else
                    System.out.println("The game code is not found");
            } else if (inputNum == 2) {
                System.out.print("Registration success! your ID is ");
                for (int i = 0; i < 100; i++) {
                    if (customers[i].getCustomerId() == 500) {
                        customers[i].setCustomerId(i + 1);
                        System.out.println(i + 1);
                        break;
                    }
                }
            } else if (inputNum == 3) {
                System.out.println("enter your ID");
                boolean found = false;
                int id = -1;
                try {
                    id = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("invalid input");
                }
                for (int i = 0; i < 100; i++) {
                    if (customers[i].getCustomerId() == id) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Logged in Successful");
                    System.out.println("To reserve a ticket enter 1");
                    System.out.println("To cancel a reservation enter 2");
                    System.out.println("To upgrade your seat category enter 3");
                    System.out.println("To make a bet for the game result enter 4");
                    System.out.println("To view reserved Matched enter 5");
                    String in = "-1";
                    in = scanner.next();
                    if (in.equals("1")) {
                        int gameIndex = Game.gameFound(game);
                        if (gameIndex != 0) {
                            Game.gameReserve(customers, id, game, gameIndex);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in.equals("2")) {
                        int gameIndex = Game.gameFound(game);
                        if (gameIndex != 0) {
                            System.out.println("enter your reserved seat number");
                            int inreservedSeat = scanner.nextInt();
                            game[gameIndex].gameCancel(customers, id, game, gameIndex, inreservedSeat);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in.equals("3")) {
                        int gameIndex = Game.gameFound(game);
                        if (gameIndex != 0) {
                            System.out.println("enter your reserved seat number");
                            int inreservedSeat = scanner.nextInt();
                            Game.gameUpgrade(game, gameIndex, inreservedSeat, id, customers);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in.equals("4")) {
                        int gameIndex = Game.gameFound(game);
                        if (gameIndex != 0) {
                            if (game[gameIndex].getGameCode() == gameIndex) {
                                System.out.println("enter your bet value");
                                try {
                                    customers[id].setBetedValue(scanner.nextInt());
                                } catch (Exception e) {
                                    System.out.println("invalid input");
                                }
                                System.out.println("chosen the wining team");
                                System.out.println("1 " + game[gameIndex].getTeam1());
                                System.out.println("2 " + game[gameIndex].getTeam2());
                                int winChosenTeam = -1;
                                try {
                                    winChosenTeam = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println("invalid input");
                                }
                                customers[id].setWinChosenTeam(winChosenTeam);
                                winChosenTeam--;
                                Random rand = new Random();
                                int winRandomNum = rand.nextInt(2);
                                if (winRandomNum == 0)
                                    System.out.println(game[gameIndex].getTeam1() + " Wins");
                                else
                                    System.out.println(game[gameIndex].getTeam2() + " Wins");
                                if (winChosenTeam == winRandomNum) {
                                    System.out.println("your team wins! you get the double of your bet which is:" + customers[id].getBetedValue() * 2);
                                    /**
                                     * Applying Calculated data members
                                     */
                                    customers[id].setBetedValue(customers[id].getBetedValue() * 2);
                                } else
                                    System.out.println("Sorry your team loses! you get no thing");
                            }
                        } else
                            System.out.println("The game code is not found");

                    } else if (in.equals("5")) {
                        customers[id].displayReservedMatches();
                    } else
                        System.out.println("invalid input");
                } else
                    throw new MyExecption();
            } else if (inputNum == 0) {
                System.out.println("Thanks for using our system");
                break;
            }
            while (true) {
                System.out.println("Do you want to check any thing else ? enter y/n");
                char yesOrNo = 'y';
                try {
                    yesOrNo = scanner.next().charAt(0);
                } catch (Exception e) {
                    scanner.reset();
                    scanner.next();
                    System.out.println("invalid input");
                }
                System.out.println(yesOrNo);
                if (yesOrNo == 'N' || yesOrNo == 'n') {
                    System.out.println("Thanks for using our system");
                    return;
                } else if (yesOrNo == 'Y' || yesOrNo == 'y')
                    break;
                else
                    System.out.println("invalid input");
            }
        }
    }
    /**
     * References
     * https://www.javatpoint.com/java-get-current-date
     *
     * https://www.javatpoint.com/java-vector#:~:text=Java%20Vector,is%20found%20in%20the%20java.
     *
     * https://beginnersbook.com/2017/10/java-8-calculate-days-between-two-dates/
     */
}