package com.codegym.blog.validator;

import com.codegym.blog.model.Blog;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomFileValidator implements Validator {

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
    public static final long TEN_MB_IN_BYTES = 10485760; // 10 MB

    @Override
    public boolean supports(Class<?> clazz) {
        return Blog.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Blog blog = (Blog) target;
        MultipartFile file = blog.getImageData();
        if (file.isEmpty()) {
            errors.rejectValue("imageData", "label.upload.file.required");
        } else if (!contentTypes.contains(file.getContentType())) {
            errors.rejectValue("imageData", "label.upload.invalid.file.type");
        } else if (file.getSize() > TEN_MB_IN_BYTES) {
            errors.rejectValue("imageData", "label.upload.exceeded.file.size");
        }
    }
}
