package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.service.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMyCurrentCarsController extends HttpServlet {
    private static final String DRIVER_ID = "driverId";
    private static final Injector INJECTOR = Injector.getInstance("core.basesyntax");
    private final CarService carService = (CarService) INJECTOR.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        List<Car> cars = carService.getAllByDriver(driverId);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
