package by.itacademy.akulov.utils;

public class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    public static SessionManager getInstance() {
        return INSTANCE;
    }
}
