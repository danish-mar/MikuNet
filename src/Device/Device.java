package Device;

public abstract class Device {
    String name;
    String ipV4Address;
    String macAddress;
    String memory;
    boolean isConnected;

    Device connectedTo;

    int connect(Device connectTo){
        if(isConnected == true){
            System.err.println("Cannot Connect Already Connected");
            return -1;
        }else{
            this.connectedTo = connectTo;
            this.isConnected = true;
            return 1;
        }
    };

    int disconnect(){
        if(isConnected == false){
            System.err.println("Not Connected to anything");
            return -1;
        }else{
            this.connectedTo = null;
            this.isConnected = false;
            return  1;
        }
    };

    public Device getConnectedTo() {
        return connectedTo;
    }

    public String getIpV4Address() {
        return ipV4Address;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void send(String destination, String data){
        if(isConnected){
            Packet packetToBeSent = new Packet(this.getMacAddress(), destination,data);
            connectedTo.receive(packetToBeSent);
        }else{
            System.err.println("[" + this.name + "]" + " failed to send packet is not connected to anything");
        }
    }

    public void receive(Packet packet){
        if(packet.getDestination() == this.macAddress){
            memory = packet.payload.getClass() + "<-" + packet.getHeader().getSource();
        }else{
            System.err.println("this Packet dosnt belong to " + getMacAddress() + " it belongs to " + packet.getDestination());
        }
    }

    public String getMemory() {
        return memory;
    }
}
