import java.util.*;

class Pessoa{
    private String nome;
    
    public Pessoa(String nome){
        this.nome = nome;
    }

    public String toString(){
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class Mercantil{
    private ArrayList<Pessoa> caixas;
    private LinkedList<Pessoa> esperando;

    public Mercantil(int qtdCaixas){
        caixas = new ArrayList<>(Collections.nCopies(qtdCaixas, null));
        esperando = new LinkedList<>();
    }

    private boolean validarIndice(int indice){
        if (indice >= 0 && indice < caixas.size()) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Caixas: [");
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i) == null) {
                sb.append("-----");
            } else {
                sb.append(caixas.get(i).getNome());
            }
            if (i < caixas.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\nEspera: [");
        for (int i = 0; i < esperando.size(); i++) {
            sb.append(esperando.get(i).getNome());
            if (i < esperando.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public void chegar(Pessoa pessoa){
        this.esperando.addLast(pessoa);
    }

    public boolean chamar(int indice){
        if (validarIndice(indice)) {
            if(caixas.get(indice) == null){
                if(!esperando.isEmpty()){
                Pessoa pessoaAtendida = esperando.pollFirst();
                caixas.set(indice, pessoaAtendida);
                return true;
                }else{
                    System.out.println("fail: sem clientes");
                    return false;
                }
            }else{
                System.out.println("fail: caixa ocupado");
                return false;
            }
        }
        return false;
    }

    public Pessoa finalizar(int indice) {
        if (validarIndice(indice)) {
            Pessoa pessoaAtendida = caixas.get(indice);
            if (pessoaAtendida != null) {
                caixas.remove(indice);
                caixas.add(indice, null);
                return pessoaAtendida;
            }else{
                System.out.println("fail: caixa vazio");
            }
        }else{
            System.out.println("fail: caixa inexistente");
        }
    return null;
}
}

public class Budega{
    public static void main(String[] commands){

        Scanner scanner = new Scanner(System.in);
        Mercantil merc = new Mercantil(1);

        while(true){
            var line = scanner.nextLine();
            System.out.println("$" + line);
            var com = line.split(" ");

            if(com[0].equals("end")){
                break;
            }else if(com[0].equals("init")){
                merc = new Mercantil(Integer.parseInt(com[1]));
            }else if(com[0].equals("arrive")){
                merc.chegar(new Pessoa(com[1]));
            }else if(com[0].equals("call")){
                merc.chamar(Integer.parseInt(com[1]));
            }else if(com[0].equals("finish")){
                merc.finalizar(Integer.parseInt(com[1]));
            }else if(com[0].equals("show")){
                System.out.println(merc);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
