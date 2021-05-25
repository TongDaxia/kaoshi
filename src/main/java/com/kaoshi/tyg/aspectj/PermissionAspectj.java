package com.kaoshi.tyg.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.kaoshi.tyg.aspectj.annotation.FromPermission;
import com.kaoshi.tyg.aspectj.annotation.Permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
public class PermissionAspectj {
    private static final Logger logger = LoggerFactory.getLogger(PermissionAspectj.class);

    public PermissionAspectj() {
        logger.info("PermissionAspectj started ...");
    }

    @Pointcut("@annotation(com.kaoshi.tyg.aspectj.annotation.Permission)")
    private void permissionAnnotation() {

        ConcurrentHashMap map = new ConcurrentHashMap(1);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }


    @Around(value = "permissionAnnotation()")
    public Object executePermissionCheck(ProceedingJoinPoint point) throws Throwable {
        //获取http请求中的参数
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference("request");
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();

        Method realMethod = point.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());

        //验证当前是 Permission 和 Controller
        if (realMethod.isAnnotationPresent(Permission.class)
                && (realMethod.getDeclaringClass().isAnnotationPresent(Controller.class)
                || realMethod.getDeclaringClass().isAnnotationPresent(RestController.class))) {

            logger.info("[x] 请求接口进行校验中: {}", realMethod.getDeclaringClass().getName() + "." + realMethod.getName());
            PermissionData now = this.getPermissions(request);
            Permission permissionData = realMethod.getAnnotation(Permission.class);

            Boolean result = PermissionChecker.checkPermission(now, permissionData);
            if (result) {
                return point.proceed();
            } else {
                this.buildErrorResponse(response, "-1", "请求权限不足");
                return null;
            }
        } else {
            return point.proceed();
        }

    }


    private void buildErrorResponse(HttpServletResponse response, String code, String message) throws IOException {
        response.setHeader("Content-type", "application/json; charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("desc", message);
        String res = object.toString();
        logger.info("res： " + res);
//        outputStream.write((new ObjectMapper()).writeValueAsString(res).getBytes(StandardCharsets.UTF_8));
        outputStream.write(res.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }


    private PermissionData getPermissions(HttpServletRequest request) {
        FromPermission requestFrom = FromPermission.trans(request.getHeader("_from"));
        boolean session = !StringUtils.isEmpty(request.getHeader("_session"));
        boolean token = !StringUtils.isEmpty(request.getHeader("_token"));

        return new PermissionData(requestFrom, session, token);
    }
}
