package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class ClientThread extends Thread {
	Socket s = null;
	String clientname = null;
	OutputStreamWriter osw;
	public ClientThread(Socket s){

		this.s = s;
		this.start();
	}

	public void run(){

		try {

			//		s = EchoServerTCP2.server.accept();
			clientname = s.getInetAddress().toString();


			System.out.println(clientname); 
			OutputStream os = s.getOutputStream(); 
			InputStream is = s.getInputStream();	//Klient-strömmar.
			InputStreamReader inReader = new InputStreamReader(is);
			BufferedReader buf = new BufferedReader(inReader);
			osw = new OutputStreamWriter(os); // Används för att skicka echo till klient



			osw.write("Welcome " +clientname +", write 'exit' to disconnect");
			while(true){	// Loopa för evigt..

				String currentLine = buf.readLine();

				if(currentLine.equals("exit")){
					osw.write("Exiting..");
					break;
				} else {

					osw.write(currentLine +"\r\n"); // Skriv ut linen som lästs.
					System.out.println(clientname +"said: " +currentLine +" at ");
					osw.flush(); 

				}
			}

			osw.flush();
			os.close();
			is.close();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getAddress(){
		return clientname;
	}
	
}