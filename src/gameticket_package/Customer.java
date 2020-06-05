package gameticket_package;

import java.util.*;

public class Customer {
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
     *  Here is one of SOLID principles in action which is single responsibility
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
