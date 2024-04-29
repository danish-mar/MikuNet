public class Packet {
    String source;
    String destination;

    String data;

    int packetSize = data.length() + source.length() + destination.length();

    int dataSize = data.length();

    public Packet(String source, String destination, String data){
        this.source = source;
        this.destination = destination;
        this.data = data;
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
