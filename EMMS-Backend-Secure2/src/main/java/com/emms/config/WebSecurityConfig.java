package com.emms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emms.UserDetialsServiceImpl;
import com.emms.filters.JwtRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private UserDetialsServiceImpl userDetailsServicImpl;
	
	@Bean
	public UserDetailsService userDetailService() {
		return new UserDetialsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailService());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
		//auth.userDetailsService(userDetailsServicImpl);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().   csrf().disable()
		.authorizeRequests().antMatchers("/authenticate").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/some").hasAnyAuthority("ADMIN","LEADER")
		
		//inventory access control
		//.antMatchers("/api/inventory/**").hasAnyAuthority("ADMIN","LEADER")
		
		.antMatchers("/api/inventory/equipment/**").hasAnyAuthority("ADMIN","LEADER", "VIEWER")
		.antMatchers("/api/inventory/getEquipmentById/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/checkIdAvailability/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/addEquipment/").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/updateEquipment/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/deleteEquipment/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/inventory/assetCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getDepartmentAssetCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getTypeDeptAssetCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getLocationAssetCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getSupplierAssetCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/underWarrantyCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/noWarrantyCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getEquipmentForSupplier/**").hasAnyAuthority("ADMIN","LEADER","VIEWER")
		.antMatchers("/api/inventory/getEquipmentForLocation/**").hasAnyAuthority("ADMIN","LEADER","VIEWER")
		.antMatchers("/api/inventory/getEquipmentForDepartment/**").hasAnyAuthority("ADMIN","LEADER","VIEWER")
		.antMatchers("/api/inventory/equipmentDepartmentCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/equipmentLocationCount/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getEquipmentForLocationAndDepartment/**").hasAnyAuthority("ADMIN","LEADER")
		
		.antMatchers("/api/inventory/getEquipmentForAssetId/**").hasAnyAuthority("ADMIN","LEADER","VIEWER")
		.antMatchers("/api/inventory/getEquipmentForSerialNumber/**").hasAnyAuthority("ADMIN","LEADER", "VIEWER")
		
		.antMatchers("/api/inventory/getEquipmentForTimePeriod/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getUnderWarrantyEquipment/**").hasAnyAuthority("ADMIN","LEADER", "VIEWER")
		.antMatchers("/api/inventory/getNoWarrantyEquipment/**").hasAnyAuthority("ADMIN","LEADER", "VIEWER")
		
		//access control related to equipment condemning
		.antMatchers("/api/inventory/getPendingCondemnEquipment/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/getAllCondemnedEquipment/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/inventory/requestEquipmentCondemn/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/cancelCondemnRequest/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/performCondemn/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/inventory/isEquipmentCondemnPending/**").hasAnyAuthority("ADMIN","LEADER")
		.antMatchers("/api/inventory/isEquipmentCondemned/**").hasAnyAuthority("ADMIN","LEADER")
		
		//access control for addInventory
		.antMatchers("/api/addInventory/**").hasAnyAuthority("ADMIN","LEADER","EDITOR")
		
		//supplier access control
		//.antMatchers("/api/supplier/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/allSuppliers/**").hasAnyAuthority("ADMIN", "LEADER", "VIEWER")
		.antMatchers("/api/supplier/addSupplier/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/getSupplierById/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/isSupplierAvailable/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/editSupplier/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/deleteSupplierById/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/supplier/getSupplierNameForId/**").hasAnyAuthority("ADMIN","LEADER", "VIEWER")
		
		//access control for model
		.antMatchers("/api/model/**").hasAnyAuthority("ADMIN")
		
		//access control for department
		//.antMatchers("/api/department/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/department/allDepartments/**").hasAnyAuthority("ADMIN", "LEADER", "VIEWER")
		.antMatchers("/api/department/addDepartment/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/department/getIdAvailability/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/department/deleteDepartment/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/department/getDepartmentNameById/**").hasAnyAuthority("ADMIN", "LEADER", "VIEWER")
		
		.antMatchers("/api/brand/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/category/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/user/**").hasAnyAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		/*.formLogin().permitAll()
		.and()*/
		.logout().permitAll()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	
	
	

}
