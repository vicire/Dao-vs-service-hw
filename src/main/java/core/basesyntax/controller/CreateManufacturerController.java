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
    private static final Injector INJECTOR = Injector.getInstance("core.basesyntax");
    private final ManufacturerService manufacturerService = (ManufacturerService) INJECTOR
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturer/creation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String country = req.getParameter("country");
        if (!brand.isEmpty() && !country.isEmpty()) {
            Manufacturer manufacturer = new Manufacturer(brand, country);
            manufacturerService.create(manufacturer);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Please, fill all fields");
            req.getRequestDispatcher("/WEB-INF/views/manufacturer/creation.jsp").forward(req, resp);
        }
    }
}
