package metier;

public class Preference {
	private String name;
	public Preference(String name) {
		this.name=name;
	}
	@Override
	public String toString() {
		return "Preference [name=" + name + "]";
	}
}
