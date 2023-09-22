import java.util.*;

class Lead {
    private float thickness;
    private String hardness;
    private int size;

    public Lead(float thickness, String hardness, int size){
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public float getThickness() {
        return thickness;
    }

    public String getHardness() {
        return hardness;
    }

    public int getSize() {
        return size;
    }

    public int usagePerSheet(){
        if(hardness.equals("HB")) {
            return 1;
        }else if (hardness.equals("2B")) {
            return 2;
        }else if (hardness.equals("4B")) {
            return 4;
        }else{
            return 6;
        }   
    }

    public String toString() {
        return String.format("%.1f:%s:%d", thickness, hardness, size);
    }
}

class Pencil{
    private float thickness;
    private Lead tip;

    public Pencil(Float thickness){
        this.thickness = thickness;
        this.tip = null;
    }

    public float getThickness() {
        return thickness;
    }

    public boolean hasGrafite(){
        if(this.tip == null){
            return false;
        }
        return true;
    }

    public boolean insert(Lead grafite) {
        if (hasGrafite()) {
            System.out.println("fail: ja existe grafite");
            return false;
        } else if (grafite.getThickness() != thickness) {
            System.out.println("fail: calibre incompativel");
            return false;
        } else {
            tip = grafite;
            return true;
        }
    }

    public Lead remove() {
        if (!hasGrafite()) {
            System.out.println("fail: nao existe grafite");
            return null;
        }
        Lead removedTip = tip;
        tip = null;
        return removedTip;
    }

    public void writePage() {
        if (hasGrafite()) {
            int usage = tip.usagePerSheet();
            if(tip.getSize() > 10){
                if (tip.getSize() - usage >= 10) {
                    tip.setSize(tip.getSize() - usage);
                }else{
                    System.out.println("fail: folha incompleta"); 
                    tip.setSize(10);
                }
            }else{
                System.out.println("fail: tamanho insuficiente");
            }
        }else{
            System.out.println("fail: nao existe grafite");
        }
    }

    public String toString() {
        String saida = "calibre: " + thickness + ", grafite: ";
        if (tip != null)
            saida += "[" + tip + "]";
        else
            saida += "null";
        return saida;
    }
}

public class Grafite{
    public static void main(String[] comandos){
        Pencil pencil = new Pencil(0.5f);

        while(true){
            var line = scanner.nextLine();

            System.out.println("$" + line);

            var comando = line.split(" ");

            if(comando[0].equals("end")){
                break;
            }else if(comando[0].equals("init")){
                pencil = new Pencil(transinter(comando[1]));
            }else if(comando[0].equals("show")){
                System.out.println(pencil.toString());
            }else if(comando[0].equals("insert")){
                pencil.insert(new Lead(transinter(comando[1]), comando[2], (int) transinter(comando[3])));
            }else if(comando[0].equals("remove")){
                pencil.remove();
            }else if(comando[0].equals("write")){
                pencil.writePage();
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }

    private static Scanner scanner = new Scanner(System.in);

    private static float transinter(String variavel){
        return (Float.parseFloat(variavel));
    }
}
