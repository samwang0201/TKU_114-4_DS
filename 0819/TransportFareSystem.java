abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public abstract double calculateFare(int distance);

    public void showFare(int distance) {
        System.out.println("路線：" + routeName);
        System.out.println("距離：" + distance + " 公里");
        System.out.println("票價：" + calculateFare(distance) + " 元");
        System.out.println("--------------------");
    }
}

class Bus extends Transport {

    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        if (distance <= 10) {
            return 15;
        } else {
            return 15 + (distance - 10) * 2;
        }
    }
}

// 計程車
class Taxi extends Transport {

    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        if (distance <= 1) {
            return 85;
        } else {
            return 85 + (distance - 1) * 25;
        }
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {

        Transport[] transports = new Transport[4];

        transports[0] = new Bus("307公車");
        transports[1] = new Taxi("台北車站計程車");
        transports[2] = new Bus("262公車");
        transports[3] = new Taxi("淡水計程車");

        int distance = 12;

        for (int i = 0; i < transports.length; i++) {
            transports[i].showFare(distance);
        }
    }
}
