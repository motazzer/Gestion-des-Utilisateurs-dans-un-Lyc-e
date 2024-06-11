package Models;

public class Profile {
	private float marks;
    private String branch;

    public Profile(String branch) {
        this.branch = branch;
    }

	public float getMarks() {
		return marks;
	}

	public void setMarks(float marks) {
		this.marks = marks;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
