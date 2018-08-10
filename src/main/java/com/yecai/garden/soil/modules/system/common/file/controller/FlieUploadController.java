package com.yecai.garden.soil.modules.system.common.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yecai.garden.soil.modules.system.user.entity.User;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value ="file")
public class FlieUploadController {
	/*
     * 管理员新增用户
     */
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String doAddUser(HttpSession session,HttpServletRequest request,
                            @RequestParam(value="attachs",required=false)MultipartFile[] attachs,
                            User user){
        
        //定义两个上传文件的路径
        String wkpicpath = null;
        String hdpicpath = null;
        String errorinfo = null;
        //定义上传过程管理标记
        boolean flag = true;
        //定义文件保存的位置
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
        //循环读取文件信息
        for(int i=0;i<attachs.length;i++){
            MultipartFile attach = attachs[i];
            //判断文件是否为空
            if(!attach.isEmpty()){
                //判断是第几个文件
                if(i==0){
                    errorinfo = "uploadwkError";
                }else if(i==1){
                    errorinfo = "uploadhdError";
                }
            //获取源文件名
            String oldName= attach.getOriginalFilename();
            //获取源文件名后缀
            String prefixName = FilenameUtils.getExtension(oldName);
            
            int fileSize = 500000;
            //判断上传大小不得超过500K
            if(attach.getSize()>fileSize){
                session.setAttribute(errorinfo, "上传文件不得大于500k");
                flag = false;
            }else if(prefixName.equalsIgnoreCase("jpg")
                    || prefixName.equalsIgnoreCase("png")
                    || prefixName.equalsIgnoreCase("jpeg")
                    || prefixName.equalsIgnoreCase("pneg")){
                //判断上传格式
                //定义新的文件名，当前系统时间+随机数+固定后缀，
                //RandomUtils需要引入jar文件commons-lang.jar
//String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"personer.jpg";
                String fileName = System.currentTimeMillis()+"personer.jpg";
                //创建新的文件，用于接收用户上传的文件流
                File targetFile = new File(path, fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                
                //将上传的文件保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute(errorinfo,"上传失败！");
                    flag = false;
                } 
                
                //更新上传的路径
                if(i==0){
                    wkpicpath = path + File.separator + fileName;
                }else if(i==1){
                    hdpicpath = path + File.separator + fileName;
                }                
            }else{
                session.setAttribute(errorinfo,"图片格式不正确！");
                flag = false;
            }                
           }        
        }        
        
//        //准备User
//        if(flag){
//            user.setWkpicpath(wkpicpath);
//            user.setHdpicpath(hdpicpath);
//            user.setUserrole(1);
//                        //插入数据库
//            if(userService.findAddUser(user)>0){                
//                return "redirect:/index/user";
//            }            
//        }        
        return "manager/user-add.jsp";
    }

    @RequestMapping(value ="fileupload.do")
    @ResponseBody
    public  JSONObject upload(MultipartFile file, HttpServletRequest request) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());

        // uploads文件夹位置
        String rootPath = request.getSession().getServletContext().getRealPath("resource/uploads/");
        // 原始名称
        String originalFileName = file.getOriginalFilename();
        // 新文件名
        String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));
        // 创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));

        // 新文件
        File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
        // 判断目标文件所在目录是否存在
        if( !newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        // 完整的url
        String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + newFileName;
        JSONObject js=new JSONObject();
        js.put("fileUrl", fileUrl);
        return  js;
    }
}
