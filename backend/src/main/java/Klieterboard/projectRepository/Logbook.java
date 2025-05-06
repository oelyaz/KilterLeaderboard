package Klieterboard.projectRepository;

import lombok.*;
import org.json.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Logbook {

    @Getter
    @Setter
    private static LocalDateTime seasonStart;

    private final JSONArray logbook;
    private int maxDifficulty;
    private int lastDifficulty;

    public Logbook(JSONArray logbook) {
        this.logbook = logbook;
        maxDifficulty = Integer.MIN_VALUE;
        lastDifficulty = Integer.MIN_VALUE;
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
        double sum = 0;
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
        return (int) Math.round(sum / climbs);
    }

    /**
     * Generates a sorted list containing the difficulties of the x hardest boulders.
     * @param topXClimbs the length the list should have
     * @return An ascending sorted list containing the highest grades.
     */
    public List<Integer> determineTopDifficulties(int topXClimbs){
        PriorityQueue<Integer> queue = new PriorityQueue<>(topXClimbs);
        for(int i = 0; i < logbook.length();i++ ){
            if(logbook.getJSONObject(i).getString("_type").equals("ascent")) {
                if (queue.size() < topXClimbs) {
                    queue.add(logbook.getJSONObject(i).getInt("difficulty"));

                } else if (logbook.getJSONObject(i).getInt("difficulty")>queue.peek()) {
                    queue.poll();
                    queue.add(logbook.getJSONObject(i).getInt("difficulty"));
                }
            }
        }
        ArrayList<Integer> list  =  new ArrayList<>(queue);
        list.sort(Integer::compareTo);
        return list;
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
            return determineLastDifficulty();
        }
        return lastDifficulty;
    }

    /**
     * Calculates the average difficulty of the x hardest boulders.
     * @param topXClimbs  the amount of boulders to be considered
     * @return The average difficulty.
     *  If there are less than x ascents, the highest difficulty in the climber's logbook is returned.
     * <br> If there are no ascents {@code -1} is returned
     */
    public int getAverageTopDifficulty(int topXClimbs){
        double sum = 0;
        List<Integer> list = determineTopDifficulties(topXClimbs);
        if(list.isEmpty()){
            return -1;
        }
        if(list.size() < topXClimbs){
            return getMaxDifficulty();
        }
        for( int grade :  list){
            sum += grade;
        }
        return (int) Math.round(sum / list.size());
    }

    /**
     * Determines the score for all boulders in the logbook since the start of the season.
     * @param userGrade average user grade, needed for a fair rating
     * @return the score
     */
    public int determineScore(int userGrade){
        int score = 0;
        if(userGrade == -1 ){
            return 0;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < logbook.length(); i++) {
            try {
                JSONObject json = logbook.getJSONObject(i);
                if(! json.get("_type").equals("ascent")){
                    continue;
                }
                String date = json.getString("climbed_at");
                LocalDateTime d;
                try {
                    d = LocalDateTime.parse(date, dtf);
                }catch (DateTimeParseException e){
                    continue;
                }
                if(d.isBefore(seasonStart))break;

                score += Climb.scoreForBoulder(userGrade, json.getInt("difficulty"));
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }
        }
        return score;
    }

}
