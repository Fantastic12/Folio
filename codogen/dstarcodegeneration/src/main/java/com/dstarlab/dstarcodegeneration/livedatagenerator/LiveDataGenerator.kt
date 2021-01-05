package com.dstarlab.dstarcodegeneration.livedatagenerator

import com.dstarlab.dstarannotation.InternalLiveData
import com.dstarlab.dstarannotation.PublicLiveData
import com.dstarlab.dstarcodegeneration.DstarFileGenerator.Companion.KAPT_KOTLIN_GENERATED_OPTION_NAME
import com.dstarlab.dstarcodegeneration.Generator
import com.dstarlab.dstarcodegeneration.TypeMapping
import com.dstarlab.dstarcodegeneration.replaceTypes
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.tools.Diagnostic

class LiveDataGenerator(private val processingEnv: ProcessingEnvironment) : Generator() {
    companion object {
        private const val END_FILE_NAME = "LvContainer"

        fun processLiveDataAnnotation(roundEnvironment: RoundEnvironment,
                                      processingEnv: ProcessingEnvironment) {
            val generator = LiveDataGenerator(processingEnv)

            (roundEnvironment.getElementsAnnotatedWith(PublicLiveData::class.java) +
                    roundEnvironment.getElementsAnnotatedWith(InternalLiveData::class.java))
                .forEach { generator.prepareClassInitialization(it) }

            generator.generateFile()
        }
    }

    private var liveDataMap = mutableMapOf<String, MutableSet<LiveDataGeneratorModel>>()

    private var isFirstElement = true

    override fun prepareClassInitialization(element: Element) {
        if(element.simpleName.startsWith('_').not()) {
            showWarning(element, "field name must starts with \"_\" symbol")
        }

        val fieldName = element.simpleName.toString()
        val pack = processingEnv.elementUtils.getPackageOf(element).toString()
        val enclosingElement = element.enclosingElement.simpleName.toString()
        val internalAnnotation = element.getAnnotation(InternalLiveData::class.java) != null
        val modifiers = if (internalAnnotation) "internal " else ""
        val elementType = getElementType(element)

        with(enclosingElement) {
            val model = LiveDataGeneratorModel(modifiers, fieldName, pack, this@with, elementType)

            if (liveDataMap.containsKey(this)) {
                liveDataMap[this]?.add(model)
                return@with
            }
            liveDataMap[this] = mutableSetOf<LiveDataGeneratorModel>()
                .apply { add(model) }
        }
    }

    private fun getElementType(element: Element): String {
        getTypeParameter(element)
            ?.let { return "LiveData<$it>" }

        val originalType = element.asType().toString()
        return replaceTypes(originalType) { index, type ->
            when (index) {
                0 -> "LiveData"
                else -> TypeMapping.getOrDefault(type, type)
            }
        }
    }

    private fun getTypeParameter(element: Element): String? {
        element.getAnnotation(PublicLiveData::class.java)?.of
            ?.let { return if(it.isEmpty()) null else it }

        element.getAnnotation(InternalLiveData::class.java)?.of
            ?.let { return if(it.isEmpty()) null else it }

        return null
    }

    /**
     * It's recommended to show warning instead of error because
     * showing errors in any annotation processor (this one, room, dagger, etc.)
     * triggers error in Glide annotation processor and confuses programmer
     */
    private fun showWarning(element: Element, text: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING,
            "in module dstarcodegeneration: in class LiveDataGenerator: $text", element)
    }

    fun generateFile() {
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]

        liveDataMap.forEach {
            val file = File(kaptKotlinGeneratedDir, "${it.key}$END_FILE_NAME.kt")

            it.value.forEach { model ->
                if (isFirstElement) processFirstElementForWrite(file, model)
                else processSubsequentElementForWrite(file, model)
            }

            isFirstElement = true
        }
    }

    private fun processFirstElementForWrite(file: File, liveDataModel: LiveDataGeneratorModel) {
        val defaultFileContent = LiveDataObjectBuilder(liveDataModel).getDefaultContent()
//        file.appendText(defaultFileContent)
        isFirstElement = false
    }

    private fun processSubsequentElementForWrite(file: File, liveDataModel: LiveDataGeneratorModel) {
        val subsequentFileContent = LiveDataObjectBuilder(liveDataModel).getAdditionalContent()
//        file.appendText(subsequentFileContent)
    }
}