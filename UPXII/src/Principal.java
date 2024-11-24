import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	    public static void main(String[] args) throws SQLException {
	       
	        System.out.println("Conectando Java e MySQL");
	       
	        Scanner input = new Scanner (System.in);
	       
	        System.out.println("Usuário: ");
	        String usuario = input.nextLine();
	       
	        System.out.println("Senha: ");
	        String senha = input.nextLine();
	       
	       
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
	       
	       
	        System.out.println("\n-- Bem vindo(a) ao MartCode! --");
	   
	        for(int i = 0; i < 1; ) {
	        	System.out.println("\nO que deseja fazer? ");
	 	        System.out.println("0 - Sair\n" +
	 	        				   "1 - Visualizar\n" +
	 	        				   "2 - Pesquisar \n" +
	 	        				   "3 - Adicionar\n" +
	 	        		           "4 - Editar/Atualizar\n" +
	 	        				   "5 - Deletar");
	 	       
	 	        int opcao1 = input.nextInt();
	 	       
	 	        switch(opcao1) {
	 	        case 0: 
	 	        	System.out.println("Saindo do MartCode...");
	 	        	i++;
	 	        	break;
	 	        	
	 	       	case 1: 
	 	       		for(int j = 0; j < 1;) {
		 	       		System.out.println("\nQual tabela de dados você deseja visualizar? ");
		 	        	System.out.println("0 - Voltar \n" +
		 	        					   "1 - Produto \n" +
		 	        					   "2 - MercadoFornecedor \n" +
		 	        					   "3 - Estoque");
		 	        	int opcao2 = input.nextInt();
	 	        			
	 	        		switch(opcao2) {
	 	        		case 0: 
	 	        			j++;
	 	        			break;
	 	        			
	 	        		case 1: 
	 	        			for (String linha: MartCode.ListProduto(conn, "SELECT * from PRODUTO")) {
	 	        				System.out.println(linha);
	 		        	    }
	 	        			break;
	 	        				
	 	        		case 2: 
	 	        			for (String linha: MartCode.ListMercadoFornecedor(conn, "SELECT * from MERCADO_FORNECEDOR")) {
	 	        				System.out.println(linha);
	 	   	        	     }
	 	        			break;
	 	        					
	 	        		case 3:
	 	        			for (String linha: MartCode.ListEstoque(conn, "SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome as 'nome do produto', PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida as 'sistema de medida', ESTOQUE.validade, ESTOQUE.categoria, MERCADO_FORNECEDOR.nome as 'mercado/fornecedor' from MERCADO_FORNECEDOR inner join\r\n"
	 	        					+ "			ESTOQUE on MERCADO_FORNECEDOR.cod_mercado_fornecedor = ESTOQUE.cod_mercado_fornecedor inner join\r\n"
	 	        					+ "            PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto")) {
	 	   	        	         System.out.println(linha);
	 	   	        	    }
	 	   	        		break;
	 	   	        			
	 	        		default: 
	 	        			System.out.println("Opção inválida, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        	
	 	       	case 2:
	 	       		for(int j = 0; j < 1;) {
		 	       		System.out.println("\nO que você deseja pesquisar?");
		 	        	System.out.println("0 - Voltar \n" +
		 	        					   "1 - Entre quantidades(Estoque) \n" +
		 	        					   "2 - Entre validades(Estoque)");
		 	        	int opcao2 = input.nextInt();
		 	        	
		 	        	switch(opcao2) {
		 	        	case 0:
		 	        		j++;
		 	        		break;
		 	        		
		 	        	case 1: 
		 	        		MartCode.EstoqueQuantidade(conn, usuario, senha);
	 	        			break;
	 	        			
	 	        		case 2:
	 	        			MartCode.EstoqueValidade(conn, usuario, senha);
	 	        			break;
		 	        	
		 	        	default: 
		 	        		System.out.println("Opção inválida, tente novamente.");
		 	        	}
		 	        }
	 	       		break;
	 	       		
	 	        case 3: 
	 	        	for(int j = 0; j < 1;) {
		 	        	System.out.println("\nEm qual tabela de dados você deseja adicionar?");
		 	        	System.out.println("0 - Voltar \n" +
		 	        					   "1 - Produto \n" +
		 	        					   "2 - MercadoFornecedor \n" +
		 	        					   "3 - Estoque");
		 	        	int opcao2 = input.nextInt();	
	 	       
	 	        		switch(opcao2) {
	 	        		case 0: 
	 	        			j++;
	 	        			break;
	 	        			
	 	        		case 1: 
	 	        			MartCode.AdicionarProduto(conn, usuario, senha);
		        			break;
	 	        				
	 	        		case 2: 
	 	        			MartCode.AdicionarMercadoFornecedor(conn, usuario, senha);
		        			break;
	 	        					
	 	        		case 3:
	 	        			MartCode.AdicionarEstoque(conn, usuario, senha);
		        			break;
	 	        			
	 	        		default: 
	 	        			System.out.println("Opção inválida, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        
	 	        case 4: 
	 	        	for(int j = 0; j < 1;) {
		 	        	System.out.println("\nO que você deseja atualizar/modificar?");
		 	        	System.out.println("0 - Voltar \n" +
		 	        					   "1 - Endereço(Mercado/Fornecedor) \n" +
		 	        					   "2 - Contato(Mercado/Fornecedor)" +
		 	        					   "3 - Quantidade(Estoque)");
		 	        	int opcao2 = input.nextInt();

	 	        		switch(opcao2) {
	 	        		case 0: 
	 	        			j++;
	 	        			break;
	 	        		
	 	        		case 1: 
	 	        			MartCode.AtualizarEndereco(conn, usuario, senha);
		        			break;
		        		
	 	        		case 2:
	 	        			MartCode.AtualizarContato(conn, usuario, senha);
		        			break;
		        			
	 	        		case 3: 
	 	        			MartCode.AtualizarQuantidade(conn, usuario, senha);
		        			break;
	 	        			
	 	        		default: 
	 	        			System.out.println("Opção inválida, tente novamente.");
	 	        		}	
	 	        	}
	 	        	break;
	 	        	
	 	        case 5: 
	 	        	for(int j = 0; j < 1;) {
			 	        System.out.println("\nO que você deseja deletar?");
			 	        System.out.println("0 - Voltar \n" + 
			 	        				   "1 - Item do Estoque \n" +
			 	        				   "Demais 'deletes' só poderão ser feitos dentro do próprio banco de dados.");
			 	        int opcao2 = input.nextInt();
		 	        
		 	        	switch(opcao2) {
		 	        	case 0:
		 	        		j++;
		 	        		break;
		 	        		
		 	        	case 1: 
		 	        		MartCode.DeletarEstoque(conn, usuario, senha);
		 	        		break;
		 	        		
		 	        	default:
		 	        		System.out.println("Opção inválida, tente novamente.");
		 	        	}
	 	        	}
	 	        	break;
	 	        	
	 	        default:
	 	        	System.out.println("Opção inválida, tente novamente.");
	 	        }
	        }
	        input.close();
	    }
	}
