
/* public class anotação {
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
      		case 0: System.out.println("Saindo do MartCode...");
      				i = 2;
      				
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
      			MartCode.AdicionarProduto(conn, usuario, senha);
      			break;
      			
      		case 5: 
      			MartCode.AdicionarMercadoFornecedor(conn, usuario, senha);
      			break;
      		
      		case 6: 
      			MartCode.AdicionarEstoque(conn, usuario, senha);
      			break;
      		
      			
      		default: System.out.println("Opção não encontrada, tente novamente.");
      	}
      }
} */
