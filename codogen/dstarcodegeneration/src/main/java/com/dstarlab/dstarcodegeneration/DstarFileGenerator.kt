package com.dstarlab.dstarcodegeneration

import com.dstarlab.dstarannotation.RoomDaoGeneration
import com.dstarlab.dstarannotation.InternalLiveData
import com.dstarlab.dstarannotation.PublicLiveData
import com.dstarlab.dstarcodegeneration.componentinjector.ComponentInjectorGenerator
import com.dstarlab.dstarcodegeneration.dynamicinjector.RoomDaoGenerationGenerator
import com.dstarlab.dstarcodegeneration.livedatagenerator.LiveDataGenerator
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(DstarFileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class DstarFileGenerator : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(
        RoomDaoGeneration::class.java.name,
        PublicLiveData::class.java.name,
        InternalLiveData::class.java.name
    )

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        roundEnvironment?.apply {
            ComponentInjectorGenerator.processComponentInjectorAnnotation(this, processingEnv)
            LiveDataGenerator.processLiveDataAnnotation(this, processingEnv)
            RoomDaoGenerationGenerator.processComponentInjectorAnnotation(this, processingEnv)
        }
        return true
    }
}