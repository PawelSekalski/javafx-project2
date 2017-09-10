package app.model;

public class tableModel {
	
	private int id;
	private String name;
	private String last;
	private double salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public tableModel () {}
	
	public tableModel(int id, String name, String last, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.last = last;
		this.salary = salary;
	}
	
	
	
}
