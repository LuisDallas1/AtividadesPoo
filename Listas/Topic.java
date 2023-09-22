import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pass{
    private int age;
    private String name;

    public Pass(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPriority(){
        if (this.age > 64) {
            return true;
        }
        return false;
    }
}

class Topic{
    private ArrayList<Pass> normalSeats;
    private ArrayList<Pass> prioritySeats;

    public Topic(int capacity, int qtdPriority){
        prioritySeats = new ArrayList<>(Collections.nCopies(qtdPriority, null));
        normalSeats = new ArrayList<>(Collections.nCopies(capacity - qtdPriority, null));
    }

    private static int findFree(ArrayList<Pass> list){//acha o primeiro espaço não nulo ou -1
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private static int findName(ArrayList<Pass> list, String name){//acha o indice do nome na lista ou -1
        for (int i = 0; i < list.size(); i++) {
            Pass pass = list.get(i);
            if (pass != null && pass.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isIn(String name){
        for (int i = 0; i < prioritySeats.size(); i++) {
            Pass pass = prioritySeats.get(i);
            if (pass != null && pass.getName().equals(name)) {
                return true;
            }
        }
        for (int i = 0; i < normalSeats.size(); i++) {
            Pass pass = normalSeats.get(i);
            if (pass != null && pass.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean insertOnList(ArrayList<Pass> list, Pass pass){
        if (findFree(list) != -1) {
            list.set(findFree(list), pass);
            return true;
        }
        return false;
    }

    private static boolean removeOfList(ArrayList<Pass> list, String name){
        if (findName(list, name) != -1) {
            list.set(findName(list, name), null);
            return true;
        }
        return false;
    }

    public boolean insert(Pass pass) {
        if (!isIn(pass.getName())) {
            if (pass.isPriority()) {
                if (insertOnList(prioritySeats, pass)) {
                    return true;
                } else if (insertOnList(normalSeats, pass)) {
                    return true;
                } else {
                    System.out.println("fail: topic lotada");
                    return false;
                }
            } else {
                if (insertOnList(normalSeats, pass)) {
                    return true;
                } else if (insertOnList(prioritySeats, pass)) {                   
                    return true;
                } else {
                    System.out.println("fail: topic lotada");
                    return false;
                }
            }
        }else{
            System.out.println("fail: " + pass.getName() + " ja esta na topic");
            return false;
        }
    }

    public boolean remove(String name){
        if (removeOfList(normalSeats, name)) {
            return true;
        }else if(removeOfList(prioritySeats, name)){
            return true;
        }
        System.out.println("fail: " + name + " nao esta na topic");
        return false;
    }

    public String toString() {
        return "[" + Stream.concat(
            this.prioritySeats.stream().map(p -> ("@" + (p != null ? p.getName() + ":" + p.getAge() : ""))), 
            this.normalSeats.stream().map(p -> ("=" + (p != null ? p.getName() + ":" + p.getAge() : ""))))
            .collect(Collectors.joining(" ")) + "]";
    }
}

class Topicc{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Topic topic = new Topic(0, 0);
        while(true){
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if(line.equals("end")) {
                break;
            } else if(ui[0].equals("init")) { //capacity qtdPriority
                topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            } else if(ui[0].equals("show")) {
                System.out.println(topic);
            } else if(ui[0].equals("in")) {
                topic.insert(new Pass(ui[1], Integer.parseInt(ui[2])));
            } else if(ui[0].equals("out")) {//value value
                topic.remove(ui[1]);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
