import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SistemaAmigoMapTest {

    private SistemaAmigoMap sistema;

    @BeforeEach
    public void setUp() {
        sistema = new SistemaAmigoMap();
    }

    @Test
    public void testCadastroEPesquisaAmigo() {
        sistema.cadastraAmigo("João", "joao@email.com");
        Amigo a = sistema.pesquisaAmigo("joao@email.com");
        assertNotNull(a);
        assertEquals("João", a.getNome());
    }

    @Test
    public void testConfiguraAmigoSecreto() {
        sistema.cadastraAmigo("Ana", "ana@email.com");
        sistema.cadastraAmigo("Bia", "bia@email.com");
        try {
            sistema.configuraAmigoSecretoDe("ana@email.com", "bia@email.com");
            assertEquals("bia@email.com", sistema.pesquisaAmigoSecretoDe("ana@email.com"));
        } catch (Exception e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testAmigoInexistenteException() {
        assertThrows(AmigoInexistenteException.class, () -> {
            sistema.pesquisaAmigoSecretoDe("inexistente@email.com");
        });
    }

    @Test
    public void testAmigoNaoSorteadoException() {
        sistema.cadastraAmigo("Carlos", "carlos@email.com");
        assertThrows(AmigoNaoSorteadoException.class, () -> {
            sistema.pesquisaAmigoSecretoDe("carlos@email.com");
        });
    }
    
    @Test
    public void testPesquisaMensagensAnonimas() {
        sistema.enviarMensagem(new MensagemParaTodos("Oi", "ana@email.com", true));
        sistema.enviarMensagem(new MensagemParaTodos("Olá", "bia@email.com", false));
        
        List<Mensagem> anonimas = sistema.pesquisaMensagensAnonimas();
        assertEquals(1, anonimas.size());
        assertTrue(anonimas.get(0).ehAnonima());
    }
}