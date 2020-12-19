
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int MinMinutes;
    private int MaxMinutes;
    public MinutesFilter(int minminutes, int maxminutes){
        MinMinutes = minminutes;
        MaxMinutes = maxminutes;
    }
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= MinMinutes && MovieDatabase.getMinutes(id) <= MaxMinutes;
    }
}
