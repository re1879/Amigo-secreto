import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo() {
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) {
        this.amigos.add(new Amigo(nomeAmigo, emailAmigo));
    }

    public Amigo pesquisaAmigo(String emailAmigo) {
        for (Amigo a : amigos) {
            if (a.getEmail().equals(emailAmigo)) {
                return a;
            }
        }
        return null;
    }

    public void enviarMensagem(Mensagem m) {
        this.mensagens.add(m);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> msgsAnonimas = new ArrayList<>();
        for (Mensagem m : mensagens) {
            if (m.ehAnonima()) {
                msgsAnonimas.add(m);
            }
        }
        return msgsAnonimas;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexException {
        Amigo a = pesquisaAmigo(emailDaPessoa);
        if (a == null) {
            throw new AmigoInexException("O amigo com e-mail " + emailDaPessoa + " não existe.");
        }
        a.setEmailAmigoSorteado(emailAmigoSorteado);
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexException, AmigoNaoException {
        Amigo a = pesquisaAmigo(emailDaPessoa);
        if (a == null) {
            throw new AmigoInexException("O amigo com e-mail " + emailDaPessoa + " não existe.");
        }
        
        String sorteado = a.getEmailAmigoSorteado();
        if (sorteado == null) {
            throw new AmigoNaoException("Amigo secreto não configurado para " + emailDaPessoa);
        }
        return sorteado;
    }
}