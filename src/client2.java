
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client2{
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s=new Socket("localhost",4747);
		String request="Request";
		DataOutputStream DOS=new DataOutputStream(s.getOutputStream());
		DataInputStream DIS=new DataInputStream(s.getInputStream());
		while(true) {
			DOS.writeUTF(request);
			String received=DIS.readUTF();
			String total=DIS.readUTF();
			long tINT = Integer.parseInt(total);
			long rINT = Integer.parseInt(received);
			if(tINT==0) {
				DOS.writeUTF("Exit");
				break;
			}
			else {
				for(long i=rINT;i<(rINT+1000);i++) {
					System.out.println("koko");
				}
			}
		}
	}
}