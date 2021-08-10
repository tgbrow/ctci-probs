import java.util.LinkedList;

public class Prob_03_06 {

    public static void main(String[] args) {
        AnimalShelter as = new AnimalShelter();
        as.enqueue(new Dog("Fido"));
        as.enqueue(new Dog("Spot"));
        as.enqueue(new Cat("Liz"));
        as.enqueue(new Dog("Lassie"));
        as.enqueue(new Cat("Garf"));
        as.enqueue(new Cat("Mr. B"));

        System.out.println("dequeue cat: " + as.dequeueCat().name);
        System.out.println("dequeue dog: " + as.dequeueDog().name);
        System.out.println("dequeue cat: " + as.dequeueCat().name);
        System.out.println("dequeue any: " + as.dequeueAny().name);
        System.out.println("dequeue cat: " + as.dequeueCat().name);
        System.out.println("dequeue any: " + as.dequeueAny().name);
    }

    private static class AnimalShelter {
        // These lists act as queues, where head is front of queue (oldest arrival).
        LinkedList<AnimalRecord<Dog>> dogs;
        LinkedList<AnimalRecord<Cat>> cats;
        // For simplicity's sake, use a fake "clock" that counts up from zero each
        // time an animal is enqueued.
        int fakeClock;

        public AnimalShelter() {
            dogs = new LinkedList<>();
            cats = new LinkedList<>();
            fakeClock = 0;
        }

        public void enqueue(Dog dog) {
            dogs.add(new AnimalRecord<Dog>(dog, fakeClock++));
        }

        public void enqueue(Cat cat) {
            cats.add(new AnimalRecord<Cat>(cat, fakeClock++));
        }

        public Animal dequeueAny() {
            if (dogs.isEmpty() && cats.isEmpty()) {
                return null;
            } else if (dogs.isEmpty()) {
                return cats.removeFirst().animal;
            } else if (cats.isEmpty()) {
                return dogs.removeFirst().animal;
            } else {
                return (dogs.peek().arrivalTime < cats.peek().arrivalTime) ?
                    dogs.removeFirst().animal : cats.removeFirst().animal;
            }
        }

        public Dog dequeueDog() {
            return dequeue(dogs);
        }

        public Cat dequeueCat() {
            return dequeue(cats);
        }

        private static <T extends Animal> T dequeue(LinkedList<AnimalRecord<T>> list) {
            AnimalRecord<T> ar = removeFirstOrNull(list);
            return ar == null ? null : ar.animal;
        }

        private static <T> T removeFirstOrNull(LinkedList<T> list) {
            return list.isEmpty() ? null : list.removeFirst();
        }

    }

    private static class AnimalRecord<T extends Animal> {
        T animal;
        // "Time" simplified to an int. True implementation would use something like DateTime.
        int arrivalTime;

        public AnimalRecord(T animal, int arrivalTime) {
            this.animal = animal;
            this.arrivalTime = arrivalTime;
        }
    }

    private static class Animal {
        String name;
        public Animal(String name) {
            this.name = name;
        }
    }

    private static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    private static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }
    
}
