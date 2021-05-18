import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadServidor implements  Runnable{
    Socket clientSocket1 = null;
    Socket clientSocket2 = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    ObjectInputStream in2 = null;
    ObjectOutputStream out2 = null;
    Tauler taulerEntrant;
    Jugada j;

    boolean acabat;


    public ThreadServidor(Socket clientSocket1, Socket clientSocket2) throws IOException {
        this.clientSocket1 = clientSocket1;
        System.out.println("Contructor");
        acabat = false;
        out= new ObjectOutputStream(clientSocket1.getOutputStream());
        in =new ObjectInputStream(clientSocket1.getInputStream());
        this.clientSocket2 = clientSocket2;
        System.out.println("Contructor");
        acabat = false;
        out2= new ObjectOutputStream(clientSocket2.getOutputStream());
        in2 =new ObjectInputStream(clientSocket2.getInputStream());

        System.out.println("implementacio");

    }

    @Override
    public void run() {
        System.out.println("ThreadServer");
        try {
            while(!acabat) {

              j = (Jugada) in.readObject();
              //por aqui
                taulerEntrant.setJugadaTauler(j.num,j.ox);
                taulerEntrant.showTauler();

                out.writeObject(taulerEntrant.tauler);


                out.flush();

                acabat = true;



            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        }
        //System.out.println(msgEntrant + " - intents: " + intentsJugador);
        try {
            clientSocket1.close();
            clientSocket2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/*public String generaResposta(String en) {
		String ret;

		if(en == null) ret="Benvingut al joc!";
		else {
			ret = ns.comprova(en);
			if(ret.equals("Correcte")) {
				acabat = true;
			}
		}
		return ret;
	}
	 */

}
