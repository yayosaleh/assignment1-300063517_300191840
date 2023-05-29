import java.util.Arrays;

public class Employee {
  
  private String name;
  private int hours;
  private double rate;
  private Address[] address; //based on memory diagram, this would be more appropriately named 'addresses'

  //Arity-4 constructor (given Address array)
  public Employee(String name, int hours, double rate, Address[] address) {
    this.name = name;
    this.hours = hours;
    this.rate = rate;
    this.address = address;
  }

  //Arity-3 constructor
  public Employee(String name, int hours, double rate) {
    this.name = name;
    this.hours = hours;
    this.rate = rate; 
    this.address = new Address[5]; //memory diagram shows this array contains 5 elements
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  //Arity-0: returns address array
  public Address[] getAddress() {
    return address;
  }

  //Arity-1: returns address at given index
  public Address getAddress(int index) {
    if (index >= this.address.length){
      return null; 
    }
    return this.address[index]; 
  }

  //Arity-1: sets all addresses
  public void setAddress(Address[] address) {
    this.address = address;
  }

  //Arity-2: sets address at given index
  public void setAddress(Address address, int index){
    if (index < this.address.length){
      this.address[index] = address; 
    }
  } 

  public String toString() {
    return "Employee [name=" + name + ", hours=" + hours + ", rate=" + rate + "\n, address=" + Arrays.toString(address) + "]";
  }

}
