
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadClient {

    public static void main(String[] args) {
        int numTasks = 5;

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(createTask0("192.168.1.122",9000));
        exec.execute(createTask0("192.168.1.122",9001));
    }
    // 定义一个简单的任务
    private static Runnable createTask0(final String ip,final int port) {
        return new Runnable() {
            private Socket socket = null;
            

            public void run() {
                System.out.println("Task " + 0 + ":start");
                try {                    
                    socket = new Socket(ip, port);
                    //获取输出流，用于客户端向服务器端发送数据
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    //获取输入流，用于接收服务器端发送来的数据
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    //客户端向服务器端发送数据
                    dos.writeUTF("我是客户端，请求连接!");
                    byte[] b=new byte[1024];
                    
                    while(b!=null)
                    {
                    	dis.read(b);
                    	String msg = new String(b);
                    	System.out.println(msg);
                    }
                } catch (IOException e) {                    
                	try {
						if (!socket.isClosed()) {
							socket.close();
						}
						System.out.println("连接断开异常");
						Thread.sleep(3000);
						System.out.println("重新连接。。。");
						run();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}catch (IOException e2) {
						e2.printStackTrace();
					}
                }
            }

        };
    }
}
