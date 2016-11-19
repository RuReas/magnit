package net.usachev.magnit;

import net.usachev.magnit.repository.NRepository;
import net.usachev.magnit.repository.SqlNRepositoryImpl;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private NRepository repository;
    private Properties appProps;

    public static AppConfig get() {
        return INSTANCE;
    }

    public NRepository getRepository() {
        return repository;
    }

    private AppConfig() {
        try (InputStream webAppIS = getClass().getClassLoader().getResourceAsStream("db.properties")
        ) {
            appProps = new Properties();
            appProps.load(webAppIS);
            repository = new SqlNRepositoryImpl(
                    appProps.getProperty("db.url"),
                    appProps.getProperty("db.user"),
                    appProps.getProperty("db.password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
