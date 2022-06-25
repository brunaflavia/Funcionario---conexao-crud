//Variaveis e tal
public class Funcionarios {
    private int id;
    private String nome;
    private String cargo;
    private String cpf;


    public Funcionarios(int id,String nome, String cargo, String cpf){
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
    }

    //get e set
    //get
    public int getId() {
        return id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCargo(){
        return this.cargo;
    }
    public String getCPF(){
        return this.cpf;
    }


    //set
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String novo){
        this.nome = novo;
    }
    public void setCargo(String novo){
        this.cargo = novo;
    }
    public void setCPF(String novo){
        this.cpf = novo;
    }


}
