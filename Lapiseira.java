//1. fiz tudo e passou em todos os testes.
//2. fiz sozinho
//3. ainda estou aprendendo isso de arraylist (fiz usando o código solver do professor e o meu de grafite como base)
//4. 1h

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
    private ArrayList<Lead> barrel;

    public Pencil(Float thickness){
        this.thickness = thickness;
        this.barrel = new ArrayList<>();
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
        if (grafite.getThickness() != thickness) {
            System.out.println("fail: calibre incompatível");
            return false;
        } else {
            this.barrel.add(grafite);
            return true;
        }
    }

    public Lead remove() {
        if (!hasGrafite()) {
            System.out.println("fail: nao existe grafite no bico");
            return null;
        }
        Lead removedTip = tip;
        this.tip = null;
        return removedTip;
    }
    public boolean pull(){
        if(hasGrafite()){
            System.out.println("fail: ja existe grafite no bico");
            return false;
        }
        if(this.barrel.size() == 0){
            System.out.println("fail: nao existe grafite no barril");
            return false;
        }
        this.tip = this.barrel.remove(0);
        return true;
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
            System.out.println("fail: nao existe grafite no bico");
        }
    }

    public String toString() {
        String output = "calibre: " + thickness + ", bico: ";
        if (this.tip != null)
            output += "[" + this.tip + "]";
        else
            output += "[]";
        output += ", tambor: {";
            for (Lead g : barrel)
                output += "[" + g + "]";
        return output + "}";
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public Lead getTip() {
        return tip;
    }

    public void setTip(Lead tip) {
        this.tip = tip;
    }

    public ArrayList<Lead> getBarrel() {
        return barrel;
    }

    public void setBarrel(ArrayList<Lead> barrel) {
        this.barrel = barrel;
    }
}

public class Lapiseira{
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
            }else if(comando[0].equals("help")) {
                System.out.println("init _calibre; insert _calibre _dureza _tamanho; remove; writePage _folhas");
            }else if(comando[0].equals("show")){
                System.out.println(pencil.toString());
            }else if(comando[0].equals("insert")){
                pencil.insert(new Lead(transinter(comando[1]), comando[2], (int) transinter(comando[3])));
            }else if(comando[0].equals("remove")){
                pencil.remove();
            }else if(comando[0].equals("write")){
                pencil.writePage();
            }else if(comando[0].equals("pull")){
                pencil.pull();
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