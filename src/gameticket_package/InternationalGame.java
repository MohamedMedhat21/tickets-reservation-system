package gameticket_package;

import java.time.LocalDateTime;

public class InternationalGame extends Game{
    /**
     *  Applying Inheritance
     */
    public InternationalGame(String location, String team1, String team2, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        super(location, team1, team2, date, gameCode, firstClass, secondClass, thirdClass);
    }
}
