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
	   
	        for(int i = 0; i < 1; ) {
	        	System.out.println("O que deseja fazer? ");
	 	        System.out.println("0 - Sair\n" +
	 	        				   "1 - Visualizar\n" +
	 	        				   "2 - Adicionar\n" +
	 	        		           "3 - Editar/Atualizar\n" +
	 	        				   "4 - Deletar\n");
	 	       
	 	        int opcao = in.nextInt();
	 	       
	 	        switch(opcao) {
	 	        case 0: 
	 	        	System.out.println("Saindo do MartCode...");
	 	        	i = 2;
	 	        
	 	       	case 1: 
	 	       		for(int j = 0; j < 1;) {
	 	       		System.out.println("\nQual tabela de dados você deseja visualizar? ");
	 	        	System.out.println("1 - Produto \n" +
	 	        					   "2 - MercadoFornecedor \n" +
	 	        					   "3 - Estoque \n" +
	 	        					   "4 - Voltar");
	 	        
	 	        	int opcao2 = in.nextInt();
	 	        			
	 	        		switch(opcao2) {
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
	 	        			j = 1;
	 	        			
	 	        		default: 
	 	        			System.out.println("Opção não encontrada, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        	
	 	        case 2: 
	 	        	for(int j = 0; j < 1;) {
	 	        	System.out.println("\\nEm qual tabela de dados você deseja adicionar?");
	 	        	System.out.println("1 - Produto \n" +
	 	        					   "2 - MercadoFornecedor \n" +
	 	        					   "3 - Estoque \n" +
	 	        					   "4 - Voltar");
	 	        	
	 	        	int opcao3 = in.nextInt();	
	 	       
	 	        		switch(opcao3) {
	 	        		case 1: 
	 	        			MartCode.AdicionarProduto(conn, usuario, senha);
		        			break;
	 	        				
	 	        		case 2: 
	 	        			MartCode.AdicionarMercadoFornecedor(conn, usuario, senha);
		        			break;
	 	        					
	 	        		case 3:
	 	        			MartCode.AdicionarEstoque(conn, usuario, senha);
		        			break;
	 	   	        			
	 	        		case 4: 
	 	        			j = 1;
	 	        			
	 	        		default: 
	 	        			System.out.println("Opção não encontrada, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        
	 	        case 3: 
	 	        	for(int j = 0; j < 1;) {
	 	        	System.out.println("\nO que você deseja atualizar/modificar?");
	 	        	System.out.println("1 - Quantidade(Estoque) \n" +
	 	        					   "2 - Endereço(Fornecedor) \n" +
	 	        					   "3 - Contato(Fornecedor) \n" +
	 	        					   "4 - Voltar");
	 	        	
	 	        	int opcao4 = in.nextInt();

	 	        		switch(opcao4) {
	 	        		
	 	        		case 1: 
	 	        			MartCode.AtualizarQuantidade(conn, usuario, senha);
		        			break;
	 	        				
	 	        		case 2: 
	 	        			MartCode.AtualizarEndereco(conn, usuario, senha);
		        			break;
	 	        					
	 	        		case 3:
	 	        			MartCode.AtualizarContato(conn, usuario, senha);
		        			break;
	 	   	        			
	 	        		case 4: 
	 	        			j = 1;
	 	        			
	 	        		default: 
	 	        			System.out.println("Opção não encontrada, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        	
	 	        case 4: 
	 	        	for(int j = 0; j < 1;) {
		 	        System.out.println("\nO que você deseja deletar?\nDemais 'deletes' só poderão ser feitos no banco de dados.");
		 	        System.out.println("1 - Item do Estoque \n" +
		 	        				   "2 - Voltar");
		 	        
		 	        int opcao5 = in.nextInt();
		 	        
		 	        	switch(opcao5) {
		 	        	
		 	        	case 1: 
		 	        		MartCode.DeletarEstoque(conn, usuario, senha);
		 	        		break;
		 	        	
		 	        	case 2:
		 	        		j = 1;
		 	        		
		 	        	default:
		 	        		System.out.println("Opção não encontrada, tente novamente.");
		 	        	}
	 	        	}
	 	        }
	        }
	    }
	}
