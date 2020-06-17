package whu.zhj.imageshare.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 13:04
 */
@Component
public class ImageUtil {
    public static String dirPath;
    public static String url;

    @Value("${filePath}")
    public void setDirPath(String path){
        dirPath = path + "images/";
    }
    @Value("${resourceUrl}")
    public void setUrl(String URL){
        url = URL + "images/";
    }

    public static String saveImage(MultipartFile image, String username){
        String filename = image.getOriginalFilename();
        String suffix = filename.substring(filename.indexOf("."), filename.length());
        //创建用户对应的文件夹
        String dirLocation = dirPath + username + "/";
        File dir = new File(dirLocation);
        if(!dir.exists()){
            dir.mkdir();
        }
        //保存图片
        try{
            File file = new File(dirLocation + filename);
            image.transferTo(file);
            return url + username + "/" + filename;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static void deleteImage(String username, String filename){
        String image = dirPath + username + "/" + filename;
        File file = new File(image);
        if(file.exists()){
            file.delete();
        }
    }

    public static List<String> getAllImageUrl(String username){
        String dirLocation = dirPath + username + "/";
        File dir = new File(dirLocation);
        File[] files = null;
        if(dir.exists()){
            files = dir.listFiles();
            List<String> strings = new ArrayList<>();
            for(int i=0; i<files.length; i++){
                String imageUrl = url + username + "/" + files[i].getName();
                strings.add(imageUrl);
            }
            return strings;
        }else{
            dir.mkdir();
        }
        return null;
    }
}

