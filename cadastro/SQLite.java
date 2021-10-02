package cadastro;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SQLite {
	private Connection conn;
	private Statement stm;
	public SQLite(String arquivo) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection("jdbc:sqlite:" + arquivo);
		this.stm = this.conn.createStatement();
	}

	public void initDB() {
		try {
            this.stm.execute("create table if not exists cadastro(id integer primary key autoincrement, nome  text, telefone text, endereco text)");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void inserir(Dados dados) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.executeUpdate("INSERT INTO cadastro (nome,telefone,endereco) VALUES ('"
				+ dados.getNome() + "','"
                + dados.getTelefone() + "','"
				+ dados.getEndereco() + "')");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public void alterar(Dados dados) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.execute("update cadastro set nome = '"
            +dados.getNome()+"', telefone = '"
            +dados.getTelefone()+"', endereco = '"
            +dados.getEndereco()+"' where id = " 
            +dados.getId());
            System.out.print(dados.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void apagar(String id) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.executeUpdate("delete from cadastro WHERE id = " + id);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Dados> listar() {
        ArrayList<Dados> lista = new ArrayList<Dados>();
		ResultSet rs;
		try {
			rs = this.stm.executeQuery("SELECT * FROM cadastro ");
			while (rs.next()) {
                Dados dados = new Dados(rs.getString("nome"), rs.getString("telefone"), rs.getString("endereco"),rs.getString("id"));
				lista.add(dados);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
    public Dados pegar(String id){
        ResultSet rs;
        Dados dados = new Dados(null,null,null,null);
        try {
			rs = this.stm.executeQuery("SELECT * FROM cadastro where id = " + id);
			while (rs.next()) {
                dados = new Dados(rs.getString("nome"), rs.getString("telefone"), rs.getString("endereco"),rs.getString("id"));
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return dados;

    }
}
