package day0822;

public class ColumnVO {
	private String column_name, data_type;
	private int data_length;
	
	public ColumnVO() {
		super();
	}
	
	public ColumnVO(String column_name, String data_type, int data_length) {
		super();
		this.column_name = column_name;
		this.data_type = data_type;
		this.data_length = data_length;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_length() {
		return data_length;
	}
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}

	@Override
	public String toString() {
		return "ColumnVO [column_name=" + column_name + ", data_type=" + data_type + ", data_length=" + data_length
				+ "]";
	}
	
	
	
	
}//class
