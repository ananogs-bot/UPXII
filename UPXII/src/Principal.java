import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	    public static void main(String[] args) throws SQLException {
	       
	        Scanner input = new Scanner (System.in);
	        Connection conn = null;
	        boolean conectividade = false;
	        
	    	JOptionPane.showMessageDialog(null, "Conectando Java e MySql", "MartCode", JOptionPane.INFORMATION_MESSAGE);

	        String usuario = JOptionPane.showInputDialog(null, "Usuário", "Login", JOptionPane.QUESTION_MESSAGE);
	        String senha = JOptionPane.showInputDialog(null, "Senha", "Login", JOptionPane.QUESTION_MESSAGE);
	       
	       //Tentativas da Conectividade com o Banco de Dados:
	        try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
		    	JOptionPane.showMessageDialog(null, "Conectou!", "Status", JOptionPane.INFORMATION_MESSAGE);
		    	try {
		            conn = MartCode.getConnection("MARTCODE", usuario, senha);
			    	JOptionPane.showMessageDialog(null, "Conectou denovo!", "Status", JOptionPane.INFORMATION_MESSAGE);
		            conectividade = true; 
		        } catch (ClassNotFoundException | SQLException e) {
			    	JOptionPane.showMessageDialog(null, "Não conectou!", "Status", JOptionPane.INFORMATION_MESSAGE);
		            e.printStackTrace();
		        }
		    	
	        } catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, "Não conectou!", "Status", JOptionPane.INFORMATION_MESSAGE);
	            e.printStackTrace();
	        }
	        
	        
	        //Continuidade, caso a conexão tenha dado certo:
	        if(conectividade) {
		        //Mensagem de ínicio:
	        	JOptionPane.showMessageDialog(null, "-- Bem vindo(a) ao MartCode! --", "MartCode", JOptionPane.INFORMATION_MESSAGE);
		        
	        	//Menu Principal:
		        for(int i = 0; i < 1;) {
		        	String[] menuPrincipal = {"Visualizar", "Pesquisar", "Adicionar", "Editar/Atualizar", "Deletar"};
		        	int opcaoMP = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuPrincipal, menuPrincipal[0]);

		        	switch(opcaoMP) {
			        case 0:
				        String[] menuVisualizar = {"Produto", "Mercado/Fornecedor", "Estoque", "Voltar"};
				        for(int j = 0; j < 1;) {
				        	int opcaoMV = JOptionPane.showOptionDialog(null, "Qual tabela de dados você deseja visualizar?", "Visualizar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuVisualizar, menuVisualizar[0]);
				        	
				        	switch(opcaoMV) {
				        	case 0: 
		 	        			for (String linha: MartCode.ListProduto(conn, "SELECT * from PRODUTO")) {
		 	        				System.out.println(linha);
		 		        	    }
		 	        			break;
		 	        				
		 	        		case 1: 
		 	        			for (String linha: MartCode.ListMercadoFornecedor(conn, "SELECT * from MERCADO_FORNECEDOR")) {
		 	        				System.out.println(linha);
		 	   	        	     }
		 	        			break;
		 	        					
		 	        		case 2:
		 	        			for (String linha: MartCode.ListEstoque(conn, "SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome as 'nome do produto', PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida as 'sistema de medida', ESTOQUE.validade, ESTOQUE.categoria, MERCADO_FORNECEDOR.nome as 'mercado/fornecedor' from MERCADO_FORNECEDOR inner join\r\n"
		 	        					+ "			ESTOQUE on MERCADO_FORNECEDOR.cod_mercado_fornecedor = ESTOQUE.cod_mercado_fornecedor inner join\r\n"
		 	        					+ "            PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto")) {
		 	   	        	         System.out.println(linha);
		 	   	        	    }
		 	   	        		break;
		 	   	        		
		 	        		case 3:
				        		
				        	}
			        	}
			        }
		        }
		        
		        
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
	        }
	    }
	}
