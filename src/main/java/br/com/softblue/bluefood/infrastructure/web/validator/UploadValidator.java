package br.com.softblue.bluefood.infrastructure.web.validator;

import br.com.softblue.bluefood.util.FileType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {

    private List<FileType> acceptedTypes;

    @Override
    public void initialize(UploadConstraint constraintAnnotation) {
        acceptedTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null) {
            return true;
        }

        return acceptedTypes.stream().anyMatch(fileType -> fileType.sameOf(multipartFile.getContentType()));
    }
}
