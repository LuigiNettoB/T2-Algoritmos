import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FilaImpressao fila = new FilaImpressao(4);
        PilhaReimpressao pilha = new PilhaReimpressao(4);
        
        while (true) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Adicionar documento à fila");
            System.out.println("2. Imprimir documento");
            System.out.println("3. Consultar documento na fila");
            System.out.println("4. Mostrar fila de impressão");
            System.out.println("5. Reimprimir documento");
            System.out.println("6. Consultar documento na pilha");
            System.out.println("7. Mostrar pilha de reimpressão");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 0:
                    sc.close();
                    System.out.println("Encerrando sistema");
                    return;
                case 1:
                    System.out.print("Nome do arquivo: ");
                    String nomeArq = sc.nextLine();
                    System.out.print("Nome do usuário: ");
                    String nomeUser = sc.nextLine();
                    System.out.println("\n");
                    fila.inserirDocumento(nomeArq,nomeUser);
                        
                    break;
                case 2:
                    Documento doc = fila.retornarDocumento();
                    if (doc == null) {
                        System.out.println("❌ Fila está vazia. Nada a imprimir.");
                    } else {
                        System.out.println("✅ Documento '" + doc.getNomeArquivo() + "' impresso com sucesso.");
                        pilha.push(doc); 
                    }
                    break;
                
                case 3:
                    System.out.print("Nome do arquivo para consultar: ");
                    String nomeConsultaFila = sc.nextLine();
                    System.out.println(fila.consultarDocumento(nomeConsultaFila));
                    break;

                case 4: 
                    System.out.println(fila.toString());
                    break;
                
                case 5:
                    System.out.println(pilha.reimprimirDocumento());
                    break;

                case 6:
                    System.out.print("Nome do arquivo para consultar: ");
                    String nomeConsultaPilha = sc.nextLine();
                    System.out.println(pilha.consultarDocumento(nomeConsultaPilha));
                    break;

                case 7:
                    System.out.println(pilha.toString());
                    break;
                default:
                    break;
            }

        }
        
        
    }
}
