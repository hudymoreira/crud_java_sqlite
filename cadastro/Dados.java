package cadastro;
public class Dados {
	private String nome;
	private String telefone;
    private String endereco;
    private String id;
    

	public Dados(String nome, String telefone, String endereco, String id) {
		this.nome = nome;
		this.telefone = telefone;
        this.endereco = endereco;
        this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

    public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.endereco = id;
	}
    public String toString(){
        return  "id: "
        + this.id + ", nome: " 
        + this.nome + ", telefone : " 
        + this.telefone + ", endereco: " 
        + this.endereco;  
    }
}
