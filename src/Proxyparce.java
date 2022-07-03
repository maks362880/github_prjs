import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Proxyparce {
    public static void main(String[] args) throws IOException {
        HttpsURLConnection connection;
        int timeout = 5000;

        try {
            URL url = new URL("https://thepiratebay.org/search.php?q=user:Borusssia:");


//------------------------------------Настройка подключения через прокси сервер ------------------------------------------//

//            Authenticator.setDefault(new Authenticator() {
//                public PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("Login","password".toCharArray());
//                }
//            });

            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("194.28.91.10", 5678));
            connection = (HttpsURLConnection) url.openConnection(proxy);

//-----------------------------------------------------------------------------------------------------------------------//

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setDoInput(true);
            connection.setDoOutput(true);

//------------------------------------------------------------------------------------------------------------------------//

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                System.out.println(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getRequestMethod());
            }
        } catch (Exception e) {
            System.out.println("Connection init error" + e.getMessage());
        }
    }
}