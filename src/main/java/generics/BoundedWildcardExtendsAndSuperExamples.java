package generics;

import java.util.List;

public class BoundedWildcardExtendsAndSuperExamples {

    public void findAnimal(List<? extends Pokemon> pokemons, Pokemon pokemonToFind) {
        for (Pokemon animal : pokemons) {
            if (animal.equals(pokemonToFind)) {
                System.out.print(animal + "found");
            }
        }
    }
}
