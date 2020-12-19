import java.util.*;

public class FourthRatings {
    //private ArrayList<Movie> myMovies;
    //private ArrayList<Rater> myRaters;
    /*public FourthRatings(String Raters){
        //FirstRatings FR = new FirstRatings();
        //myMovies = FR.loadMovies(Movies);
        //myRaters = FR.loadRaters(Raters);
    }
    public FourthRatings() {
        // default constructor
        //this("ratings.csv");
    }
    public int getMovieSize(){
        return myMovies.size();
    }
    public int getRaterSize(){
        return myRaters.size();
    }*/
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> Me = me.getItemsRated();
        ArrayList<String> R = me.getItemsRated();
        //System.out.println(Me);
        //System.out.println(Me);
        //System.out.println(R);
        double product = 0.0;
        for(String MyMovieID : Me){
            //System.out.println(MyMovieID);
            double rRating = r.getRating(MyMovieID);
            if(rRating !=-1){
                product += (me.getRating(MyMovieID)-5)*(r.getRating(MyMovieID)-5);
            }
        }
        //System.out.println("FINAL : "+product);
        return product;
    }
    private ArrayList<Rating> getSimilarities(String raterID){
        //System.out.println("RATERID: "+raterID);
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        for(String RaterID : RaterDatabase.getRaterIDs() ){
            
            if(RaterID.equals(raterID))continue;
            //System.out.println("RaterID: "+RaterID);
            Double dotProduct = dotProduct(RaterDatabase.getRater(raterID),
            RaterDatabase.getRater(RaterID));
            if(dotProduct >= 0.0){
                Rating Rate= new Rating(RaterID,dotProduct);
                similarities.add(Rate);
            }
        }
        Collections.sort(similarities,Collections.reverseOrder());
        //System.out.println("SIMILARITIES SIZE:"+similarities.size());
        return similarities;
    }
    private double getAverageByID(String id,int minimalRaters){
       double RatingSum = 0.0;
       double freq = 0.0;
       for(Rater rt : RaterDatabase.getRaters()){
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
    public ArrayList<Rating> getAverageRatings(ArrayList<String> movieList, int minimalRaters){
        ArrayList<Rating> MostRated = new ArrayList<Rating>();
        ArrayList<String> movies = movieList;
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
    public ArrayList<Rating> getSimilarRatings(String raterID,
    int numSimilarRaters,int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<>();
        // get Arraylist<movieID> from top numSimilarRaters.
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                    //System.out.println("Movie id = " + movieID + " Rater id = " + raterID1);
                }
            }
        }
        //System.out.println("MoviebyTopSimilar Size = " + movidIDByTopSimilar.size());
        //for (String i:)
        
        //rating for movies in the movieIDByTopSimilar list;
        //Filter trueFilter = new TrueFilter();
        for (String j : movidIDByTopSimilar) {
            // rating for one movie
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;
            // System.out.println("total");
            for (int i = 0; i < numSimilarRaters; i++) {
                //   System.out.println("i=" + i);
                
                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                if (rating != -1) {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                    //System.out.println("Movie id = " + j + " count " + count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                    
                }
            }
            if (count >= minimalRaters)
                //ave = total / simiweighttotal;
                ave = total / count;
            //System.out.println("Movie id = " + j + " count " + count + " : " + " rating " + ave + " total/count " + total / count);
            //(9*31+10*20)/50=279+200=479/50=9.58
            //(9*31+10*20)/2 = 479/2 = 239.5
            // rating for one movie end
            if (ave > 0)
                ratingList.add(new Rating(j, ave));
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        
        return ratingList;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //rating for all movie
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }
        
        for (String j : movidIDByTopSimilar) {
            if (f.satisfies(j)) {
                // rating for one movie
                double ave = 0;
                ArrayList<Rating> simiList = getSimilarities(raterID);
                // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
                int count = 0;
                double total = 0;
                double simiweighttotal = 0;
                for (int i = 0; i < numSimilarRaters; i++) {
                    double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                    if (rating != -1) {
                        count++;
                        total += rating * simiList.get(i).getValue();
                        simiweighttotal += simiList.get(i).getValue();
                        
                        //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                        
                    }
                }
                if (count >= minimalRaters)
                    //ave = total / simiweighttotal;
                    ave = total / count;
                
                // rating for one movie end
                if (ave > 0)
                    ratingList.add(new Rating(j, ave));
            }
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        
        return ratingList;
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