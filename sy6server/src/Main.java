import java.io.DataInput;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket;
            while(true){
                socket = serverSocket.accept();
                ServerSocketThread socketThread = new ServerSocketThread(socket);
                socketThread.run();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

//class ServerSocketThread extends Thread{
//    private Socket socket;
//    ServerSocketThread(Socket socket){
//        this.socket=socket;
//    }
//    public void run(){
//        try{
//            DataInputStream in;
//            DataOutputStream out;
//            Scanner scan;
//            scan = new Scanner(System.in);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
//            while (true) {
//                System.out.println(in.read());
//                out.writeUTF(scan.nextLine());
//            }
//        }catch (Exception ex){
//        }
//    }
//}