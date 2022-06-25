import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

//Responsavel pela conexao 
//inserir,remover, consultar e listar
public class FuncionariosDAO {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Funcionario";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        System.out.println("Conectando ao banco....");
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (ClassNotFoundException e){
            System.out.println("Erro aqui no 1");
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("Erro aqui no 2");
            throw new RuntimeException(e);
        }
            return null;
    }

    //Inserir no banco
    public static void inserir(Funcionarios f) {
        String sql = "INSERT INTO dados(Nome, Cargo, CPF) values (?,?,?)";
        PreparedStatement stmt = null;
        System.out.println("\n----- Inserindo dados no banco de funcionários -----");
        try{
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1,f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setString(3, f.getCPF());
            stmt.executeUpdate();
            System.out.println("Inserido com sucesso");
        } catch(SQLException ex){
            System.out.println("Triste, erro" + ex);
        }
    }
    //Deletar no banco
    public static void deletar(int i) {
        Funcionarios f = new Funcionarios(i, null, null, null);
        var faz = getConnection();
        String sql = "DELETE FROM dados where id = ?";
        System.out.println("\n----- Deletando funcionario do banco de funcionários, fornecido pelo ID -----");

        PreparedStatement stmt = null;

        try{
            stmt = faz.prepareStatement(sql);
            stmt.setInt(1,f.getId());
            stmt.execute();
            System.out.println("Deletado com sucesso");
            stmt.close();    
        } catch(SQLException ex){
            System.out.println("Triste, erro" + ex);
        }
    }
    //Consulta dados de um funcionário no banco de dados.
    public static void consultar(int valor) throws SQLException{
        Funcionarios f = new Funcionarios(valor, null, null, null);
        var faz = getConnection();
        var sql = "SELECT Id, Nome, Cargo, CPF FROM dados WHERE id = ?";
        var stmt = faz.prepareStatement(sql);
        try{
            //stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1,f.getId());
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n----- Consultando o banco de funcionários informado pelo ID -----");
        while (rs.next()) {
            String nome = rs.getString("Nome");
            String cargo = rs.getString("Cargo");
            String cpf = rs.getString("CPF");
            System.out.println(
            "Id " + "         ➞    " + valor + "\n" + 
            "Funcionario" + " ➞    " + nome + "\n" +
            "Cargo" + "       ➞    " + cargo + "\n" +
            "CPF " + "        ➞    " + cpf + "");
            System.out.println("-------------------------------");
          }
        System.out.println("Consulta finalizada com sucesso");  
        } catch(SQLException ex){
            System.out.println("Triste NA CONSULTA, erro" + ex);
        }

        
    }
    //Consulta dados de todos funcionários no banco de dados.
    public static void listar() throws SQLException{
        //Funcionarios f = new Funcionarios(0, null, null, null);
        var faz = getConnection();
        var sql = "SELECT Id, Nome, Cargo, CPF FROM dados";
        var stmt = faz.prepareStatement(sql);
        try{
            //stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n----- Consultando o banco de funcionários, todos eles -----");
        while (rs.next()) {
            Integer id = rs.getInt("Id");
            String nome = rs.getString("Nome");
            String cargo = rs.getString("Cargo");
            String cpf = rs.getString("CPF");
            System.out.println("-------------------------------");
            System.out.println(
            "Id " + "         ➞    " + id + "\n" + 
            "Funcionario" + " ➞    " + nome + "\n" +
            "Cargo" + "       ➞    " + cargo + "\n" +
            "CPF " + "        ➞    " + cpf + "");
            System.out.println("-------------------------------");
          }
        System.out.println("Consulta geral de todos funcionário está ok!");  
        } catch(SQLException ex){
            System.out.println("Triste NA CONSULTA, erro" + ex);
        }

        
    }

}
