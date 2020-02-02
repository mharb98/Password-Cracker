import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server{
	public static void main(String[] args) throws IOException {
	   	ServerSocket ss=new ServerSocket(4747);
	   	while(true) {
	   		Socket s1=null;
	   		try {
	   		s1=ss.accept();
	   		DataOutputStream O = new DataOutputStream(s1.getOutputStream());
	   		DataInputStream I=new DataInputStream(s1.getInputStream());
	   		System.out.println("Assign new thread for the new client!");
	   		Thread t=new ThreadHandler(s1,I,O);
	   		t.start();
	   		}catch(Exception e) {
	   			s1.close();
	   			e.printStackTrace();
	   		}
	   	}
	}
}

class ThreadHandler extends Thread {
	final DataInputStream i;
	final DataOutputStream o;
	final Socket s;
	ThreadHandler(Socket s,DataInputStream i,DataOutputStream o){
		this.s=s;
		this.i=i;
		this.o=o;
	}
	 @Override
	 public void run() {
		 String received;
		 long ToReturn=0;
		 long total=10000000;
		 while(true) {
			 try {
				received=i.readUTF();
				 if(received.equals("Exit")) 
	                {  
	                    System.out.println("Client " + this.s + " sends exit..."); 
	                    System.out.println("Closing this connection."); 
	                    this.s.close(); 
	                    System.out.println("Connection closed"); 
	                    break; 
	                } 
				 else if(received.equals("Request")) {
					  String x = String.valueOf(ToReturn); 
					  String y = String.valueOf(total);
					  o.writeUTF(x);
					  o.writeUTF(y);
					 ToReturn+=1000;
					 total-=1000;
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	 try
     { 
         // closing resources 
         this.i.close(); 
         this.o.close(); 
           
     }catch(IOException e){ 
         e.printStackTrace(); 
     } 
 } 
}