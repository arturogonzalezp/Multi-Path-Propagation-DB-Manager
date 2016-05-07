import java.lang.reflect.Field;
import java.util.Date;

import com.google.gson.internal.LazilyParsedNumber;

import mx.arturogonzalezp.mppdb.structures.MPPItemInterface;


public class NodeItemTest implements MPPItemInterface{
	private Number ID;
	private String title;
	public NodeItemTest(Number ID, String title){
		this.setID(ID);
		this.setTitle(title);
	}
	public NodeItemTest(String ID, String title){
		this.setID(Long.parseLong(ID));
		this.setTitle(title);
	}
	@Override
	public Number getID() {
		return this.ID;
	}
	@Override
	public void setID(Number ID) {
		this.ID = ID;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(MPPItemInterface o) {
		NodeItemTest test = (NodeItemTest) o;
		if(this.getTitle().compareTo(test.getTitle()) > 0){
			return 1;
		}else if(this.getTitle().compareTo(test.getTitle()) < 0){
			return -1;
		}
		return 0;
	}
	public String toString(){
		return "ID: " + this.getID() + "\nTitle: " + this.getTitle();
	}
	@Override
	public <V> boolean valueEquals(String property, V value) {
		try{
			Field field = this.getClass().getDeclaredField(property);
			field.setAccessible(true);
			Object o = field.get(this);
			if(o instanceof LazilyParsedNumber){
				Number other = (Number) o;
				if (value instanceof Integer){
					Integer tempValue = (Integer)value;
					return other.intValue() == tempValue;
				}else if(value instanceof Double){
					Double tempValue = (Double)value;
					return other.doubleValue() == tempValue;
				}else if(value instanceof Long){
					Long tempValue = (Long)value;
					return other.longValue() == tempValue;
				}else if(value instanceof Short){
					Short tempValue = (Short)value;
					return other.shortValue() == tempValue;
				}else if(value instanceof Float){
					Float tempValue = (Float)value;
					return other.floatValue() == tempValue;
				}else if(value instanceof Byte){
					Byte tempValue = (Byte)value;
					return other.byteValue() == tempValue;
				}else if(value instanceof Number){
					return other.equals(value);
				}
				return false;
			}
			return o.equals(value) || o == value;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
