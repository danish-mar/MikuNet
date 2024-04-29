import Device.Computer;
import Device.Switch;

public class Main {
    public static void main(String[] args) {

        Computer com1 = new Computer("Denizuh-pc", Util.generateRandomMacAddress());
        Computer com2 = new Computer("umme-pc",Util.generateRandomMacAddress());
        Computer com3 = new Computer("keqing-pc", Util.generateRandomMacAddress());
        Computer com4 = new Computer("Ayaka-pc", Util.generateRandomMacAddress());
        Computer com5 = new Computer("Kokomi-pc", Util.generateRandomMacAddress());

        Switch switch0 = new Switch(0,"MainSwitch",4);

        switch0.connectDevice(com1,0);
        switch0.disconnectDevice(0);
        switch0.connectDevice(com2, 1);
        switch0.connectDevice(com3,2);
        switch0.connectDevice(com4, 3);

        com1.send(com3.getMacAddress(),"Hello");
        com2.send(com1.getMacAddress(),"umm, hi?");
        System.out.println(com3.getMemory());

    }
}