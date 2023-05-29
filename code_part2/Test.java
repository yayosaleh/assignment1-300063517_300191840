public class Test {
  public static void main(String[] args) {
    Address add1 = new Address("Queen", 48, "KINPIN2");
    Address add2 = new Address("King Edward", 800, "KIN6N5");
    
    Address[] add = new Address[5];
    add[0] = add1;
    add[1] = add2;

    Employee o1 = new Employee("Falcao", 40, 15.50, add);

    System.out.println(o1.toString());
  }
}