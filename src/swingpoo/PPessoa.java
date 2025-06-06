package swingpoo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class PPessoa {

    public ArrayList<EPessoa> consultarPessoaPorNome(String nome) throws SQLException {

        ArrayList<EPessoa> oListaPessoa = new ArrayList();

        Connection conn = Banco.obterConexaoMySQL();

        String SQL = "select * from pessoa where nome like ? order by nome";
        try {
            ResultSet oRS;
            try (PreparedStatement pstm = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, "%" + nome.toUpperCase().trim() + "%");
                oRS = pstm.executeQuery();
                int i = 0;
                while (oRS.next()) {
                    
                    EPessoa oEPessoa = new EPessoa();
                    
                    oEPessoa.setCPF(oRS.getString("cpf"));
                    oEPessoa.setNome(oRS.getString("nome"));
                    oEPessoa.setDataNasc(oRS.getString("data_nasc"));
                    oEPessoa.setSexo(oRS.getString("sexo"));
                    
                    oListaPessoa.add(i++, oEPessoa);
                    
                }
            }
            oRS.close();
            Banco.fecharConexao();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return oListaPessoa;

    }

    public ArrayList<EPessoa> consultarPessoa() throws SQLException {

        ArrayList<EPessoa> oListaPessoa = new ArrayList();

        Connection conn = Banco.obterConexaoMySQL();

        String SQL = "select * from pessoa order by nome";
        try {
            ResultSet oRS;
            try (PreparedStatement pstm = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                oRS = pstm.executeQuery();
                int i = 0;
                while (oRS.next()) {
                    
                    EPessoa oEPessoa = new EPessoa();
                    
                    oEPessoa.setCPF(oRS.getString("cpf"));
                    oEPessoa.setNome(oRS.getString("nome"));
                    oEPessoa.setDataNasc(oRS.getString("data_nasc"));
                    oEPessoa.setSexo(oRS.getString("sexo"));
                    
                    oListaPessoa.add(i++, oEPessoa);
                    
                }
            }
            oRS.close();
            Banco.fecharConexao();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return oListaPessoa;

    }

    public String incluirPessoa(String cpf, String nome, String dataNasc, String sexo) {

        String resultado;

        Connection conn = Banco.obterConexaoMySQL();

        try {

            String SQL = "insert into pessoa values(?,?,?,?)";
            try (PreparedStatement pstm = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, cpf.toUpperCase());
                pstm.setString(2, nome.toUpperCase());
                pstm.setString(3, dataNasc.toUpperCase());
                pstm.setString(4, sexo.toUpperCase());
                pstm.executeUpdate();
            }

            resultado = "Inclusão efetuada com sucesso!";
        } catch (SQLException e) {
            resultado = "Erro na inclusão: " + e.getMessage();

        }

        Banco.fecharConexao();

        return resultado;
    }

    public String excluirPessoa(String cpf) throws SQLException {
        String resultado;

        Connection conn = Banco.obterConexaoMySQL();

        try {

            String SQL = "delete from pessoa where cpf = ?";
            try (PreparedStatement pstm = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, cpf.toUpperCase());
                pstm.executeUpdate();
            }
            resultado = "Exclusao efetuada com sucesso!";
        } catch (SQLException e) {
            resultado = "Erro na exclusao: " + e.getMessage();

        }

        Banco.fecharConexao();

        return resultado;
    }

    public String alterarPessoa(String cpf, String nome, String dataNasc, String sexo) {

        String resultado;

        Connection conn = Banco.obterConexaoMySQL();

        try {

            String SQL = "update pessoa set nome = ?,data_nasc = ?,sexo = ? where cpf = ?";
            try (PreparedStatement pstm = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, nome.toUpperCase());
                pstm.setString(2, dataNasc.toUpperCase());
                pstm.setString(3, sexo.toUpperCase());
                pstm.setString(4, cpf.toUpperCase());
                pstm.executeUpdate();
            }
            resultado = "Alteraçao efetuada com sucesso!";
        } catch (SQLException e) {
            resultado = "Erro na alteraçao: " + e.getMessage();

        }

        Banco.fecharConexao();

        return resultado;
    }

}
