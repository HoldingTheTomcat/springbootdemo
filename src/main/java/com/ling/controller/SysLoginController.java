package com.ling.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ling.common.entity.R;
import com.ling.dao.entity.SysUserEntity;
import com.ling.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 * 
 */
@Controller
@Api("登录信息接口")
public class SysLoginController {
	@Autowired
	private Producer producer;
	
	@GetMapping("ling")
	public String toLing(){
		System.out.println(1);
		return "index";
	}

	@GetMapping("/sys/user/info")
	public R userInfo() {
		//取得就是SimpleAuthenticationInfo一个参数
		SysUserEntity user = ShiroUtils.getUserEntity();
		return R.ok().put("user", user);
	}
	
	//获取验证码：不拦截
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        
        //响应给客户端
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
		out.flush();
	}
	
	/**
	 * 登录：不拦截
	 */
	@ResponseBody
	@PostMapping(value = "/sys/login")
	public R login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}

		try{
			Subject subject = ShiroUtils.getSubject();
			
			//controller直接加盐加密，SimpleAuthenticationInfo就不用加了，到时候跟数据库里面加密的密码比对
			// Md5Hash(Object source, Object salt, int hashIterations) 
			Md5Hash md5Hash = new Md5Hash(password, username, 1024);
			UsernamePasswordToken token = new UsernamePasswordToken(username, md5Hash.toString());
			
			//记住我，会向浏览器写入cookie:rememberMe ,默认是一年
			//虽然有了cookie,但是过滤器不支持，访问需要认真、授权的资源，还是会被拦截
			token.setRememberMe(true);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return R.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return R.error(e.getMessage());
		}catch (LockedAccountException e) {
			return R.error(e.getMessage());
		}catch (AuthenticationException e) {
			return R.error("账户验证失败");
		}
	    
		return R.ok();
	}
	
	/**
	 * 退出：
	 * 用户退出后，记住我功能失效：因为会清空服务端的session
	 * 服务器把session清了，你有cookie也没用
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:login.html";
	}
	
}
