import java.util.*;
import java.util.stream.Collectors;

public class Ass1 {
    static class Employee{
        public String name;
        public int age;

        public Employee(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName()
        {
            return name;
        }

        public int getAge() {
            return age;
        }

    }

    public static void main(String[] args) throws Exception{
        streamOne();
        streamTwo();
        streamThree();
    }

    public static void streamOne(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Mike", 25));
        employeeList.add(new Employee("Boo",19));
        employeeList.add(new Employee("Steven", 30));
        employeeList.add(new Employee("Tony", 28));
        employeeList.add(new Employee("Ben",20));

        List<Employee> sortList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getName).reversed().thenComparing(Employee::getAge))
                .collect(Collectors.toList());

        for(int i = 0; i < sortList.size(); i++){
            System.out.println("name: " + sortList.get(i).getName() + " age: " + sortList.get(i).getAge());
        }
    }

    public static void streamTwo(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> map = list.stream()
                .collect(Collectors.partitioningBy(x -> x %2 == 0));
        List<Integer> evenList = map.get(Boolean.TRUE);
        List<Integer> oddList = map.get(Boolean.FALSE);
        System.out.println(evenList + "" + oddList);

    }

    public static void streamThree(){
        List<Character> list = Arrays.asList('a','b','f','d','g','h');
        String result = list.stream()
                .map(String::valueOf).collect(Collectors.joining());

        System.out.println(result);
    }
}
