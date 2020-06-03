package gameticket_package;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GameTicket {

    public static void main(String[] args) {
        Game[] game = new Game[3];
        Customer[] customers = new Customer[100];
        for (int i = 0; i < 100; i++) {
            customers[i] = new Customer(0);
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = LocalDateTime.of(2020, Month.JUNE, 1, 18, 00);
        game[1] = new NationalGame("NadiAljazeraa", "AlAhlyVSAlZamalek", then, 1, 30, 20, 10);
        game[2] = new InternationalGame("CairoStadium", "EgyptVSGhana", now, 2, 30, 20, 10);
        while (true) {
            System.out.println("Welcome to Game Ticket Reservation\nThe available matches:");
            System.out.println("Game\t\t\t\t\tGame code\t\t\tavailability");
            for (int i = 1; i <= 2; i++) {
                if (game[i].getSeatsNum() > 0)
                    System.out.println(game[i].getMatchTeams() + "\t\t\t" + game[i].getGameCode() + "\t\t\t\tOpened");
                else
                    System.out.println(game[i].getMatchTeams() + "\t\t\t" + game[i].getGameCode() + "\t\t\t\tFull");
            }
            System.out.println("------------------------------------------------------");
            System.out.println("To check the available seats for specific game enter 1");
            System.out.println("To register enter 2\nTo login enter 3\nTo exit the system enter 0");
            Scanner scanner = new Scanner(System.in);
            int inputNum = scanner.nextInt();
            if (inputNum == 1) {
                System.out.println("enter game code");
                int in = scanner.nextInt();
                boolean found = false;
                for (int i = 1; i <= 2; i++) {
                    if (game[i].getGameCode() == in) {
                        if (game[i].getSeatsNum() > 0) {
                            System.out.println("these are the available seats");
                            for (int j = 0; j < 12; j++) {
                                if (!game[i].seats[j].getAvailability())
                                    System.out.print(j + 1+" ");
                            }
                        } else
                            System.out.println("Sorry this game is Full");
                        found = true;
                        break;
                    }
                }
                if (!found)
                    System.out.println("The game code is not found");
            } else if (inputNum == 2) {
                System.out.print("Registration success! your ID is ");
                for (int i = 0; i < 100; i++) {
                    if (customers[i].getCustomerId() == 0) {
                        customers[i].setCustomerId(i + 1);
                        System.out.println(i + 1);
                        break;
                    }
                }
            } else if (inputNum == 3) {
                System.out.println("enter your ID");
                boolean found = false;
                int id = scanner.nextInt();
                for (int i = 0; i < 100; i++) {
                    if (customers[i].getCustomerId() == id) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Logged Successful");
                    System.out.println("To reserve a ticket enter 1");
                    System.out.println("To cancel a reservation enter 2");
                    System.out.println("To upgrade your seat category enter 3");
                    System.out.println("To make a bet for the game result enter 4");
                    int in = scanner.nextInt();
                    if (in == 1) {
                        System.out.println("enter game code");
                        int inGameCode = scanner.nextInt();
                        found = false;
                        for (int i = 1; i <= 2; i++) {
                            if (game[i].getGameCode() == inGameCode) {
                                if (game[i].getSeatsNum() > 0) {
                                    System.out.println("Game Info");
                                    System.out.println("Teams: " + game[i].getMatchTeams());
                                    System.out.println("Location: " + game[i].getLocation());
                                    System.out.println("Date: " + game[i].getDate());
                                    System.out.println("------------------------------------------");
                                    System.out.println("Please select the desired category");
                                    System.out.println("third category for " + game[i].getThirdClass() + " $ enter 1");
                                    System.out.println("second category for " + game[i].getSecondClass() + " $ enter 2");
                                    System.out.println("first category for " + game[i].getFirstClass() + " $ enter 3");
                                    int inClass = scanner.nextInt();
                                    boolean canReserve = true;
                                    for (int k = 0; k < customers[id].getReservedGames().size(); k++) {
                                        if (ChronoUnit.MINUTES.between(customers[id].getReservedGames().elementAt(k).getDate(), now) <= 90) {
                                            System.out.println("Sorry you can't reserve two games at the same time");
                                            canReserve = false;
                                        }
                                    }
                                    if (canReserve) {
                                        boolean reserved = false;
                                        System.out.println("these are the available seats in this category choose your seat");
                                        if (inClass == 1) {
                                            for (int k = 0; k < 4; k++) {
                                                if (!game[i].seats[k].getAvailability()) {
                                                    System.out.println(k + 1);
                                                }
                                            }
                                            int chosenSeat = scanner.nextInt();
                                            if (!game[i].seats[chosenSeat - 1].getAvailability()) {
                                                game[i].seats[chosenSeat - 1].setAvailability(true);
                                                reserved = true;
                                            } else
                                                System.out.println("invalid seat is reserved or not found");
                                        } else if (inClass == 2) {
                                            for (int k = 4; k < 8; k++) {
                                                if (!game[i].seats[k].getAvailability()) {
                                                    System.out.println(k + 1);
                                                }
                                            }
                                            int chosenSeat = scanner.nextInt();
                                            if (!game[i].seats[chosenSeat - 1].getAvailability()) {
                                                game[i].seats[chosenSeat - 1].setAvailability(true);
                                                reserved = true;
                                            } else
                                                System.out.println("invalid seat is reserved or not found");
                                        } else if (inClass == 3) {
                                            for (int k = 8; k < 12; k++) {
                                                if (!game[i].seats[k].getAvailability()) {
                                                    System.out.println(k + 1);
                                                }
                                            }
                                            int chosenSeat = scanner.nextInt();
                                            if (!game[i].seats[chosenSeat - 1].getAvailability()) {
                                                game[i].seats[chosenSeat - 1].setAvailability(true);
                                                reserved = true;
                                            } else
                                                System.out.println("invalid seat is reserved or not found");
                                        }
                                        if (reserved) {
                                            customers[id].getReservedGames().addElement(game[inGameCode]);
                                            game[inGameCode].setSeatsNum(game[inGameCode].getSeatsNum() - 1);
                                            //game[inGameCode].seatsNum--;
                                        }
                                    }
                                } else
                                    System.out.println("Sorry this game is Full");
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("The game code is not found");
                    } else if (in == 2) {
                        System.out.println("enter game code");
                        int inGameCode = scanner.nextInt();
                        found = false;
                        for (int i = 1; i <= 2; i++) {
                            if (game[i].getGameCode() == inGameCode) {
                                System.out.println("enter your reserved seat number");
                                int inreservedSeat = scanner.nextInt();
                                boolean foundSeat = false;
                                for (int j = 0; j < 12; j++) {
                                    if (game[i].seats[j].getSeatNum() == inreservedSeat) {
                                        foundSeat = true;
                                        if (game[i].seats[j].getAvailability()) {
                                            if (Math.abs(ChronoUnit.DAYS.between(game[i].getDate(), now)) <= 3) {
                                                System.out.println("Sorry you can not cancel your reservation because the game will start in less than 3 days");
                                            } else {
                                                game[i].seats[j].setAvailability(false);
                                                game[i].setSeatsNum(game[i].getSeatsNum() + 1);
                                                //game[i].seatsNum++;
                                                customers[id].getReservedGames().remove(game[inGameCode]);
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
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("The game code is not found");
                    } else if (in == 3) {
                        System.out.println("enter game code");
                        int inGameCode = scanner.nextInt();
                        found = false;
                        for (int i = 1; i <= 2; i++) {
                            if (game[i].getGameCode() == inGameCode) {
                                System.out.println("enter your reserved seat number");
                                int inreservedSeat = scanner.nextInt();
                                boolean foundSeat = false, upgraded = false;
                                for (int j = 0; j < 12; j++) {
                                    if (game[i].seats[j].getSeatNum() == inreservedSeat) {
                                        foundSeat = true;
                                        if (game[i].seats[j].getAvailability()) {
                                            if (j >= 0 && j < 4) {
                                                System.out.println("choose 2nd category for 20 $ enter 1 or 1st category for 30 $ enter 2");
                                                int inClass = scanner.nextInt();
                                                if (inClass == 1) {
                                                    System.out.println("these are the available seats in this category choose your seat");
                                                    for (int k = 4; k < 8; k++) {
                                                        if (!game[i].seats[k].getAvailability()) {
                                                            System.out.println(k + 1);
                                                        }
                                                    }
                                                    int chosenSeat = scanner.nextInt();
                                                    game[i].seats[chosenSeat - 1].setAvailability(true);
                                                    upgraded = true;
                                                } else if (inClass == 2) {
                                                    System.out.println("these are the available seats in this category choose your seat");
                                                    for (int k = 8; k < 12; k++) {
                                                        if (!game[i].seats[k].getAvailability()) {
                                                            System.out.println(k + 1);
                                                        }
                                                    }
                                                    int chosenSeat = scanner.nextInt();
                                                    game[i].seats[chosenSeat - 1].setAvailability(true);
                                                    upgraded = true;
                                                }
                                            } else if (j >= 4 && j < 8) {
                                                System.out.println("choose 1st category for 30 $ enter 1");
                                                int inClass = scanner.nextInt();
                                                if (inClass == 1) {
                                                    System.out.println("these are the available seats in this category choose your seat");
                                                    for (int k = 8; k < 12; k++) {
                                                        if (!game[i].seats[k].getAvailability()) {
                                                            System.out.println(k + 1);
                                                        }
                                                    }
                                                    int chosenSeat = scanner.nextInt();
                                                    game[i].seats[chosenSeat - 1].setAvailability(true);
                                                    upgraded = true;
                                                }
                                            } else
                                                System.out.println("Sorry you can not downgrade your ticket");
                                        } else {
                                            System.out.println("Sorry you did not reserve this seat");
                                        }
                                        if (upgraded) {
                                            game[i].seats[j].setAvailability(false);
                                        }
                                    }
                                }
                                if (!foundSeat) {
                                    System.out.println("Seat number is invalid");
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("The game code is not found");
                    } else if (in == 4) {
                        System.out.println("enter game code");
                        int inGameCode = scanner.nextInt();
                        found = false;
                        for (int i = 1; i <= 2; i++) {
                            if (game[i].getGameCode() == inGameCode) {
                                System.out.println("enter your bet value");
                                customers[id].setBetedValue(scanner.nextInt());
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("The game code is not found");
                    }
                } else
                    System.out.println("ID is Not Found");
            } else if (inputNum == 0) {
                System.out.println("Thanks for using our system");
                break;
            }
            System.out.println("Do you want to check any thing else ? enter y/n");
            char yesOrNo = scanner.next().charAt(0);
            if (yesOrNo == 'N' || yesOrNo == 'n') {
                System.out.println("Thanks for using our system");
                break;
            }
        }

    }
}