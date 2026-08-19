abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

class MonthlyEmployee extends Employee {
    private double salary;

    public MonthlyEmployee(String name, double salary) {
        super(name);

        if (salary < 0) {
            salary = 0;
        }

        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyPay;
    private double hours;

    public HourlyEmployee(String name, double hourlyPay, double hours) {
        super(name);

        if (hourlyPay < 0) {
            hourlyPay = 0;
        }

        if (hours < 0) {
            hours = 0;
        }

        this.hourlyPay = hourlyPay;
        this.hours = hours;
    }

    @Override
    public double calculatePay() {
        return hourlyPay * hours;
    }
}

class SalesEmployee extends Employee {
    private double baseSalary;
    private double sales;
    private double commissionRate;

    public SalesEmployee(String name, double baseSalary,
                         double sales, double commissionRate) {
        super(name);

        if (baseSalary < 0) {
            baseSalary = 0;
        }

        if (sales < 0) {
            sales = 0;
        }

        if (commissionRate < 0) {
            commissionRate = 0;
        }

        this.baseSalary = baseSalary;
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + sales * commissionRate;
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {

        Employee[] employees = {
            new MonthlyEmployee("小明", 40000),
            new HourlyEmployee("小華", 200, 160),
            new SalesEmployee("小美", 25000, 200000, 0.1),
            new MonthlyEmployee("小王", 45000)
        };

        Employee highestEmployee = employees[0];
        double highestPay = employees[0].calculatePay();

        for (int i = 0; i < employees.length; i++) {

            double pay = employees[i].calculatePay();

            System.out.println(
                employees[i].getName() + " 薪資：" + pay
            );

            if (pay > highestPay) {
                highestPay = pay;
                highestEmployee = employees[i];
            }
        }

        System.out.println("--------------------");
        System.out.println(
            "最高薪資員工：" + highestEmployee.getName()
        );
        System.out.println("最高薪資：" + highestPay);
    }
}