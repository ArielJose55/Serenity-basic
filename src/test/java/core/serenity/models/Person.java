package core.serenity.models;
public class Person {
	
	private String name;
	private String email;
	private String subject;
	private String note;
	
	public Person() {
		super();
	}

	public Person(String name, String email, String subject, String note) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", subject=" + subject + ", note=" + note + "]";
	}
		
}
