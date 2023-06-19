package com.sre.transportation;

import java.util.ArrayList;
import java.util.List;

import com.sre.transportation.entity.*;
import com.sre.transportation.repository.UserCredentialsRepository;

import com.sre.transportation.security.authority.Privilege;
import com.sre.transportation.security.authority.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sre.transportation.repository.RouteRepository;

@SpringBootApplication
public class TransportationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);
	}
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@Override
	public void run(String... args) throws Exception {
		Vehicle vehicle1 = new Vehicle("06-AB-123");
		Station station1 = new Station("121231","Güvenpark");
		Station station2 = new Station("35553","Sıhhiye");
		Route route = new Route("304", "Kırkkonaklar");

		station1.getVehicles().add(vehicle1);
		station2.getVehicles().add(vehicle1);

		Route r2 = routeRepository.findByCode(route.getCode());
		if(r2 == null){
			route.getStations().add(station1);
			route.getStations().add(station2);
			routeRepository.save(route);
		}
		List<Privilege> privilegeList = new ArrayList<>();
		privilegeList.add(Privilege.UPDATE_ROUTE);
		privilegeList.add(Privilege.DELETE_ROUTE);

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("evrim");
		userCredentials.setPassword("12345678");
		userCredentials.setRoles(Role.ADMIN);
		userCredentials.setPermissions(privilegeList);
		userCredentialsRepository.save(userCredentials);
	}

}
