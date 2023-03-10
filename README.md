# Collections and Interfaces

## Learning Goals

- Create a collection using an interface as the element type.
- Show how to iterate through a collection and how dynamic binding is used.

## Introduction

In this lesson, we will review iterating through a `List` with an interface
structure.

Fork and clone this lesson. You will see a `Duck` class, a `Fish` class, a
`ListExample` class, and a `Swimmable` interface. Code along with us in this
lesson to further your understanding of collections and dynamic binding.

```java
public interface Swimmable {
    void swim();
}
```

```java
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
```

```java
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
```

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    
    public static void main(String[] args) {
        List<Swimmable> swimmingAnimals = new ArrayList<>();
    }
}
```

## Interface Review

Before we jump into our example code, let us remember a few things about
interfaces first:

- An interface is never instantiated and does not have constructors.
- An interface may define one or more abstract methods.
  - In our `Swimmiable` interface, we can see an abstract method called
    `swim()`. This is an abstract method, as in it has no method body is public
    by default.
- An interface may define constants.
- An interface may define default and static methods (more on that in the next
  lesson).
- A class that declares it **implements** an interface must provide an
  implementation (i.e. a method body) for every abstract method declared in the
  interface.

We can see that we have two classes that implement the `Swimmable` interface:
the `Duck` class and the `Fish` class. Both override and provide an
implementation for the `swim()` abstract method.

In the Interface Lesson we saw earlier, we saw how we could create an array
with an interface as the declared type for the array:

```java
// We created a Shape interface in the Interface Lesson
// Rectangle and Circle both implemented the Shape interface
Shape[] tables = new Shape[] {
        new Rectangle(7, 5),
        new Rectangle(2, 4),
        new Rectangle(3, 5),
        new Circle(4),
        new Circle(6)
};
```

We can also do the same with the collection framework we just learned about! If
we look in the `ListExample` class, we'll see we have declared a `List` of
`Swimmable` objects:

```java
List<Swimmable> swimmingAnimals = new ArrayList<>();
```

Let's go ahead and add some `Swimmable` animals to the `List`! Modify the
`ListExample` class as shown below:

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {

    public static void main(String[] args) {
        List<Swimmable> swimmingAnimals = new ArrayList<>();

        swimmingAnimals.add(new Fish("Clownfish"));
        swimmingAnimals.add(new Fish("Blue Tang"));
        swimmingAnimals.add(new Duck("Mallard"));

        for (Swimmable swimmer : swimmingAnimals) {
            swimmer.swim();
        }
    }
}
```

## Iterating and Dynamic Binding

Now that we have some animals added to our `List`, let's go ahead and iterate
through the list. Set a breakpoint at the `for` loop to see what this looks like
in memory:

![arraylist-in-memory](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/java-visualizer-arraylist-swimmable.png)

We can look at what is in the `List` by opening up the debug console as well:

![arraylist-debugger](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-swimmables.png)

We'll use the step-over action to see what the `swimmer` variable gets assigned
to. We expect it to be assigned to the first element in the `ArrayList`, which
would be the "Clownfish".

![first-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-swimmers-iteration-1.png)

Now let's step into the `swim()` method.

![step-into-Fish](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-swim-clownfish.png)

Notice how the execution jumped over to the `Fish` class to call the `swim()`
method! Java uses dynamic binding for the `swimmer.swim()` method call to
figure out which implementation to execute. **Dynamic binding** is a process
that occurs at runtime and makes use of the overriding methods. In this case,
since `Fish` overrides the `swim()` method that is defined in the `Swimmable`
interface, the execution will jump to the `swim()` method implemented in the
`Fish` class and execute it when it sees a `Fish` calling `swim()`.

If we use the step-over action, we'll see that "I'm a Clownfish and I swim using
fins!" has been printed to the console.

We'll use the step-over action again until we are back at the beginning of the
`for` loop, ready for the second iteration.

Onto the second iteration. This time, the `swimmer` variable will be assigned to
the second element in the `ArrayList`, which is the "Blue Tang".

![second-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-swimmers-iteration-2.png)

Just like before, we'll use the step-into action to go into the `swim()` method.
This will take us into the `Fish` class again to call the `swim()` method.

![step-into-Fish](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-swim-blue-tang.png)

If we use the step-over action, we'll see that "I'm a Blue Tang and I swim using
fins!" has been printed to the console.

Let's step-over until we get back to the beginning of the `for` loop to look at
the third (and last) iteration of this loop.

Once at the breakpoint, we'll use the step-over action to see that the `swimmer`
variable gets assigned to the third element in the `ArrayList`, which is the
"Mallard".

![third-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-swimmers-iteration-3.png)

Notice this time that the `Swimmable` variable, `swimmer` is assigned to a
`Duck` rather than a `Fish` object this time. What will happen this time when we
use the step-into action to go into the `swim()` method?

![step-into-Duck](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-swim-mallard.png)

This time, the execution jumped to the `Duck` class to call the `swim()` method
instead! This is dynamic binding at its best again. Java sees that the
`swimmer` is actually a `Duck`. Since the `Duck` class overrides the `swim()`
method that is defined in the `Swimmable` interface, the execution will jump to
the `swim()` method implemented in the `Duck` class.

If we resume the program, we'll see that the final output in the console:

```text
I'm a Clownfish and I swim using fins!
I'm a Blue Tang and I swim using fins!
I'm a Mallard and I swim with my webbed feet!
```

## Comprehension Check

Create a new class called `Turtle` that also inherits from the `Swimmable`
interface:

```java
public class Turtle implements Swimmable {

    private String breed;

    public Turtle(String breed) {
        this.breed = breed;
    }

    @Override
    public void swim() {
        System.out.printf("I'm a %s and I swim with my flippers!%n", breed);
    }
}
```

Modify the `ListExample` class to look like this:

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {

    public static void main(String[] args) {
        List<Swimmable> swimmingAnimals = new ArrayList<>();

        swimmingAnimals.add(new Fish("Yellow Tang"));
        swimmingAnimals.add(new Turtle("Green Sea Turtle"));
        swimmingAnimals.add(new Fish("Barracuda"));

        for (Swimmable swimmer : swimmingAnimals) {
            swimmer.swim();
        }
    }
}
```

<details>
    <summary>What do you think the program will output now?</summary>

  <p>Answer: <br>
     <p><code>I'm a Yellow Tang and I swim using fins</code></p>
     <p style="margin-top: -18px"><code>I'm a Green Sea Turtle and I swim with my flippers!</code></p>
     <p style="margin-top: -18px"><code>I'm a Barracuda and I swim using fins!</code></p>
  </p>

  <p>This time through, we will first call the <code>swim()</code> method in the <code>Fish</code> class.
     In the second iteration, we'll call the <code>swim()</code> method in the <code>Turtle</code> class.
     Then in the third iteration, the execution will call <code>swim()</code> again from the <code>Fish</code> class.</p>
  <img src="https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/java-visualizer-arraylist-swimmable-turtles.png" alt="arraylist-with-fish-turtle-fish">

</details>
