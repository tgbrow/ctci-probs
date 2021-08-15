package ch03;

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

        System.out.println("dequeue cat: " + as.dequeueCat().getName());
        System.out.println("dequeue dog: " + as.dequeueDog().getName());
        System.out.println("dequeue cat: " + as.dequeueCat().getName());
        System.out.println("dequeue any: " + as.dequeueAny().getName());
        System.out.println("dequeue cat: " + as.dequeueCat().getName());
        System.out.println("dequeue any: " + as.dequeueAny().getName());
    }

    private static class AnimalShelter {
        // These lists act as queues, where head is front of queue (oldest arrival).
        private LinkedList<AnimalRecord<Dog>> dogs;
        private LinkedList<AnimalRecord<Cat>> cats;
        // For simplicity's sake, use a fake "clock" that counts up from zero each
        // time an animal is enqueued.
        private int fakeClock;

        public AnimalShelter() {
            dogs = new LinkedList<>();
            cats = new LinkedList<>();
            fakeClock = 0;
        }

        public void enqueue(Animal a) throws IllegalArgumentException {
            if (a instanceof Dog) {
                dogs.add(new AnimalRecord<Dog>((Dog) a, fakeClock++));
            } else if (a instanceof Cat) {
                cats.add(new AnimalRecord<Cat>((Cat) a, fakeClock++));
            } else {
                throw new IllegalArgumentException("This animal shelter only accepts dogs and cats!");
            }
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
            return dequeueAnimal(dogs);
        }

        public Cat dequeueCat() {
            return dequeueAnimal(cats);
        }

        private static <T extends Animal> T dequeueAnimal(LinkedList<AnimalRecord<T>> list) {
            AnimalRecord<T> ar = list.poll();
            return ar == null ? null : ar.animal;
        }
    }

    private static class AnimalRecord<T extends Animal> {
        private T animal;
        // "Time" simplified to an int. True implementation would use something like DateTime.
        private int arrivalTime;

        public AnimalRecord(T animal, int arrivalTime) {
            this.animal = animal;
            this.arrivalTime = arrivalTime;
        }
    }

    private static class Animal {
        private String name;

        public Animal(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
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
