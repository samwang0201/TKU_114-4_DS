abstract class Device {
    protected String name;

    public Device(String name) {
        this.name = name;
    }

    public abstract void runDiagnostic();
}

class Laptop extends Device {

    public Laptop(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + "：執行筆電診斷");
    }
}

class Printer extends Device {

    public Printer(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + "：執行印表機診斷");
    }

    public void cleanPrintHead() {
        System.out.println(name + "：清潔印表機噴頭");
    }
}

class Router extends Device {

    public Router(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + "：執行路由器診斷");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {

        Device[] devices = {
            new Laptop("Laptop-1"),
            new Printer("Printer-1"),
            new Router("Router-1"),
            new Printer("Printer-2")
        };

        for (int i = 0; i < devices.length; i++) {

            devices[i].runDiagnostic();

            if (devices[i] instanceof Printer printer) {
                printer.cleanPrintHead();
            }

            System.out.println("--------------------");
        }
    }
}