import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	    public static void main(String[] args) throws SQLException {
	       
	        System.out.println("Conectando Java e MySQL");
	       
	        Scanner in = new Scanner (System.in);
	       
	        System.out.println("Usuário: ");
	        String usuario = in.nextLine();
	       
	        System.out.println("Senha: ");
	        String senha = in.nextLine();
	       
	       
	        Connection conn = null;
	       
	       
	        try {
	            conn = Alimento.getConnection("MARTCODE", usuario, senha);
	            System.out.println("Conectou!");
	        } catch (Exception e) {
	            System.out.println("Não conectou!");
	            e.printStackTrace();
	        }
	 
	       
	        try {
	            conn = Alimento.getConnection("MARTCODE", usuario, senha);
	            System.out.println("Conectou denovo!");
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("Não conectou de segunda!");
	        }
	       
	       
	       /* try {
	            conn = Alimento.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Nome: ");
	            String Nome = in.nextLine();
	           
	            System.out.print("Data da Fabricação: ");
	            String Data_fabricacao = in.nextLine();
	           
	            System.out.print("Data de Validade: ");
	            String Data_validade = in.nextLine();
	           
	            System.out.print("Quantidade: ");
	            String Quantidade = in.nextLine();
	           
	            String sql = String.format("insert into Alimento (Nome, Data_fabricacao, Data_validade, Quantidade)"
	                                        + " values ('%s', '%s', '%s', '%s');",
	                                        Nome, Data_fabricacao, Data_validade, Quantidade);
	           
	            Alimento.insert(conn, sql);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }*/
	       
	       
	        for (String linha: Alimento.List(conn, "select * from Alimento")) {
	            System.out.println(linha);
	        }
	        
	        for (String linha: Alimento.List(conn, "select Nome, Quantidade from Alimento")) {
	            System.out.println(linha);
	        }
	        
	        
	    }
	 
	}
