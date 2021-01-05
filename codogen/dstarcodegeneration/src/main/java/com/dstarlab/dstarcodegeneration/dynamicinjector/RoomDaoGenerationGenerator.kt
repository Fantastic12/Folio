package com.dstarlab.dstarcodegeneration.dynamicinjector

import com.dstarlab.dstarcodegeneration.DstarFileGenerator
import com.dstarlab.dstarcodegeneration.Generator
import com.dstarlab.dstarannotation.RoomDaoGeneration
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element

class RoomDaoGenerationGenerator(private val processingEnv: ProcessingEnvironment) : Generator() {
    companion object {
        private const val SUFFIX_CLASS_NAME = "Dao"
        private const val PREFIX_FOR_GENERATED_CLASS = "Anzid"

        fun processComponentInjectorAnnotation(roundEnvironment: RoundEnvironment,
                                               processingEnv: ProcessingEnvironment) {
            roundEnvironment.getElementsAnnotatedWith(RoomDaoGeneration::class.java)
                    ?.forEach { RoomDaoGenerationGenerator(processingEnv).prepareClassInitialization(it) }
        }
    }

    override fun prepareClassInitialization(element: Element) {
        val className = element.simpleName.toString()
        val pack = processingEnv.elementUtils.getPackageOf(element).toString()
        val ann = element.getAnnotation(RoomDaoGeneration::class.java)

        generateClass(className, pack, ann.tableName)
    }

    private fun generateClass(className: String, pack: String, nameTable: String) {
        val suffixForFun = className.removeSuffix(SUFFIX_CLASS_NAME)
        val fileName = PREFIX_FOR_GENERATED_CLASS + className
        val fileContent = RoomDaoGenerationObjectBuilder(fileName, pack, suffixForFun, nameTable).getContent()

        val kaptKotlinGeneratedDir = processingEnv.options[DstarFileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val file = File(kaptKotlinGeneratedDir, "$fileName.kt")

        file.writeText(fileContent)
    }
}