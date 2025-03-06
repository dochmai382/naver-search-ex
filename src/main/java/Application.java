import util.logger.MyLogger;

public class Application {
    public static void main(String[] args) {
        MyLogger logger = new MyLogger(Application.class);
        logger.info("main()");
    }
}
