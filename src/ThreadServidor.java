import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadServidor implements  Runnable{
    Socket clientSocket1 = null;
    Socket clientSocket2 = null;
    boolean turno = true;
    Tauler taulerEntrant = new Tauler();

    Jugada j;

    boolean acabat;


    public ThreadServidor(Socket clientSocket1, Socket clientSocket2) throws IOException {
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;


    }

    @Override
    public void run() {
//        System.out.println("ThreadServer");
//        try {
//            while(!acabat) {
//
//                j = (Jugada) in.readObject();
//                //por aqui
//                taulerEntrant.setJugadaTauler(j.num,j.ox);
//                taulerEntrant.showTauler();
//
//                out.writeObject(taulerEntrant.tauler);
//
//
//                out.flush();
//
//                acabat = true;

            try{
                ObjectInputStream in =new ObjectInputStream(clientSocket1.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket1.getOutputStream());

                ObjectInputStream in2 = new ObjectInputStream(clientSocket2.getInputStream());
                ObjectOutputStream out2 = new ObjectOutputStream(clientSocket2.getOutputStream());

                out.writeObject(true);
                out2.writeObject(false);

                while (!acabat){
                    out.reset();
                    out2.reset();
                    if(turno){
                        out.writeObject(true);
                        out2.writeObject(true);

                        out.writeObject(taulerEntrant);


                        j = (Jugada) in.readObject();

                        taulerEntrant.setJugadaTauler(j.num,j.ox);

                        out.reset();
                        out.writeObject(taulerEntrant);
                        turno = !turno;
                    }else{
                        out2.writeObject(false);
                        out.writeObject(false);

                        out2.writeObject(taulerEntrant);

                        j = (Jugada) in2.readObject();

                        taulerEntrant.setJugadaTauler(j.num,j.ox);

                        out2.reset();
                        out2.writeObject(taulerEntrant);
                        turno = !turno;
                    }
                    out.flush();
                    out2.flush();
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
