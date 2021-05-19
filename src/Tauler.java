import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tauler implements Serializable {
    public static final long serialVersionUID = 1L;
    public String[][] tauler = new String [8][7];
    public Map<String,Integer> map_jugadors;
    public int torn;

    public int getMap_jugadors() {
        return map_jugadors.size();
    }

    public void setJugadaTauler(int num, String ox){
        for (int i = tauler.length-1;i>0;i--){
            if(tauler[i][num].equals("-")) {
                tauler[i][num] = ox;
                break;
            }

        }

}
    public Tauler() {
        setTauler();
        map_jugadors = new HashMap<>();
        torn=0;
    }
   public void showTauler(){
       for(int i = 0; i<tauler.length;i++){
           for(int j = 0; j<tauler[i].length;j++){
               System.out.print(tauler[i][j] + " ");
           }
           System.out.println();
       }
   }
    public void setTauler(){
        for(int i = 0; i<tauler.length;i++){
            for(int j = 0; j<tauler[i].length;j++){
               tauler[i][j]="-";
            }
        }
    }
    public static void main(String[] args) {
    Tauler tauler = new Tauler();
    int columna = 3;
    String ox = "x";


    tauler.setTauler();
    tauler.setJugadaTauler(columna,ox);
        tauler.showTauler();
    }


}
class Jugada implements Serializable {
    public static final long serialVersionUID = 1L;
    String Nom;
    int num;
    String ox;

    public Jugada(int num, String ox) {
        this.num = num;
        this.ox = ox;
    }
}

