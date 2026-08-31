import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivityReport {

    public static class LoginRecord {
        private String account;
        private String ip;

        public LoginRecord(String account, String ip) {
            this.account = account;
            this.ip = ip;
        }

        public String getAccount() {
            return account;
        }

        public String getIp() {
            return ip;
        }

        @Override
        public String toString() {
            return account + "|" + ip;
        }
    }

    private Map<String, Integer> loginCount;
    private Map<String, Set<String>> accountIps;
    private Set<String> allIps;

    public LoginActivityReport() {
        loginCount = new HashMap<String, Integer>();
        accountIps = new HashMap<String, Set<String>>();
        allIps = new HashSet<String>();
    }

    public boolean addLogin(String account, String ip) {

        if (account == null || ip == null) {
            return false;
        }

        account = account.trim();
        ip = ip.trim();

        if (account.isEmpty() || ip.isEmpty()) {
            return false;
        }

        loginCount.put(
                account,
                loginCount.getOrDefault(account, 0) + 1
        );

        if (!accountIps.containsKey(account)) {
            accountIps.put(
                    account,
                    new HashSet<String>()
            );
        }

        accountIps.get(account).add(ip);

        allIps.add(ip);

        return true;
    }

    public int loginCount(String account) {

        if (account == null) {
            return 0;
        }

        account = account.trim();

        return loginCount.getOrDefault(
                account,
                0
        );
    }

    public int uniqueIpCount() {
        return allIps.size();
    }

    public int uniqueIpCount(String account) {

        if (account == null) {
            return 0;
        }

        account = account.trim();

        Set<String> ips =
                accountIps.get(account);

        if (ips == null) {
            return 0;
        }

        return ips.size();
    }

    public List<String> suspiciousAccounts() {

        List<String> result =
                new ArrayList<String>();

        for (String account : loginCount.keySet()) {

            if (loginCount.get(account) > 1) {
                result.add(account);
            }
        }

        Collections.sort(result);

        return result;
    }

    public void printReport() {

        List<String> accounts =
                new ArrayList<String>(
                        loginCount.keySet()
                );

        Collections.sort(accounts);

        System.out.println("===== 登入統計 =====");

        for (String account : accounts) {

            System.out.println(
                    account
                    + " 登入次數="
                    + loginCount(account)
                    + ", 不同IP="
                    + uniqueIpCount(account)
            );
        }

        System.out.println("--------------------");

        System.out.println(
                "全部不同 IP 數量="
                + uniqueIpCount()
        );

        System.out.println("--------------------");

        System.out.println("===== 異常重複登入 =====");

        List<String> suspicious =
                suspiciousAccounts();

        if (suspicious.isEmpty()) {
            System.out.println("無");
        } else {

            for (String account : suspicious) {

                System.out.println(
                        account
                        + " 登入次數="
                        + loginCount(account)
                        + ", 不同IP="
                        + uniqueIpCount(account)
                );
            }
        }
    }

    public static void main(String[] args) {

        LoginActivityReport report =
                new LoginActivityReport();

        report.addLogin(
                "amy",
                "192.168.1.10"
        );

        report.addLogin(
                "bob",
                "192.168.1.20"
        );

        report.addLogin(
                "amy",
                "192.168.1.10"
        );

        report.addLogin(
                "amy",
                "10.0.0.5"
        );

        report.addLogin(
                "cindy",
                "192.168.1.30"
        );

        report.addLogin(
                "bob",
                "192.168.1.21"
        );

        report.printReport();
    }
}