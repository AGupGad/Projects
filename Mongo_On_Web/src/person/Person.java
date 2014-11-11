package person;

public class Person {
String firstName;
String lastName;
int age;
String hairColor;

String id;

/* id for Mongodb*/
public String getId(){
	return this.id;
}
public void setId(String id){
	this.id = id;
}

public String getFirstName(){
	return this.firstName;
}
public void setFirstName(String firstName){
	this.firstName = firstName;
}
public String getLastName(){
	return this.lastName;
}

public void setLastName(String lastName){
	this.lastName = lastName;
}
public int getAge(){
	return this.age;
}
public void setAge(int age){
	this.age = age;
}

public String getHairColor(){
	return this.hairColor;
}

public void setHairColor(String hairColor){
	this.hairColor = hairColor;
}


}
