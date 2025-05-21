import java.time.Duration;
import java.time.LocalDateTime;

public class PilhaReimpressao {
    
    private Documento topo;
    private int capacidade;
    private int tamanho;

    public PilhaReimpressao(int capacidade){
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    public PilhaReimpressao(){
        this(10);
    }

    public void push(Documento doc) {
        if (pilhaCheia()) {
            System.out.println("⚠️ Pilha está cheia. Não é possível inserir.");
            return;
        }

        doc.setProximo(topo);
        topo = doc;
        System.out.println("📄 Documento adicionado para reimpressão: " + doc.getNomeArquivo());
    }

    public boolean pilhaVazia(){
        return topo == null;
    }

    public boolean pilhaCheia(){
        return tamanho == capacidade;
    }

    public String reimprimirDocumento(){
        if (pilhaVazia())
            return "❌ Pilha vazia, não há o que reimprimir.";

        Documento doc = topo;
        topo = topo.getProximo();
        tamanho--;

        LocalDateTime horarioReimpressao = LocalDateTime.now();
        Duration duracao = Duration.between(doc.getHorarioSolicitacao(), horarioReimpressao);

        long minutos = duracao.toMinutes();
        long segundos = duracao.toSeconds() % 60;

        return "✅ Documento "+doc.getNomeArquivo()+" reimpresso.\nTempo desde a solicitação: "+minutos+" min "+segundos+" s.";
    }

    public String consultarDocumento(String nomeArquivo){
        if (pilhaVazia())
            return "📂 A pilha está vazia.";

        Documento runner = topo;
        int posicao = 1;

        while (runner != null) {
            if (runner.getNomeArquivo().equals(nomeArquivo)) {
                return "📑 Documento "+nomeArquivo+" está na posição "+posicao+" da pilha.\nHorário da solicitação: "+runner.getHorarioSolicitacao().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
                        
            }
            runner = runner.getProximo();
            posicao++;
        }

        return "❓ Documento não encontrado na pilha.";
    }

    @Override
    public String toString(){
        if (pilhaVazia()) return "📭 Pilha de reimpressão está vazia.";

        String s = "📭 Pilha de Reimpressão:\n";
        Documento runner = topo;
        while (runner != null) {
            s += runner.toString() + "->\n";
            runner = runner.getProximo();
        }
        return s.toString();
    }
}

