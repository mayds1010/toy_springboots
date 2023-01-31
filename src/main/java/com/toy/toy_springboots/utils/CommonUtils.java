package com.toy.toy_springboots.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component

public class CommonUtils {
    public String getUniqueSequence() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // "src/main/resources/static/files" -> root directory + 상대 경로
    public String getRelativeToAbsolutePath(String relativePath) {
        String relativePathWithSeparator = relativePath.replace("/", File.separator); // 슬래시로 바꿈
        String absolutePath = new File(relativePathWithSeparator).getAbsolutePath() + File.separator;// 폴더파일이 file에 들어감
        return absolutePath;
    }
}
