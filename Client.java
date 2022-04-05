/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionesocketcianetti;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Studente
 */
public class Client 
{   
    public static void main(String[] args) 
    {
        //porta del server maggiore di 1024
        int port = 2000;
        //oggetto socket da usare per la connessione 
        Socket socket = null;

        try 
        {
            //1 Richiesta di connessione al server
            socket = new Socket("127.0.0.1",port);
            System.out.println("Server connesso con il client " + socket.getRemoteSocketAddress());
            System.out.println("Porta client: " + socket.getLocalPort());

        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
