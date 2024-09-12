package edu.unisabana.dyas.registry;

import java.util.HashSet;
import java.util.Set;

public class Registry {
    private Set<Integer> registeredIds = new HashSet<>();

    public RegisterResult registerVoter(Person p) {
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (p.getAge() <= 0 || p.getAge() > 130) {
            throw new IllegalArgumentException("La edad no puede ser negativa o superior a 130");
        }
        if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }
        registeredIds.add(p.getId());
        return RegisterResult.VALID;
    }
}
