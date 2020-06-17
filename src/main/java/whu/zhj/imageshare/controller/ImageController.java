package whu.zhj.imageshare.controller;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import whu.zhj.imageshare.model.ResponseResult;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 11:27
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @PostMapping("/deleteImage")
    public ResponseResult deleteImage(@RequestParam("filename")String filename, HttpServletRequest request){
        String username = (String)request.getAttribute("username");
        Role role = Role.getEnum((int)request.getAttribute("role"));
        if(role != Role.ADMIN){
            //普通角色不能删除图片
            return ResponseResult.fail();
        }else{
            //管理员才能够删除图片
            ImageUtil.deleteImage(username,filename);
            return ResponseResult.success(username, filename);
        }

    }

    @PostMapping("/uploadImage")
    public ResponseResult uploadImage(@RequestParam("source")MultipartFile image, HttpServletRequest request){
        String username = (String)request.getAttribute("username");
        String url = ImageUtil.saveImage(image,username);
        if(url == "" || url == null){
            return ResponseResult.fail("上传失败");
        }else{
            return ResponseResult.success("上传成功", url);
        }
    }

    @GetMapping("/getAllImageUrl")
    public ResponseResult uploadImage(HttpServletRequest request){
        String username = (String)request.getAttribute("username");
        List<String> urls = ImageUtil.getAllImageUrl(username);
        if(urls == null){
            return ResponseResult.fail();
        }else{
            System.out.println(urls.toString());
            return ResponseResult.success("成功", urls);
        }
    }
}
