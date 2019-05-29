package vo;

public class TestDataVo {
	private String id;
	private String de;
	public TestDataVo() {}
	public TestDataVo(String id, String de) {
		this.id = id;
		this.de = de;
	}
	public String getId() {
		return id;
	}
	public String getDe() {
		return de;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDe(String de) {
		this.de = de;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + super.toString() + "]=[" + this.id + ", " + this.de + "]";
	}
}
