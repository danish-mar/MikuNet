package Device;

import java.util.HashMap;
import java.util.Map;

public class Router extends Device {
    private Map<String, String> routingTable; // Destination network -> Next hop IP address
    private Map<String, String> dhcpTable;    // MAC address -> IP address

    public Router(int deviceId, String deviceName) {
        this.name = deviceName;
        this.routingTable = new HashMap<>();
        this.dhcpTable = new HashMap<>();
    }

    public void addRoute(String destinationNetwork, String nextHop) {
        routingTable.put(destinationNetwork, nextHop);
    }

    @Override
    public void receive(Packet packet) {
        String destinationIP = packet.getHeader().getDestination();

        // Check if destination IP is in routing table
        if (routingTable.containsKey(destinationIP)) {
            String nextHop = routingTable.get(destinationIP);
            forwardPacket(packet, nextHop);
        } else {
            System.err.println("Destination unreachable: No route to " + destinationIP);
        }

        // Check if packet is a DHCP request
        if (packet.getHeader().getType().equals("DHCP")) {
            handleDHCPRequest(packet);
        }
    }

    private void forwardPacket(Packet packet, String nextHop) {
        // Forward packet to next hop (next router or directly connected device)
        // Implementation depends on network architecture
    }

    private void handleDHCPRequest(Packet packet) {
        // Get MAC address of the requesting device
        String macAddress = packet.getHeader().getSource();

        // Check if MAC address already has an assigned IP address
        if (dhcpTable.containsKey(macAddress)) {
            System.out.println("DHCP: " + macAddress + " already has IP address " + dhcpTable.get(macAddress));
        } else {
            // Assign a new IP address to the device
            String ipAddress = "192.168.1.100"; // Example IP address
            dhcpTable.put(macAddress, ipAddress);
            System.out.println("DHCP: Assigned IP address " + ipAddress + " to " + macAddress);
        }
    }
}
