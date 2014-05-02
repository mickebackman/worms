package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class Player extends Thread {
	Socket s;
	OutputStream os;
	OutputStreamWriter osw;
	InputStream is; 
	CommandCenter m;
	String name;

public Player(Socket s, CommandCenter m){
		this.s = s;
		this.m = m;
		 os = null;
		 is = null;
		 osw = null;
	}




	public void run(){
		try {
			name = s.getInetAddress().toString();
			os = s.getOutputStream();
			is = s.getInputStream();
			


			//Klient-strömmar.
			
			InputStreamReader inReader = new InputStreamReader(is);
			BufferedReader buf = new BufferedReader(inReader);
			osw = new OutputStreamWriter(os); 
			osw.flush();
			osw.write("Whalecum!" +"\r\n");
			System.out.println("Ansluten");
			
			while(true){	// Loopa för evigt..

				String currentLine = buf.readLine();
				if(!currentLine.equals("")){
				m.setMessage(currentLine, name);
				}
			}
		} catch (IOException e) {
			// do nating
			e.printStackTrace();
		} 
	}

	public Socket getClientSocket(){
		return s;
	}
	public OutputStreamWriter getOSW(){
		return osw;
	}
	public void disconnect(){
		try{
		osw.flush();
		os.close();
		is.close();
		
	} catch(IOException e){
		e.printStackTrace();
	}
		
		

}
	public String getNameBro(){
		return name;
	}
}
