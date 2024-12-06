
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
	        	JOptionPane.showMessageDialog(null, "Bem vindo(a) ao MartCode!", "MartCode", JOptionPane.INFORMATION_MESSAGE);
		        
	        	//Menu Principal:
		        for(int i = 0; i < 1;) {
		        	String[] menuPrincipal = {"Visualizar", "Pesquisar", "Adicionar", "Editar/Atualizar", "Deletar"};
		        	int opcaoPrincipal = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuPrincipal, menuPrincipal[0]);

		        	switch(opcaoPrincipal) {
			        case 0:
				        String[] menuVisualizar = {"Produto", "Mercado/Fornecedor", "Estoque", "Voltar"};
				        for(int j = 0; j < 1;) {
				        	int opcaoVisualizar = JOptionPane.showOptionDialog(null, "Qual tabela de dados você deseja visualizar?", "Visualizar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuVisualizar, menuVisualizar[0]);
				        	
				        	switch(opcaoVisualizar) {
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
		 	        			JOptionPane.showMessageDialog(null, menuVisualizar, senha, opcaoVisualizar);
		 	        			for (String linha: MartCode.ListEstoque(conn, "SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome as 'nome do produto', PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida as 'sistema de medida', ESTOQUE.validade, ESTOQUE.categoria, MERCADO_FORNECEDOR.nome as 'mercado/fornecedor' from MERCADO_FORNECEDOR inner join\r\n"
		 	        					+ "			ESTOQUE on MERCADO_FORNECEDOR.cod_mercado_fornecedor = ESTOQUE.cod_mercado_fornecedor inner join\r\n"
		 	        					+ "            PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto")) {
		 	   	        	         System.out.println(linha);
		 	   	        	    }
		 	   	        		break;
		 	   	        		
		 	        		case 3:
		 	        			j++;
		 	        			break;
		 	        			
		 	        		default:
					        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
		 	        			j++;
					        	i++;
		 	        			break;
				        	}
			        	}
				        break;
				       
			        case 1:
				        String[] menuPesquisar = {"Entre quantidades(Estoque)", "Entre validades(Estoque)", "Voltar"};
				        for(int j = 0; j < 1;) {
				        	int opcaoPesquisar = JOptionPane.showOptionDialog(null, "O que você deseja pesquisar?", "Pesquisar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuPesquisar, menuPesquisar[0]);

				        	switch(opcaoPesquisar) {
				        	case 0:
				        		MartCode.EstoqueQuantidade(conn, usuario, senha);
		 	        			break;
				        		
				        	case 1:
				        		MartCode.EstoqueValidade(conn, usuario, senha);
		 	        			break;
		 	        			
				        	case 2:
				        		j++;
				        		break;
				        		
				        	default:
					        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
				        		i++;
		 	        			j++;
				        		break;
				        	}
				        }
				        break;

			        	
			        case 2:
				        String[] menuAdicionar = {"Produto", "MercadoFornecedor", "Estoque", "Voltar"};
				        for(int j = 0; j < 1;) {
				        	int opcaoAdicionar = JOptionPane.showOptionDialog(null, "Em qual tabela você deseja adicionar?", "Adicionar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAdicionar, menuAdicionar[0]);
				        	
				        	switch(opcaoAdicionar) {
				        	case 0:
				        		MartCode.AdicionarProduto(conn, usuario, senha);
			        			break;
			        			
				        	case 1: 
				        		MartCode.AdicionarMercadoFornecedor(conn, usuario, senha);
				        		break;
				        		
				        	case 2:
				        		MartCode.AdicionarEstoque(conn, usuario, senha);
				        		break;
				        	
				        	case 3:
				        		j++;
				        		break;
				        		
				        	default:
					        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
					        	i++;
		 	        			j++;
					        	break;
				        	}
				        }
				        break;

			        case 3:
			        	String[] menuAtualizarModificar = {"Endereço (Mercado/Fornecedor)", "Contato(Mercado/Fornecedor)", "Quantidade(Estoque)", "Voltar"};
			        	for(int j = 0; j < 1;) {
			        		int opcaoAtualizarModificar = JOptionPane.showOptionDialog(null, "O que você deseja atualizar/modificar", "Atualizar/Modificar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAtualizarModificar, menuAtualizarModificar[0]);
			        		
			        		switch(opcaoAtualizarModificar) {
			        		case 0:
			        			MartCode.AtualizarEndereco(conn, usuario, senha);
			        			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar/Modificar 'Endereço(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
			        			break;
			        			
			        		case 1:
			        			MartCode.AtualizarContato(conn, usuario, senha);
			        			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar/Modificar 'Contato(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
			        			break;
			        			
			        		case 2:
			        			MartCode.AtualizarQuantidade(conn, usuario, senha);
			        			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar/Modificar 'Quantidade(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
			        			break;
			        		
			        		case 3:
			        			j++;
			        			break;
			        			
			        		default:
					        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
					        	i++;
		 	        			j++;
					        	break;
			        		}
			        	}
			        	break;     	
			        	
			        case 4:
			        	String[] menuDeletar = {"Item(Estoque)", "Voltar"};
			        	for(int j = 0; j < 1;) {
			        		int opcaoDeletar = JOptionPane.showOptionDialog(null, "O que você deseja deletar? Obs:Demais 'deletes' só poderão ser feitos dentro do próprio banco de dados", "Deletar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuDeletar, menuDeletar[0]);
			        		
			        		switch(opcaoDeletar) {
			        		case 0:
			        			MartCode.DeletarEstoque(conn, usuario, senha, i);
			        			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Deletar'Item(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
			 	        		break;
			 	        		
			        		case 1:
			        			j++;
			        			break;
			        			
			        		default:
					        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
			        			i++;
		 	        			j++;
			        			break;
			        		}
			        	}
			        	break;
			        	
			        default:
			        	JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "MartCode", JOptionPane.INFORMATION_MESSAGE);
			        	i++;
			        	break;
		        	}
		      	}
	     	}
	}
}
