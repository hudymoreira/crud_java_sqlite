package cadastro;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Cadastro {
	public static void main(String[] args) {
		try {
			SQLite dbCon = new SQLite("dados.db");
			dbCon.initDB();
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
                Dados dados = new Dados(null,null,null,null);
                System.out.print("Nome: ");
                dados.setNome(sc.nextLine());
                System.out.print("Telefone: ");
                dados.setTelefone(sc.nextLine());
                System.out.print("Endereco: ");
                dados.setEndereco(sc.nextLine());
                System.out.println("\n\n Deseja realmente savar os dados \n ");
                System.out.println(dados.toString());
                System.out.print("\n\n [s/n]: ");
                String r = sc.nextLine();
                if (r.equals("s")){
                    dbCon.inserir(dados);
                }
            }else if(opt.equals("2")){
                System.out.println("Listar pessoas cadastradas\n ");
                listar(dbCon);
            } else if(opt.equals("3")){
                String i,r;
                System.out.println("\n Editar um cadastro ");
                System.out.print("Digite o id do cadastro para editar: ");
                i = sc.nextLine();
                Dados dados = dbCon.pegar(i);
                if (dados.getId() != null){
                    Dados dadosN = new Dados(null,null,null,null);
                    System.out.print("Nome: ");
                    dadosN.setNome(sc.nextLine());
                    System.out.print("Telefone: ");
                    dadosN.setTelefone(sc.nextLine());
                    System.out.print("Endereco: ");
                    dadosN.setEndereco(sc.nextLine());
                    System.out.println("\n\n Antes : \n\n");
                    System.out.println(dados.toString());
                    System.out.println("\n\n Depois : \n\n");
                    System.out.println(dadosN.toString());
                    System.out.print("\n\n Deseja fazer essa alteracao: [s/n]: ");
                    r = sc.nextLine();
                    if (r.equals("s")){
                        dadosN.setId(i);
                        dbCon.alterar(dadosN);
                    }
                }else{
                    System.out.println("Cadastro nao Localizado.");
                }

            } else if(opt.equals("4")){
                System.out.println("Apagar um cadastro \n");
                String i,r;
                System.out.print("Digite o id do cadastro para apagar: ");
                i = sc.nextLine();
                Dados dados = dbCon.pegar(i);
                if (dados.getId() != null){
                    System.out.print(dados.toString());
                    System.out.print("\n\n Deseja realmete apagar? [s/n]: ");
                    r = sc.nextLine();
                    if (r.equals("s")){
                        dbCon.apagar(i);
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void listar(SQLite dbCon) {
		
		List<Dados> lista = dbCon.listar();
        Iterator<Dados> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Dados dados = (Dados) iterator.next();
            System.out.println("Id:" + dados.getId());
            System.out.println("Nome:" + dados.getNome());
            System.out.println("Telefone:" + dados.getTelefone());
			System.out.println("Endereco:" + dados.getEndereco() + "\n");
        }
	}
}
