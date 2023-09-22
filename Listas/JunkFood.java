import java.util.*;
import java.text.DecimalFormat;

class Slot{
    private String name;
    private float price;
    private int quantity;

    public Slot(String name,  int quantity, float price){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return this.name + " : " + this.quantity + " U : " + df.format(this.price) + " RS";
    }
}

class VendingMachine{
    private ArrayList<Slot> slots;
    private float profit;
    private float cash;

    public VendingMachine(int capacity){
        this.slots = new ArrayList<>();
        for(int i = 0; i < capacity; i++){
            this.slots.add(new Slot("  empty", 0, 0f));
        }
    }

    public void setSlot(int index, Slot slot){
        if (index >= 0 && index < slots.size()) {
            slots.set(index, slot);
        }else{
            System.out.println("fail: indice nao existe");
        }
    }

    public void clearSlot(int index){
        if (index >= 0 && index < slots.size()) {
            Slot emptySlot = new Slot("  empty", 0, 0f);
            slots.set(index, emptySlot);
        }
    }

    public void insertCash(float cash){
        this.cash += cash;
    }

    public double getChange() {
        double change = this.cash;
        this.cash = 0;
        return change;
    }

    public float withdrawCash(){
        float auxiliar = this.cash;
        this.cash = 0;
        return auxiliar;
    }

    public void buyItem(int index){
        if (index >= 0 && index < slots.size()) {
            if (slots.get(index).getName() != "  empty") {
                if (this.cash >= slots.get(index).getPrice()) {
                    if (slots.get(index).getQuantity() > 0) {
                        slots.get(index).setQuantity(slots.get(index).getQuantity() - 1);
                        this.cash -= slots.get(index).getPrice();
                        this.profit += slots.get(index).getPrice();
                        System.out.println("voce comprou um " + slots.get(index).getName());
                    }else{
                        System.out.println("fail: espiral sem produtos");
                    }
                }else{
                    System.out.println("fail: saldo insuficiente");
                }
            }
        }else{
            System.out.println("fail: indice nao existe");
        }
    }

    public float getCash() {
        return cash;
    }

    public float getProfit() {
        return profit;
    }

    public void show() {
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("saldo: " + df.format(this.cash));

        for (int i = 0; i < this.slots.size(); i++) {
        if (this.slots.get(i) == null) {
            System.out.println(i + "   " + "[   empty : 0 U : 0.00 RS]");
        } else {
            System.out.println(i + " [ " + this.slots.get(i) + "]");
        }
        }
    }
}

public class JunkFood{
    public static void main(String[] command){

        VendingMachine vendingMachine = new VendingMachine(0);

        Scanner scanner = new Scanner(System.in);

        while(true){
            var line = scanner.nextLine();

            System.out.println("$" + line);

            var commands = line.split(" ");

            switch (commands[0]) {
                case "end":
                scanner.close();
                return;
            
                case "init":
                vendingMachine = new VendingMachine(Integer.parseInt(commands[1]));
                break;

                case "set":
                Slot slot = new Slot(commands[2], Integer.parseInt(commands[3]), Float.parseFloat(commands[4]));
                vendingMachine.setSlot(Integer.parseInt(commands[1]), slot);
                break;

                case "dinheiro":
                vendingMachine.insertCash(Integer.parseInt(commands[1]));
                break;

                case "limpar":
                vendingMachine.clearSlot(Integer.parseInt(commands[1]));
                break;

                case "show":
                vendingMachine.show();
                break;

                case "troco":
                System.out.println(String.format("voce recebeu %.2f RS", vendingMachine.getChange()));
                break;

                case "comprar":
                vendingMachine.buyItem(Integer.parseInt(commands[1]));
                break;
            }
        }
    }
}
