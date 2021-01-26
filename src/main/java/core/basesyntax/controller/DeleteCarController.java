package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        carService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/cars/all");
    }
}
