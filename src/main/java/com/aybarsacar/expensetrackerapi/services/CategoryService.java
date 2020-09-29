package com.aybarsacar.expensetrackerapi.services;

import com.aybarsacar.expensetrackerapi.domain.Category;
import com.aybarsacar.expensetrackerapi.exceptions.EtBadRequestException;
import com.aybarsacar.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {

  List<Category> fetchAllCategories(Integer userId);

  Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

  Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

  void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

  /*
    this is a cascade delete
    we need to remove all of its transactions then delete the category
   */
  void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;


}
