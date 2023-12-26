import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
    public static void main(String[] args){
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());pane.prefHeightProperty().bind(primaryStage.heightProperty());//版面绑定

        Label account_label = new Label("账号：");
        pane.getChildren().add(account_label);
        account_label.layoutXProperty().bind(pane.widthProperty().multiply(0.15));
        account_label.layoutYProperty().bind(pane.heightProperty().multiply(0.2));

        TextField account_text = new TextField("");
        pane.getChildren().add(account_text);
        account_text.layoutXProperty().bind(account_label.layoutXProperty().add(account_label.widthProperty()));
        account_text.layoutYProperty().bind(account_label.layoutYProperty());

        Label password_label = new Label("密码：");
        pane.getChildren().add(password_label);
        password_label.layoutXProperty().bind(pane.widthProperty().multiply(0.15));
        password_label.layoutYProperty().bind(pane.heightProperty().multiply(0.3));

        PasswordField password_text = new PasswordField();
        pane.getChildren().add(password_text);
        password_text.layoutXProperty().bind(password_label.layoutXProperty().add(password_label.widthProperty()));
        password_text.layoutYProperty().bind(password_label.layoutYProperty());

        TextField password_view_text = new TextField();
        password_view_text.setVisible(false);
        pane.getChildren().add(password_view_text);
        password_view_text.layoutXProperty().bind(password_label.layoutXProperty().add(password_label.widthProperty()));
        password_view_text.layoutYProperty().bind(password_label.layoutYProperty());
        password_text.textProperty().bindBidirectional(password_view_text.textProperty());

//        Button visualize_button = new Button();
        Image visualizeImage = new Image("img/visualize.png");
        Image unvisualizeImage = new Image("img/unvisualize.png");
        ImageView visualizeImageView = new ImageView(unvisualizeImage);
        pane.getChildren().add(visualizeImageView);
        visualizeImageView.setFitWidth(30);
        visualizeImageView.setFitHeight(30);
        visualizeImageView.layoutXProperty().bind(password_text.layoutXProperty().add(password_text.widthProperty()).add(5)); // 设置相对布局位置
        visualizeImageView.layoutYProperty().bind(password_text.layoutYProperty());

        visualizeImageView.setOnMouseClicked((MouseEvent event) -> {
            // 点击图像时执行的操作
            if (visualizeImageView.getImage() == visualizeImage) {
                System.out.println("1");
                visualizeImageView.setImage(unvisualizeImage);
                password_view_text.setVisible(false);
                password_text.setVisible(true);
                password_text.requestFocus();
            } else {
                visualizeImageView.setImage(visualizeImage);
                password_view_text.setVisible(true);
                password_text.setVisible(false);
                password_view_text.requestFocus();
            }
        });

        Button login_button = new Button("注册");
        pane.getChildren().add(login_button);
        login_button.layoutXProperty().bind(pane.widthProperty().multiply(0.3));
        login_button.layoutYProperty().bind(pane.heightProperty().multiply(0.4));

        Button logon_button = new Button("登录");
        pane.getChildren().add(logon_button);
        logon_button.layoutXProperty().bind(pane.widthProperty().multiply(0.5));
        logon_button.layoutYProperty().bind(pane.heightProperty().multiply(0.4));

        logon_button.setOnMouseClicked((MouseEvent event) -> {
            MessageThread messageThread = new MessageThread();

            // 启动MessageThread线程
            Thread thread = new Thread(messageThread::run);
            thread.start();
            String message = String.format("%-10s,%-32s", account_text.getText());
            // 发送登录请求
            messageThread.sendMessage(Constants.RE_LOGON+message);

            try {
                // 获取下一条消息
                String response = messageThread.getNextMessage();

                // 检查登录结果
                if (response.equals(new String(Constants.LOGIN_SUCCESS))) {
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(pane,500,400));//页面大小
        primaryStage.show();
    }
}

//class SocketThread extends Thread{
//    private Socket socket;
//
//
//    BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(100);
//    SocketThread(Socket socket){
//        this.socket=socket;
//    }
//    public void run(){
//        try {
//            Socket socket = new Socket(serverIP, serverPort);
//            System.out.println("已连接到服务器");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String message;
//            while ((message = reader.readLine()) != null) {
//                messageQueue.put(message); // 将收到的消息放入队列
//            }
//        }catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
