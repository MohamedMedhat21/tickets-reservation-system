package gameticket_package;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
            customers[i] = new Customer(0);
        }
        LocalDateTime then = LocalDateTime.of(2020, Month.AUGUST, 1, 18, 00);
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
                System.out.println("invalid input");
            }
            if (inputNum == 1) {
                int gameIndex = gameFound(game);
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
                    if (customers[i].getCustomerId() == 0) {
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
                    int in = -1;
                    try {
                        in = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                    if (in == 1) {
                        int gameIndex = gameFound(game);
                        if (gameIndex != 0) {
                            Game.gameReserve(customers, id, game, gameIndex);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in == 2) {
                        int gameIndex = gameFound(game);
                        if (gameIndex != 0) {
                            System.out.println("enter your reserved seat number");
                            int inreservedSeat = scanner.nextInt();
                            game[gameIndex].gameCancel(customers, id, game, gameIndex, inreservedSeat);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in == 3) {
                        int gameIndex = gameFound(game);
                        if (gameIndex != 0) {
                            System.out.println("enter your reserved seat number");
                            int inreservedSeat = scanner.nextInt();
                            Game.gameUpgrade(game, gameIndex, inreservedSeat);
                        } else
                            System.out.println("The game code is not found");
                    } else if (in == 4) {
                        int gameIndex = gameFound(game);
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

                    }
                    else if(in==5)
                    {
                        customers[id].displayReservedMatches();
                    }
                } else
                    throw new MyExecption();
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

    /**
     * Applying final and Static method
     * gamefound :before applying any reservation actions we must search for the requested game to ensure it's existence
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
        }
        for (int i = 1; i <= 2; i++) {
            if (game[i].getGameCode() == inGameCode) {
                return i;
            }
        }
        return 0;
    }
}