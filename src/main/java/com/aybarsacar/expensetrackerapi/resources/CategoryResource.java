package com.aybarsacar.expensetrackerapi.resources;

import com.aybarsacar.expensetrackerapi.domain.Category;
import com.aybarsacar.expensetrackerapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

  @Autowired
  CategoryService categoryService;

  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {

//    get the userId from the token
    int userId = (Integer) request.getAttribute("userId");

    List<Category> categories = categoryService.fetchAllCategories(userId);

    return new ResponseEntity<>(categories, HttpStatus.OK);

  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(HttpServletRequest request,
                                                  @PathVariable("categoryId") Integer categoryId) {

//    get user id from the params
    int userId = (Integer) request.getAttribute("userId");

    Category category = categoryService.fetchCategoryById(userId, categoryId);

    return new ResponseEntity<>(category, HttpStatus.OK);
  }


  @PostMapping("")
  public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                              @RequestBody Map<String, Object> categoryMap) {

//    get user id from the request headers / parameters
    int userId = (Integer) request.getAttribute("userId");

//    get the category info from the request body
    String title = (String) categoryMap.get("title");
    String description = (String) categoryMap.get("description");

//    create category
    Category category = categoryService.addCategory(userId, title, description);

    return new ResponseEntity<>(category, HttpStatus.CREATED);

  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                             @PathVariable("categoryId") Integer categoryId,
                                                             @RequestBody Category category) {

    int userId = (Integer) request.getAttribute("userId");

    categoryService.updateCategory(userId, categoryId, category);

    Map<String, Boolean> map = new HashMap<>() {{
      put("success", true);
    }};

    return new ResponseEntity<>(map, HttpStatus.OK);


  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                             @PathVariable("categoryId") Integer categoryId) {

    int userId = (Integer) request.getAttribute("userId");

    categoryService.removeCategoryWithAllTransactions(userId, categoryId);

    Map<String, Boolean> map = new HashMap<>() {{
      put("success", true);
    }};

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

}
