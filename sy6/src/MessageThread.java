import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageThread {
    private final BlockingQueue<String> messageQueue;
    Socket socket;
    public MessageThread() {
        super();
        messageQueue = new LinkedBlockingQueue<>(); // 使用阻塞队列实现消息存储
    }

    public void run() {
        try {
            socket = new Socket(Constants.serverIP, Constants.serverPort);
            System.out.println("已连接到服务器");
            while (!Thread.currentThread().isInterrupted()) { // 检查当前线程是否被中断
                BufferedReader reader = receiveMessage(); // 接收消息
                String ls="";
                String ls1=reader.readLine();
                while ((ls = reader.readLine()) != null) {
                    ls1+="\n"+ls;
                }
                messageQueue.offer(ls1); // 将收到的消息放入队列
            }
        }catch (UnknownHostException e) {
            // 处理无法解析主机名或 IP 地址的情况
            e.printStackTrace();
            // 或者给用户一个提示信息
            System.err.println("无法解析服务器的主机名或 IP 地址");
        } catch (IOException e) {
            // 处理其他 I/O 异常，如连接超时、网络不可达等
            e.printStackTrace();
            // 或者给用户一个提示信息
            System.err.println("连接超时");
        }

    }

    public String getNextMessage() throws InterruptedException {
        return messageQueue.take(); // 从队列中获取下一条消息，如果队列为空则阻塞等待
    }

    private BufferedReader receiveMessage() {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            // 处理其他 I/O 异常，如连接超时、网络不可达等
            e.printStackTrace();
            // 或者给用户一个提示信息
            System.err.println("接收超时");
        }
        return reader; // 这里假设接收到的消息为空字符串
    }

    public void sendMessage(String message) {
        try{
            DataOutputStream out = new DataOutputStream (socket.getOutputStream());
            out.writeUTF(message);
        }catch(IOException e){
            // 处理其他 I/O 异常，如连接超时、网络不可达等
            e.printStackTrace();
            // 或者给用户一个提示信息
            System.err.println("发送超时");
        }
    }
}
