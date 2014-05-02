package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameServer{
	public static ServerSocket server = null;
	static boolean connected;
	static Vector<Player> clientlist = new Vector<Player>();
	
	public static void main(String[] args) {

		try {
			CommandCenter mailbox = new CommandCenter();
			CommandReader mr = new CommandReader(mailbox);
			mr.start();
			server = new ServerSocket(30000);
			
			
			while(1==1){
				Socket client = server.accept(); // Vänta på connection.
				if(client != null) {			// Skapa ny tråd.
					Player t = new Player(client, mailbox);
					t.start();
					clientlist.add(t);
				}
			}
			
		} catch (IOException e) {
			// TODO 
			e.printStackTrace();
		}
	}
}

