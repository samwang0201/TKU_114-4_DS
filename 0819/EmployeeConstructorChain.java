abstract class EmployeeBase4 {
    protected String id;
    protected String name;

    public EmployeeBase4(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase 建構函式");
    }

    public abstract double calculatePay();
}

class FullTimeEmployee4 extends EmployeeBase4 {
    private double salary;

    public FullTimeEmployee4(String id, String name, double salary) {
        super(id, name);

        if (salary < 0) {
            salary = 0;
        }

        this.salary = salary;
        System.out.println("FullTimeEmployee 建構函式");
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class PartTimeEmployee4 extends EmployeeBase4 {
    private double hourlyPay;
    private double hours;

    public PartTimeEmployee4(String id, String name,
                             double hourlyPay, double hours) {
        super(id, name);

        if (hourlyPay < 0) {
            hourlyPay = 0;
        }

        if (hours < 0) {
            hours = 0;
        }

        this.hourlyPay = hourlyPay;
        this.hours = hours;

        System.out.println("PartTimeEmployee 建構函式");
    }

    @Override
    public double calculatePay() {
        return hourlyPay * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {

        System.out.println("建立全職員工：");

        FullTimeEmployee4 e1 =
                new FullTimeEmployee4("E001", "小明", 40000);

        System.out.println("薪資：" + e1.calculatePay());

        System.out.println("--------------------");

        System.out.println("建立兼職員工：");

        PartTimeEmployee4 e2 =
                new PartTimeEmployee4("E002", "小華", 200, 80);

        System.out.println("薪資：" + e2.calculatePay());
    }
}