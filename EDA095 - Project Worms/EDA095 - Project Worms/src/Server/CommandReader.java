package Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;



public class CommandReader extends Thread {
	CommandCenter m;

	public CommandReader(CommandCenter m){
		this.m = m;
	}

	public void run(){
		while(true){
			if(m.mailExists()){
			String mess = m.getMessage();

			String lastEdit = m.lastEdit();


			if(mess.startsWith("W")){
				for(Player p : GameServer.clientlist){
					try{
						OutputStreamWriter osw = p.getOSW();
						osw.write(lastEdit + " wrote: " + mess +"\r\n");
					} catch(IOException io){
					}
				}

			}
			else if(mess.startsWith("D")){
				for(Player p : GameServer.clientlist){
					try{
						OutputStreamWriter osw = p.getOSW();
						osw.write(lastEdit + " wrote: " + mess +"\r\n");
					} catch(IOException io){
					}
				}

			}
			else if(mess.startsWith("A")){
				for(Player p : GameServer.clientlist){
					try{
						OutputStreamWriter osw = p.getOSW();
						osw.write(lastEdit + " wrote: " + mess +"\r\n");
					} catch(IOException io){
					}
				}

			}
			else if(mess.startsWith("S")){
				for(Player p : GameServer.clientlist){
					try{
						OutputStreamWriter osw = p.getOSW();
						osw.write(lastEdit + " wrote: " + mess +"\r\n");
					} catch(IOException io){
					}
				}

			}


				else {

					for(Player p : GameServer.clientlist){
						if(p.getNameBro().equals(lastEdit)){
							try{
								OutputStreamWriter osw = p.getOSW();

								
							} catch(Exception e) {
								
							}
							
							
							}
						}
					}
					
				}
			}
		}
	}






