import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class PeopleStats {
    private final List<Person> people;

    public PeopleStats(Path inputFilePath) {
        try {
            people = Files.lines(inputFilePath)
                    .map(line -> line.split("\t"))
                    .map(chunks -> new Person(chunks[0], chunks[1]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    public long count() {
        return people.size();
    }
    public long countUniqueLastNames() {
        return people.stream()
                .map(Person::getLastName)
                .distinct()
                .count();
    }
}