package de.keag.lager.db.connection;

public class SqlParam{
	private String key;
	private Object value;
	protected SqlParam(String key, Object value){
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	protected void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	protected void setValue(Object value) {
		this.value = value;
	}
}
