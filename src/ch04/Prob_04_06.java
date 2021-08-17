package ch04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Prob_04_06 {

    public static void main(String[] args) {
        Proj[] projects = {
            new Proj('a'), 
            new Proj('b'), 
            new Proj('c'), 
            new Proj('d'), 
            new Proj('e'), 
            new Proj('f'), 
            new Proj('g'), 
            new Proj('h'), 
        };
        Proj[][] deps = {
            {projects['a'-'a'], projects['e'-'a']}, // a -> e ("e depends on a")
            {projects['b'-'a'], projects['a'-'a']}, // b -> a
            {projects['b'-'a'], projects['e'-'a']}, // b -> e
            {projects['b'-'a'], projects['h'-'a']}, // b -> h
            {projects['c'-'a'], projects['a'-'a']}, // c -> a
            {projects['d'-'a'], projects['g'-'a']}, // d -> g
            {projects['f'-'a'], projects['a'-'a']}, // f -> a
            {projects['f'-'a'], projects['b'-'a']}, // f -> b
            {projects['f'-'a'], projects['c'-'a']}, // f -> c
        };
        ArrayList<Proj> buildOrder = buildOrder(projects, deps);
        if (buildOrder == null) {
            System.out.println("No valid build order found.");
            return;
        }
        for (Proj p : buildOrder) {
            System.out.print(p.id + " ");
        }
    }

    private static ArrayList<Proj> buildOrder(Proj[] projects, Proj[][] deps) {
        // Maps from a project to a set of other projects that depend upon *it*.
        Map<Proj, Set<Proj>> dependents = new HashMap<>();
        // Maps from a project to a set of other projects that *it* depends upon.
        Map<Proj, Set<Proj>> dependencies = new HashMap<>();
        for (Proj p : projects) {
            dependents.put(p, new HashSet<>());
            dependencies.put(p, new HashSet<>());
        }
        for (Proj[] dep : deps) {
            dependents.get(dep[0]).add(dep[1]);
            dependencies.get(dep[1]).add(dep[0]);
        }

        ArrayList<Proj> buildOrder = new ArrayList<>();
        Queue<Proj> q = new LinkedList<>();

        // Start with projects that have no dependencies.
        for (Map.Entry<Proj, Set<Proj>> e : dependencies.entrySet()) {
            if (e.getValue().isEmpty()) q.add(e.getKey());
        }

        while (!q.isEmpty()) {
            Proj p = q.remove();
            buildOrder.add(p);
            for (Proj o : dependents.get(p)) {
                Set<Proj> oDeps = dependencies.get(o);
                oDeps.remove(p);
                if (oDeps.isEmpty()) q.add(o);
            }
        }

        // No valid build order exists.
        if (buildOrder.size() != projects.length) return null;

        return buildOrder;
    }



    private static class Proj {
        char id;
        public Proj(char id) {
            this.id = id;
        }
    }


}
