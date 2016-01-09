import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("192.168.1.111",9000);
            //获取输出流，用于客户端向服务器端发送数据
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //获取输入流，用于接收服务器端发送来的数据
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            //客户端向服务器端发送数据
            dos.writeUTF("我是客户端，请求连接!");
            //打印出从服务器端接收到的数据
            byte[] b=new byte[1024];
            
            while(b!=null)
            {
            	dis.read(b);
            	System.out.println(new String(b));
            }
            
            
            
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//       	 String msg = null;
//       	msg=br
//            while (msg != null)
       	
            
//            System.out.println(dis.readUTF());
            //不需要继续使用此连接时，记得关闭哦
//            dos.close();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
 
