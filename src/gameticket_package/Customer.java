package gameticket_package;
import java.util.*;

public class Customer {
   private int getCustomerId,betedValue;
    private Vector<Game> reservedGames = new Vector<Game>();

    public int getCustomerId() {
        return getCustomerId;
    }

    public void setCustomerId(int customerId) {
        this.getCustomerId = customerId;
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
        this.getCustomerId = customerId;
        betedValue=0;
    }
}
