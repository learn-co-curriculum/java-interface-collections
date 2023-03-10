public class Fish implements Swimmable{

    private String species;

    public Fish(String species) {
        this.species = species;
    }

    @Override
    public void swim() {
        System.out.printf("I'm a %s and I swim using fins!%n", species);
    }
}
