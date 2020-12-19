import java.util.*;
public class DirectorsFilter implements Filter {
    private String[] DirectorsList; 
    public DirectorsFilter(String directors){
        DirectorsList = directors.split(",");
        Arrays.sort(DirectorsList);
        /*for(String Director : DirectorsList){
            System.out.println(Director);
        }*/
    }
    public boolean satisfies(String id){
        String DirectorName = MovieDatabase.getDirector(id);
        
        int index = Arrays.binarySearch(DirectorsList,MovieDatabase.getDirector(id));
        /*System.out.println(MovieDatabase.getDirector(id));
        System.out.println("INDEX: "+ index);*/
        if(index >= 0)
        return true;
        return false;
    }
}
