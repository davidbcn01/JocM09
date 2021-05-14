import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientConnecta4 {
    private int portDesti;
    private int result;
    private String Nom, ipSrv;
    private int intents;
    private InetAddress adrecaDesti;
    private Tauler t;
    private Jugada j;
    public ClientConnecta4(String ip, int port){
        this.portDesti = port;
        result = -1;
        intents = 0;
        ipSrv = ip;
        try {
            adrecaDesti = InetAddress.getByName(ipSrv);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void runClient() throws IOException, ClassNotFoundException {
        byte [] receivedData = new byte[1024];
        int n;
        DatagramPacket packet;
        DatagramSocket socket = new DatagramSocket();
        //Missatge de benvinguda
        System.out.println("Hola: Comencem!\n Digues la teva jugada: ");
        //Bucle de joc
        while(result!=0 && result!=-2) {


            //creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
            //espera de les dades
            socket.setSoTimeout(5000);
            try {
                socket.receive(packet);
                ByteArrayInputStream in = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(in);
                t = (Tauler) ois.readObject();
                //processament de les dades rebudes i obtenció de la resposta
                //result = getDataToRequest(packet.getData(), packet.getLength());
            }catch(SocketTimeoutException e) {
                System.out.println("El servidor no respòn: " + e.getMessage());
                result=-2;
            }







            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            //j.Nom = Nom;
            j.num = n;
            //byte[] missatge = ByteBuffer.allocate(4).putInt(n).array();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(j);
            byte[] missatge = os.toByteArray();

            //creació del paquet a enviar
            packet = new DatagramPacket(missatge, missatge.length, adrecaDesti, portDesti);
            //creació d'un sòcol temporal amb el qual realitzar l'enviament
            //socket = new DatagramSocket();
            //Enviament del missatge
            socket.send(packet);


        }
        socket.close();
        //Si és l'últim jugador no cal connexió multicast per saber l'estat del joc perquè
        //el servidor també acaba amb l'encert de l'últim jugador

    }


    public static void main(String[] args) throws ClassNotFoundException {
        String ipSrv;
        int port;

        //Demanem la ip del servidor i nom del jugador
        System.out.println("IP del servidor?");
        Scanner sip = new Scanner(System.in);
        ipSrv = sip.nextLine();
        System.out.println("Port?:");
        port = sip.nextInt();

        ClientConnecta4 cAdivina = new ClientConnecta4(ipSrv, port);


        try {
            cAdivina.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*if(cAdivina.getResult() == 0) {
            System.out.println("Fi, ho has aconseguit amb "+ cAdivina.t.map_jugadors.get(jugador).intValue() +" intents");
            cAdivina.t.map_jugadors.forEach((k,v)-> System.out.println(k + "->" + v));
        } else {
            System.out.println("Has perdut");
        }
        */

    }
}
