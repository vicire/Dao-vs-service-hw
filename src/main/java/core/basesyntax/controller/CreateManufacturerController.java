package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String country = req.getParameter("country");
        Manufacturer manufacturer = new Manufacturer(brand, country);
        manufacturerService.create(manufacturer);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
