package com.dsofttech.dsoftentitymapperversion1.core.annotations

import com.dsofttech.dsoftentitymapperversion1.utils.Constants.KAPT_KOTLIN_GENERATED_OPTION_NAME
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

class Processor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        mutableSetOf(DSoftEntityMapper::class.java.canonicalName)

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment,
    ): Boolean {
        val kaptKotlinGeneratedDir =
            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: return false
        val map = mutableMapOf<String, MutableList<String>>()
        roundEnv.getElementsAnnotatedWith(DSoftEntityMapper::class.java)
            .forEach {
                val packageName = processingEnv.elementUtils.getPackageOf(it).toString()
                val name = it.simpleName.toString()
                if (map[packageName] == null) {
                    map[packageName] = mutableListOf()
                }
                map[packageName]?.add(name)
            }
        map.keys.forEach {
            FileSpec.builder(it, "DSoftMapperMarkers").apply {
                addType(
                    TypeSpec.enumBuilder("DSoftTechMarker1")
                        .apply {
                            map[it]?.forEach {
                                addEnumConstant(it.uppercase())
                            }
                        }.build(),
                ).build()
                    .writeTo(File(kaptKotlinGeneratedDir))
            }
        }
        return true
    }
}
