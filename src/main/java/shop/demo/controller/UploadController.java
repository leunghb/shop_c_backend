package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.demo.config.UserLoginToken;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 上传单张图片
     *
     * @param type * int  0-用户 1-退货退款图片 2-商品封面 3-商品分类图标
     */
    @UserLoginToken
    @PostMapping("upload/uploadSinglePicture")
    public Result<Object> uploadSinglePicture(@RequestParam("file") MultipartFile file,
                                              @RequestParam("fileType") int type) throws FileNotFoundException {
        if (file.isEmpty()) {
            return Result.error(CodeMsg.FAIL, "文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取项目运行的绝对路径
        String userDir = System.getProperty("user.dir");
        // 文件上传路径
        String filePath = "";
        String resultPath = "";
        String uploadPath = userDir + "/src/main/resources/static/upload/";
        //账号名为文件夹
        String userFilePath = TokenUtil.getJwtToken(httpServletRequest).replace("@qq.com", "") + "/";
        switch (type) {
            case 0:
                filePath = uploadPath + "user/" + userFilePath;
                break;
            case 1:
                filePath = uploadPath + "refunds/" + userFilePath;
                break;
            case 2:
                filePath = uploadPath + "goods/";
                break;
            case 3:
                filePath = uploadPath + "goodsType/";
                break;
        }
        resultPath = filePath.replace(userDir + "/src/main/resources", "") + fileName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return Result.success(resultPath);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error(CodeMsg.FAIL, "上传失败");
    }
}
