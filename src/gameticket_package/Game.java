package gameticket_package;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public abstract class Game implements GameInterface {
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
     * gameReserve : this method reserves the required seat to the customer
     */
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
            }
            boolean canReserve = true;
            for (int k = 0; k < customers[id].getReservedGames().size(); k++) {
                if (ChronoUnit.MINUTES.between(customers[id].getReservedGames().elementAt(k).getDate(), now) <= 90) {
                    System.out.println("Sorry you can't reserve two games at the same time");
                    canReserve = false;
                }
            }
            if (canReserve) {
                boolean reserved = false;
                if (inClass == 1) {
                    if (game[gameIndex].gettSeatsNum() <= 0) {
                        System.out.println("sorry this category is full");
                    } else {
                        System.out.println("these are the available seats in this category choose your seat");
                        for (int k = 0; k < 4; k++) {
                            if (!game[gameIndex].seats[k].getAvailability()) {
                                System.out.println(k + 1);
                            }
                        }
                        int chosenSeat = -1;
                        try {
                            chosenSeat = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("invalid input");
                        }
                        if (!game[gameIndex].seats[chosenSeat - 1].getAvailability()) {
                            game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
                            game[gameIndex].settSeatsNum(game[gameIndex].gettSeatsNum() - 1);
                            reserved = true;
                        } else
                            System.out.println("invalid seat is reserved or not found");
                    }
                } else if (inClass == 2) {
                    if (game[gameIndex].getsSeatsNum() <= 0) {
                        System.out.println("sorry this category is full");
                    } else {
                        System.out.println("these are the available seats in this category choose your seat");
                        for (int k = 4; k < 8; k++) {
                            if (!game[gameIndex].seats[k].getAvailability()) {
                                System.out.println(k + 1);
                            }
                        }
                        int chosenSeat = scanner.nextInt();
                        if (!game[gameIndex].seats[chosenSeat - 1].getAvailability()) {
                            game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
                            game[gameIndex].setsSeatsNum(game[gameIndex].getsSeatsNum() - 1);
                            reserved = true;
                        } else
                            System.out.println("invalid seat is reserved or not found");
                    }
                } else if (inClass == 3) {
                    if (game[gameIndex].getfSeatsNum() <= 0) {
                        System.out.println("sorry this category is full");
                    } else {
                        System.out.println("these are the available seats in this category choose your seat");
                        for (int k = 8; k < 12; k++) {
                            if (!game[gameIndex].seats[k].getAvailability()) {
                                System.out.println(k + 1);
                            }
                        }
                        int chosenSeat = scanner.nextInt();
                        if (!game[gameIndex].seats[chosenSeat - 1].getAvailability()) {
                            game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
                            reserved = true;
                            game[gameIndex].setfSeatsNum(game[gameIndex].getfSeatsNum() - 1);
                        } else
                            System.out.println("invalid seat is reserved or not found");
                    }
                }
                if (reserved) {
                    System.out.println("Game reserved Successfully");
                    customers[id].getReservedGames().addElement(game[gameIndex]);
                }
            }
        } else
            System.out.println("Sorry this game is Full");
    }

    /**
     * game cancel method : cancels the required seat in the required game
     * @param customers the customers object
     * @param id customer id who wants to cancel the game
     * @param game the game object
     * @param gameIndex the index of the game we want to cancel the seat in
     * @param inreservedSeat the seat number we want to cancel
     */
    public void gameCancel(Customer customers[], int id, Game game[], int gameIndex, int inreservedSeat) {
        boolean foundSeat = false;
        for (int j = 0; j < 12; j++) {
            if (game[gameIndex].seats[j].getSeatNum() == inreservedSeat) {
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
                        System.out.println("you canceled your reservation");
                    }
                } else {
                    System.out.println("Sorry you did not reserve this seat");
                }
            }
        }
        if (!foundSeat) {
            System.out.println("Seat number is invalid");
        }
    }

    /**
     * gameUpgrade : this method upgrades the seat for the customer
     */
    static void gameUpgrade(Game game[], int gameIndex, int inreservedSeat) {
        boolean foundSeat = false, upgraded = false;
        for (int j = 0; j < 12; j++) {
            if (game[gameIndex].seats[j].getSeatNum() == inreservedSeat) {
                foundSeat = true;
                if (game[gameIndex].seats[j].getAvailability()) {
                    if (j >= 0 && j < 4) {
                        System.out.println("choose 2nd category for 20 $ enter 1 or 1st category for 30 $ enter 2");
                        int inClass = scanner.nextInt();
                        if (inClass == 1) {
                            if (game[gameIndex].getsSeatsNum() <= 0) {
                                System.out.println("sorry this category is full");
                            } else {
                                System.out.println("these are the available seats in this category choose your seat");
                                for (int k = 4; k < 8; k++) {
                                    if (!game[gameIndex].seats[k].getAvailability()) {
                                        System.out.println(k + 1);
                                    }
                                }
                                int chosenSeat = scanner.nextInt();
                                game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
                                upgraded = true;
                            }
                        } else if (inClass == 2) {
                            if (game[gameIndex].getfSeatsNum() <= 0) {
                                System.out.println("sorry this category is full");
                            } else {
                                System.out.println("these are the available seats in this category choose your seat");
                                for (int k = 8; k < 12; k++) {
                                    if (!game[gameIndex].seats[k].getAvailability()) {
                                        System.out.println(k + 1);
                                    }
                                }
                                int chosenSeat = scanner.nextInt();
                                game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
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
                                System.out.println("these are the available seats in this category choose your seat");
                                for (int k = 8; k < 12; k++) {
                                    if (!game[gameIndex].seats[k].getAvailability()) {
                                        System.out.println(k + 1);
                                    }
                                }
                                int chosenSeat = scanner.nextInt();
                                game[gameIndex].seats[chosenSeat - 1].setAvailability(true);
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
            System.out.println("Seat number is invalid");
        }
    }

}
