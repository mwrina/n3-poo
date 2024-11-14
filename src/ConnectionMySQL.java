import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    public static String status = "Não conectou";

    public ConnectionMySQL () {

    }

    public static java.sql.Connection getConnectionMySQL () {

        //INSTANCIA conexão nula (pq ainda não sabemos o que vamos conectar)
        Connection connection = null;

        // DRIVER que vamos usar para a conexão
        String driverName = "com.mysql.cj.jdbc.Driver";

        //Para verificar se o nome do driver está correto.
        //Se estiver errado, não podemos continuar.
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String server = "localhost";
        String db = "n3poo";

        //jdbc = protocolo de conexão
        //mysql = tipo de banco de dados
        //server = servidor (local)
        //db = nome do banco utilizado
        String url = "jdbc:mysql://" + server + "/" + db;

        String username = "root";
        String password = "";

        //Faz conexão
        try {
            connection = DriverManager.getConnection(url, username, password);
            status = "conectado";
            System.out.println(status);
        } catch (SQLException e) {
            //Executa se algo der errado
            System.out.println(status);
            e.printStackTrace();
        }

        return connection;
    }

}