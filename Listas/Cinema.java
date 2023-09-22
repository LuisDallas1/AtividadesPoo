import java.util.*;

class Client{
    private String fone;
    private String id;

    public Client(String id, String fone){
        this.id = id;
        this.fone = fone;
    }

    public String getFone() {
        return fone;
    }

    public String getId() {
        return id;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return id + ":" + fone;
    }
}

class Sala{
    private ArrayList<Client> cadeiras;

    private int procurar(String id){//indice se achar e -1 se não
        for (int i = 0; i < cadeiras.size(); i++) {
            Client cliente = cadeiras.get(i);
            if (cliente != null && cliente.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean verificarIndice(int indice){//indice valido true, senão false
        if (indice >= 0 && indice < cadeiras.size()) {
            return true;
        }
        return false;
    }

    public Sala(int capacidade){
        cadeiras = new ArrayList<>(Collections.nCopies(capacidade, null));
    }

    public boolean reservar(String id, String fone, int indice){
        if (verificarIndice(indice)) {
            if (cadeiras.get(indice) == null) {
                if (procurar(id) == -1) {
                    cadeiras.set(indice, new Client(id, fone));
                    return true;
                }else{
                    System.out.println("fail: cliente ja esta no cinema");
                }
            }else{
                System.out.println("fail: cadeira ja esta ocupada");
            }
        }else{
            System.out.println("fail: cadeira nao existe");
        }
        return false;
    }

    public void cancelar(String id){
        int auxiliar = procurar(id);
        if (auxiliar != -1) {
            cadeiras.set(auxiliar, null);
        }else{
            System.out.println("fail: cliente nao esta no cinema");
        }
    }

    public ArrayList<Client> getCadeiras() {
        return cadeiras;
    }

    public String toString() {
        StringBuilder saida = new StringBuilder("[");
        boolean first = true;

        for (Client cliente : cadeiras) {
            if (!first) {
                saida.append(" ");
            }
            if (cliente == null) {
                saida.append("-");
            } else {
                saida.append(cliente);
            }
            first = false;
        }

        saida.append("]");

        return saida.toString();
    }

}

public class Cinema {
    public static void main(String[] commands){
        Scanner scanner = new Scanner(System.in);
        Sala sala = new Sala(0);
        while(true){
            var line = scanner.nextLine();
            System.out.println("$" + line);
            var com = line.split(" ");

            if(com[0].equals("end")){
                break;
            }else if(com[0].equals("reservar")){
                sala.reservar(com[1], com[2], Integer.parseInt(com[3]));
            }else if(com[0].equals("init")){
                sala = new Sala(Integer.parseInt(com[1]));
            }else if(com[0].equals("cancelar")){
                sala.cancelar(com[1]);
            }else if(com[0].equals("show")){
                System.out.println(sala);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
