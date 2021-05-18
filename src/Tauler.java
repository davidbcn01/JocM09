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
            if(tauler[i][num] == null) {
                tauler[i][num] = ox;
                break;
            }

        }

}
    public Tauler() {
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
    public static void main(String[] args) {
    Tauler tauler = new Tauler();
    int columna = 3;
    String ox = "x";



    tauler.setJugadaTauler(columna,ox);
        tauler.showTauler();
    }


}
class Jugada implements Serializable {
    public static final long serialVersionUID = 1L;
    String Nom;
    int num;
    String ox;
}

