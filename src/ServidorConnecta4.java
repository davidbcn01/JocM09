import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorConnecta4 {

        /* Servidor TCP que genera un número perquè ClientTcpAdivina.java jugui a encertar-lo
         * i on la comunicació dels diferents jugador passa per el Thread : ThreadServidorAdivina.java
         * */

        int port;


        public ServidorConnecta4(int port ) {
            this.port = port;

        }

        public void listen() {
            ServerSocket serverSocket = null;
            Socket clientSocket1 = null;
            Socket clientSocket2 = null;

            try {
                serverSocket = new ServerSocket(port);
        while(true) {
            clientSocket1 = serverSocket.accept();
            clientSocket2 = serverSocket.accept();
                 if (clientSocket1.isConnected() && clientSocket2.isConnected()) { //esperar connexió del client i llançar thread


                        System.out.println("Despres del accept");
        //Llançar Thread per establir la comunicació
        ThreadServidor FilServidor = new ThreadServidor(clientSocket1,clientSocket2);
        Thread client = new Thread(FilServidor);
        client.start();
    }
}
            } catch (IOException ex) {
                Logger.getLogger(ServidorConnecta4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void main(String[] args) {
		/*if (args.length != 1) {
            System.err.println("Usage: java SrvTcpAdivina <port number>");
            System.exit(1);
        }*/


            //int port = Integer.parseInt(args[0]);
            ServidorConnecta4 srv = new ServidorConnecta4(5558);
            srv.listen();

        }

    }

