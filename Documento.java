import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Documento {
    private String nomeArquivo;
    private String nomeUsuario;
    private LocalDateTime horarioSolicitacao;
    private Documento proximo;

    public Documento(String nomeArquivo, String nomeUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = LocalDateTime.now(); // hora da criação
    }

    public String getNomeArquivo() { return nomeArquivo; }
    public String getNomeUsuario() { return nomeUsuario; }
    public LocalDateTime getHorarioSolicitacao() { return horarioSolicitacao; }

    public Documento getProximo() { return proximo; }
    public void setProximo(Documento proximo) { this.proximo = proximo; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("[Nome=%s, Usuario=%s, Solicitado=%s]",
                nomeArquivo, nomeUsuario, horarioSolicitacao.format(formatter));
    }
}
