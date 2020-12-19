import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> MovieDetailsList = new ArrayList<Movie>(); 
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            Movie currMovieDetails = new Movie(record.get("id"),record.get("title"),record.get("year"),
            record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),
            Integer.parseInt(record.get("minutes")));
            MovieDetailsList.add(currMovieDetails);
        }
        return MovieDetailsList;
    }  
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        
        System.out.println("Number of movies: " + movies.size());
        
        String countInGenre = "Comedy"; // variable
        int countComedies = 0;
        
        int minutes = 150; // variable
        int countMinutes = 0;
        
        for (Movie movie : movies) {
            if (movie.getGenres().contains(countInGenre)) {
                countComedies +=1;
            }
            
            if (movie.getMinutes() > minutes) {
                countMinutes +=1;
            }
        }
        System.out.println("There are " + countComedies + " comedies in the list");
        System.out.println("There are " + countMinutes + " movies with more than " + minutes + 
        " minutes in the list");
        
        // Create a HashMap with count of how many movies each particular director filmed
        HashMap<String,Integer> countMoviesByDirector = new HashMap<String,Integer> ();
        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(",");
            
            for (String director : directors ) {
                director = director.trim();
                if (! countMoviesByDirector.containsKey(director)) {
                    countMoviesByDirector.put(director, 1);
                } else {
                    countMoviesByDirector.put(director, countMoviesByDirector.get(director) + 1);
                }
            }
        }
        
        // Count max number of movies directed by a particular director
        int maxNumOfMovies = 0;
        for (String director : countMoviesByDirector.keySet()) {
            if (countMoviesByDirector.get(director) > maxNumOfMovies) {
                maxNumOfMovies = countMoviesByDirector.get(director);
            }
        }
        
        // Create an ArrayList with directors from the list that directed max number of movies
        ArrayList<String> directorsList = new ArrayList<String> ();
        for (String director : countMoviesByDirector.keySet()) {
            if (countMoviesByDirector.get(director) == maxNumOfMovies) {
                directorsList.add(director);
            }
        }
        System.out.println("Max number of movies directed by one director: " + maxNumOfMovies);
        System.out.println("Directors who directed that many movies are " + directorsList); 
    }
    public ArrayList<Rater> loadRaters(String fileName){
        ArrayList<Rater> RateDetailsList = new ArrayList<Rater>();
        ArrayList<String> myIDs = new ArrayList<String>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String recordID = record.get("rater_id");
            if(myIDs.contains(recordID)){
                for(Rater r : RateDetailsList){
                    String rID = r.getID();
                    if(rID.equals(recordID)){
                        r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                    }
                }
            }
            else{
                Rater currRater = new EfficientRater(recordID);
                currRater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                RateDetailsList.add(currRater);
                myIDs.add(recordID);
            }
        }
        return RateDetailsList;
    }
    public void testLoadRaters () {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        
        System.out.println("Total number of raters: " + raters.size());
        
        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>> ();
        for (Rater rater : raters) {
            HashMap<String, Double> ratings = new HashMap<String, Double> ();
            ArrayList<String> itemsRated = rater.getItemsRated();
            
            for (int i=0; i < itemsRated.size(); i++) {
                String movieID = itemsRated.get(i);
                double movieRating = rater.getRating(movieID);
                
                ratings.put(movieID, movieRating);
            }
            hashmap.put(rater.getID(), ratings);
        }
        
        String raterID = "193"; //rater_id
        int ratingsSize = hashmap.get(raterID).size();
        System.out.println("Number of ratings for the rater " + raterID + " : " + ratingsSize);
        
        int maxNumOfRatings = 0;
        for (String key : hashmap.keySet()) {
            int currAmountOfRatings = hashmap.get(key).size();
            
            if (currAmountOfRatings > maxNumOfRatings) {
                maxNumOfRatings = currAmountOfRatings;
            }
        }
        System.out.println("Maximum number of ratings by any rater : " + maxNumOfRatings);
        
        ArrayList<String> raterWithMaxNumOfRatings = new ArrayList<String> ();
        for (String key : hashmap.keySet()) {
            int currAmountOfRatings = hashmap.get(key).size();
            
            if (maxNumOfRatings == currAmountOfRatings) {
                raterWithMaxNumOfRatings.add(key);
            }
        }
        System.out.println("Rater(s) with the most number of movies rated : " + raterWithMaxNumOfRatings);
        
        String movieID = "1798709";
        int numOfRatings = 0;
        for (String key : hashmap.keySet()) {
            if(hashmap.get(key).containsKey(movieID)) {
                numOfRatings +=1;
            }
        }
        System.out.println("Number of ratings movie " + movieID + " has : " + numOfRatings);
        
        ArrayList<String> uniqueMovies = new ArrayList<String> ();
        for (String key : hashmap.keySet()) {
            for (String currMovieID : hashmap.get(key).keySet()) {
                if (! uniqueMovies.contains(currMovieID)) {
                    uniqueMovies.add(currMovieID);
                }
            }
        }
        System.out.println("Total number of movies that were rated : " + uniqueMovies.size());
    }
}
