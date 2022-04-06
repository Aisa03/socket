package gestioneSocket;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main (String[] args) {
		try {
			//1) Apertura porta, avvio del servizio
			ServerSocket serverSocket=new ServerSocket(2000);
			System.out.println("server avviato con successo");
			//2) Attesa di richiesta del server
			Socket socket = serverSocket.accept();
			System.out.println("connessione avvenuta");
			System.out.println("Socket");
			//3 scambio dati
            socket.getInputStream();
		} catch (BindException e) {
			System.err.println("porta occupata");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("errore avvio server sulla porta 2000");
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

        //4 chiusura socket  
        finally
        {
            try
            { 
                if(socket != null)
                    socket.close();
            }
            catch(IOException ex)
            {
            
            }
        }
    }
	
}
