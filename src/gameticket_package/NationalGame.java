package gameticket_package;

import java.time.LocalDateTime;

public class NationalGame extends Game {

    /**
     *  Applying Inheritance
     */
    public NationalGame(String location, String team1, String team2, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        super(location, team1, team2, date, gameCode, firstClass, secondClass, thirdClass);
    }
}
