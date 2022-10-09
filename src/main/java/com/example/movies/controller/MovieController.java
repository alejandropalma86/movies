package com.example.movies.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movies.model.Movie;
import com.example.movies.service.MovieService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

	@GetMapping("/")
	public String index(Model model, String keyword) {

		List<Movie> list;
		if(keyword == null) {
			list = movieService.getAll();
		} else {
			list = movieService.findByKeyword(keyword);
		}
	
		model.addAttribute("list", list);
		return "index";
	}

	
	@GetMapping("/{id}")
    public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("list", movieService.get(id));
        return "singlemovie";
    }  

	
	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("movie", movieService.get(id));
		} else {
			model.addAttribute("movie", new Movie());
		}
		return "save";
	}


	@PostMapping("/save")
	public String save(@RequestParam(name="file", required = false) MultipartFile cover, Movie movie,
		RedirectAttributes flash) {
			if(!cover.isEmpty()){
				String route = "C://java//covers";

				try {
					byte[] bytes = cover.getBytes();
					Path absoluteRoute = Paths.get(route + "//" + cover.getOriginalFilename());
					Files.write(absoluteRoute, bytes);
					movie.setCover(cover.getOriginalFilename());
				} catch(Exception e){

				}
			} 

		movieService.save(movie);
		flash.addFlashAttribute("success", "Portada Subida!!!");
		return "redirect:/";
	} 

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		movieService.delete(id);

		return "redirect:/";
	}
    
}
