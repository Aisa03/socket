package gestioneSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;


public class Client {
	
	public static void main (String[] args) {

		//dichiarazione delle variabili
		Socket socket;
		BufferedReader inputC;
		DataOutputStream outputC;
		BufferedReader tastiera;
		String messaggioServer;
		String messaggioClient;

		//1) il server e' in ascolto
		try {
			//2) il client avvia una richiesta
			socket = new Socket("127.0.0.1",2000);
			//3) il server accetta la richiesta e la realizza
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
	            //Inizializzazione variabili lato client
	            inputC = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            outputC = new DataOutputStream(socket.getOutputStream());
	            tastiera = new BufferedReader(new InputStreamReader(System.in));
	            //4)Invio e ricezione dei dati
	            messaggioServer = inputC.readLine();
	            System.out.println("messaggio server: "+messaggioServer);
	            System.out.print("risposta: ");
	            messaggioClient = tastiera.readLine();
	            outputC.writeBytes(messaggioClient+"\n");
	            outputC.flush();
	            //5) il server risponde al client
	            messaggioServer = inputC.readLine();
	            if(!messaggioServer.equals("1")){
	                Timestamp data = new Timestamp(Long.parseLong(messaggioServer)); //crea un nuovo oggetto Timestamp in cui si converte una stringa in un valore long
	                System.out.println("Data ricevuta dal server: "+data);
	            }

	        } catch (IOException ex) {
	            System.err.print(ex);
	        }
	    }
		//6) chiusura connessione lato client
		finally //utilizzo finally per fare in modo che in qualunque caso la connessio si chiuda
        {
            try 
            {
                    socket.close();
            }
            catch (IOException ex) 
            {
            	System.err.print(ex);
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
		//7) chiusura connessione lato server
  }


		}
		