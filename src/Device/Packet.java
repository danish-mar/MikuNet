package Device;


class PacketHeader{
    String source;
    int sourcePort;
    String destination;
    int destinationPort;

    String type;

    public String getDestination() {
        return destination;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public PacketHeader(){

    }

    public PacketHeader(String source, int sourcePort, String destination, int destinationPort){
        this.destination = destination;
        this.destinationPort = destinationPort;
        

        this.source = source;
        this.sourcePort = sourcePort;
    }

    public PacketHeader(String source, String destination){
        this.source = source;
        this.destination = destination;
    }
}

class Payload{
    String data;
    Payload(String data){
        this.data = data;
    }

    String getData(){
        return this.data;
    }
}


public class Packet {

    PacketHeader packetHeader;

    Payload payload;

    int packetSize;

    int dataSize;

    public Packet(String source, String destination, String data){
        this.packetHeader = new PacketHeader(source, destination);
 
        //        this.packetHeader.source = source;
        //        this.packetHeader.destination = destination;

        this.payload = new Payload(data);
        packetSize = data.length() + source.length() + destination.length();
        dataSize = data.length();
    }

    public int getDataSize() {
        return dataSize;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public String getDestination() {
        return this.packetHeader.destination;
    }

    public String getSource() {
        return this.packetHeader.source;
    }

    public PacketHeader getHeader(){
        return this.packetHeader;
    }
}
