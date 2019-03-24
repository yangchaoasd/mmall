package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.model.Category;

import java.util.List;

public interface CategoryService {

    ServerResponse addCategory(String categoryName, int parentId);

    ServerResponse updateCategoryName(int categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(int categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(int categoryId);
}
