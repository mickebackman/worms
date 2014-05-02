package Server;

public class CommandCenter {

	String message;
	String lastEdit;
	Boolean available;
	Boolean msgExists;
	public CommandCenter(){
		message = "";
		available = true;
		msgExists = false;
		lastEdit = "";
		
		
	}
	
	
public synchronized String getMessage(){
	String temp = message;
	message = "";
	msgExists = false;
	available = true;
	notifyAll();
	return temp;
}

public synchronized void setMessage(String set, String source){
	 while (available == false) {
	        try {
	            wait();
	        } catch (InterruptedException e) {
	        }
	    }
	    available = false;          
	    message = set;
	    msgExists = true;
	    lastEdit = source;
	    notifyAll(); 
}

public synchronized String lastEdit(){
	return lastEdit;
}

public boolean mailExists() {
	return msgExists;
		
	}
}


