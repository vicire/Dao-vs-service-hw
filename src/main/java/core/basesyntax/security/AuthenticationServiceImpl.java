package core.basesyntax.security;

import core.basesyntax.exception.AuthenticationException;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isPresent() && driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Incorrect login or password. Please, try again");
    }
}
