
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String Genre;
    public GenreFilter(String gen){
        Genre = gen;
    }
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(Genre);
    }
}
