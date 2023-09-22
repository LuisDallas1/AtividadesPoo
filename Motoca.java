//1. fiz tudo e passou em todos os testes.
//2. fiz sozinho
//3. tive muias dificuldades nesse código, mas foi principalmente por motivos bestas, tenho certa dificuldade em fazer os códigos sozinho.
//4. acho que levei umas 3h pra fazer


import java.util.*;

class Person {
    private int age;
    private String name;

    public Person (String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return String.format("%s:%d", name, age);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}

class Motorcycle{
    private Person person;
    private int power;
    private int time;

    public Motorcycle(int power){
        this.power = power;
        this.time = 0;
        this.person = null;
    }
    
    public boolean hasPerson(){
        if(this.person == null){
            return false;
        }
        return true;
    }

    public boolean insert(Person person){
        if(hasPerson()){
            System.out.println("fail: busy motorcycle");
            return false;
        }else{
            this.person = person;
            return true;
        }
    }

    public Person remove(){
        if(!hasPerson()){
            System.out.println("fail: empty motorcycle");
            return null;
        }
        Person removedPerson = this.person;
        this.person = null;
        System.out.println(removedPerson.toString());
        return removedPerson;
    }

    public void buyTime(int time){
        this.time += time;
    }

    public void drive(int time){
        if(this.time > 0){
            if(hasPerson()){
                if(person.getAge() <= 10){
                    if(this.time < time){
                        System.out.println("fail: time finished after " + this.time + " minutes");
                        this.time = 0;
                    }else{
                        this.time -= time;
                    }
                }else{
                    System.out.println("fail: too old to drive");
                }
            }else{
                System.out.println("fail: empty motorcycle");
            }
        }else{
            System.out.println("fail: buy time first");
        }
    }

    public void honk(){
        String honk = "P";
        for(int i = 0; i < this.power; i++){
            honk += "e";
        }
        honk += "m";
        System.out.println(honk);
    }

    public int getPower() {
        return power;
    }
    public int getTime() {
        return time;
    }
    public Person getPerson() {
        return person;
    }
    
    public String toString() {
        if(hasPerson()){
            return String.format("power:%d, time:%d, person:(%s:%d)", power, time, person.getName(), person.getAge());
        }else{
            return String.format("power:%d, time:%d, person:(empty)", power, time);
        }
    }

}

public class Motoca{
    public static void main(String[] comandos){
        Motorcycle motorcycle = new Motorcycle(1);

        while(true){
            var line = scanner.nextLine();

            System.out.println("$" + line);

            var comando = line.split(" ");

            if(comando[0].equals("end")){
                break;
            }else if(comando[0].equals("init")){
                motorcycle = new Motorcycle(transinter(comando[1]));
            }else if(comando[0].equals("show")){
                System.out.println(motorcycle.toString());
            }else if(comando[0].equals("enter")){
                motorcycle.insert(new Person(comando[1], transinter(comando[2])));
            }else if(comando[0].equals("leave")){
                motorcycle.remove();
            }else if(comando[0].equals("drive")){
                motorcycle.drive(transinter(comando[1]));
            }else if(comando[0].equals("honk")){
                motorcycle.honk();
            }else if(comando[0].equals("buy")){
                motorcycle.buyTime(transinter(comando[1]));
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