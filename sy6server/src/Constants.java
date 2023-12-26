import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final SimpleDateFormat dft_en = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    public static final int serverPort = 8000;
    public static final String serverIP = "localhost";
    public static final int head_length = 16;
    public static final String LOGIN_SUCCESS = String.format("%-"+head_length+"s", "LOGIN_SUCCESS");
    public static final String LOGIN_FAIL = String.format("%-"+head_length+"s", "LOGIN_FAIL");
    public static final String MESSAGE_IN = String.format("%-"+head_length+"s", "LOGIN_FAIL");
    public static final String MESSAGE_OUT = String.format("%-"+head_length+"s", "LOGIN_FAIL");
    public static final String RE_LOGIN = String.format("%-"+head_length+"s", "RE_LOGIN");
    public static final String RE_LOGON = String.format("%-"+head_length+"s", "RE_LOGON");

}
/*


 */
