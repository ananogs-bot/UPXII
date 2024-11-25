import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.List;
import java.util.Scanner;

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
	            linhas.add("\ncódigo produto:"  + dados.getInt(1));
	            linhas.add("nome"          		+ dados.getString(2));
	            linhas.add("marca:"         	+ dados.getString(3));
	            linhas.add("--------------------------------------\n");
	        }
	        return linhas;
	    }

	    
	    //Lista de Mercado/Fornecedor (SELECT)
	    public static List<String> ListMercadoFornecedor(Connection conn, String sql) throws SQLException {
	        
	    	List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncódigo mercado/fornecedor" 	+ dados.getInt(1));
	            linhas.add("nome:"                     		+ dados.getString(2));
	            linhas.add("contato:"                 		+ dados.getString(3));
	            linhas.add("endereço:"                 		+ dados.getString(4));
	            linhas.add("--------------------------------------\n");
	        }
	        return linhas;
	    }
	    
	    
	    //Lista de Estoque (SELECT)
	    public static List<String> ListEstoque(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncódigo estoque:"    	 + dados.getInt(1));
	            linhas.add("nome: "  	 			 + dados.getString(2));
	            linhas.add("marca: "            	 + dados.getString(3));
	            linhas.add("quantidade: "            + dados.getInt(4));
	            linhas.add("sistema de medida: "     + dados.getString(5));
	            linhas.add("validade: "              + dados.getString(6));
	            linhas.add("categoria: "             + dados.getString(7));
	            linhas.add("mercado/fornecedor: "    + dados.getString(8));
	            linhas.add("--------------------------------------\n");
	        }
	        return linhas;
	    }
	    
	    //Lista de Estoque entre quantidade (SELECT)
	    public static void EstoqueQuantidade(Connection conn, String usuario, String senha) throws SQLException {
	        Scanner input = new Scanner(System.in);
	        
	        System.out.println("Qual o primeiro valor?");
	        int valor1 = input.nextInt();
	        System.out.println("Qual o segundo valor?");
	        int valor2 = input.nextInt();
	        
	        String sql = String.format("SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome, PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida, ESTOQUE.validade " +
	                                   "from ESTOQUE " +
	                                   "INNER JOIN PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto " +
	                                   "WHERE ESTOQUE.quantidade BETWEEN %d and %d;", 
	                                   valor1, valor2);
	        
	        for (String linha : ListEstoqueQuantidade(conn, sql)) {
	            System.out.println(linha);
	        }
	    }
	    
	    public static List<String> ListEstoqueQuantidade(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncódigo estoque:"    	 + dados.getInt(1));
	            linhas.add("nome: "  	 			 + dados.getString(2));
	            linhas.add("marca: "            	 + dados.getString(3));
	            linhas.add("quantidade: "            + dados.getInt(4));
	            linhas.add("sistema de medida: "     + dados.getString(5));
	            linhas.add("validade: "              + dados.getString(6));
	            linhas.add("--------------------------------------\n");
	        }
	        return linhas;
	    }
	    
	    
	  //Lista de Estoque entre quantidade (SELECT)
	    public static void EstoqueValidade(Connection conn, String usuario, String senha) throws SQLException {
	        Scanner input = new Scanner(System.in);
	        
	        System.out.println("Qual a primeira data?");
	        int data1 = input.nextInt();
	        System.out.println("Qual a segunda data?");
	        int data2 = input.nextInt();
	        
	        String sql = String.format("SELECT ESTOQUE.cod_estoque as 'código do estoque', PRODUTO.nome, PRODUTO.marca, ESTOQUE.quantidade, ESTOQUE.sistema_medida, ESTOQUE.validade " +
	                                   "from ESTOQUE " +
	                                   "INNER JOIN PRODUTO on ESTOQUE.cod_produto = PRODUTO.cod_produto " +
	                                   "WHERE ESTOQUE.validade BETWEEN '%s' and '%s';", 
	                                   data1, data2);
	        
	        for (String linha : ListEstoqueValidade(conn, sql)) {
	            System.out.println(linha);
	        }
	    }
	    
	    public static List<String> ListEstoqueValidade(Connection conn, String sql) throws SQLException {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\ncódigo estoque:"    	 + dados.getInt(1));
	            linhas.add("nome: "  	 			 + dados.getString(2));
	            linhas.add("marca: "            	 + dados.getString(3));
	            linhas.add("quantidade: "            + dados.getInt(4));
	            linhas.add("sistema de medida: "     + dados.getString(5));
	            linhas.add("validade: "              + dados.getString(6));
	            linhas.add("--------------------------------------\n");
	        }
	        return linhas;
	    }
	    
	    //Adicionar item ao Produto (INSERT)
	    public static void AdicionarProduto(Connection conn, String usuario, String senha) throws SQLException {
	    	
	    	Scanner input = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.println("Nome: ");
	            String nome = input.next();
	           
	            System.out.println("Marca: ");
	            String marca = input.next();
	 
	            String sql = String.format("INSERT into PRODUTO (nome, marca)"
	                                        + " values ('%s', '%s');", 
	                                        nome, marca);
	           
	            MartCode.insert(conn, sql);
	            System.out.println("Item adicionado com sucesso!");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //Adicionar item ao MercadoFornecedor (INSERT)
	    public static void AdicionarMercadoFornecedor(Connection conn, String usuario, String senha) throws SQLException {
	    	
	    	Scanner input = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Nome: ");
	            String nome = input.nextLine();
	           
	            System.out.print("Contato: ");
	            String contato = input.nextLine();
	            
	            System.out.print("Endereço: ");
	            String endereco = input.nextLine();
	 
	            String sql = String.format("INSERT into MERCADO_FORNECEDOR (nome, contato, endereco)"
	                                        + " values ('%s', '%s', '%s');", 
	                                        nome, contato, endereco);
	           
	            MartCode.insert(conn, sql);
	            System.out.println("Item adicionado com sucesso!");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //Adicionar item ao Estoque (INSERT)
	    public static void AdicionarEstoque(Connection conn, String usuario, String senha) throws SQLException {
	    	
	    	Scanner input = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Código MercadoFornecedor: ");
	            int cod_mercado_fornecedor = input.nextInt();
	            
	            System.out.print("Código Produto: ");
	            int cod_produto = input.nextInt();
	           
	            System.out.print("Quantidade: ");
	            int quantidade = input.nextInt();
	            
	            System.out.println("Sistema de Medida:\n1- KG\n2- L\n3- Unidade");
	            String sistema_medida = null;
	            for(int i = 0; i < 1;) {
	            	
	            	int sM = input.nextInt();
	            	
	            	if(sM == 1) {
	            		sistema_medida = "KG";
	            		i++;
	            	} else if(sM == 2) {
	            		sistema_medida = "L";
	            		i++;
	            	} else if(sM == 3) {
	            		sistema_medida = "Unidade";
	            		i++;
	            	} else {
	            		System.out.println("Opção inválida, tente novamente");
	            	}
	            }
	            
	            System.out.print("Validade: ");
	            String validade = input.next();
	            
	            System.out.print("Categoria: ");
	            String categoria = input.next();
	 
	            String sql = String.format("INSERT into ESTOQUE (cod_mercado_fornecedor, cod_produto, "
	            							+ "quantidade, sistema_medida, validade, categoria)"
	                                        + " values (%d, %d, %d, '%s', '%s', '%s');", 
	                                        cod_mercado_fornecedor, cod_produto, quantidade, sistema_medida, validade, categoria);
	           
	            MartCode.insert(conn, sql);
	            System.out.println("Item adicionado com sucesso!");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    //Atualizar quantidade do Estoque (UPDATE)
	    public static void AtualizarQuantidade (Connection conn, String usuario, String senha) throws SQLException {
	    	
	    	Scanner input = new Scanner(System.in);
	    	
	    	try {
		        System.out.println("Digite o Código Estoque do item a ser atualizado: ");
		        int cod_estoque = input.nextInt();
		        
		        System.out.println("Digite a nova quantidade: ");
		        int quantidade = input.nextInt();
		        
		        
		        String sql = String.format("UPDATE ESTOQUE SET quantidade = %d "
		        						+ " WHERE cod_estoque = %d;",
		                                    quantidade, cod_estoque);
		        
		        MartCode.insert(conn, sql);
		        System.out.println("Quantidade atualizada com sucesso!");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }
	    
	    
	    //Atualizar endereço do Mercado/Fornecedor (UPDATE)
	    public static void AtualizarEndereco (Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	
	    	Scanner input = new Scanner(System.in);
	    	
	    	try {
	    	      
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Digite o código do mercado/fornecedor que deseja atualizar: ");
	            int cod_mercado_fornecedor = input.nextInt();
	           
	            System.out.print("Novo endereço do mercado/fornecedor: ");
	            String endereco = input.next();
	    	
	            String sql = String.format("UPDATE MERCADO_FORNECEDOR"
	            						+ " SET endereco = '%s' "
	            						+ " WHERE cod_mercado_fornecedor = %d;", 
	            						endereco, cod_mercado_fornecedor);
	            
	            MartCode.insert(conn, sql);
	            System.out.println("Endereço do Mercado/Fornecedor atualizado com sucesso!");
	    	} catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    	}
	    }
	    
	    
	    //Atualizar contato do Mercado/Fornecedor (UPDATE)
	    public static void AtualizarContato(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	Scanner input = new Scanner(System.in);

	    	try {
	    		conn = MartCode.getConnection("MARTCODE", usuario, senha);
	    		
	    		System.out.println("Digite o código de Mercado/Fornecedor que deseja atualizar o contato: ");
	    		int cod_mercado_fornecedor = input.nextInt();
	    		
	    		System.out.println("Digite o novo contato do Fornecedor: ");
	    		String contato= input.next();
	    		
	    		String sql = String.format("UPDATE MERCADO_FORNECEDOR"
	    								+ " SET contato = '%s' "
	    								+ " WHERE cod_mercado_fornecedor = %d;", 
	    								contato, cod_mercado_fornecedor);
	    		
	    		MartCode.insert(conn, sql);
	    	    System.out.println("Contato do Mercado/Fornecedor atualizado com sucesso!");
	    	} catch (ClassNotFoundException | SQLException e) {
	    	    e.printStackTrace();
	    	}
	    }
	    
	    
	    //Deletar item do Estoque (DELETE)
	    public static void DeletarEstoque(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	Scanner input = new Scanner(System.in);

	    	try {
                System.out.println("Digite o código de estoque do item a ser deletado: ");
                int cod_estoque = input.nextInt();
                
                System.out.println("Você tem certeza?\n1 - Sim\n2 - Não");
                
                for(int i = 0; i < 1;) {
                	int simNao = input.nextInt();
                	
                	if(simNao == 1) {
                		String sql = String.format("DELETE from Estoque "
        										+ " WHERE cod_estoque = %d;", 
        										cod_estoque);
                		
                		MartCode.insert(conn, sql);
                        System.out.println("Item deletado com sucesso!");
                        i++;
                        
                	} else if (simNao == 2) {
                		System.out.println("Operação cancelada com sucesso!");
                		i++;
                	} else {
                		System.out.println("Opção inválida, tente novamente.");
                	}
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	      
}