import org.junit.Test;

/**
 * Create by longhchen on  2021-03-08 23:52
 */
public interface Sender {
    public void send();
}

class MailSender implements Sender{

    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}

class MsSender implements Sender{

    @Override
    public void send() {
        System.out.println("this is mssender!");
    }
}

class SenderFactory{
    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceMs(){
        return new MsSender();
    }
}


