package com.mz.aclasser.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecognitionResult {

    private BufferedImage imagePreview;
    private List<Recognition> recognitions;
}
