package us.kanddys.laia.modules.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Esta clase se encarga de configurar el CORS (Cross-Origin Resource Sharing)
 * para permitir el acceso
 * a los recursos de la API desde otros dominios.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {

      // * Permitir solicitudes CORS para todas las rutas
      registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200", "https://laia-development.netlify.app",
                  "https://laia-dev.up.railway.app", "https://www.flores.club/", "https://kanddys-1088e.web.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");

      // * Permitir solicitudes CORS para la ruta de GraphQL
      registry.addMapping("/graphql")
            .allowedOrigins("http://localhost:4200", "https://laia-dev.up.railway.app",
                  "https://laia-development.netlify.app", "https://www.flores.club", "https://kanddys-1088e.web.app")
            .allowedMethods("POST", "GET") // ! Por lo general, las solicitudes GraphQL son POST
            .allowedHeaders("*");

      // * Permitir solicitudes CORS para la URL de GraphiQL
      registry.addMapping("/graphiql")
            .allowedOrigins("http://localhost:4200", "https://laia-dev.up.railway.app",
                  "https://laia-development.netlify.app", "https://www.flores.club", "https://kanddys-1088e.web.app")
            .allowedMethods("GET", "POST") // ! Por lo general, GraphiQL usa solicitudes GET
            .allowedHeaders("*");
   }
}
