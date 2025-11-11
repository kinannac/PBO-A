
/**
 * Write a description of class Tumbuhan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tumbuhan extends MakhlukHidup {
    private String Akar;
    public Tumbuhan (String Akar){
        this.Akar = Akar;
    }
    public void berdiri (){
        System.out.println("Pohon berdiri dengan     :  "+Akar);
    }
}