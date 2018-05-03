package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Course {
	private List<Module> modules = new ArrayList<>();

	public void addModule(Module module) {
		modules.add(module);
	}

	public List<Module> getModules() {
		return modules;
	}
}

class Module {
	private Optional<Student> students = Optional.empty();
	private String moduleName;

	public Module(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Optional<Student> getStudent() {
		return students;
	}

	public void setStudents(Optional<Student> students) {
		this.students = students;
	}
}

class Student {
	private int numberOfStudents;

	public Student(int number) {
		this.numberOfStudents = number;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

}

public class OptionalTest {

	// get() This will returns the value in the Optional if present, otherwise
	// throws NoSuchElementException.
	public static void main(String[] args) {
		Course computerScience = new Course();
		Module algorithms = new Module("Algorithms");

		Optional<Student> studentsOnAlgorithm = Optional.of(new Student(50));
		algorithms.setStudents(studentsOnAlgorithm);
		computerScience.addModule(algorithms);

		Module cancelledModules = new Module("Pascal");
		computerScience.addModule(cancelledModules);

		computerScience.getModules().stream().forEach((m) -> {
			if (m.getStudent().isPresent()) {
				System.out.println("Module ---> " + m.getModuleName() + " \n"
						+ m.getStudent().get().getNumberOfStudents() + " students take this module");
			} else {
				System.out.println("Module ---> " + m.getModuleName() + "\nhas no students attached to it");
			}
		});
	}
}
