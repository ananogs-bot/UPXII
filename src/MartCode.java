import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import java.util.ArrayList;

public class MartCode {
	
	//Conexão do Banco de Dados com Java
	 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/";
	   
	    public static Connection getConnection(String nomebanco, String usuario, String senha) throws ClassNotFoundException, SQLException {
	       
	        Class.forName(DRIVER);
	        return DriverManager.getConnection(URL+nomebanco, usuario, senha);
	    }
	 
	    
	    public static boolean insert (Connection conn, String sql) throws SQLException {
	        
	    	Statement cmd = conn.createStatement();
	        cmd.executeUpdate(sql);
	       
	        return true;
	    }
	   
	    
	    //Lista de Produtos (SELECT)
	    public static List<String> ListProduto(Connection conn, String sql) throws SQLException {
	    	
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncod_produto: 	"  	+ dados.getInt(1));
	            linhas.add("nome: 		"  + dados.getString(2));
	            linhas.add("marca: 		"  + dados.getString(3));
	            linhas.add("---------------------------------------------\n");
	        }
			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Visualizar 'Produto'", JOptionPane.INFORMATION_MESSAGE);
	        return linhas;
	    }

	    
	    //Lista de Mercado/Fornecedor (SELECT)
	    public static List<String> ListMercadoFornecedor(Connection conn, String sql) throws SQLException {
	        
	    	List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncod_mercado_fornecedor:   " 	+ dados.getInt(1));
	            linhas.add("nome:					"   + dados.getString(2));
	            linhas.add("contato:				"   + dados.getString(3));
	            linhas.add("endereço:				"   + dados.getString(4));
	            linhas.add("---------------------------------------------\n");
	        }
			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Visualizar 'MercadoFornecedor'", JOptionPane.INFORMATION_MESSAGE);
	        return linhas;
	    }
	    
	    
	    //Lista de Estoque (SELECT)
	    public static List<String> ListEstoque(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncod_estoque:			"   + dados.getInt(1));
	            linhas.add("nome: 				"  	+ dados.getString(2));
	            linhas.add("marca: 				"   + dados.getString(3));
	            linhas.add("quantidade: 		"   + dados.getInt(4));
	            linhas.add("sistema de medida: 	"   + dados.getString(5));
	            linhas.add("validade: 			"   + dados.getString(6));
	            linhas.add("categoria: 			"   + dados.getString(7));
	            linhas.add("mercado/fornecedor: "   + dados.getString(8));
	            linhas.add("--------------------------------------\n");
	        }
			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Visualizar 'Estoque'", JOptionPane.INFORMATION_MESSAGE);
	        return linhas;
	    }
	    
	    //Lista de Estoque entre quantidade (SELECT)
	    public static void EstoqueQuantidade(Connection conn, String usuario, String senha) throws SQLException {
	        
	        String inputValorI = JOptionPane.showInputDialog(null, "valor inicial:", "Pesquisar 'Entre quantidades(Estoque)'", JOptionPane.QUESTION_MESSAGE);
	        int valor_inicial = Integer.parseInt(inputValorI);
	        
	        String inputValorF = JOptionPane.showInputDialog(null, "valor final:", "Pesquisar 'Entre quantidades(Estoque)", JOptionPane.QUESTION_MESSAGE);
	        int valor_final = Integer.parseInt(inputValorF);
	        
	        
	        String sql = String.format("SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome, PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida, ESTOQUE.validade " +
	                                   "from ESTOQUE " +
	                                   "INNER JOIN PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto " +
	                                   "WHERE ESTOQUE.quantidade BETWEEN %d and %d;", 
	                                   valor_inicial, valor_final);
	        
	        for (String linha : ListEstoqueQuantidade(conn, sql)) {
	            System.out.println(linha);
	        }
	    }
	    
	    public static List<String> ListEstoqueQuantidade(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncódigo estoque:		"   + dados.getInt(1));
	            linhas.add("nome: 				"  	+ dados.getString(2));
	            linhas.add("marca: 				"   + dados.getString(3));
	            linhas.add("quantidade: 		"   + dados.getInt(4));
	            linhas.add("sistema de medida: 	"   + dados.getString(5));
	            linhas.add("validade: 			"   + dados.getString(6));
	            linhas.add("--------------------------------------\n");
	        }
			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Pesquisar 'Entre quantidades(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
	        return linhas;
	    }
	    
	    
	  //Lista de Estoque entre quantidade (SELECT)
	    public static void EstoqueValidade(Connection conn, String usuario, String senha) throws SQLException {
	        
	    	String inputData_Inicial = JOptionPane.showInputDialog(null, "data inicial:", "Pesquisar 'Entre validades(Estoque)'", JOptionPane.QUESTION_MESSAGE);
	        int data_inicial = Integer.parseInt(inputData_Inicial);
	        
	        String inputData_Final = JOptionPane.showInputDialog(null, "data final", "Pesquisar 'Entre validades(Estoque)'", JOptionPane.QUESTION_MESSAGE);
	        int data_final = Integer.parseInt(inputData_Final);
	        
	        String sql = String.format("SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome, PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida, ESTOQUE.validade " +
	                                   "from ESTOQUE " +
	                                   "INNER JOIN PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto " +
	                                   "WHERE ESTOQUE.validade BETWEEN '%s' and '%s';", 
	                                   data_inicial, data_final);
	        
	        for (String linha : ListEstoqueValidade(conn, sql)) {
	            System.out.println(linha);
	        }
	    }
	    
	    public static List<String> ListEstoqueValidade(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncod_estoque:			"  + dados.getInt(1));
	            linhas.add("nome: 				"  	+ dados.getString(2));
	            linhas.add("marca: 				"   + dados.getString(3));
	            linhas.add("quantidade: 		"   + dados.getInt(4));
	            linhas.add("sistema de medida: 	"   + dados.getString(5));
	            linhas.add("validade: 			"   + dados.getString(6));
	            linhas.add("--------------------------------------\n");
	        }
			JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Pesquisar 'Entre validades(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
	        return linhas;
	    }
	    
	    //Adicionar item ao Produto (INSERT)
	    public static void AdicionarProduto(Connection conn, String usuario, String senha) throws SQLException {
	    		    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            String nome = JOptionPane.showInputDialog(null, "nome:", "Adicionar 'Produto'", JOptionPane.QUESTION_MESSAGE);
	           
	            String marca = JOptionPane.showInputDialog(null, "marca:", "Adicionar 'Produto'", JOptionPane.QUESTION_MESSAGE);
	 
	            String sql = String.format("INSERT into PRODUTO (nome, marca)"
	                                        + " values ('%s', '%s');", 
	                                        nome, marca);
	           
	            MartCode.insert(conn, sql);
        		JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Adicionar 'Produto'", JOptionPane.INFORMATION_MESSAGE);
	        } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Adicionar 'Produto'", JOptionPane.INFORMATION_MESSAGE);
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //Adicionar item ao MercadoFornecedor (INSERT)
	    public static void AdicionarMercadoFornecedor(Connection conn, String usuario, String senha) throws SQLException {
	    		    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            String nome = JOptionPane.showInputDialog(null, "nome:", "Adicionar 'MercadoFornecedor'", JOptionPane.QUESTION_MESSAGE);

	            String contato = JOptionPane.showInputDialog(null, "nome:", "Adicionar 'Produto'", JOptionPane.QUESTION_MESSAGE);

	            String endereco = JOptionPane.showInputDialog(null, "nome:", "Adicionar 'Produto'", JOptionPane.QUESTION_MESSAGE);
	 
	            String sql = String.format("INSERT into MERCADO_FORNECEDOR (nome, contato, endereco)"
	                                        + " values ('%s', '%s', '%s');", 
	                                        nome, contato, endereco);
	           
	            MartCode.insert(conn, sql);
	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Adicionar 'MercadoFornecedor'", JOptionPane.INFORMATION_MESSAGE);
	        } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Adicionar 'MercadoFornecedor'", JOptionPane.INFORMATION_MESSAGE);
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //Adicionar item ao Estoque (INSERT)
	    public static void AdicionarEstoque(Connection conn, String usuario, String senha) throws SQLException {
	    		    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            String inputCod_Mercado_Fornecedor = JOptionPane.showInputDialog(null, "cod_mercado:", "Adicionar 'Estoque'", JOptionPane.QUESTION_MESSAGE);
	            int cod_mercado_fornecedor = Integer.parseInt(inputCod_Mercado_Fornecedor);
	            
	            String inputCod_Produto = JOptionPane.showInputDialog(null, "cod_produto:", "Adicionar 'Estoque'", JOptionPane.QUESTION_MESSAGE);
	            int cod_produto = Integer.parseInt(inputCod_Produto);
	            
	            String inputQuantidade = JOptionPane.showInputDialog(null, "quantidade", "Adicionar 'Estoque'", JOptionPane.QUESTION_MESSAGE);
	            int quantidade = Integer.parseInt(inputQuantidade);
	            
	            String[] menuSistemaMedida = {"KG", "L", "Unidade"};
	            String sistema_medida = null;
	            for(int j = 0; j < 1;) {
	            	int inputSistema_Medida = JOptionPane.showOptionDialog(null, "sistema de medida:", "Adicionar 'Estoque'", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuSistemaMedida, menuSistemaMedida);
	            
	            	switch(inputSistema_Medida) {
	            	case 0:
	            		sistema_medida = "KG";
	            		j++;
	            		break;
	            		
	            	case 1: 
	            		sistema_medida = "L";
	            		j++;
	            		break;
	            		
	            	case 2: 
	            		sistema_medida = "Unidade";
	            		j++;
	            		break;
	            		
	            	default:
	            		JOptionPane.showMessageDialog(null, "Opção não encontrada!", "Adicionar 'Estoque'", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            }
	            
	            String validade = JOptionPane.showInputDialog(null, "validade:", "Adicionar 'Estoque'", JOptionPane.QUESTION_MESSAGE);
	            
	            String categoria = JOptionPane.showInputDialog(null, "categoria:", "Adicionar 'Estoque'", JOptionPane.QUESTION_MESSAGE);
	         
	            String sql = String.format("INSERT into ESTOQUE (cod_mercado_fornecedor, cod_produto, "
	            							+ "quantidade, sistema_medida, validade, categoria)"
	                                        + " values (%d, %d, %d, '%s', '%s', '%s');", 
	                                        cod_mercado_fornecedor, cod_produto, quantidade, sistema_medida, validade, categoria);
	           
	            MartCode.insert(conn, sql);
	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Adicionar 'Estoque'", JOptionPane.INFORMATION_MESSAGE);
	        } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Adicionar 'Estoque'", JOptionPane.INFORMATION_MESSAGE);
	        	e.printStackTrace();
	        }
	    }
	    
	    
	    //Atualizar quantidade do Estoque (UPDATE)
	    public static void AtualizarQuantidade (Connection conn, String usuario, String senha) throws SQLException {
	    		    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	    		
		        String inputCod_Estoque = JOptionPane.showInputDialog(null, "cod_estoque:", "Atualizar 'Quantidade'", JOptionPane.QUESTION_MESSAGE);
		        int cod_estoque = Integer.parseInt(inputCod_Estoque);
		        
		        String inputQuantidade = JOptionPane.showInputDialog(null, "nova quantidade:", "Atualizar 'Quantidade'", JOptionPane.QUESTION_MESSAGE);
		        int quantidade = Integer.parseInt(inputQuantidade);
		        
		        String sql = String.format("UPDATE ESTOQUE SET quantidade = %d "
		        						+ " WHERE cod_estoque = %d;",
		                                    quantidade, cod_estoque);
		        
		        MartCode.insert(conn, sql);
	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar 'Quantidade(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
		    } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Atualizar 'Quantidade(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
		        e.printStackTrace();
		    }
	    }
	    
	    
	    //Atualizar endereço do Mercado/Fornecedor (UPDATE)
	    public static void AtualizarEndereco (Connection conn, String usuario, String senha) throws SQLException {
	    		    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	            
		        String inputCod_Mercado_Fornecedor = JOptionPane.showInputDialog(null, "cod_mercado_fornecedor:", "Atualizar 'Endereço(MercadoFornecedor)'", JOptionPane.QUESTION_MESSAGE);
		        int cod_mercado_fornecedor = Integer.parseInt(inputCod_Mercado_Fornecedor);
		        
		        String endereco = JOptionPane.showInputDialog(null, "novo endereço:", "Atualizar 'Endereço(MercadoFornecedor)'", JOptionPane.QUESTION_MESSAGE);

	            String sql = String.format("UPDATE MERCADO_FORNECEDOR"
	            						+ " SET endereco = '%s' "
	            						+ " WHERE cod_mercado_fornecedor = %d;", 
	            						endereco, cod_mercado_fornecedor);
	            
	            MartCode.insert(conn, sql);
	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar 'Endereço(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
	    	} catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Atualizar 'Endereço(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    //Atualizar contato do Mercado/Fornecedor (UPDATE)
	    public static void AtualizarContato(Connection conn, String usuario, String senha) throws SQLException {

	    	try {
	    		conn = MartCode.getConnection("MARTCODE", usuario, senha);
	    		
	    		String inputCod_Mercado_Fornecedor = JOptionPane.showInputDialog(null, "cod_mercado_fornecedor:", "Atualizar 'Contato(MercadoFornecedor)'", JOptionPane.QUESTION_MESSAGE);
			    int cod_mercado_fornecedor = Integer.parseInt(inputCod_Mercado_Fornecedor);
	    		
	    		String contato = JOptionPane.showInputDialog(null, "novo contato:", "Atualizar 'Contato(MercadoFornecedor)'", JOptionPane.QUESTION_MESSAGE);
	    		
	    		String sql = String.format("UPDATE MERCADO_FORNECEDOR"
	    								+ " SET contato = '%s' "
	    								+ " WHERE cod_mercado_fornecedor = %d;", 
	    								contato, cod_mercado_fornecedor);
	    		
	    		MartCode.insert(conn, sql);
	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Atualizar 'Contato(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
	    	} catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação  não concluída!", "Atualizar 'Contato(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
	    	    e.printStackTrace();
	    	}
	    }
	    
	    
	    //Deletar item do Estoque (DELETE)
	    public static void DeletarEstoque(Connection conn, String usuario, String senha, int i) throws SQLException {

	    	try {
	    		conn = MartCode.getConnection("MARTCODE", usuario, senha);
	    		
	    		String inputCod_Estoque = JOptionPane.showInputDialog(null, "cod_estoque:", "Deletar 'Item(Estoque)'", JOptionPane.QUESTION_MESSAGE);
			    int cod_estoque = Integer.parseInt(inputCod_Estoque);
			    
			    String[] confirmacao = {"Sim", "Não"};
	        	for(int j = 0; j < 1;) {
	        		int opcao = JOptionPane.showOptionDialog(null, "Você tem certeza que deseja deletar esse item?", "Deletar 'Item(Estoque)'", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirmacao, confirmacao[0]);
	        		
	        		switch(opcao) {
	        		case 0:
	        			String sql = String.format("DELETE from Estoque "
								+ " WHERE cod_estoque = %d;", 
								cod_estoque);
		
	        			MartCode.insert(conn, sql);
	    	            JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!", "Deletar 'Item(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
	        			j++;
	        			break;
	        			
	        		case 1:
	    	            JOptionPane.showMessageDialog(null, "Operação cancelada!", "Deletar 'Item(Estoque)'", JOptionPane.INFORMATION_MESSAGE);
	    	            j++;
	    	            break;
	        			
	    	        default:
	    	            JOptionPane.showMessageDialog(null, "Operação não concluída!", "Atualizar 'Contato(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
	    	            i++;
	        		}
	        	}
            } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(null, "Operação  não concluída!", "Atualizar 'Contato(Mercado/Fornecedor)'", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
	    }  
}