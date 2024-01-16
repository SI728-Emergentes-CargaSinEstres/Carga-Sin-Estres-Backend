package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Shared.util.Utilities;
import com.upc.cargasinestres.CargaSinEstres.Users.model.enums.ERole;
import com.upc.cargasinestres.CargaSinEstres.Users.repository.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//jdbc:mysql://viaduct.proxy.rlwy.net:44692/railway?useSSL=false&serverTimezone=UTC //a la db desplegada

//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlQGdtYWlsLmNvbSIsImlhdCI6MTcwMDE1MTYzMSwiZXhwIjoxNzAyNzQzNjMxLCJyb2xlcyI6WyJST0xFX1VTRVIiXX0.NmGugKKb4Rmx8grtAQMHH7ZqFfHXp3n1SVNISoZdORI

/**
 * The CargaSinEstresApplication class is the main entry point for the Carga Sin Estres application.
 * It includes configuration for the application, initializes necessary beans, and inserts roles into the database.
 */
@Slf4j
@SpringBootApplication
public class CargaSinEstresApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/**")
						.allowedOrigins("*") //aquí va el link de tu frontend desplegado
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permitir los métodos necesarios
						.allowedHeaders("*"); // Permitir todos los encabezados
			}
		};
	}

	/**
	 * Configures and initializes the ModelMapper bean.
	 *
	 * @return A ModelMapper bean for mapping objects.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * The main method that starts the Carga Sin Estres application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CargaSinEstresApplication.class, args);
	}

	/**
	 * Initializes the database with user roles during application startup.
	 *
	 * @param roleRepository The repository for managing user roles.
	 * @return A CommandLineRunner to execute role initialization during application startup.
	 */
	@Bean
	CommandLineRunner initDatabase(IRoleRepository roleRepository) {
		return args -> {
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_USER);
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_ADMIN);
		};
	}
} //http://localhost:8080/swagger-ui/index.html
