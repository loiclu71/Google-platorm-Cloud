import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import model.Countries;

import com.googlecode.objectify.Key;


@Entity
@Index
public class City {
	@Id String name;
	int postal_code;
	@Parent Key<Countries> parent;
	
	public City() {}
	
	public City(String name,int postal_code,Key<Countries> parent) {
		this.name = name;
		this.postal_code = postal_code;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPostal_Code() {
		return postal_code;
	}
	
	public Key<Countries> getParent(){
		return parent;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPostal_Code(int postal_code) {
		this.postal_code = postal_code;
	}
	
	public void setParent(Key<Countries> parent) {
		this.parent = parent;
	}
}