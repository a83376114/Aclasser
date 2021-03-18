package com.mz.aclasser.service;

import com.mz.aclasser.classifier.Classifier;
import com.mz.aclasser.classifier.impl.MobilenetV2Classifier;
import com.mz.aclasser.classifier.impl.PooledClassifier;
import com.mz.aclasser.controller.error.ServiceException;
import com.mz.aclasser.data.Recognition;
import com.mz.aclasser.data.RecognitionResult;
import com.mz.aclasser.util.ImageUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


@Log4j2
@Component
public class ImageProcessingService {

    private final Classifier<BufferedImage, List<Recognition>> classifier;
    private final int previewSize;

    @Autowired
    public ImageProcessingService(MobilenetV2Classifier classifier,
                                  @Value("${aclasser.maxExecutorsCount}") int maxExecutorsCount,
                                  @Value("${aclasser.previewSize}") int previewSize
    ) {
        this.classifier = new PooledClassifier<>(classifier, maxExecutorsCount);
        this.previewSize = previewSize;
    }

    public RecognitionResult processImageFile(MultipartFile file) {
        if (file == null) {
            throw new ServiceException("Failed reading image file");
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new ServiceException("Failed reading image file");
            }
            List<Recognition> recognitions = classifier.classify(image);
            BufferedImage imagePreview = getImagePreview(image);
            return new RecognitionResult(imagePreview, recognitions);
        } catch (IOException e) {
            log.info("Error during reading file input stream", e);
            return new RecognitionResult();
        } finally {
            if (image != null) {
                image.flush();
            }
        }
    }

    private BufferedImage getImagePreview(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        float scale = (float) previewSize / width;
        return ImageUtil.scaleImage(image, previewSize, (int) (height * scale));
    }
}
