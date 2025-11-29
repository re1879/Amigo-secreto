import java.util.List;

public class TestaSistemaAmigo {
    public static void main(String[] args) {
        SistemaAmigo sistema = new SistemaAmigo();

        // Cadastro
        sistema.cadastraAmigo("José", "jose@email.com");
        sistema.cadastraAmigo("Maria", "maria@email.com");

        // Configuração Amigo Secreto
        try {
            sistema.configuraAmigoSecretoDe("jose@email.com", "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com", "jose@email.com");
        } catch (AmigoInexException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Mensagens
        sistema.enviarMensagem(new MensagemParaAlguem("Oi José", "maria@email.com", "jose@email.com", true));
        sistema.enviarMensagem(new MensagemParaTodos("Oi Pessoal", "maria@email.com", true));

        // Pesquisa Anonimas
        System.out.println("--- Mensagens Anônimas ---");
        List<Mensagem> anonimas = sistema.pesquisaMensagensAnonimas();
        for (Mensagem m : anonimas) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        // Pesquisa Amigo Secreto
        try {
            String amigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@email.com");
            if (amigoDeJose.equals("maria@email.com")) {
                System.out.println("--- Sorteio ---");
                System.out.println("Ok, José tirou Maria.");
            }
        } catch (AmigoInexException | AmigoNaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}