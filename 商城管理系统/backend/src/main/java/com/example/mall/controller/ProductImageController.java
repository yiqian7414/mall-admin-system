package com.example.mall.controller;

import com.example.mall.service.ProductImageService;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {
    private final ProductImageService images;
    public ProductImageController(ProductImageService images) { this.images = images; }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return Map.of("imageUrl", images.upload(file));
    }
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> read(@PathVariable String filename) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).cacheControl(CacheControl.noCache().cachePrivate())
                .header("X-Content-Type-Options", "nosniff").body(images.read(filename));
    }
}
