import java.util.*;
import java.sql.*;
class Cadastro  {
    private static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists cadastro(id integer primary key autoincrement, nome  text, telefone text, endereco text)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void inserir(String nome, String telefone, String endereco) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {
            System.out.println("Conexao realizada !!!!");
            Statement statement = connection.createStatement();
            statement.execute("insert into cadastro (nome,telefone, endereco) values ('"+nome+"','"+telefone+"','"+endereco+"')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void alterar(String nome, String telefone, String endereco, String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {

            Statement statement = connection.createStatement();
            statement.execute("update cadastro set nome = '"+nome+"', telefone = '"+telefone+"', endereco = '"+endereco+"' where id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void listar() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {
            PreparedStatement stmt = connection.prepareStatement("select * from cadastro");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                System.out.println( id + " - " + nome + " - " + telefone + " - " + endereco);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static Map<String, String> pegar(String valor) {
        Map<String, String> dados = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {
            PreparedStatement stmt = connection.prepareStatement("select * from cadastro where id = " + valor);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                dados.put("id",resultSet.getString("id"));
                dados.put("nome",resultSet.getString("nome"));
                dados.put("telefone",resultSet.getString("telefone"));
                dados.put("endereco",resultSet.getString("endereco"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dados;
    }
    private static void apagar(String id ) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db")) {
           Statement statement = connection.createStatement();
           statement.execute("delete from cadastro where id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args)  {
        connect();
        while(true){
            System.out.println("\n Cadastro \n");
            System.out.println("1 Novo Cadastro;");
            System.out.println("2 Listar pessas cadastradas;");
            System.out.println("3 Editar um cadastro;");
            System.out.println("4 Apagar um cadastro;");
            System.out.println("5 Sair ");
            System.out.print("Escolha uma opcao: ");
            Scanner sc= new Scanner(System.in);
            String opt= sc.nextLine();
            if (opt.equals("1")){
                System.out.println("Novo cadastro \n ");
                String nome, telefone, endereco,r;

                System.out.print("Nome: ");
                nome = sc.nextLine();

                System.out.print("Telefone: ");
                telefone = sc.nextLine();

                System.out.print("Endereco: ");
                endereco = sc.nextLine();

                System.out.println("\n\n Deseja realmente savar os dados \n ");
                System.out.println("Nome: "+nome);
                System.out.println("Telefone: "+telefone);
                System.out.println("Endereco " +endereco);

                System.out.print("\n\n [s/n]: ");
                r = sc.nextLine();
                if (r.equals("s")){
                    inserir(nome,telefone,endereco);
                }
            } else if(opt.equals("2")){
                System.out.println("Listar pessoas cadastradas\n ");
                listar();
            } else if(opt.equals("3")){
                String nome, telefone, endereco,r,i;
                System.out.println("\n Editar um cadastro ");
                System.out.print("Digite o id do cadastro para editar: ");
                i = sc.nextLine();
                Map<String, String> dados = pegar(i);
                if (dados.size() == 4 ){
                    System.out.print("Nome: ");
                    nome = sc.nextLine();

                    System.out.print("Telefone: ");
                    telefone = sc.nextLine();

                    System.out.print("Endereco: ");
                    endereco = sc.nextLine();

                    System.out.println("\n\n Antes : \n\n");
                    System.out.println("Nome: " + dados.get("nome"));
                    System.out.println("Telefone : " + dados.get("telefone"));
                    System.out.println("Endereco : " + dados.get("endereco"));
                    System.out.println("\n\n Depois : \n\n");
                    System.out.println("Nome: " + nome);
                    System.out.println("Telefone : " + telefone);
                    System.out.println("Endereco : " + endereco);
                    System.out.print("\n\n Deseja fazer essa alteracao: [s/n]: ");
                    r = sc.nextLine();
                    if (r.equals("s")){
                        alterar(nome,telefone,endereco,i);
                    }
                } else {
                    System.out.println("Cadastro nao Localizado.");
                }

            } else if(opt.equals("4")){
                System.out.println("Apagar um cadastro \n");
                String i,r;
                System.out.print("Digite o id do cadastro para editar: ");
                i = sc.nextLine();
                Map<String, String> dados = pegar(i);
                if (dados.size() == 4 ){
                    System.out.println("\n\n Dados para apagar : \n\n");
                    System.out.println("Nome: " + dados.get("nome"));
                    System.out.println("Telefone : " + dados.get("telefone"));
                    System.out.println("Endereco : " + dados.get("endereco"));
                    System.out.print("\n\n Deseja realmete apagar? [s/n]: ");
                    r = sc.nextLine();
                    if (r.equals("s")){
                        apagar(i);
                    }
                } else {
                    System.out.println("Cadastro nao Localizado.");
                }
            } else if(opt.equals("5")){
                System.out.println("Sair ");
                System.exit(0);
            } else {
                System.out.println("Opcao Invalida \n\n ");
            }
        }
    }
}

