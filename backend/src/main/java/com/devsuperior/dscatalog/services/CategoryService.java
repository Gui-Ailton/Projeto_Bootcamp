package com.devsuperior.dscatalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	//Quando for operação que é apenas leitura, sempre colocar essa anotação, para melhorar performace
	@Transactional(readOnly = true)
	public List<CategoryDTO>findAll(){
		List<Category>list = repository.findAll();
		
		List<CategoryDTO> listDto = new ArrayList<>();
		for(Category cat :list) {
			listDto.add(new CategoryDTO(cat));
		}
		
		return listDto;
	}

	@Transactional
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
}
