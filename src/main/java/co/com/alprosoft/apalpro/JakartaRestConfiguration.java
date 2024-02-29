package co.com.alprosoft.apalpro;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.com.alprosoft.apalpro.controller.resources.RolesResource.class);
        resources.add(co.com.alprosoft.apalpro.controller.resources.UsersResource.class);
        resources.add(co.com.alprosoft.apalpro.controller.resources.ExercisesResource.class);
    }
    
}
