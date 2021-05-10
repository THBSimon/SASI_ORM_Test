package com.example.orm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@SpringBootApplication
@RestController
public class OrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmApplication.class, args);
	}

	/*Zum Hinzufügen neuer Studenten;
	 "Name" ist das Attribut, welches alle Studenten bei der Erstellung haben müssen
	 die anderen Angaben können bei der Erstellung angegeben werden, müssen sie aber nicht.*/
	@PostMapping ("/students/add")
	public String createStudent(@RequestParam("name") String name,
								@RequestParam(value = "age",required = false)Integer age,
								@RequestParam(value = "phone",required = false) String phone,
								@RequestParam(value = "mail",required = false)String mail,
								@RequestParam(value = "sex",required = false)String sex,
								@RequestParam(value = "studentnr",required = false)String studentnr) {
		Student SpeicherStudent = new Student();
		SpeicherStudent.setName(name);
		if (Objects.nonNull(age))
			SpeicherStudent.setAge(age);
		else
			SpeicherStudent.setAge(20);
		if (Objects.nonNull(phone))
			SpeicherStudent.setPhoneNumber(phone);
		if (Objects.nonNull(mail))
			SpeicherStudent.setEmailAddress(mail);
		if (Objects.nonNull(studentnr))
			SpeicherStudent.setStudentNumber(Double.parseDouble(studentnr));
		if (Objects.nonNull(sex))
			SpeicherStudent.setSex(Boolean.parseBoolean(sex));


		DB.Persist(SpeicherStudent);
		return "Persistierung erfolgreich durchgeführt!";
	}

	/*Zum Verändern vorhandener Studenten;
	 "id" ist das Attribut, welches angegeben werden muss, um den zu verändernden Eintrag abrufen zu können.
	 Darauf folgen die zu verändernden Parameter als Eingabe.*/
	@PostMapping ("/students/change")
	public String createStudent(@RequestParam(value = "id") Integer id,
								@RequestParam(value = "name",required = false) String name,
								@RequestParam(value = "age",required = false)Integer age,
								@RequestParam(value = "phone",required = false) String phone,
								@RequestParam(value = "mail",required = false)String mail,
								@RequestParam(value = "sex",required = false)String sex,
								@RequestParam(value = "studentnr",required = false)String studentnr) {

		//Einlesen des zu verändernden Objekts:
		Student EingelesenerStudent = DB.CallStudentById(id);

		//Ändern der Parameter:
		if (Objects.nonNull(name))
			EingelesenerStudent.setName(name);
		if (Objects.nonNull(age))
			EingelesenerStudent.setAge(age);
		if (Objects.nonNull(phone))
			EingelesenerStudent.setPhoneNumber(phone);
		if (Objects.nonNull(mail))
			EingelesenerStudent.setEmailAddress(mail);
		if (Objects.nonNull(studentnr))
			EingelesenerStudent.setStudentNumber(Double.parseDouble(studentnr));
		if (Objects.nonNull(sex))
			EingelesenerStudent.setSex(Boolean.parseBoolean(sex));

		//Updaten des zu verändernden Objekts:
		DB.EvictAndMerge(EingelesenerStudent, id);
		return "Veränderung des Datensatzes erfolgreich durchgeführt!";
	}

	//Anzeigen eines Objekts mit einer bestimmten id:
	@GetMapping("/students/show")
	public String viewStudents(@RequestParam(value = "id", defaultValue = "1") Integer id) {
		String StudentString = null;

		Student student = DB.CallStudentById(id);

		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			StudentString = om.writeValueAsString(student);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return StudentString;
	}
}
