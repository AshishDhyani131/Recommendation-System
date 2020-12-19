import java.util.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public ThirdRatings(String Raters){
        FirstRatings FR = new FirstRatings();
        //myMovies = FR.loadMovies(Movies);
        myRaters = FR.loadRaters(Raters);
    }
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    /*public int getMovieSize(){
        return myMovies.size();
    }*/
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
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> FilterRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieID : movies){
            //String movieID = mv.getID();
            double MovieRatingAvg = getAverageByID(movieID, minimalRaters);
            if(MovieRatingAvg != 0.0){
                Rating rat = new Rating(movieID,MovieRatingAvg);
                FilterRatings.add(rat);
            }
        } 
        Collections.sort(FilterRatings);
        return FilterRatings;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> MostRated = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : movies){
            //String movieID = mv.getID();
            double MovieRatingAvg = getAverageByID(movie, minimalRaters);
            if(MovieRatingAvg != 0.0){
                Rating rat = new Rating(movie,MovieRatingAvg);
                MostRated.add(rat);
            }
        } 
        Collections.sort(MostRated);
        return MostRated;
    }
    /*public String getTitle(String id){
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
    }*/
}