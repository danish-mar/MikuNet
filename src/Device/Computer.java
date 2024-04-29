package Device;

public class Computer extends Device{
    public Computer(String name, String macAddress){
        this.name = name;
        this.macAddress = macAddress;
        System.out.println("[" + this.name + "]" + " cooking Completed : " + "(" + getMacAddress() + ")");
    }
}
