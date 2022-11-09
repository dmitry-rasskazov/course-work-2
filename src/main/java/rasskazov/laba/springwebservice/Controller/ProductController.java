package rasskazov.laba.springwebservice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rasskazov.laba.springwebservice.Entity.Product;
import rasskazov.laba.springwebservice.Repository.ProductRepository;
import rasskazov.laba.springwebservice.Service.LogService;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Controller
public class ProductController
{
    private final ProductRepository productRepository;
    private final LogService logService;

    @Autowired
    public ProductController(@NotNull ProductRepository productsRepository, @NotNull LogService logService)
    {
        this.productRepository = productsRepository;
        this.logService = logService;
    }

    @GetMapping("/list")
    public ModelAndView getAllProducts() {
        log.info("/list -> connection");

        ModelAndView model = new ModelAndView("list-products");
        model.addObject("products", productRepository.findAll());
        return model;
    }

    @GetMapping("/addProductForm")
    public ModelAndView addProductForm()
    {
        logService.info("Add product as " + currentUserName());

        ModelAndView model = new ModelAndView("add-product-form");
        Product product = new Product();

        model.addObject("product", product);
        return model;
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product products)
    {
        logService.info("Save products as " + currentUserName());

        productRepository.save(products);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long productId) {
        ModelAndView model = new ModelAndView("add-product-form");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = new Product();
        if(optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        model.addObject("product", product);
        return model;
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long productId) {
        logService.info("Delete product as " + currentUserName());

        productRepository.deleteById(productId);
        return "redirect:/list";
    }

    private String currentUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
