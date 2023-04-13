package com.example.jpacontroller.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpacontroller.models.Author;
import com.example.jpacontroller.repository.AuthorRepository;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@RequestMapping(value = "authors")
public class AuthorController {
    
    @Autowired
    public AuthorRepository aRepository;

    //@RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping(value = "test")
    public @ResponseBody String testString() {
        return "test";
    }

    @PostMapping(value = "test")
    public @ResponseBody String testString2(@RequestBody String test) {
        return test.toUpperCase();
    }

    @GetMapping
    public @ResponseBody List<Author> getAll(){
        return aRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Author createAuthor(@RequestBody Author author) {
        return aRepository.save(author);
    }

    @DeleteMapping(value = "{id}")
    public @ResponseBody String deleteAuthor(@PathVariable("id") Long id){
        aRepository.deleteById(id);
        return "autore cancellato";
    }
    
    @PutMapping(value="{id}")
    public @ResponseBody String updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> optionalAuthor = aRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author oldAuthor = optionalAuthor.get();
            oldAuthor.setFirstname(author.getFirstname());
            oldAuthor.setLastname(author.getLastname());
            oldAuthor.setEmail(author.getEmail());

            aRepository.save(oldAuthor);
            return "autore modificato";        
        }
        return "l'autore non esiste";

    }

}
