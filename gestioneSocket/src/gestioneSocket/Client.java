package gestioneSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Timestamp;

public class Client {
	public static void main (String[] args) {

		Socket socket;
		BufferedReader inputC;
		DataOutputStream outputC;
		BufferedReader tastiera;
		String messaggioServer;
		String messaggioClient;

		
		try {
			socket = new Socket("127.0.0.1",2000);
			System.out.println("Server connesso con il client " + socket.getRemoteSocketAddress());
            System.out.println("Porta client: " + socket.getLocalPort());
            } 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("host inesistente");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella connessione al server");
			e.printStackTrace();
			//Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
			try {
	            //Inizializzazione degli oggetti di comunicazione lato client
	            inputC = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            outputC = new DataOutputStream(socket.getOutputStream());
	            tastiera = new BufferedReader(new InputStreamReader(System.in));
	            //Invio e ricezione di messaggi con il server
	            messaggioServer = inputC.readLine();
	            System.out.println("messaggio server: "+messaggioServer);
	            System.out.print("risposta: ");
	            messaggioClient = tastiera.readLine();
	            outputC.writeBytes(messaggioClient+"\n");
	            outputC.flush();
	            messaggioServer = inputC.readLine();
	            if(!messaggioServer.equals("1")){
	                Timestamp serverDate = new Timestamp(Long.parseLong(messaggioServer));
	                System.out.println("Data del server: "+serverDate);
	            }

	        } catch (IOException ex) {
	            System.err.print(ex);
	        }
	    }
		finally
        {
            try 
            {
				if(socket != null)
                    socket.close();
            }
            catch (IOException ex) 
            {
            	System.err.print(ex);
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
  }


		}
		