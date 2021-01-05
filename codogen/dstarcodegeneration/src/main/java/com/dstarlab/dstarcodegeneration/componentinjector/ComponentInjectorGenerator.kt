package com.dstarlab.dstarcodegeneration.componentinjector

import com.dstarlab.dstarannotation.ComponentInjector
import com.dstarlab.dstarcodegeneration.Generator
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element

class ComponentInjectorGenerator(private val processingEnv: ProcessingEnvironment) : Generator() {
    companion object {
        fun processComponentInjectorAnnotation(roundEnvironment: RoundEnvironment,
                                               processingEnv: ProcessingEnvironment) {
            roundEnvironment.getElementsAnnotatedWith(ComponentInjector::class.java)
                    ?.forEach { ComponentInjectorGenerator(processingEnv).prepareClassInitialization(it) }
        }
    }

    override fun prepareClassInitialization(element: Element) {
        TODO("Not yet implemented")
    }
}