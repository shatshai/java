package me.shatshai.se.quickstart;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.helidon.webserver.Handler;
import io.helidon.common.http.Http;
import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

/**
 * A simple service to list Employees Examples:
 *
 * Get employee list:
 * curl -X GET http://localhost:8080/employees
 *
 * The message is returned as a JSON object
 */
public class HRService implements Service {
    /**
     * The config value for the key {@code employee}.
     */
    private final AtomicReference<String> hr = new AtomicReference<>();
    private static final Logger LOGGER = Logger.getLogger(HRService.class.getName());

    HRService(Config config) {}

    /**
     * A service registers itself by updating the routing rules.
     * @param rules the routing rules.
     */
    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/", this::getEmployeesHandler);
    }

    /**
     * Return a worldly greeting message.
     * @param request the server request
     * @param response the server response
     */
    private void getEmployeesHandler(ServerRequest request, ServerResponse response) {
        Department dept = new Department("Department Education");

        dept.addEmployee(new Employee(101, "Ann", 1234.56));
        dept.addEmployee(new Employee(102, "Bob", 1200.34));
        dept.addEmployee(new Employee(103, "Ray", 1122.33));

        sendResponse(response, dept.getEmployees());
    }

    private void sendResponse(ServerResponse response, Employee[] employees) {
        LOGGER.log(Level.INFO, "Employee list sent");
        response.send(employees);
    }
}
