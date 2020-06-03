package gameticket_package;

public class Seat {
    private int seatNum,seatPrice;
    private boolean availability;

    public Seat(int seatNum, int seatPrice) {
        this.seatNum = seatNum;
        this.seatPrice = seatPrice;
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
