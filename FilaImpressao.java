import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FilaImpressao {

    private Documento primeiro;
    private Documento ultimo;
    private int capacidade;
    private int tamanho;

    public FilaImpressao(int capacidade){
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    public FilaImpressao(){
        this(10);
    }

    public boolean filaVazia(){
        return primeiro == null;
    }

    public boolean filaCheia() {
        return tamanho == capacidade;
    }

    public void inserirDocumento(String nomeArquivo, String nomeUsuario){
        if (filaCheia()) {
            System.out.println("⚠️ Fila de Impressão está cheia. Não é possível inserir.\n");
            return;
        }

        Documento novo = new Documento(nomeArquivo, nomeUsuario);

        if (filaVazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            ultimo.setProximo(novo);
            ultimo = novo;
        }
        tamanho++;
        System.out.println("📄 Documento adicionado à fila: " + nomeArquivo + "\n");
    }

    public Documento retornarDocumento() {
        if (filaVazia()) return null;
    
        Documento doc = primeiro;
        primeiro = primeiro.getProximo();
        if (primeiro == null)
            ultimo = null;
    
        tamanho--;
        return doc;
    }
    

    public String imprimirDocumento(){
        if (filaVazia()) return "❌ Fila de Impressão está vazia. Nada a imprimir.\n";

        Documento doc = primeiro;
        primeiro = primeiro.getProximo();
        if (primeiro == null)
            ultimo = null;

        tamanho--;

        LocalDateTime horarioImpressao = LocalDateTime.now();
        Duration duracao = Duration.between(doc.getHorarioSolicitacao(), horarioImpressao);
        long minutos = duracao.toMinutes();
        long segundos = duracao.toSeconds() % 60;

        return "✅ Documento" + doc.getNomeArquivo()+ " impresso.\nTempo de espera:" + minutos + " min " + segundos + "s.\n";
    }

    public String consultarDocumento(String nomeArquivo){
        if (filaVazia())
            return "📭 A fila está vazia.";

        Documento runner = primeiro;
        int posicao = 1;

        while (runner != null) {
            if (runner.getNomeArquivo().equals(nomeArquivo)) {
                return "📑 Documento " + nomeArquivo+ " está na posição "+ posicao+" da fila.\nHorário de solicitação: "+runner.getHorarioSolicitacao().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"\n";
            }
            runner = runner.getProximo();
            posicao++;
        }

        return "❓ Documento não encontrado na fila.\n";
    }

    @Override
    public String toString(){
        if (filaVazia()) return "📭 Fila de Impressão está vazia.";

        String s = "📋 Fila de Impressão:\n";
        Documento runner = primeiro;
        while (runner != null) {
            s += runner.toString() + "->\n";
            runner = runner.getProximo();
        }
        return s.toString();
    }
}
