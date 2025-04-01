//package com.emoticon.web.controller.app;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.emoticon.common.core.domain.AjaxResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * 图片处理控制器
// * 提供图片添加文字的相关功能
// */
//@RestController
//@RequestMapping("/app/img")
//public class ImgController {
//    private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
//
//    @Value("${emoticon.profile}")
//    private String baseUploadPath;
//
//    /**
//     * 获取上传文件目录路径
//     */
//    private String getUploadDir() {
//        return Paths.get(baseUploadPath, "uploads").toString();
//    }
//
//    /**
//     * 获取处理后文件输出目录路径
//     */
//    private String getOutputDir() {
//        return Paths.get(baseUploadPath, "outputs").toString();
//    }
//
//    /**
//     * 为图片添加单个文字
//     *
//     * @param file 要处理的图片文件
//     * @param text 要添加的文字内容
//     * @return 处理结果
//     *         成功：返回输出文件路径
//     *         失败：返回错误信息
//     *
//     * @apiNote
//     * 文字将使用默认样式：
//     * - 字体：黑体（simhei.ttf）
//     * - 颜色：白色
//     * - 大小：16px
//     * - 位置：x=10, y=10
//     */
//    @PostMapping("/addText")
//    public AjaxResult addTextToImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam String text) {
//
//        if (file.isEmpty()) {
//            return AjaxResult.error("请选择要上传的图片文件");
//        }
//
//        try {
//            // 创建上传和输出目录
//            createDirectories();
//
//            // 处理文件上传
//            FileProcessResult fileResult = handleFileUpload(file);
//            if (!fileResult.isSuccess()) {
//                return AjaxResult.error(fileResult.getMessage());
//            }
//
//            // 处理图片文件
//            boolean success = processImageWithText(
//                fileResult.getUploadPath().toString(),
//                fileResult.getOutputPath().toString(),
//                text
//            );
//
//            return handleProcessResult(success, fileResult);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AjaxResult.error("处理过程发生错误: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 为图片添加多个文字，支持自定义样式
//     *
//     * @param file 要处理的图片文件
//     * @param textConfigs JSON格式的文字配置列表，每个配置对象包含：
//     *                    必需参数：
//     *                    - text: 文字内容
//     *                    - x: x坐标（像素）
//     *                    - y: y坐标（像素）
//     *                    可选参数：
//     *                    - fontsize: 字体大小（像素，默认16）
//     *                    - fontcolor: 字体颜色（如"#ffffff"，默认白色）
//     *                    - font_opacity: 字体透明度（0-1之间）
//     *                    - boxcolor: 背景框颜色（如"#000000"）
//     *                    - box_opacity: 背景框透明度（0-1之间）
//     *                    - boxborderw: 背景框边框宽度（像素）
//     *                    - bordercolor: 文字边框颜色（如"#000000"）
//     *                    - border_opacity: 文字边框透明度（0-1之间）
//     *                    - borderw: 文字边框宽度（像素，默认1）
//     * @return 处理结果
//     *         成功：返回输出文件路径
//     *         失败：返回错误信息
//     *
//     * @apiNote
//     * 示例请求参数：
//     * {
//     *   "file": [二进制文件],
//     *   "textConfigs": "[
//     *     {
//     *       \"text\": \"第一行文字\",
//     *       \"x\": 10,
//     *       \"y\": 10,
//     *       \"fontsize\": 16,
//     *       \"fontcolor\": \"#ffffff\",
//     *       \"font_opacity\": 0.8
//     *     },
//     *     {
//     *       \"text\": \"第二行文字\",
//     *       \"x\": 10,
//     *       \"y\": 70,
//     *       \"fontcolor\": \"#ff0000\",
//     *       \"bordercolor\": \"#000000\"
//     *     }
//     *   ]"
//     * }
//     */
//    @PostMapping("/addTexts")
//    public AjaxResult addTextsToImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam String textConfigs,
//            @RequestParam(required = false) Integer width,
//            @RequestParam(required = false) Integer height) {
//
//        if (file.isEmpty()) {
//            return AjaxResult.error("请选择要上传的图片文件");
//        }
//
//        try {
//            // 解析文字配置JSON
//            List<Map<String, Object>> textConfigsList = parseTextConfigs(textConfigs);
//
//            // 处理文件上传
//            FileProcessResult fileResult = handleFileUpload(file);
//            if (!fileResult.isSuccess()) {
//                return AjaxResult.error(fileResult.getMessage());
//            }
//
//            // 处理图片文件
//            boolean success = processImageWithMultipleTexts(
//                fileResult.getUploadPath().toString(),
//                fileResult.getOutputPath().toString(),
//                textConfigsList,
//                width,
//                height
//            );
//
//            return handleProcessResult(success, fileResult);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AjaxResult.error("处理过程发生错误: " + e.getMessage());
//        }
//    }
//
//    private void createDirectories() throws IOException {
//        // Create parent directory first if it doesn't exist
//        File baseDir = new File(baseUploadPath);
//        if (!baseDir.exists()) {
//            if (!baseDir.mkdirs()) {
//                throw new IOException("无法创建基础目录: " + baseUploadPath);
//            }
//        }
//
//        // Create upload directory
//        Path uploadDir = Paths.get(getUploadDir());
//        if (!Files.exists(uploadDir)) {
//            Files.createDirectories(uploadDir);
//        }
//
//        // Create output directory
//        Path outputDir = Paths.get(getOutputDir());
//        if (!Files.exists(outputDir)) {
//            Files.createDirectories(outputDir);
//        }
//    }
//
//    private boolean processImageWithText(String inputFile, String outputFile, String text) throws IOException {
//        // FFmpeg 命令行，使用 drawtext 滤镜添加文本
//        String[] command = {
//                "ffmpeg",
//                "-i", inputFile,
//                "-vf", "drawtext=text='" + text + "':fontfile=/usr/share/fonts/simhei/simhei.ttf:fontcolor=white:fontsize=16:x=10:y=10",
//                "-c:a", "copy",
//                // "flags", "lanczos",
//                "unsharp", "5:5:0.8:3:3:0.4",
//                outputFile
//        };
//
//        // 创建 ProcessBuilder 并执行命令
//        ProcessBuilder processBuilder = new ProcessBuilder(command);
//        Process process = processBuilder.start();
//
//        // 输出命令执行的日志
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);  // 输出 FFmpeg 执行的 stderr 日志
//        }
//
//        try {
//            int exitCode = process.waitFor();
//            return exitCode == 0;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 使用FFmpeg处理图片并添加多个文字
//     * @param inputFile 输入文件路径
//     * @param outputPath 输出文件路径
//     * @param textConfigs 文字配置列表，每个配置包含以下参数：
//     *                    必需参数：
//     *                    - text: 文字内容
//     *                    - x: x坐标
//     *                    - y: y坐标
//     *                    可选参数：
//     *                    - fontsize: 字体大小
//     *                    - fontcolor: 字体颜色（需带#号）
//     *                    - font_opacity: 字体透明度（0-1）
//     *                    - boxcolor: 背景框颜色（需带#号）
//     *                    - box_opacity: 背景框透明度（0-1）
//     *                    - boxborderw: 背景框边框宽度
//     *                    - bordercolor: 文字边框颜色（需带#号）
//     *                    - border_opacity: 文字边框透明度（0-1）
//     *                    - borderw: 文字边框宽度
//     * @return 处理是否成功
//     */
//    private boolean processImageWithMultipleTexts(
//            String inputFile,
//            String outputPath,
//            List<Map<String, Object>> textConfigs,
//            Integer width,
//            Integer height) throws IOException {
//
//        // 构建滤镜字符串
//        StringBuilder filterBuilder = new StringBuilder();
//
//        // 如果指定了宽度或高度，添加scale滤镜
//        if (width != null || height != null) {
//            String scaleFilter = buildScaleFilter(width, height);
//            if (!scaleFilter.isEmpty()) {
//                filterBuilder.append(scaleFilter);
//                // 如果后面还有drawtext滤镜，添加逗号
//                if (!textConfigs.isEmpty()) {
//                    filterBuilder.append(",");
//                }
//            }
//        }
//
//        // 添加文字滤镜
//        for (int i = 0; i < textConfigs.size(); i++) {
//            Map<String, Object> config = textConfigs.get(i);
//            if (i > 0) {
//                filterBuilder.append(",");
//            }
//
//            // 添加基本文字设置
//            filterBuilder.append("drawtext=");
//            filterBuilder.append(String.format("text='%s':", escapeText(config.get("text").toString())));
//            filterBuilder.append("fontfile=/usr/share/fonts/simhei/simhei.ttf:");
//            filterBuilder.append(String.format("x=%s:y=%s:", config.get("x"), config.get("y")));
//
//            // 设置字体大小（默认16）
//            if (config.containsKey("fontsize")) {
//                filterBuilder.append(String.format("fontsize=%s:", config.get("fontsize")));
//            } else {
//                filterBuilder.append("fontsize=16:");
//            }
//
//            // 设置字体颜色（默认白色）
//            if (config.containsKey("fontcolor")) {
//                filterBuilder.append(String.format("fontcolor=%s:", config.get("fontcolor")));
//            } else {
//                filterBuilder.append("fontcolor=white:");
//            }
//
//            // 设置字体透明度
//            if (config.containsKey("font_opacity")) {
//                filterBuilder.append(String.format("alpha=%s:", config.get("font_opacity")));
//            }
//
//            // 设置背景框
//            if (config.containsKey("boxcolor")) {
//                String boxColor = config.get("boxcolor").toString();
//                if (config.containsKey("box_opacity")) {
//                    // 将透明度添加到颜色值中
//                    boxColor += "@" + config.get("box_opacity");
//                }
//                filterBuilder.append(String.format("box=1:boxcolor=%s:", boxColor));
//                if (config.containsKey("boxborderw")) {
//                    filterBuilder.append(String.format("boxborderw=%s:", config.get("boxborderw")));
//                }
//            }
//
//            // 设置文字边框
//            if (config.containsKey("bordercolor")) {
//                filterBuilder.append(String.format("bordercolor=%s:borderw=%s:",
//                    config.get("bordercolor"),
//                    config.getOrDefault("borderw", 1)));
//                if (config.containsKey("border_opacity")) {
//                    filterBuilder.append(String.format("border_opacity=%s:", config.get("border_opacity")));
//                }
//            }
//
//            // 移除最后一个冒号
//            if (filterBuilder.charAt(filterBuilder.length() - 1) == ':') {
//                filterBuilder.setLength(filterBuilder.length() - 1);
//            }
//        }
//
//        // 构建FFmpeg命令，添加质量相关参数
//        String[] command = {
//            "ffmpeg",
//            "-i", inputFile,
//            "-vf", filterBuilder.toString(),
//            "-q:v", "1",           // 最高质量设置 (1-31, 1为最好)
//            "-quality", "100",     // 100%质量
//            "-compression_level", "0",  // 最小压缩
//            "-c:a", "copy",
//            outputPath
//        };
//
//        ProcessBuilder processBuilder = new ProcessBuilder(command);
//        Process process = processBuilder.start();
//
//        // 输出命令执行的日志
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        try {
//            int exitCode = process.waitFor();
//            return exitCode == 0;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 构建scale滤镜字符串
//     * @param width 目标宽度
//     * @param height 目标高度
//     * @return scale滤镜字符串
//     */
//    private String buildScaleFilter(Integer width, Integer height) {
//        if (width == null && height == null) {
//            return "";
//        }
//
//        StringBuilder scaleBuilder = new StringBuilder("scale=");
//
//        if (width != null && height != null) {
//            // 同时指定宽度和高度
//            scaleBuilder.append(width).append(":").append(height);
//        } else if (width != null) {
//            // 只指定宽度，高度按比例缩放
//            scaleBuilder.append(width).append(":-1");
//        } else {
//            // 只指定高度，宽度按比例缩放
//            scaleBuilder.append("-1:").append(height);
//        }
//
//        return scaleBuilder.toString();
//    }
//
//    /**
//     * 转义文本中的特殊字符
//     * 主要用于处理文本中的单引号，防止FFmpeg命令出错
//     */
//    private String escapeText(String text) {
//        return text.replace("'", "'\\''");
//    }
//
//    /**
//     * 处理文件上传逻辑
//     */
//    private FileProcessResult handleFileUpload(MultipartFile file) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//        if (originalFilename == null || originalFilename.isEmpty()) {
//            throw new IOException("文件名不能为空");
//        }
//
//        // 安全地获取文件扩展名
//        String extension = "";
//        int lastDotIndex = originalFilename.lastIndexOf(".");
//        if (lastDotIndex > 0) {
//            extension = originalFilename.substring(lastDotIndex);
//        }
//
//        String fileName = UUID.randomUUID().toString() + extension;
//
//        Path uploadPath = Paths.get(getUploadDir(), fileName);
//        Path outputPath = Paths.get(getOutputDir(), fileName);
//
//        logger.info("Uploading file to: {}", uploadPath);
//
//        // Create parent directories if they don't exist
//        Files.createDirectories(uploadPath.getParent());
//
//        File uploadFile = uploadPath.toFile();
//        file.transferTo(uploadFile);
//
//        logger.info("File uploaded successfully to: {}", uploadPath);
//
//        return new FileProcessResult(true, "上传成功", uploadPath, outputPath);
//    }
//
//    /**
//     * 处理FFmpeg执行结果
//     */
//    private AjaxResult handleProcessResult(boolean success, FileProcessResult fileResult) throws IOException {
//        if (success) {
//            // Get just the filename from the full path
//            String fileName = fileResult.getOutputPath().getFileName().toString();
//            // Construct the URL
//            String url = "/profile/outputs/" + fileName;
//
//            return AjaxResult.success("图片处理成功")
//                .put("url", url);
//        } else {
//            // 处理失败时删除所有相关文件
//            Files.deleteIfExists(fileResult.getUploadPath());
//            Files.deleteIfExists(fileResult.getOutputPath());
//            return AjaxResult.error("图片处理失败");
//        }
//    }
//
//    /**
//     * 解析文字配置JSON
//     */
//    private List<Map<String, Object>> parseTextConfigs(String textConfigs) throws IOException {
//        // URL decode the textConfigs first
//        String decodedTextConfigs = java.net.URLDecoder.decode(textConfigs, "UTF-8");
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(
//            decodedTextConfigs,
//            new TypeReference<List<Map<String, Object>>>(){}
//        );
//    }
//}
//
///**
// * 文件处理结果类
// * 用于封装文件上传和处理的结果信息，包括处理状态、消息提示以及相关文件路径
// */
//class FileProcessResult {
//    /** 处理是否成功 */
//    private final boolean success;
//
//    /** 处理结果消息，可用于返回成功提示或错误说明 */
//    private final String message;
//
//    /** 上传文件的存储路径 */
//    private final Path uploadPath;
//
//    /** 处理后文件的输出路径 */
//    private final Path outputPath;
//
//    /**
//     * 构造文件处理结果对象
//     *
//     * @param success    处理是否成功
//     * @param message    处理结果消息
//     * @param uploadPath 上传文件路径
//     * @param outputPath 输出文件路径
//     */
//    public FileProcessResult(boolean success, String message, Path uploadPath, Path outputPath) {
//        this.success = success;
//        this.message = message;
//        this.uploadPath = uploadPath;
//        this.outputPath = outputPath;
//    }
//
//    /**
//     * 获取处理是否成功
//     * @return true 表示处理成功，false 表示处理失败
//     */
//    public boolean isSuccess() {
//        return success;
//    }
//
//    /**
//     * 获取处理结果消息
//     * @return 处理结果的描述信息
//     */
//    public String getMessage() {
//        return message;
//    }
//
//    /**
//     * 获取上传文件路径
//     * @return 原始文件的上传存储路径
//     */
//    public Path getUploadPath() {
//        return uploadPath;
//    }
//
//    /**
//     * 获取输出文件路径
//     * @return 处理后文件的存储路径
//     */
//    public Path getOutputPath() {
//        return outputPath;
//    }
//}
//
