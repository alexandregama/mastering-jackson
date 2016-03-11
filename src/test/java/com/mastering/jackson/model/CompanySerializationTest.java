package com.mastering.jackson.model;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CompanySerializationTest {

	@Test
	public void shouldSerializeACompanyIntoPrettyJson() throws Exception {
		List<Employee> employees = asList(new Employee("Alexandre", "Gama"), new Employee("Gustavo", "Maia"));
		Company company = new Company("Elo7", employees);
		
		ObjectWriter jsonWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String json = jsonWriter.writeValueAsString(company);
		
		System.out.println(json);
	}
	
	@Test
	public void shouldSerializeACompanyIntoADefaultJson() throws Exception {
		List<Employee> employees = asList(new Employee("Alexandre", "Gama"), new Employee("Gustavo", "Maia"));
		Company company = new Company("Elo7", employees);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(company);
		
		System.out.println(json);
	}
	
	@Test
	public void shouldDeserializeAJsonIntoACompanyObjectUsingReadValueMethod() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"name\":\"Elo7\",\"employees\":[{\"first_name\":\"Alexandre\",\"last_name\":\"Gama\"},{\"first_name\":\"Gustavo\",\"last_name\":\"Maia\"}]}";
		
		Company company = mapper.readValue(json, Company.class);
		
		assertThat(company.getName(), is(equalTo("Elo7")));
		assertThat(company.getEmployees().get(0).getFirstName(), is(equalTo("Alexandre")));
		assertThat(company.getEmployees().get(0).getLastName(), is(equalTo("Gama")));
		assertThat(company.getEmployees().get(1).getFirstName(), is(equalTo("Gustavo")));
		assertThat(company.getEmployees().get(1).getLastName(), is(equalTo("Maia")));
	}
	
	@Test
	public void shouldSerializeACompanyIntoADefaultJsonFile() throws Exception {
		List<Employee> employees = asList(new Employee("Alexandre", "Gama"), new Employee("Gustavo", "Maia"));
		Company company = new Company("Elo7", employees);
		
		ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
		
		writer.writeValue(new File("company.json"), company);
	}
	
	@Test
	public void shouldDeserializeAJsonIntoACompanyObjectFromJsonFile() throws Exception {
		ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
		
		List<Employee> employees = asList(new Employee("Alexandre", "Gama"), new Employee("Gustavo", "Maia"));
		Company companyToJson = new Company("Elo7", employees);
		
		writer.writeValue(new File("company.json"), companyToJson);
		
		ObjectMapper mapper = new ObjectMapper();
		Company companyFromJson = mapper.readValue(new File("company.json"), Company.class);
		
		assertThat(companyFromJson.getName(), is(equalTo("Elo7")));
		assertThat(companyFromJson.getEmployees().get(0).getFirstName(), is(equalTo("Alexandre")));
		assertThat(companyFromJson.getEmployees().get(0).getLastName(), is(equalTo("Gama")));
		assertThat(companyFromJson.getEmployees().get(1).getFirstName(), is(equalTo("Gustavo")));
		assertThat(companyFromJson.getEmployees().get(1).getLastName(), is(equalTo("Maia")));
	}
	
}
