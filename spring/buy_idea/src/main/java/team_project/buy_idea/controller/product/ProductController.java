package team_project.buy_idea.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team_project.buy_idea.controller.product.request.ProductRequest;
import team_project.buy_idea.repository.product.mapping.ProductImageMapping;
import team_project.buy_idea.repository.product.mapping.ProductMapping;
import team_project.buy_idea.service.product.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void productRegister(
            @RequestPart(value = "files") List<MultipartFile> fileList,
            @RequestPart(value = "product") ProductRequest productRequest) {
        log.info("productRegister files :  " + fileList);
        log.info("productRegister product :  " + productRequest);

        productService.register(productRequest, fileList);

    }

    @GetMapping("/list")
    public List<ProductMapping> productList(
            @RequestParam("category") String category,
            @RequestParam("productSize") int productSize) {

        log.info("productList()");
        log.info("category : " + category);
        log.info("productsNum : " + productSize);

        return productService.list(category, productSize);
    }

    @GetMapping("/list/next")
    public List<ProductMapping> nextProductList(
            @RequestParam("productNo") Long productNo,
            @RequestParam("category") String category,
            @RequestParam("productSize") int productSize) {

        log.info("nextProductList()");
        log.info("productNo : " + productNo);
        log.info("category : " + category);
        log.info("productSize : " + productSize);

        return productService.nextList(productNo, category, productSize);
    }

    @GetMapping("/image/thumbnail/{productNo}")
    public ProductImageMapping productThumbnailImage(@PathVariable("productNo") Long productNo) {

        log.info("productThumbnailImage()");
        log.info("productNo : " + productNo);

        return productService.thumbnailImage(productNo);
    }
}
