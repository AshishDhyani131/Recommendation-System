import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public SecondRatings(String Movies, String Raters){
        FirstRatings FR = new FirstRatings();
        myMovies = FR.loadMovies(Movies);
        myRaters = FR.loadRaters(Raters);
    }
    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    public int getMovieSize(){
        return myMovies.size();
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    private double getAverageByID(String id,int minimalRaters){
       double RatingSum = 0.0;
       double freq = 0.0;
       for(Rater rt : myRaters){
           if(rt.hasRating(id)){
               freq++;
               RatingSum += rt.getRating(id);
           }
       }
       if(freq >= minimalRaters){
           return RatingSum/freq;
       }
       return 0.0; 
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> MostRated = new ArrayList<Rating>();
        for(Movie mv : myMovies){
            String movieID = mv.getID();
            double MovieRatingAvg = getAverageByID(movieID, minimalRaters);
            if(MovieRatingAvg != 0.0){
                Rating rat = new Rating(mv.getTitle(),MovieRatingAvg);
                MostRated.add(rat);
            }
        } 
        Collections.sort(MostRated);
        return MostRated;
    }
    public String getTitle(String id){
        for(Movie mv : myMovies){
            if(mv.getID().equals(id)){
                return mv.getTitle();
            }
        }
        return "NO SUCH MOVIE";
    }
    public String getID(String title){
        for(Movie mv: myMovies){
            if(mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}