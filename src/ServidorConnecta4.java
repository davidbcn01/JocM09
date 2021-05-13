import java.io.IOException;
import java.net.*;

public class ServidorConnecta4 {

    DatagramSocket socket;
    int port, fi, acabats, multiport=5557;
    boolean acabat;
    tauler tauler;
    InetAddress multicastIp;

    public ServidorConnecta4(int port, int max) {
        try {
            socket = new DatagramSocket(port);
            multicastIp = InetAddress.getByName("224.0.0.10");
            NetworkInterface.networkInterfaces().forEach(i -> System.out.println(i.toString()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        this.port = port;
        //tauler = new Tauler();
        acabat = false;
        acabats = 0;
        fi=-1;
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

        //el servidor atén el port mentre hi hagi jugadors
        while(acabats < tauler.map_jugadors.size() || acabats==0){
            DatagramPacket packet = new DatagramPacket(receivingData, receivingData.length);
            socket.receive(packet);
            sendingData = processData(packet.getData(), packet.getLength());
            clientIP = packet.getAddress();
            clientPort = packet.getPort();
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
            socket.send(packet);
            //A cada jugada també enviem les dades del tauler per multicast
            //perquè el clients que ja hagin acabat puguin seguir el jic
            DatagramPacket multipacket = new DatagramPacket(sendingData, sendingData.length,
                    multicastIp,multiport);

        }
        socket.close();
    }

    private byte[] processData(byte[] data, int length) {

    return null;
    }

        public static void main(String[] args) throws SocketException, IOException {
            ServidorConnecta4 sAdivina = new ServidorConnecta4(5556, 100);

            try {
                sAdivina.runServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Fi Servidor");



        }
}
