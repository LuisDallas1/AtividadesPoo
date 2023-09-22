//fiz sozinho e só tive dificuldade na hora de fazer a main, por alguns erros bobos (as declarações de variaveis ficaram fora do loop while)


import java.util.*;

class Pet{
    private boolean vivo = true;
    private int limpeza;
    private int limpezaMax;
    private int energia;
    private int energiaMax;
    private int fome;
    private int fomeMax;
    private int idade;
    private int diamante;

    private boolean testarVivo(){
        if(this.vivo){
            return true; 
        }else{
            System.out.println("fail: pet esta morto");
            return false;
        }
    }
    Pet(){

    }

    Pet(int energiaMax, int fomeMax, int limpezaMax){
        this.energiaMax = energiaMax;
        this.fomeMax = fomeMax;
        this.limpezaMax = limpezaMax;
        this.energia = energiaMax;
        this.fome = fomeMax;
        this.limpeza = limpezaMax;
    }

    void comer(){//-1 energia; +4 saciedade; -2 limpeza; +0 diamantes; +1 idade; 
        if(!testarVivo())
            return;
        setEnergia(this.energia - 1);
        setFome(this.fome + 4);
        setLimpeza(this.limpeza - 2);
        this.idade++; 
    }

    void brincar(){//-2 energia; -1 saciedade; -3 limpeza; +1 diamantes; +1 idade;
        if(!testarVivo())
            return;
        setEnergia(this.energia - 2);
        setFome(this.fome - 1);
        setLimpeza(this.limpeza - 3);
        this.idade++;
        this.diamante++;
    }

    void banho(){//-3 energia; -1 saciedade; Max limpeza; +0 diamantes; +1 idade;
        if(!testarVivo())
            return;
        setEnergia(this.energia - 3);
        setFome(this.fome - 1);
        this.limpeza = this.limpezaMax;
        this.idade += 2;
    }

    void durmir() {
        if (!testarVivo()) {
            return;
        }
        if (this.energia <= this.energiaMax - 5) {
            setFome(this.fome - 1);
            this.idade += (this.energiaMax - this.energia);
            this.energia = this.energiaMax;
        } else {
            System.out.println("fail: nao esta com sono");
        }
    }

    void setLimpeza(int value){
        if(value <= 0){
            this.limpeza = 0;
            System.out.println("fail: pet morreu de sujeira");
            this.vivo = false;
            return;
        } 
        if(value > this.limpezaMax) {
            this.limpeza = this.limpezaMax;
            return;
        }
        this.limpeza = value;        
    }

    void setEnergia(int value){
        if(value <= 0){
            this.energia = 0;
            System.out.println("fail: pet morreu de fraqueza");
            this.vivo = false;
            return;
        } 
        if(value > this.energiaMax) {
            this.energia = this.energiaMax;
            return;
        }
        this.energia = value;
    }

    void setFome(int value) {
        if (value <= 0) {
            this.fome = 0;
            System.out.println("fail: pet morreu de fome");
            this.vivo = false;
            return;
        } 
        if (value > this.fomeMax) {
            this.fome = this.fomeMax;
            return;
        }
        this.fome = value;
    }

    int getLimpezaMax(){
        return limpezaMax;
    }

    int getEnergia(){
        return energia;
    }

    int getEnergiaMax(){
        return energiaMax;
    }

    int getFome(){
        return fome;
    }

    int getFomeMax(){
        return fomeMax;
    }

    public String toString(){
        return "E:" + this.energia + "/" + this.energiaMax + ", S:" + this.fome + "/" + this.fomeMax + ", L:"
                    + this.limpeza + "/" + this.limpezaMax + ", D:" + this.diamante + ", I:" + this.idade;
    }
}

public class Tamagotchi{
    public static void main(String[] args){
        Pet bixo = new Pet(0, 0, 0);

        while(true){
            var line = scanner.nextLine();

            System.out.println("$" + line);

            var comando = line.split(" ");

            if(comando[0].equals("end")){
                break;
            }else if(comando[0].equals("init")){
                bixo = new Pet(transinter(comando[1]), transinter(comando[2]), transinter(comando[3]));
            }else if(comando[0].equals("show")){
                System.out.println(bixo);
            }else if(comando[0].equals("eat")){
                bixo.comer();
            }else if(comando[0].equals("play")){
                bixo.brincar();
            }else if(comando[0].equals("sleep")){
                bixo.durmir();
            }else if(comando[0].equals("shower")){
                bixo.banho();
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }

    private static Scanner scanner = new Scanner(System.in);

    private static int transinter(String variavel){
        return (Integer.parseInt(variavel));
    }
}