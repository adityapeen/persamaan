package persamaan;
import java.util.Random;

public class Gen {
    public int angka;

    public Gen() {
        bangkitkanGen();
    }

    public Gen(int angka) {
        this.angka = angka;
    }
    
    private void bangkitkanGen(){
        Random r = new Random();
        this.angka = r.nextInt(30-1)+1;
    }
    
    public int getAngka(){
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }
    
    public void setGen(Gen gen){
        this.angka = gen.getAngka();
    }
       
    
}
