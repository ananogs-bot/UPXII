import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.List;
	import java.util.ArrayList;
	
public class Alimento {
	
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
	   
	    public static List<String> List(Connection conn, String sql)
	            throws SQLException
	    {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	        while(dados.next()) {
	            linhas.add("\nCod_alimento:" + dados.getInt(1));
	            linhas.add("Nome:" + dados.getString(2));
	            linhas.add("Data Fabricação:" + dados.getString(3));
	            linhas.add("Data Validade:" + dados.getString(4));
	            linhas.add("Quantidade:" + dados.getInt(5));
	            linhas.add("\n--------------------------------------\n");
	        }
	       
	        return linhas;
	    }
	    /*  public static List<String> Estoque(Connection conn, String sql)
	            throws SQLException
	    {
	        List<String> linhas = new ArrayList<String>();
	       
	        Statement cmd = conn.createStatement();
	        ResultSet dados = cmd.executeQuery(sql);
	       
	      while(dados.next()) {
	            linhas.add("\nNome_alimento:" + dados.getString(2) + "Quantidade:" + dados.getInt(5));
	            linhas.add("\n--------------------------------------\n");
	        }
	       
	        return linhas;
	       
	    } */

	}

