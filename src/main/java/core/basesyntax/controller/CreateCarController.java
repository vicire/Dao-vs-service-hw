package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.service.CarService;
import core.basesyntax.service.ManufacturerService;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("core.basesyntax");
    private final CarService carService = (CarService) INJECTOR.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) INJECTOR
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/car/creation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String model = req.getParameter("model");
            Long manufacturerId = Long.valueOf(req.getParameter("manufacturer_id"));
            if (!model.isEmpty()) {
                Car car = new Car(model, manufacturerService.get(manufacturerId));
                carService.create(car);
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                req.setAttribute("message", "Please, fill model field");
                req.getRequestDispatcher("/WEB-INF/views/car/creation.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Please, enter numeric id");
            req.getRequestDispatcher("/WEB-INF/views/car/creation.jsp").forward(req, resp);
        } catch (NoSuchElementException e) {
            req.setAttribute("message", "There is no such manufacturer id, "
                    + "please enter valid id");
            req.getRequestDispatcher("/WEB-INF/views/car/creation.jsp").forward(req, resp);
        }
    }
}
