package proassign.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java.
 * Includes all REST API endpoint mappings for the Animal object.
 */
@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    /**
     * Get a list of all Animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animal objects.
     */
    @GetMapping("/all")
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    /**
     * Get a specific Animal by Id.
     * http://localhost:8080/animals/2
     *
     * @param animalId the unique Id for an Animal.
     * @return One Animal object.
     */
    @GetMapping("/{animalId}")
    public Animal getAnimalById(@PathVariable int animalId) {
        return animalService.getAnimalById(animalId);
    }

    /**
     * Get a list of Animals based on their species
     *
     * @param species the search key.
     * @return A list of Animal objects matching the search key.
     */
    @GetMapping("")
    public List<Animal> getAnimalsBySpecies(@RequestParam(name = "species", defaultValue = "dog") String species) {
        return animalService.getAnimalsBySpecies(species);
    }

    /**
     * Get a list of animals based on a name search.*
     * @param name the name to search for.
     * @return A list of Animal objects matching the search key.
     */
    @GetMapping("/search")
    public List<Animal> searchByName(@RequestParam(name = "name") String name) {
        return animalService.searchByName(name);
    }

    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new --data '{ "animalId": 4, "name": "Max", "species": "dog", "age": 5}'
     *
     * @param animal the new Animal object.
     * @return the updated list of Animals.
     */
    @PostMapping("/new")
    public List<Animal> addNewAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
        return animalService.getAllAnimals();
    }

    /**
     * Update an existing Animal object.
     * http://localhost:8080/animals/update/2 --data '{ "animalId": 2, "name": "Buddy", "species": "cat", "age": 3}'
     *
     * @param animalId the unique Animal Id.
     * @param animal   the new update Animal details.
     * @return the updated Animal object.
     */
    @PutMapping("/update/{animalId}")
    public Animal updateAnimal(@PathVariable int animalId, @RequestBody Animal animal) {
        animal.setAnimalId(animalId); // Set the ID of the animal to be updated
        animalService.updateAnimal(animal);
        return animalService.getAnimalById(animalId);
    }

    /**
     * Delete an Animal object.
     * http://localhost:8080/animals/delete/2
     *
     * @param animalId the unique Animal Id.
     * @return the updated list of Animals.
     */
    @DeleteMapping("/delete/{animalId}")
    public List<Animal> deleteAnimalById(@PathVariable int animalId) {
        animalService.deleteAnimal(animalId);
        return animalService.getAllAnimals();
    }
}
