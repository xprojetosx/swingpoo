package swingpoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

    private static String status = "Não conectou!";

    public static final String SERVIDOR = "jdbc:mysql://localhost:3306/cadastro";
    public static final String USUARIO = "root";
    public static final String SENHA = "";

    public static Connection obterConexaoMySQL() {

        Connection conexao = null;

        try {

            conexao = DriverManager.getConnection(SERVIDOR, USUARIO, SENHA);

            if (conexao != null) {
                status = "Conectado!";
            }

            return conexao;

        } catch (Exception e) {

            System.out.println("Driver não foi encontrado.");

            return null;

        }

    }// fim do método de conexão

    public static String retornaStatusConexao() {
        return status;
    }

    public static boolean fecharConexao() {

        try {

            Banco.obterConexaoMySQL().close();

            return true;

        } catch (SQLException e) {

            return false;

        }

    }

}