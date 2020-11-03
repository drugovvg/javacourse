package part1.lesson11.task01;

import java.net.InetAddress;
import java.util.Objects;

class Client {
    private String name;
    private InetAddress address;
    private int port;

    public Client(InetAddress address, int port) {
        this.name = null;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Client(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return port == client.port &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }
}
