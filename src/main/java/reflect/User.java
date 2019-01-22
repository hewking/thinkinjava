package reflect;

public class User {

    private String age;

    private String name;

    private String address;

    public User(String age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
