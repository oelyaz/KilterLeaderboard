package Klieterboard.projectRepository;


import lombok.Getter;

@Getter
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

}
