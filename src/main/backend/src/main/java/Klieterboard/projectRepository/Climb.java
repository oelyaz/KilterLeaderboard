package Klieterboard.projectRepository;

import lombok.*;

@Getter
@AllArgsConstructor
public class Climb {

    private String uuid;
    private String difficulty;


    /** Converts the internal kilter difficulty to the fb scale.
     * @param difficulty Internal kilter difficulty.
     * @return The corresponding difficulty on the fb-scale.
     */
    public static String difficultyInFbScala(int difficulty) {
        return switch (difficulty) {
            case 10 -> "4a";
            case 11 -> "4b";
            case 12 -> "4c";
            case 13 -> "5a";
            case 14 -> "5b";
            case 15 -> "5c";
            case 16 -> "6a";
            case 17 -> "6a+";
            case 18 -> "6b";
            case 19 -> "6b+";
            case 20 -> "6c";
            case 21 -> "6c+";
            case 22 -> "7a";
            case 23 -> "7a+";
            case 24 -> "7b";
            case 25 -> "7b+";
            case 26 -> "7c";
            case 27 -> "7c+";
            case 28 -> "8a";
            case 29 -> "8a+";
            case 30 -> "8b";
            case 31 -> "8b+";
            case 32 -> "8c";
            case 33 -> "8c+";
            default -> "not a valid difficulty";
        };
    }


    /**
     * Calculates the score for a given boulder difficulty based on the level of the boulderer.
     * @param userGrade average user grade
     * @param difficulty difficulty of the boulder
     * @return The score for the given boulder.
     */
    public static int scoreForBoulder(int userGrade, int difficulty){
        if(difficulty < 10 || difficulty > 33) return 0;
        if(userGrade < 10 || userGrade > 33) return 0;

        int diff = difficulty - userGrade;

        if(diff >= 4) return 222;
        if(diff <= -6) return 1;

        return switch (diff){
            case 3 -> 185;
            case 2 -> 150;
            case 1 -> 120;
            case 0 -> 100;
            case -1 -> 90;
            case -2 -> 75;
            case -3 -> 55;
            case -4 -> 22;
            case -5 -> 11;
            default -> 0;
        };
    }

}
