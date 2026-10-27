package com.example.mall.service;

import com.example.mall.common.BusinessException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** 校验实际图像并重新编码成 PNG；不信任原文件名、后缀或客户端 MIME。 */
@Service
public class ProductImageService {
    private final Path directory;
    public ProductImageService(@Value("${mall.upload-directory:./data/uploads/products}") String directory) {
        this.directory = Path.of(directory).toAbsolutePath().normalize();
    }

    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty() || file.getSize() > 5L * 1024 * 1024)
            throw BusinessException.badRequest("请选择不超过 5 MB 的商品图片");
        try (var source = file.getInputStream(); var input = ImageIO.createImageInputStream(source)) {
            var readers = ImageIO.getImageReaders(input);
            if (!readers.hasNext()) throw BusinessException.badRequest("文件不是有效的 JPG、PNG 或 WebP 图片");
            var reader = readers.next();
            try {
                if (!Set.of("jpeg", "jpg", "png", "webp").contains(reader.getFormatName().toLowerCase(java.util.Locale.ROOT)))
                    throw BusinessException.badRequest("仅支持 JPG、PNG、WebP 图片");
                reader.setInput(input, true, true);
                int width = reader.getWidth(0), height = reader.getHeight(0);
                if (width < 1 || height < 1 || width > 4096 || height > 4096)
                    throw BusinessException.badRequest("图片宽高均须为 1～4096 像素");
                var image = reader.read(0);
                var output = new ByteArrayOutputStream();
                if (!ImageIO.write(image, "png", output) || output.size() > 20 * 1024 * 1024)
                    throw BusinessException.badRequest("图片处理后过大，请压缩后重试");
                Files.createDirectories(directory);
                String filename = UUID.randomUUID() + ".png";
                Files.write(directory.resolve(filename), output.toByteArray(), java.nio.file.StandardOpenOption.CREATE_NEW);
                return "/api/product-images/" + filename;
            } finally { reader.dispose(); }
        } catch (javax.imageio.IIOException | IllegalArgumentException e) {
            throw BusinessException.badRequest("图片内容损坏或格式不受支持，请重新选择图片");
        }
    }

    public void validateReference(String url) {
        if (url == null || url.isBlank()) return;
        if (!url.matches("/api/product-images/[a-f0-9-]{36}\\.png") || !Files.isRegularFile(directory.resolve(url.substring(url.lastIndexOf('/') + 1))))
            throw BusinessException.badRequest("商品图片不存在，请重新上传");
    }

    public Resource read(String filename) {
        if (!filename.matches("[a-f0-9-]{36}\\.png")) throw BusinessException.notFound("图片不存在");
        Path file = directory.resolve(filename).normalize();
        if (!file.startsWith(directory) || !Files.isRegularFile(file)) throw BusinessException.notFound("图片不存在");
        return new FileSystemResource(file);
    }
}
