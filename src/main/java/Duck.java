public class Duck implements Swimmable {

    private String breed;

    public Duck(String breed) {
        this.breed = breed;
    }

    @Override
    public void swim() {
        System.out.printf("I'm a %s and I swim with my webbed feet!%n", breed);
    }
}
