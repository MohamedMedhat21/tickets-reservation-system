package gameticket_package;

import java.time.LocalDateTime;

public class NationalGame extends Game {

    public NationalGame(String location, String matchTeams, LocalDateTime date, int gameCode, int firstClass, int secondClass, int thirdClass) {
        super(location, matchTeams, date, gameCode, firstClass, secondClass, thirdClass);
    }
}
