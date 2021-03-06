package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String licenseNumber = req.getParameter("licenseNumber");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("passwordRepeat");
        if (password.equals(repeatPassword)) {
            Driver driver = new Driver(name, licenseNumber, login, password);
            driverService.create(driver);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message",
                    "Your password and repeat password are incorrect. Please, try again");
            req.getRequestDispatcher("/WEB-INF/views/drivers/create.jsp").forward(req, resp);
        }
    }
}
