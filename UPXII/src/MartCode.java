import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.List;
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
	}