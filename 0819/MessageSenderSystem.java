interface MessageSender {
    void send(String receiver, String message);
}
class EmailSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        System.out.println("Email");
        System.out.println("收件者：" + receiver);
        System.out.println("訊息：" + message);
    }
}

class SmsSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        System.out.println("SMS");
        System.out.println("收件者：" + receiver);
        System.out.println("訊息：" + message);
    }
}

class ConsoleSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        System.out.println("Console");
        System.out.println("收件者：" + receiver);
        System.out.println("訊息：" + message);
    }
}

public class MessageSenderSystem {

    public static void notify(MessageSender sender, String receiver, String message) {

        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("錯誤：接收者不可為空白");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("錯誤：訊息不可為空白");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {

        MessageSender[] senders = {
            new EmailSender(),
            new SmsSender(),
            new ConsoleSender()
        };

        for (int i = 0; i < senders.length; i++) {
            notify(senders[i], "王小明", "明天記得上課");
            System.out.println("--------------------");
        }

        notify(new EmailSender(), "", "測試訊息");

        notify(new SmsSender(), "王小明", "");
    }
}