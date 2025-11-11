
/**
 * Write a description of class Hewan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hewan extends MakhlukHidup {
    private String kakiEmpat, kakiDua ;
    public Hewan(String kakiEmpat, String kakiDua){
        this.kakiEmpat = kakiEmpat;
        this.kakiDua   = kakiDua;
    }
    
    public void berdiri (){
        System.out.println("Serigala berdiri menggunakan :  " +kakiEmpat);
        System.out.println("Ayam berdiri menggunakan    :  " +kakiDua);
        System.out.println("Unta berdiri menggunakan    :  " +kakiEmpat);
    }    
}