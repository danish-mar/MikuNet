package Device;

public class Packet {
    String source;
    String destination;

    String data;

    int packetSize;

    int dataSize;

    public Packet(String source, String destination, String data){
        this.source = source;
        this.destination = destination;
        this.data = data;
        packetSize = data.length() + source.length() + destination.length();
        dataSize = data.length();
    }

    public int getDataSize() {
        return dataSize;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public String getData() {
        return data;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }
}
