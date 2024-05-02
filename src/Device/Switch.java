package Device;

import java.util.HashMap;

public class Switch extends Device {
    private int noOfPorts;
    private HashMap<String, Device> macTable;
    private Device[] connectedDevices;

    public Switch(int deviceId, String deviceName, int noOfPorts) {
        if (isValidPortCount(noOfPorts)) {
            this.noOfPorts = noOfPorts;
            this.name = deviceName;
            this.macTable = new HashMap<>();
            this.connectedDevices = new Device[noOfPorts];
        } else {
            System.out.println("Invalid number of ports.");
        }
    }

    @Override
    public void receive(Packet packet) {
        String destinationMAC = packet.getDestination();

        // Check if the destination MAC address is in the MAC table
        if (macTable.containsKey(destinationMAC)) {
            // Get the device associated with the destination MAC address
            Device destinationDevice = macTable.get(destinationMAC);

            // Forward the packet to the destination device
            destinationDevice.receive(packet);
        } else {
            System.err.println("Packet dropped: Destination MAC address not found.");
        }
    }


    public void connectDevice(Device device, int port) {
        if (port >= 0 && port < noOfPorts) {
            if (connectedDevices[port] == null) {
                connectedDevices[port] = device;
                macTable.put(device.getMacAddress(), device);
                device.connect(this);
            } else {
                System.out.println("Port " + port + " is already consumed.");
            }
        } else {
            System.out.println("Invalid port number.");
        }
    }

    public void disconnectDevice(int port) {
        if (port >= 0 && port < noOfPorts) {
            Device device = connectedDevices[port];
            if (device != null) {
                device.disconnect();
                macTable.remove(device.getMacAddress());
                connectedDevices[port] = null;
            }
        } else {
            System.out.println("Invalid port number.");
        }
    }

    public void showConnectedDevices() {
        System.out.println("Connected devices:");
        for (String macAddress : macTable.keySet()) {
            Device device = macTable.get(macAddress);
            System.out.println("MAC: " + macAddress + ", Device: " + device.name);
        }
    }

    private boolean isValidPortCount(int noOfPorts) {
        return noOfPorts == 4 || noOfPorts == 8 || noOfPorts == 16 || noOfPorts == 32;
    }
}
