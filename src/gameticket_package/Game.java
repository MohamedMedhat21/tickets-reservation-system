package gameticket_package;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public abstract class Game {
    private String location,matchTeams;
    private LocalDateTime date;
    private int gameCode,firstClass,secondClass,thirdClass,seatsNum=12;
    Seat seats[]=new Seat[seatsNum];

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(String matchTeams) {
        this.matchTeams = matchTeams;
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

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    public Game(String location, String matchTeams, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        this.location = location;
        this.matchTeams=matchTeams;
        this.date = date;
        this.gameCode = gameCode;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.thirdClass = thirdClass;
        for(int i=0;i<4;i++){
            seats[i]=new Seat(i+1,10);
            seats[i].setAvailability(false);
        }
        for(int i=4;i<8;i++){
            seats[i]=new Seat(i+1,20);
            seats[i].setAvailability(false);
        }
        for(int i=8;i<12;i++){
            seats[i]=new Seat(i+1,30);
            seats[i].setAvailability(false);
        }
    }


}
