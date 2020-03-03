package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryDao;
import org.csu.mypetstore.persistence.ItemDao;
import org.csu.mypetstore.persistence.ProductDao;
import org.csu.mypetstore.persistence.impl.CategoryDaoImpl;
import org.csu.mypetstore.persistence.impl.ItemDaoImpl;
import org.csu.mypetstore.persistence.impl.ProductDaoImpl;

import java.util.List;

public class CatalogService {
    private CategoryDao categoryDAO;
    private ProductDao productDAO;
    private ItemDao itemDAO;

    public CatalogService(){
        categoryDAO = new CategoryDaoImpl();
        productDAO = new ProductDaoImpl();
        itemDAO = new ItemDaoImpl();
    }

    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }


    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
}
