package designpatterns.DAO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1, "John Rambo");
        Person p2 = new Person(2, "Bill Gates");
        Person p3 = new Person(3, "Richard Stallman");
        PersonDAO personDAO = new PersonDAOImpl();
        personDAO.newPerson(p1);
        personDAO.newPerson(p2);
        personDAO.newPerson(p3);
        System.out.println(personDAO.getAll());
        personDAO.deletePerson(new Person(1, "John Rambo"));
        System.out.println(personDAO.getAll());

    }

}

interface PersonDAO {
    List<Person> getAll();
    void newPerson(Person p);
    void deletePerson(Person p);
}

class PersonDAOImpl implements PersonDAO {
    private List<Person> data = new ArrayList<>();

    @Override
    public List<Person> getAll() {
        return data;
    }

    @Override
    public void newPerson(Person p) {
        data.add(p);
    }

    @Override
    public void deletePerson(Person p) {
        while(data.remove(p)) { }
    }
}

class Person {
    private String name;
    private int id;

    public Person(int id, String s) {
        this.id = id;
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return p.id == this.id && p.name.equals(this.name);
        }
        return false;
    }
}
