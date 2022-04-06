package gestioneSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main (String[] args) {
		
		//inizializzazione delle variabili
		Socket socket;
		BufferedReader inputServer;
		DataOutputStream outputServer;
		String messaggioServer;
	    String messaggioClient;
		
		try {
			//1) Apertura porta, avvio del servizio
			ServerSocket serverSocket=new ServerSocket(2000);
			System.out.println("server avviato con successo");
			//2) Attesa di richiesta del server
			//3) Accettazione della richiesta e realizzazione
			socket = serverSocket.accept();
			System.out.println("connessione avvenuta");
			System.out.println("Socket:"+ socket);
		} catch (BindException e) {
			System.err.println("porta occupata");
			
		} catch (IOException e) {
			System.err.println("errore avvio server sulla porta 2000");
			//Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		
		try {
            //Inizializzazione variabili lato server
            inputServer = new BufferedReader(new InputStreamReader(socket.getInputStream())); //creo un oggetto della classe bufferedReader che riceve lo stream del socket sottoforma di byte e successivamente lo converte in caratteri
            outputServer = new DataOutputStream(socket.getOutputStream());
            //4)Invio e ricezione di messaggi con il server
            messaggioServer = "Benvenuto!\n";
            outputServer.writeBytes(messaggioServer); 	//Scrive la stringa di byte specificata nel flusso di output
            outputServer.flush(); //Scarica i dati nel flusso di output e forza la scrittura nel file di tutti i dati memorizzati nel buffer
            messaggioClient = inputServer.readLine(); //leggo il contenuto di inputServer
            //5)risposta del server al client
            if(messaggioClient.equals("data"))
                messaggioServer = Long.toString(System.currentTimeMillis()); //serve ad ottenere il tempo reale ed aggiungerlo ad una stringa
            else
            	messaggioServer = "1";
            outputServer.writeBytes(messaggioServer);
            outputServer.flush();
        } catch (IOException ex) {
            System.err.print(ex);
        }

        //6) chiusura connessione lato client
		//7) chiusura connessione lato server
        finally
        {
            try
            { 
                    socket.close();
            }
            catch(IOException ex)
            {
            	System.err.print(ex);
            }
        }
    }
	
}
