import java.text.ParseException;
import java.util.Date;
import java.text.*;
import java.util.Locale;
import java.util.Objects;

/*
control_head    time        context
0-16(16)        16-46(30)   46-
LOGIN_SUCCESS
LOGIN_FAIL
MESSAGE_IN
MESSAGE_OUT
RE_LOGIN
RE_LOGON                    accont|0-10(10)    password|10-42(32)//登录
 */
public class Message {
    String control_head;
    Date date;
    String context;
    String message;
    public Message(String message){
        this.message = message;
        control_head = message.substring(0,Constants.head_length);
        if(message.length()>=46){
            context = message.substring(46);
        }
    }
    public String getControlHead(){
        return control_head;
    }
    public void getTime(){
        try {
            // 将字符串转换为 Date 对象
            date = Constants.dft_en.parse(message.substring(16,16+30));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setTime(){
        date = new Date();
        message = message.substring(0,16) + String.format("%-30s", Constants.dft_en.format(date)) + message.substring(46);
    }
    public void setTime(String datestr){
        try {
            // 将字符串转换为 Date 对象
            date = Constants.dft_en.parse(message.substring(16,16+30));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        message = message.substring(0,16) + String.format("%-30s", Constants.dft_en.format(date)) + message.substring(46);
    }
    public String getAccont(){
        if(Objects.equals(control_head, Constants.RE_LOGON)){
            return context.substring(0,10);
        }else{
            return "_err";
        }
    }
    public String getPassword(){
        if(Objects.equals(control_head, Constants.RE_LOGON)){
            return context.substring(10,42);
        }else{
            return "_err";
        }
    }
}
