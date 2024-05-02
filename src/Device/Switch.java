package Device;

public class Switch extends Device {
    private int noOfPorts;
    private int consumedPorts;
    private Packet packetMemory;
    private Device[] connectedDevices;

    public Switch(int deviceId, String deviceName, int noOfPorts) {
        if(noOfPorts == 4 || noOfPorts == 8 || noOfPorts == 16 || noOfPorts == 32){
            this.noOfPorts = noOfPorts;
            this.connectedDevices = new Device[noOfPorts];
            this.name = deviceName;
        }else{
            System.out.println("Invalid no of port");
        }
    }

    @Override
    public void receive(Packet packet) {
        this.packetMemory = packet;
        forward();
    }

    private void forward() {
        boolean packetForwarded = false;

        for (int i = 0; i < consumedPorts; i++) {
            // Check if the connected device's MAC address matches the destination MAC address of the packet
            if (connectedDevices[i].getMacAddress().equals(packetMemory.getDestination())) {
                // Forward the packet to the connected device
                connectedDevices[i].receive(packetMemory);
                packetForwarded = true;
                System.out.println("[" + this.name + "]" + " Packet Forwareded to " + packetMemory.getHeader().getSource() + " -> " + connectedDevices[i].name);
                // Exit the loop after forwarding the packet to prevent forwarding to multiple devices
                break;
            }
        }

        if (!packetForwarded) {
            System.err.println("Packet is Dropped: Destination MAC address not found.");
            packetMemory = null;
        }
    }


    // Add method to connect a device to a port on the switch
    public void connectDevice(Device device, int port) {
        if (port >= 0 && port < noOfPorts) {
            if (connectedDevices[port] == null) {
                connectedDevices[port] = device;
                device.connect(this);
                consumedPorts++;
            } else {
                System.out.println("Port " + port + " is already consumed.");
            }
        } else {
            System.out.println("Invalid port number.");
        }
    }

    // Add method to disconnect a device from a port on the switch
    public void disconnectDevice(int port) {
        if (port >= 0 && port < noOfPorts) {
            connectedDevices[port].disconnect();
            connectedDevices[port] = null;
            consumedPorts--;
        } else {
            System.out.println("Invalid port number.");
        }
    }
}
