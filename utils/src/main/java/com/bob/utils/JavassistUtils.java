package com.bob.utils;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangxiang on 17/12/19.
 */
public class JavassistUtils {

    private static Logger logger = LoggerFactory.getLogger(JavassistUtils.class);

    /**
     * 在方法执行前后打印出此方法消耗时间
     *
     * @param clasz
     * @param methodName
     * @param saveOrNot
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    public static void addTiming(CtClass clasz, String methodName, boolean saveOrNot)
            throws NotFoundException, CannotCompileException {

        CtMethod mold = clasz.getDeclaredMethod(methodName);
        addTiming(clasz, mold, saveOrNot);
    }

    /**
     * 在方法执行前后打印出此方法消耗时间
     *
     * @param clasz
     * @param method
     * @param saveOrNot
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    public static void addTiming(CtClass clasz, CtMethod method, boolean saveOrNot)
            throws NotFoundException, CannotCompileException {

        String oldMethodName = method.getName();
        String newMthodName = oldMethodName + "$impl";
        method.setName(newMthodName);
        CtMethod newMethod = CtNewMethod.copy(method, oldMethodName, clasz, null);

        StringBuffer body = new StringBuffer();
        body.append("{\nlong start = System.currentTimeMillis();\n");

        String type = method.getReturnType().getName();
        if (!"void".equals(type)) {
            body.append(type + " result = ");
        }
        body.append(newMthodName + "($$);\n");

        body.append("logger.info(\"Call to method " + oldMethodName +
                " took \" +\n (System.currentTimeMillis()-start) + " +
                "\" ms.\");\n");
        if (!"void".equals(type)) {
            body.append("return result;\n");
        }
        body.append("}");

        newMethod.setBody(body.toString());
        clasz.addMethod(newMethod);

        if (saveOrNot) {
            clasz.toClass();
        }
    }
}