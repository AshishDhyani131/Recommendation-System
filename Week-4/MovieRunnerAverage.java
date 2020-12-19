import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings SR = new SecondRatings();
        /*int MovieSize = SR.getMovieSize();
        int RatingSize = SR.getRaterSize();
        System.out.println("Number of Movies : "+MovieSize);
        System.out.println("Number of Raters : "+ RatingSize);*/
        ArrayList<Rating> AvgRatings = SR.getAverageRatings(3);
        for(Rating rt : AvgRatings ){
            //String title = SR.getTitle(rt.getItem());
            System.out.println(rt.getValue()+"  "+ rt.getItem());
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

}
