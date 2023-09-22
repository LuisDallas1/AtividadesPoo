//1. fiz tudo e passou em todos os testes.
//2. fiz sozinho
//3. estou começando a consolidar isso de lista
//4. ~2h30

import java.util.*;
import java.util.stream.Collectors;

class Kid{
    private int age;
    private String name;

    public Kid(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return name + ":" + age;
    }

    public int getAge(){
        return age;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
}

class Trampoline{
    private LinkedList<Kid> playing;
    private LinkedList<Kid> waiting;

    public Trampoline(){
        this.playing = new LinkedList<>();
        this.waiting = new LinkedList<>();
    }

    private Kid RemoveFromList(String name, LinkedList<Kid> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(name)){
                list.remove(i);
                break;
            }
        }
        return null;
    }

    public String toString(){
        return   "[" + waiting.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]" + " => "
                + "[" + playing.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]";
    }

    public void enter(){
        if(!(waiting.isEmpty())){
            Kid auxiliar = waiting.removeLast();
            playing.addFirst(auxiliar);
        }
    }

    public void leave(){
        if(!(playing.isEmpty())){
            Kid auxiliar = playing.removeLast();
            waiting.addFirst(auxiliar);
        }
    }

    public Kid arrive(Kid kid){
        this.waiting.addFirst(kid);
        return kid;
    }

    public Kid removeKid(String name){
        Kid removedKid = RemoveFromList(name, playing);
        if (removedKid == null) {
            RemoveFromList(name, waiting);
        }
        return removedKid;
    }

}

public class PulaPula {
    public static void main(String[] commands){
        Scanner scanner = new Scanner(System.in);
        Trampoline tramp = new Trampoline();
        while(true){
            var line = scanner.nextLine();
            System.out.println("$" + line);
            var com = line.split(" ");

            if(com[0].equals("end")){
                break;
            }else if(com[0].equals("arrive")){
                tramp.arrive(new Kid((com[1]), Integer.parseInt(com[2])));
            }else if(com[0].equals("enter")){
                tramp.enter();
            }else if(com[0].equals("leave")){
                tramp.leave();
            }else if(com[0].equals("remove")){
                tramp.removeKid(com[1]);
            }else if(com[0].equals("show")){
                System.out.println(tramp);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
