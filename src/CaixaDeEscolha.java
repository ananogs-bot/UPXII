import javax.swing.JOptionPane;

public class CaixaDeEscolha {
    public static void main(String[] args) {
        // Opções para o usuário escolher
        String[] opcoes = {"Adicionar", "Atualizar", "Excluir", "Sair"};
        
        // Exibir caixa com opções
        int escolha = JOptionPane.showOptionDialog(null, 
                                                   "O que você deseja fazer?", 
                                                   "Escolha uma Opção", 
                                                   JOptionPane.DEFAULT_OPTION, 
                                                   JOptionPane.QUESTION_MESSAGE, 
                                                   null, 
                                                   opcoes, 
                                                   opcoes[0]);

        // Interpretar a escolha
        if (escolha != -1) { // -1 significa que o usuário fechou a janela
            JOptionPane.showMessageDialog(null, "Você escolheu: " + opcoes[escolha]);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma opção foi selecionada!");
        }
    }
}

