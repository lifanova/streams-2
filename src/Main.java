import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vera Lifanova
 * Date: 05.05.2022
 */

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long youngCount = persons.stream().filter(item -> item.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних - " + youngCount);

        List<String> surnames = persons.stream()
                .filter(item -> item.getAge() >= 18 && item.getAge() <= 27)
                .filter(item -> item.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Фамилии призывников: " + surnames.toString());

        List<String> result = persons.stream()
                .filter(item -> item.getEducation().equals(Education.HIGHER))
                .filter(item -> (item.getSex().equals(Sex.WOMAN) && item.getAge() >= 18 && item.getAge() <= 60) || (item.getSex().equals(Sex.MAN) && item.getAge() >= 18 && item.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily)).map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Фамилии трудоспособных: " + result.toString());
    }
}
