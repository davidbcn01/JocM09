import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientConnecta4 extends Thread{
    private int portDesti;
    private int result;
    private String ipSrv;
    private String Nom;
    private InetAddress adrecaDesti;
    private Tauler t;
    private Jugada j;
    boolean jugador;
    boolean turno;
    boolean acabat = false;
    private int columna;
    private String fitxa;
    public ClientConnecta4(String ip, int port){
        this.portDesti = port;
        this.ipSrv = ip;
        try {
            adrecaDesti = InetAddress.getByName(ipSrv);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public void setNom(String n) {
        Nom=n;
    }
    public void runClient() throws IOException, ClassNotFoundException {
        Socket socket;
        Scanner sc = new Scanner(System.in);
        try{
            socket = new Socket(InetAddress.getByName(ipSrv),portDesti);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            jugador = (boolean) in.readObject();

            while(!acabat){
                turno = (boolean) in.readObject();
                if (turno==jugador){
                    t = (Tauler) in.readObject();
                    t.showTauler();
                    System.out.println("Introdueix la columna on vols ficar la fitxa");

                    columna = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix la teva fitxa");
                    fitxa = sc.nextLine();
                    j= new Jugada(columna,fitxa);
                    out.writeObject(j);
                    t = (Tauler) in.readObject();
                    t.showTauler();
                    System.out.println("///////////////////////////");




                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       /* byte [] receivedData = new byte[1024];
        int n;
        String ox;
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
            ox = sc.nextLine();
            //j.Nom = Nom;
            j.ox = ox;
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
*/
    }


    public static void main(String[] args) throws ClassNotFoundException {
        String ipSrv;
        int port;
        String jugador;
        //Demanem la ip del servidor i nom del jugador
        System.out.println("IP del servidor?");
        Scanner sip = new Scanner(System.in);
        ipSrv = sip.nextLine();
        System.out.println("Port?:");
        port = sip.nextInt();
        System.out.println("Nom jugador:");
        jugador = sip.next();
        ClientConnecta4 cAdivina = new ClientConnecta4(ipSrv, port);

        cAdivina.setNom(jugador);
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
