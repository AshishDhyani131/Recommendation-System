import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings SR = new ThirdRatings("data/ratings_short.csv");
        //int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of Movies : "+MovieDatabase.size()+" movies");
        System.out.println("Number of Raters : "+ RatingSize+" raters");
        ArrayList<Rating> AvgRatings = SR.getAverageRatings(1);
        System.out.println("Found "+AvgRatings.size()+" movies");
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            System.out.println(rt.getValue()+"  "+ MovieDatabase.getTitle(rt.getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings SR = new SecondRatings();
        ArrayList<Rating> AvgRatings = SR.getAverageRatings(3);
        String MovieTitle = "The Godfather";
        for(Rating rt : AvgRatings){
            if(rt.getItem().equals(MovieTitle)){
                System.out.println(rt.getValue()+"  "+ rt.getItem());break;
            }
        }
    }
    public void printAverageRatingsByYear(){
         ThirdRatings SR = new ThirdRatings("data/ratings_short.csv");
        //int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of Movies : "+MovieDatabase.size()+" movies");
        System.out.println("Number of Raters : "+ RatingSize+" raters");
        ArrayList<Rating> AvgRatings = SR.getAverageRatingsByFilter(1,new YearAfterFilter(2000));
        System.out.println("Found "+AvgRatings.size()+" movies");
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            //System.out.println(rt);
            String item = rt.getItem();
            System.out.println(rt.getValue()+"  "+MovieDatabase.getYear(item)+"  "+MovieDatabase.getTitle(item));
        }
    }
    public void printAverageRatingsByGenre(){
         ThirdRatings SR = new ThirdRatings("data/ratings_short.csv");
        //int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of Movies : "+MovieDatabase.size()+" movies");
        System.out.println("Number of Raters : "+ RatingSize+" raters");
        ArrayList<Rating> AvgRatings = SR.getAverageRatingsByFilter(1,new GenreFilter("Crime"));
        System.out.println("Found "+AvgRatings.size()+" movies");
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            String id = rt.getItem();
            System.out.println(rt.getValue()+"  "+MovieDatabase.getTitle(id)+"  \n       "+ MovieDatabase.getGenres(id));
        }
    }
    public void printAverageRatingsByMinutes(){
         ThirdRatings SR = new ThirdRatings("data/ratings_short.csv");
        //int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of Movies : "+MovieDatabase.size()+" movies");
        System.out.println("Number of Raters : "+ RatingSize+" raters");
        ArrayList<Rating> AvgRatings = SR.getAverageRatingsByFilter(1,new MinutesFilter(110,170));
        System.out.println("Found "+AvgRatings.size()+" movies");
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            String id = rt.getItem();
            System.out.println(rt.getValue()+"  Time: "+MovieDatabase.getMinutes(id)+"  "+ MovieDatabase.getTitle(id));
        }
    }
    public void printAverageRatingsByDirectors(){
         ThirdRatings SR = new ThirdRatings("data/ratings_short.csv");
        //int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of Movies : "+MovieDatabase.size()+" movies");
        System.out.println("Number of Raters : "+ RatingSize+" raters");
        ArrayList<Rating> AvgRatings = SR.getAverageRatingsByFilter(1,new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("Found "+AvgRatings.size()+" movies");
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            String id = rt.getItem();
            System.out.println(rt.getValue()+"  "+ MovieDatabase.getTitle(id)+"  \n      "+MovieDatabase.getDirector(id));
        }
    }
     public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");//do i need put filename here?
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());// need initialize first.
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
        AllFilters all = new AllFilters();
        all.addFilter(new GenreFilter("Romance"));
        all.addFilter(new YearAfterFilter(1980));
        
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-10d%-16s%-5s%n", i.getValue(), MovieDatabase.getYear(i.getItem()), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getGenres(i.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");//do i need put filename here?
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());// need initialize first.
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(30, 170));
        all.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2fTime:%-10s%-16s%-5s%n", i.getValue(), MovieDatabase.getMinutes(i.getItem()), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getDirector(i.getItem()));
        }
    }
    
    public static void main(String[] args) {
        MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
        System.out.println("---------------Test: printAverageRatings()----------------");
        mra.printAverageRatings();
        System.out.println("---------------Test: getAverageRatingOneMovie() ----------------");
        mra.getAverageRatingOneMovie();
        System.out.println("---------------Test: printAverageRatingsByYear() ----------------");
        mra.printAverageRatingsByYear();
        System.out.println("---------------Test: printAverageRatingsByGenre() ----------------");
        mra.printAverageRatingsByGenre();
        System.out.println("---------------Test: printAverageRatingsByMinutes() ----------------");
        mra.printAverageRatingsByMinutes();
        System.out.println("---------------Test: printAverageRatingsByDirectors() ----------------");
        mra.printAverageRatingsByDirectors();
        System.out.println("---------------Test: printAverageRatingsByYearAfterAndGenre() ----------------");
        mra.printAverageRatingsByYearAfterAndGenre();
        System.out.println("---------------Test: printAverageRatingsByDirectorsAndMinutes() ----------------");
        mra.printAverageRatingsByDirectorsAndMinutes();
        //why cannot print the default value;
        //        boolean a;
        //        String b;
        //        System.out.println(a);
        //        System.out.println(b);
        
    }
}
