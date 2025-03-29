package Klieterboard.projectRepository;

import org.json.JSONArray;

public class Logbook {

    private JSONArray logbook;
    private int maxDifficulty;
    private int lastDifficulty;
    private int averageDifficulty;

    public Logbook(JSONArray logbook) {
        this.logbook = logbook;
        maxDifficulty = Integer.MIN_VALUE;
        lastDifficulty = Integer.MIN_VALUE;
        averageDifficulty = Integer.MIN_VALUE;
    }

    /**
     * Determines the highest difficulty in the climber's logbook.
     * @return The highest difficulty. <br> If there is no ascent or an error, {@code -1} is returned.
     */
    private int determineHighestDifficulty(){
        int maxGrade = -1;
        for(int i = 0; i < logbook.length(); i++){
            if(logbook.getJSONObject(i).getString("_type").equals("ascent")){
                if(logbook.getJSONObject(i).getInt("difficulty") > maxGrade){
                    maxGrade = logbook.getJSONObject(i).getInt("difficulty");
                }
            }
        }
        this.maxDifficulty = maxGrade;
        return maxGrade;
    }

    /**
     * Determines the difficulty of the last boulder climbed.
     * @return The difficulty. <br> If there is no ascent or an error, {@code -1} is returned.
     */
    private int determineLastDifficulty(){
        for(int i = 0; i < logbook.length(); i++){
            if(logbook.getJSONObject(i).getString("_type").equals("ascent")){
                lastDifficulty =  logbook.getJSONObject(i).getInt("difficulty");
                return lastDifficulty;
            }
        }
        return -1;
    }

    /**
     * Calculates the average difficulty of the last x boulders.
     * @param lastXClimbs number of boulders to be taken into account
     * @return The average difficulty of the last x boulders. <br> If there are no ascents, {@code -1} is returned.
     */
    public int determineAverageDifficulty(int lastXClimbs){
        int climbs = 0;
        int sum = 0;
        for(int i = 0; i < logbook.length() && climbs < lastXClimbs; ){
            if(logbook.getJSONObject(i).getString("_type").equals("ascent")){
                    sum += logbook.getJSONObject(i).getInt("difficulty");
                    climbs++;
            }
        }
        if(climbs == 0){
            System.out.println("No boulder send");
            return -1;
        }
        averageDifficulty = sum / climbs;
        return averageDifficulty;
    }



    /**
     * Returns the highest difficulty in the climber's logbook.
     * @return The highest difficulty. <br> If there is no ascent or an error, {@code -1} is returned.
     */
    public int getMaxDifficulty(){
        if(maxDifficulty == Integer.MIN_VALUE){
            return determineHighestDifficulty();
        }
        return maxDifficulty;
    }

    /**
     * Returns the difficulty of the last boulder climbed.
     * @return The difficulty. <br> If there is no ascent or an error, {@code -1} is returned.
     */
    public int getLastDifficulty(){
        if(lastDifficulty == Integer.MIN_VALUE){
            return determineHighestDifficulty();
        }
        return lastDifficulty;
    }

    /**
     * Returns the average difficulty of the last x boulders.
     * @param lastXClimbs number of boulders to be taken into account
     * @return The average difficulty of the last x boulders. <br> If there are no ascents, {@code -1} is returned.
     */
    public int getAverageDifficulty(int lastXClimbs){
        if(averageDifficulty == Integer.MIN_VALUE){
            return determineAverageDifficulty(lastXClimbs);
        }
        return averageDifficulty;
    }



}
