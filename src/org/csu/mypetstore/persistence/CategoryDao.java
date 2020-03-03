package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDao {
    //获取所有的
    List<Category> getCategoryList();
    //获取一个
    Category getCategory(String categoryId);
}
