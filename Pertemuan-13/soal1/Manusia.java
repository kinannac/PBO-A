
/**
 * Write a description of class Manusia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manusia extends MakhlukHidup {
    private String duaKaki;
    public Manusia(String duaKaki){
        this.duaKaki = duaKaki;
    }
    public void berdiri (){
        System.out.println("Siti berdiri menggunakan :  "+duaKaki);
        System.out.println("Budi berdiri menggunakan :  "+duaKaki);
    }
}