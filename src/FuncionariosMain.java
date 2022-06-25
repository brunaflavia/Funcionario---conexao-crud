import java.sql.SQLException;
import java.util.Scanner;


//Testar a conexão, chama metodos e tal
public class FuncionariosMain {
    public static void main(String[] args) throws SQLException {
        System.out.println("--- Hello, amigos(as) funcionários(as) ---");
        Scanner s = new Scanner(System.in);

        //Inserir dados.
        //Valores a serem inseridos no banco. Poderia passar direto no parâmetro e tirar essa parte de ler teclado. 
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Cargo: ");
        String cargo = s.nextLine();
        System.out.print("CPF: ");
        String cpf = s.nextLine();

        Funcionarios f1 = new Funcionarios(0, nome, cargo, cpf);
        FuncionariosDAO.inserir(f1);

        //Consulta de apenas 1 funcionário, passa o valor do Id no parâmetro, e ele busca no banco
        FuncionariosDAO.consultar(1);

        //Deleta funcionário, pelo Id, passa o id dele.
        FuncionariosDAO.deletar(2);

        //Consulta de de todos funcionários do banco
        FuncionariosDAO.listar();

        
    } 
}
