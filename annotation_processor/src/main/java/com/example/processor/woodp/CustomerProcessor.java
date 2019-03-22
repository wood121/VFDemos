package com.example.processor.woodp;


import com.example.annotations.WoodAnnotation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 *
 */
//@WoodAnnotation("haha")
public class CustomerProcessor extends AbstractProcessor {

    private Elements mElementUtils;
    private Types mTypeUtils;
    private Filer mFiler;
    private Messager mMessager;

    /**
     * 一般获取我们需要的工具类
     *
     * @param processingEnvironment 提供工具类Elements, Types和Filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        //代表程序的元素：包、类、方法
        mElementUtils = processingEnvironment.getElementUtils();
        //处理TypeMirror的工具类，用于取类信息
        mTypeUtils = processingEnvironment.getTypeUtils();
        //filer用来创建文件
        mFiler = processingEnvironment.getFiler();
        //错误处理工具
        mMessager = processingEnvironment.getMessager();
    }

    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     *
     * @param set              请求处理的注解类型
     * @param roundEnvironment 所有注解的合集
     * @return 如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     * 如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(WoodAnnotation.class)) {
            System.out.println("------------------------------");
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
                System.out.println(typeElement.getSimpleName());
                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(WoodAnnotation.class).value());
            }
            System.out.println("------------------------------");
        }
        return false;
    }

    /**
     * 指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
     *
     * @return set，说明一个处理器可以处理多个注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new LinkedHashSet<>();
        sets.add(CustomerProcessor.class.getCanonicalName());
        return sets;
    }

    /**
     * 指定Java版本，一般返回最新版本即可
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
