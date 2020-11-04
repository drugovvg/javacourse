package part1.lesson11.task01;

import java.net.InetAddress;
import java.util.Objects;

/**
 * This class implements server's client entity, so we can keep information about it's hostname, port and nickname.
 * It also contains hashcode and equals methods which is NOT based on nickname, only port and hostname matters.
 */
class Client {
    private String name;
    private InetAddress address;
    private int port;

    /**
     * Constructor
     * @param address hostname of the client
     * @param port port of the client
     */
    public Client(InetAddress address, int port) {
        this.name = null;
        this.address = address;
        this.port = port;
    }

    /**
     * Constructor
     * @param name nickname of the client
     * @param address hostname of the client
     * @param port port of the client
     */
    public Client(String name, InetAddress address, int port) {
        this.name = name;
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
