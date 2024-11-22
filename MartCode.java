import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class MartCode {
	 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/";
	   
	    public static Connection getConnection(String nomebanco, String usuario, String senha)
	    throws ClassNotFoundException, SQLException
	    {
	       
	        Class.forName(DRIVER);
	        return DriverManager.getConnection(URL+nomebanco, usuario, senha);
	    }
	 
	    public static boolean insert (Connection conn, String sql)
	            throws SQLException
	    {
	        Statement cmd = conn.createStatement();
	        cmd.executeUpdate(sql);
	       
	        return true;
	   
	    }
	   
	    public static List<String> ListProduto(Connection conn, String sql)
	            throws SQLException
	    {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\nCod_produto:" + dados.getInt(1));
	            linhas.add("Nome:"          + dados.getString(2));
	            linhas.add("Marca:"         + dados.getString(3));
	            linhas.add("--------------------------------------\n");
	        }
	       
	        return linhas;
	    }

	    
	    public static List<String> ListMercadoFornecedor(Connection conn, String sql)
	            throws SQLException
	    {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\nCod_mercado_fornecedor:" + dados.getInt(1));
	            linhas.add("Nome:"                     + dados.getString(2));
	            linhas.add("Contato:"                  + dados.getString(3));
	            linhas.add("Endereço:"                 + dados.getString(4));
	            linhas.add("--------------------------------------\n");
	        }
	       
	        return linhas;
	    }
	    
	    
	    public static List<String> ListEstoque(Connection conn, String sql)
	            throws SQLException
	    {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\nCod_estoque:"           + dados.getInt(1));
	            linhas.add("Cod_mercado_forncedor: "  + dados.getString(2));
	            linhas.add("Cod_produto: "            + dados.getString(3));
	            linhas.add("Quantidade: "             + dados.getString(4));
	            linhas.add("Validade: "               + dados.getString(5));
	            linhas.add("Categoria: "              + dados.getString(6));
	            linhas.add("--------------------------------------\n");
	        }
	       
	        return linhas;
	    }
	    
	    
	    public static void AdicionarProduto(Connection conn, String usuario, String senha)
	    		throws SQLException
	    {
	    	
	    	Scanner in = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.println("Nome: ");
	            String Nome = in.next();
	           
	            System.out.println("Marca: ");
	            String Marca = in.next();
	 
	            String sql = String.format("insert into Produto (Nome, Marca)"
	                                        + " values ('%s', '%s');",
	                                        Nome, Marca);
	           
	            MartCode.insert(conn, sql);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    public static void AdicionarMercadoFornecedor(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	
	    	Scanner in = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Nome: ");
	            String Nome = in.nextLine();
	           
	            System.out.print("Contato: ");
	            String Contato = in.nextLine();
	            
	            System.out.print("Endereço: ");
	            String Endereco = in.nextLine();
	 
	            String sql1 = String.format("insert into Mercado_Fornecedor (Nome, Contato, Endereco)"
	                                        + " values ('%s', '%s', '%s');",
	                                        Nome, Contato, Endereco);
	           
	            MartCode.insert(conn, sql1);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    public static void AdicionarEstoque(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	
	    	Scanner in = new Scanner(System.in);
	    	
	    	try {
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("CodMercadoFornecedor: ");
	            int Cod_mercado_fornecedor = in.nextInt();
	            
	            System.out.print("CodProduto: ");
	            int Cod_produto = in.nextInt();
	           
	            System.out.print("Quantidade: ");
	            int Quantidade = in.nextInt();
	            
	            System.out.print("Validade: ");
	            String Validade = in.next();
	            
	            System.out.print("Categoria: ");
	            String Categoria = in.next();
	 
	            String sql = String.format("insert into Estoque (Cod_mercado_fornecedor, Cod_produto, "
	            							+ "Quantidade, Validade, Categoria)"
	                                        + " values (%d, %d, %d, '%s', '%s');",
	                                        Cod_mercado_fornecedor, Cod_produto, Quantidade, Validade, Categoria);
	           
	            MartCode.insert(conn, sql);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    public static void AtualizarQuantidade (Connection conn, String usuario, String senha)
	    		throws SQLException
	    {
	    	
	    	Scanner in = new Scanner(System.in);
	    	
	    	try {
		        System.out.println("Digite o CodEstoque do item a ser atualizado: ");
		        int CodEstoque = in.nextInt();
		        
		        System.out.println("Digite a nova quantidade: ");
		        int novaQuantidade = in.nextInt();
		        
		        
		        String sql = String.format("update Estoque set Quantidade = %d where Cod_estoque = %d;",
		                                    novaQuantidade, CodEstoque);
		        
		        
		        MartCode.insert(conn, sql);
		        System.out.println("Quantidade atualizada com sucesso!");
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }
	    
	    public static void AtualizarEndereco (Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	
	    	Scanner in = new Scanner(System.in);
	    	
	    	try {
	    	      
	            conn = MartCode.getConnection("MARTCODE", usuario, senha);
	           
	            System.out.print("Digite o código do mercado/fornecedor que deseja atualizar: ");
	            int Cod_mercado_fornecedor = in.nextInt();
	           
	            System.out.print("Novo endereço do fornecedor: ");
	            String novoEndereçoFornecedor = in.next();
	    	
	            String sql = String.format("UPDATE MERCADO_FORNECEDOR SET endereco = '%s' WHERE cod_mercado_fornecedor = %d;",
	                    novoEndereçoFornecedor, Cod_mercado_fornecedor);
	            
	            MartCode.insert(conn, sql);
	            System.out.println("Endereço do Fornecedor atualizado com sucesso!");
	    	} catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    	}
	    }
	    
	    public static void AtualizarContato(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	Scanner in = new Scanner(System.in);

	    	try {
	    		conn = MartCode.getConnection("MARTCODE", usuario, senha);
	    		
	    		System.out.println("digite o código do Fornecedor que deseja atualizar o contato: ");
	    		int Cod_mercado_fornecedor = in.nextInt();
	    		
	    		System.out.println("digite o novo contato do Fornecedor: ");
	    		String novoContato= in.next();
	    		
	    		String sql = String.format("UPDATE Mercado_Fornecedor SET Contato = '%s' WHERE cod_mercado_fornecedor = %d;",
	    				novoContato, Cod_mercado_fornecedor);
	    		
	    		MartCode.insert(conn, sql);
	    	    System.out.println("Contato do Fornecedor atualizado com sucesso!");
	    	} catch (ClassNotFoundException | SQLException e) {
	    	    e.printStackTrace();
	    	}
	    }
	    
	    public static void DeletarEstoque(Connection conn, String usuario, String senha) 
	    		throws SQLException
	    {
	    	Scanner in = new Scanner(System.in);

	    	try {
                System.out.println("Digite o CodEstoque do item a ser deletado: ");
                int CodEstoque = in.nextInt();

                
                String sql = String.format("delete from Estoque where Cod_estoque = %d;",
                		CodEstoque);
                MartCode.insert(conn, sql);
                System.out.println("Item deletado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	      
}