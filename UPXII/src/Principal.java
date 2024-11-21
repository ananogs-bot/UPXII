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
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	            System.out.println("Conectou!");
	        } catch (Exception e) {
	            System.out.println("Não conectou!");
	            e.printStackTrace();
	        }
	 
	       
	        try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	            System.out.println("Conectou denovo!");
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("Não conectou de segunda!");
	        }
	       
	       
	        System.out.println("Bem vindo(a) ao MartCode!");
	        
	        for(int i = 0; i < 1;) {
	        	System.out.println("O que deseja fazer? ");
	 	        System.out.println("1 - Visualizar a tabela 'Produto'\n" +
	 	        				   "2 - Visualizar a tabela 'MercadoFornecedor'\n" +
	 	        		           "3 - Visualizar a tabela 'Estoque'\n" +
	 	        				   "4 - Adicionar 'Produto'\n" +
	 	        		           "5 - Adicionar 'MercadoFornecedor\n" +
	 	        				   "6 - Adicionar 'Estoque'\n" +
	 	        				   "0 - Sair");
	 	        
	 	        int opcao = in.nextInt();
	        	
	        	switch(opcao) {
	        		case 1: 
	        			for (String linha: MartCode.ListProduto(conn, "select * from PRODUTO")) {
	        	            System.out.println(linha);
	        	        }
	        			break;
	        			
	        		case 2: 
	        			for (String linha: MartCode.ListMercadoFornecedor(conn, "select * from MERCADO_FORNECEDOR")) {
	        	            System.out.println(linha);
	        	        }
	        			break;
	        			
	        		case 3: 
	        			for (String linha: MartCode.ListEstoque(conn, "select * from ESTOQUE")) {
	        	            System.out.println(linha);
	        	        }
	        			break;
	        			
	        		case 4: 
	        			try {
	        	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	        	           
	        	            System.out.print("Nome: ");
	        	            String Nome = in.nextLine();
	        	           
	        	            System.out.print("Marca: ");
	        	            String Marca = in.nextLine();
	        	 
	        	            String sql = String.format("insert into Produto (Nome, Marca)"
	        	                                        + " values ('%s', '%s');",
	        	                                        Nome, Marca);
	        	           
	        	            MartCode.insert(conn, sql);
	        	        } catch (ClassNotFoundException | SQLException e) {
	        	            e.printStackTrace();
	        	        }
	        			break;
	        			
	        		case 5: 
	        			try {
	        	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	        	           
	        	            System.out.print("Nome: ");
	        	            String Nome = in.nextLine();
	        	           
	        	            System.out.print("Contato: ");
	        	            String Contato = in.nextLine();
	        	            
	        	            System.out.print("Endereço: ");
	        	            String Endereco = in.nextLine();
	        	 
	        	            String sql = String.format("insert into Mercado_Fornecedor (Nome, Contato, Endereco)"
	        	                                        + " values ('%s', '%s', '%s');",
	        	                                        Nome, Contato, Endereco);
	        	           
	        	            MartCode.insert(conn, sql);
	        	        } catch (ClassNotFoundException | SQLException e) {
	        	            e.printStackTrace();
	        	        }
	        			break;
	        		
	        		case 6: 
	        			try {
	        	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	        	           
	        	            System.out.print("CodMercadoFornecedor: ");
	        	            int Cod_mercado_fornecedor = in.nextInt();
	        	            
	        	            System.out.print("CodProduto: ");
	        	            int Cod_produto = in.nextInt();
	        	           
	        	            System.out.print("Quantidade: ");
	        	            int Quantidade = in.nextInt();
	        	            
	        	            System.out.print("Validade: ");
	        	            String Validade = in.nextLine();
	        	            
	        	            System.out.print("Categoria: ");
	        	            String Categoria = in.nextLine();
	        	 
	        	            String sql = String.format("insert into Estoque (Cod_mercado_fornecedor, Cod_produto, "
	        	            							+ "Quantidade, Validade, Categoria)"
	        	                                        + " values ('%i', '%i', '%i, '%s', '%s');",
	        	                                        Cod_mercado_fornecedor, Cod_produto, Quantidade, Validade, Categoria);
	        	           
	        	            MartCode.insert(conn, sql);
	        	        } catch (ClassNotFoundException | SQLException e) {
	        	            e.printStackTrace();
	        	        }
	        			break;
	        		
	        		case 10: System.out.println("Saindo do MartCode...");
	        		i = 2;
	        	}
	        }
	      
	       
	       
	       try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Nome: ");
	            String Nome = in.nextLine();
	           
	            System.out.print("Marca: ");
	            String Marca = in.nextLine();
	 
	            String sql = String.format("insert into Produto (Nome, Marca)"
	                                        + " values ('%s', '%s');",
	                                        Nome, Marca);
	           
	            MartCode.insert(conn, sql);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } /*
	       
	       
	        /* for (String linha: MartCode.ListProduto(conn, "select * from Produto")) {
	            System.out.println(linha);
	        }
	        
	        for (String linha: MartCode.ListMercadoFornecedor(conn, "select * from Produto")) {
	            System.out.println(linha);
	        }
	        
	        for (String linha: MartCode.ListEstoque(conn, "select * from Produto")) {
	            System.out.println(linha);
	        }
	        
	        for (String linha: MartCode.List(conn, "select Nome, Quantidade from Alimento")) {
	            System.out.println(linha);
	        } */
	        
	        
	    }
	 
	}
