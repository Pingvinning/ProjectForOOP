public class EmailNotifier implements Notifiable {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email: " + message);
    }
}