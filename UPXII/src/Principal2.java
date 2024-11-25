import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal2 {

    public static void main(String[] args) throws SQLException {

        JOptionPane.showMessageDialog(null, "Conectando Java e MySQL", "MartCode", JOptionPane.INFORMATION_MESSAGE);

        String usuario = JOptionPane.showInputDialog(null, "Usuário:", "Login", JOptionPane.QUESTION_MESSAGE);
        String senha = JOptionPane.showInputDialog(null, "Senha:", "Login", JOptionPane.QUESTION_MESSAGE);

        Connection conn = null;

        try {
            conn = MartCode.getConnection("MARTCODE", usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão estabelecida com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return; // Encerra se a conexão falhar
        }

        JOptionPane.showMessageDialog(null, "-- Bem-vindo(a) ao MartCode! --", "MartCode", JOptionPane.PLAIN_MESSAGE);

        boolean continuar = true;

        while (continuar) {
            String[] opcoesMenu = {"Sair", "Visualizar", "Pesquisar", "Adicionar", "Atualizar", "Deletar"};
            int opcao1 = JOptionPane.showOptionDialog(
                null,
                "O que deseja fazer?",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesMenu,
                opcoesMenu[0]
            );

            switch (opcao1) {
                case 0: // Sair
                    JOptionPane.showMessageDialog(null, "Saindo do MartCode...", "Encerrando", JOptionPane.INFORMATION_MESSAGE);
                    continuar = false;
                    break;

                case 1: // Visualizar
                    visualizar(conn);
                    break;

                case 2: // Pesquisar
                    pesquisar(conn, usuario, senha);
                    break;

                case 3: // Adicionar
                    adicionar(conn, usuario, senha);
                    break;

                case 4: // Atualizar
                    atualizar(conn, usuario, senha);
                    break;

                case 5: // Deletar
                    deletar(conn, usuario, senha);
                    break;

                default: // Caso o usuário feche a janela
                    continuar = false;
                    break;
            }
        }
    }

    private static void visualizar(Connection conn) throws SQLException {
        String[] opcoesVisualizar = {"Voltar", "Produto", "MercadoFornecedor", "Estoque"};
        int opcao = JOptionPane.showOptionDialog(
            null,
            "Qual tabela de dados você deseja visualizar?",
            "Visualizar Dados",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesVisualizar,
            opcoesVisualizar[0]
        );

        switch (opcao) {
            case 1:
                for (String linha : MartCode.ListProduto(conn, "SELECT * from PRODUTO")) {
                    System.out.println(linha); // Pode ser exibido em JTextArea se preferir
                }
                break;

            case 2:
                for (String linha : MartCode.ListMercadoFornecedor(conn, "SELECT * from MERCADO_FORNECEDOR")) {
                    System.out.println(linha);
                }
                break;

            case 3:
                for (String linha : MartCode.ListEstoque(conn, "SELECT * from ESTOQUE")) {
                    System.out.println(linha);
                }
                break;

            default:
                // Voltar ou nenhuma ação
                break;
        }
    }

    private static void pesquisar(Connection conn, String usuario, String senha) throws SQLException {
        String[] opcoesPesquisar = {"Voltar", "Entre quantidades (Estoque)", "Entre validades (Estoque)"};
        int opcao = JOptionPane.showOptionDialog(
            null,
            "O que você deseja pesquisar?",
            "Pesquisar Dados",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesPesquisar,
            opcoesPesquisar[0]
        );

        switch (opcao) {
            case 1:
                MartCode.EstoqueQuantidade(conn, usuario, senha);
                break;

            case 2:
                MartCode.EstoqueValidade(conn, usuario, senha);
                break;

            default:
                break;
        }
    }

    private static void adicionar(Connection conn, String usuario, String senha) throws SQLException {
        String[] opcoesAdicionar = {"Voltar", "Produto", "MercadoFornecedor", "Estoque"};
        int opcao = JOptionPane.showOptionDialog(
            null,
            "Em qual tabela de dados você deseja adicionar?",
            "Adicionar Dados",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesAdicionar,
            opcoesAdicionar[0]
        );

        switch (opcao) {
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
                break;
        }
    }

    private static void atualizar(Connection conn, String usuario, String senha) throws SQLException {
        String[] opcoesAtualizar = {"Voltar", "Endereço (Mercado/Fornecedor)", "Contato (Mercado/Fornecedor)", "Quantidade (Estoque)"};
        int opcao = JOptionPane.showOptionDialog(
            null,
            "O que você deseja atualizar/modificar?",
            "Atualizar Dados",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesAtualizar,
            opcoesAtualizar[0]
        );

        switch (opcao) {
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
                break;
        }
    }

    private static void deletar(Connection conn, String usuario, String senha) throws SQLException {
        String[] opcoesDeletar = {"Voltar", "Item do Estoque"};
        int opcao = JOptionPane.showOptionDialog(
            null,
            "O que você deseja deletar?",
            "Deletar Dados",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesDeletar,
            opcoesDeletar[0]
        );

        if (opcao == 1) {
            MartCode.DeletarEstoque(conn, usuario, senha);
        }
    }
}
