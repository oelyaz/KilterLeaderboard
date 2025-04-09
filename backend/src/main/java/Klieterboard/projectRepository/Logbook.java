package Klieterboard.projectRepository;

import org.json.*;
import java.text.*;
import java.time.*;
import java.util.*;

public class Logbook {

    private static final LocalDate date = LocalDate.of(2025, 1, 1);

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
     * @return The average difficulty. <br> If there are no ascents {@code -1} is returned
     */
    public int getAverageTopDifficulty(int topXClimbs){
        double sum = 0;
        List<Integer> list = determineTopDifficulties(topXClimbs);
        if(list.isEmpty()){
            return -1;
        }
        for( int grade :  list){
            sum += grade;
        }
        return (int) Math.round(sum / list.size());
    }

    /**
     * !! Not yet implemented !! returns 0 !!
     * <br> <br> Determines the score.
     * @return the score
     */
    public int determineScore(int userGrade){
        //TODO implement method
        int score = 0;
        Date seasonBeginning= new Date(125, 0, 1, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < logbook.length(); i++) {
            try {
                JSONObject json = logbook.getJSONObject(i);
                if(! json.get("_type").equals("ascent")){
                    continue;
                }
                String date = json.getString("climbed_at");
                Date d;
                try {
                    d = sdf.parse(date);
                } catch (ParseException e) {
                    continue;
                }
                if(d.before(seasonBeginning)) break;

                score += Climb.scoreForBoulder(userGrade, json.getInt("difficulty"));
            } catch (JSONException e) {
                continue;
            }
        }
        return score;
    }

}
